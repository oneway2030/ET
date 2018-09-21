package com.oneway.ui.widget.list.newlist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.oneway.ui.R;
import com.oneway.ui.widget.AutoSwipeRefreshLayout;
import com.oneway.ui.widget.status.OnRetryListener;
import com.oneway.ui.widget.status.StatusLayoutManager;
import com.oneway.ui.widget.status.StatusType;

/**
 * 作者 oneway on 2018/7/13
 * 描述:
 * 参考链接:
 */
public class NativeViewLayout implements RxListView {

    private RecyclerView mRecyclerView;
    private AutoSwipeRefreshLayout mAutoSwipeRefreshLayout;
    private RelativeLayout mRootView;
    private StatusLayoutManager mStatusLayoutManager;

    @Override
    public View getLayout(ViewGroup view) {
        return View.inflate(view.getContext(), R.layout.base_recyclerview_layout2, view);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public void initView(ViewGroup rooView) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(rooView.getContext()));
        mAutoSwipeRefreshLayout = rooView.findViewById(R.id.AutoSwipeRefreshLayout);
        mRecyclerView = rooView.findViewById(R.id.RecyclerView);
        mRootView = rooView.findViewById(R.id.rootView);
        //错误页面回调
        mStatusLayoutManager = StatusLayoutManager.newBuilder(rooView.getContext())
                .addContentView(mAutoSwipeRefreshLayout)
                .onRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetry(int type) {
//                        retry(type);
                    }
                })
                .build();
    }

    @Override
    public void setViewListener(ListLayoutManager manager) {
        //获取是否开启监听的状态
//        manage.get

    }


    @Override
    public void pullRefresh() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void pullRefreshFinish() {

    }

    @Override
    public void loadMoreFinish() {

    }

    @Override
    public void closePullRefresh() {

    }

    @Override
    public void closeLoadMore() {

    }


}
