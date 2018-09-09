package com.oneway.ui.widget.list.newlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者 oneway on 2018/7/13
 * 描述:
 * 参考链接:
 */
public interface RxListView {

    View getLayout(ViewGroup view);

    void initView(ViewGroup view);

    RecyclerView getRecyclerView();

    void setViewListener(ListLayoutManager manager);

    void pullRefresh();

    void loadMore();

    void pullRefreshFinish();

    void loadMoreFinish();

    void closePullRefresh();

    void closeLoadMore();


}
