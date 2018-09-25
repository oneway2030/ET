package com.xnhb.et.ui.fragment.home.page;

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

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/20
 * 描述: 订单里的列表
 * 参考链接:
 */
public class OrderSubListFrament extends BaseFragment implements ListLayout.TaskListener, BaseQuickAdapter.OnItemClickListener, RecyclerViewCreator<OrderInfo> {
    public final static int PageType = 0;
    public final static int PAGE_TYPE_EXECUTION_ = 1;//委托记录
    public final static int PAGE_TYPE_complete_ = 2;//成交记录
    public final static int PAGE_TYPE_Cancel_ = 3;//已取消
    @BindView(R.id.listLayout)
    ListLayout mListLayout;


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
        mListLayout.setEmptyText("暂无订单...");
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

    }

    @Override
    public int bindListViewLayout() {
        return R.layout.item_order_list;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, OrderInfo data) {

    }
}
