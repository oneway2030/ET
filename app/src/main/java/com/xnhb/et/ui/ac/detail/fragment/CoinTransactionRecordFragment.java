package com.xnhb.et.ui.ac.detail.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Response;
import com.oneway.tool.utils.common.TimeUtils;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;

/**
 * @author oneway
 * @describe 最近成交
 * @since 2018/9/11 0011
 */


public class CoinTransactionRecordFragment extends BaseLazyFragment implements RecyclerViewCreator<TradeInfo.RecordListBean>, ListLayout.TaskListener {
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    private XRecyclerViewAdapter mAdapter;
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());


    public static CoinTransactionRecordFragment newInstance() {
        CoinTransactionRecordFragment frament = new CoinTransactionRecordFragment();
        Bundle bundle = new Bundle();
        frament.setArguments(bundle);
        return frament;
    }

    public void update(List<TradeInfo.RecordListBean> recordList) {
        if (recordList == null) {
            recordList = new ArrayList<>();
        }
        recordList.add(0, new TradeInfo.RecordListBean());
        mAdapter.setNewData(recordList);
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
        mAdapter = new XRecyclerViewAdapter<TradeInfo.RecordListBean>(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new LinearItemDecoration());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void task() {
    }


    @Override
    public int bindListViewLayout() {
        return R.layout.item_coin_transaction_record;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, TradeInfo.RecordListBean data) {
        if (position == 0) {
            holder.setText(R.id.tv_time, "时间")
                    .setText(R.id.tv_price, "价格")
                    .setText(R.id.tv_count, "数量");
            holder.setTextColor(R.id.tv_time, UiUtils.getColor(R.color.text8c))
                    .setTextColor(R.id.tv_price, UiUtils.getColor(R.color.text8c))
                    .setTextColor(R.id.tv_count, UiUtils.getColor(R.color.text8c));
        } else {
            String time = TimeUtils.millis2String(data.getCreateTime(), mSimpleDateFormat);
            holder.setText(R.id.tv_time, time)
                    .setText(R.id.tv_price, MoneyUtils.scaleMoney4(data.getTradePrice()))
                    .setText(R.id.tv_count, MoneyUtils.scaleMoney4(data.getTradeQuantity()));
            holder.setTextColor(R.id.tv_time, UiUtils.getColor(R.color.text8c))
                    .setTextColor(R.id.tv_price, UiUtils.getColor(R.color.red_btn))
                    .setTextColor(R.id.tv_count, UiUtils.getColor(R.color.red_btn));
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
