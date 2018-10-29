package com.xnhb.et.ui.ac.detail.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Response;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.adapter.recyclerview.RecyclerViewCreator;
import com.oneway.ui.adapter.recyclerview.XRecyclerViewAdapter;
import com.oneway.ui.base.fragment.BaseLazyFragment;
import com.oneway.ui.common.UniversalItemDecoration;
import com.oneway.ui.widget.list.ListLayout;
import com.xnhb.et.R;
import com.xnhb.et.bean.RankingInfo;
import com.xnhb.et.bean.TradeInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.ui.ac.detail.CoinDetailsActivity;
import com.xnhb.et.util.MoneyUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author oneway
 * @describe 挂单薄
 * @since 2018/9/11 0011
 */


public class CoinBuyAndSellRecordFragment extends BaseLazyFragment implements RecyclerViewCreator<TradeInfo.TradeListBean> {
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    private List<TradeInfo.TradeListBean> buyList;
    private List<TradeInfo.TradeListBean> sellList;
    private XRecyclerViewAdapter<TradeInfo.TradeListBean> mAdapter;

    public static CoinBuyAndSellRecordFragment newInstance() {
        CoinBuyAndSellRecordFragment frament = new CoinBuyAndSellRecordFragment();
        Bundle bundle = new Bundle();
        frament.setArguments(bundle);
        return frament;
    }

    public void update(List<TradeInfo.TradeListBean> buyList, List<TradeInfo.TradeListBean> sellList) {
        if (buyList == null) {
            buyList = new ArrayList<>();
        }
        if (sellList == null) {
            sellList = new ArrayList<>();
        }
        buyList.add(0, new TradeInfo.TradeListBean());
        sellList.add(0, new TradeInfo.TradeListBean());
        this.buyList = buyList;
        this.sellList = sellList;
        mAdapter.setNewData(buyList.size() >= sellList.size() ? buyList : sellList);
    }


    @Override
    protected boolean isLazyLoad() {
        return false;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.base_list_layout;
    }

    @Override
    protected void initView() {
//        pageType = getArguments().getInt(ARG_PAGE_TYPE);
        mAdapter = new XRecyclerViewAdapter<>(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new LinearItemDecoration());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public int bindListViewLayout() {
        return R.layout.item_coin_buy_and_sell_record;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, TradeInfo.TradeListBean data) {
        if (position == 0) {
            holder.setText(R.id.tv_buy_count, "买入数量")
                    .setText(R.id.tv_buy_price, "买入价格")
                    .setText(R.id.tv_sell_count, "卖出数量")
                    .setText(R.id.tv_sell_price, "卖出价格");
            holder.setTextColor(R.id.tv_buy_count, UiUtils.getColor(R.color.text8c))
                    .setTextColor(R.id.tv_buy_price, UiUtils.getColor(R.color.text8c))
                    .setTextColor(R.id.tv_sell_count, UiUtils.getColor(R.color.text8c))
                    .setTextColor(R.id.tv_sell_price, UiUtils.getColor(R.color.text8c));

        } else {
            holder.setTextColor(R.id.tv_buy_count, UiUtils.getColor(R.color.white))
                    .setTextColor(R.id.tv_buy_price, UiUtils.getColor(R.color.green_dark))
                    .setTextColor(R.id.tv_sell_count, UiUtils.getColor(R.color.white))
                    .setTextColor(R.id.tv_sell_price, UiUtils.getColor(R.color.red_btn));
            if (buyList.size() - 1 >= position) {
                TradeInfo.TradeListBean tradeListBean = buyList.get(position);
                holder.setText(R.id.tv_buy_count, MoneyUtils.scaleMoney4(tradeListBean.getNums()))
                        .setText(R.id.tv_buy_price, MoneyUtils.scaleMoney4(tradeListBean.getPrice()));
            } else {
                holder.setText(R.id.tv_buy_count, "")
                        .setText(R.id.tv_buy_price, "");
            }
            if (sellList.size() - 1 >= position) {
                TradeInfo.TradeListBean info = sellList.get(position);
                holder.setText(R.id.tv_sell_count, MoneyUtils.scaleMoney4(info.getNums()))
                        .setText(R.id.tv_sell_price, MoneyUtils.scaleMoney4(info.getPrice()));
            } else {
                holder.setText(R.id.tv_sell_count, "")
                        .setText(R.id.tv_sell_price, "");
            }
        }

    }


    public class LinearItemDecoration extends UniversalItemDecoration {

        @Override
        public Decoration getItemOffsets(int position, int childCount) {
            //这里应该是你的判断逻辑  判断当前position 需要上下左右的分割线到底是多少 以及颜色
            ColorDecoration decoration = new ColorDecoration();
            //这是一个示例代码  这里要根据你自己的逻辑判断当前item需要上下左右的分割线到底是多少 以及颜色
            decoration.decorationColor = UiUtils.getColor(R.color.colorPrimary);
            if (position == 0) {
                decoration.top = UiUtils.dp2px(14);
            } else {
                decoration.top = UiUtils.dp2px(10);
            }
            if (position == childCount - 1) {
                decoration.bottom = UiUtils.dp2px(14);
            }
            return decoration;
        }

    }

}
