package com.xnhb.et.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.adapter.recyclerview.RecyclerViewCreator;
import com.oneway.ui.adapter.recyclerview.XRecyclerViewAdapter;
import com.oneway.ui.base.fragment.BaseFragment;
import com.oneway.ui.common.LinearItemDecoration;
import com.oneway.ui.widget.list.ListLayout;
import com.xnhb.et.R;
import com.xnhb.et.bean.OrderInfo;
import com.xnhb.et.ui.ac.bill.HistoricalDetailsActivity;
import com.xnhb.et.ui.fragment.home.page.C2CSubFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/20
 * 描述: 充提记录
 * 参考链接:
 */
public class HistoricalFrament extends BaseFragment implements ListLayout.TaskListener, BaseQuickAdapter.OnItemClickListener, RecyclerViewCreator<OrderInfo> {
    public final static int PageType = 0;
    private static String ARG_PAGE_TYPE = "arg_page_type";
    private static String ARG_TAG_PRICE = "unit_price";
    @BindView(R.id.listLayout)
    ListLayout mListLayout;

    public static HistoricalFrament newInstance(int type) {
        HistoricalFrament frament = new HistoricalFrament();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE_TYPE, type);
        frament.setArguments(bundle);
        return frament;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.common_list_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        mListLayout.setBackgroundColor(UiUtils.getColor(R.color.black));
        mListLayout.setTaskListener(this);
        mListLayout.addItemDecoration(new LinearItemDecoration(10, R.color.black));
        mListLayout.setEmptyText("暂无记录...");
        mListLayout.setAdaper(new XRecyclerViewAdapter<OrderInfo>(this));
        mListLayout.addOnItemClickListener(this);
        mListLayout.showLoadingView();
        mListLayout.pullRefresh();
    }

    @Override
    public void task() {
        ArrayList<OrderInfo> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new OrderInfo());
        }
        mListLayout.setData(list);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HistoricalDetailsActivity.launch(getAc());
    }

    @Override
    public int bindListViewLayout() {
        return R.layout.item_historical_list;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, OrderInfo data) {

    }
}
