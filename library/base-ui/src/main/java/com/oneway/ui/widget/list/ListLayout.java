package com.oneway.ui.widget.list;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import com.oneway.ui.R;
import com.oneway.ui.widget.status.OnRetryListener;
import com.oneway.ui.widget.status.StatusLayoutManager;

/**
 * Created by ww on 2018/01/15.
 * 分页加载有三种情况
 * 1.服务器返回总条数   totalNumber
 * 2.服务器返回总页数   totalPageNumber
 * 3.服务器什么都不返回  但是可以根据请求的条数判断, 比如每次请求20条数据,如果服务器给的数据少于20条则没有更多
 */
public class ListLayout extends FrameLayout implements BaseQuickAdapter.RequestLoadMoreListener, OnRefreshListener, SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {

    //    private RecyclerView.LayoutManager layoutManger;
    private String emptyStr;
    private int emptyImg;
    private int otherErrorView; //其他错误界面

    public ListLayout(@NonNull Context context) {
        this(context, null);
    }

    public ListLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    private Context mContext;
    public static final int CLASSIC_MODE = 1;  //ios风格 显示时间下拉
    public static final int NATIVE_MODE = 2;  //系统原生 下拉刷新
    private RelativeLayout mRootView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private SwipeRefreshLayout mAutoSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    /**
     * 服务器返回的总条数  -1 代表未初始化
     */
    private int totalNumber = -1;
    /**
     * 服务器返回的总页数  -1 代表未初始化
     */
    private int totalPageNumber = -1;
    /**
     * 网络请求的起始页数  默认从 1 开始
     */
    private int startingPageNumber = 1;
    /**
     * 每页请求多少条数据
     */
    private int eachPageNumber = -1;
    /**
     * 获取数据当前页码
     */
    private int currentPageNumber = 1;

    /**
     * 是否是下拉刷新  默认true
     */
    private boolean isPullRefresh = true;
    /**
     * 是否启用下拉刷新
     */
    private boolean IS_PULLREFRESH_ENABLE = true;
    /**
     * 是否启用上拉加载更多
     */
    private boolean IS_LOADMORE_ENABLE = true;
    /**
     * 自定义空页面id
     * emptyViewId>0 才会生效 否则使用默认空页面
     */
    private View emptyView;
    /**
     * 任务回调监听  一般网络请亲放在这个里面
     */
    TaskListener mTaskListener;
    /**
     * 页面状态监听回调, 不重写则默认  点击刷新按钮后执行下拉刷新操作
     */
    PageStatusListener mPageStatusListener;

    private RecyclerView.ItemDecoration mDecor;
    private BaseQuickAdapter.OnItemChildClickListener mOnItemChildClickListener;
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener;
    private BaseQuickAdapter mAdapter;


    private void init() {
        @StyleMode int mode = getCurrentStyle();
        switch (mode) {
            case CLASSIC_MODE:  //ios  时间+箭头 风格
                View.inflate(mContext, R.layout.base_recyclerview_layout, this);
                mSmartRefreshLayout = findViewById(R.id.SmartRefreshLayout);
                mRecyclerView = findViewById(R.id.RecyclerView);
                mRootView = findViewById(R.id.rootView);
                initStatusPage(mSmartRefreshLayout);
                break;
            case NATIVE_MODE:  //原生SwipeRefreshLayout加载风格
                View.inflate(mContext, R.layout.base_recyclerview_layout2, this);
                mAutoSwipeRefreshLayout = findViewById(R.id.AutoSwipeRefreshLayout);
                mRecyclerView = findViewById(R.id.RecyclerView);
                mRootView = findViewById(R.id.rootView);
                initStatusPage(mAutoSwipeRefreshLayout);
                break;
        }
        if (mAdapter == null) {
            return;
        }
        // 设置 下拉与上下 刷新监听
        setListener();
        //设置布局 管理
        mRecyclerView.setLayoutManager(getLayoutManger());
        //添加 ItemDecoration 为null不添加
        if (mDecor != null) {
            mRecyclerView.addItemDecoration(mDecor);
        }
        //设置item中的子view点击事件
        if (mOnItemChildClickListener != null) {
            mAdapter.setOnItemChildClickListener(mOnItemChildClickListener);
        }
        //设置条目点击
        if (mOnItemClickListener != null) {
            mAdapter.setOnItemClickListener(mOnItemClickListener);
        }
        mRecyclerView.setAdapter(mAdapter);
    }


    /**
     * 默认 list 形式
     * 后期自定义这里
     */
    //TODO   后期自定义LayoutManager
    protected RecyclerView.LayoutManager getLayoutManger() {
        return new LinearLayoutManager(mContext);
    }

    /**
     * 设置adapter 并初始化
     * 此方法一定要放在最后
     */
    public void setAdaper(BaseQuickAdapter adapter) {
        this.mAdapter = adapter;
        if (mRecyclerView != null)
            mRecyclerView.setAdapter(mAdapter);
        init();
    }

    /**
     * 设置空页面的文本
     *
     * @param emptyStr
     */
    public void setEmptyText(String emptyStr) {
        this.emptyStr = emptyStr;
    }

    /**
     * 设置空页面的图片
     */
    public void setEmptyImg(int emptyImg) {
        this.emptyImg = emptyImg;
    }

    /**
     * 如果有自定义空页面使用这个方法
     *
     * @param adapter
     * @param emptyView 自定义空页面
     */
    public void setAdaper(BaseQuickAdapter adapter, View emptyView) {
        this.mAdapter = adapter;
        this.emptyView = emptyView;
        if (adapter != null && emptyView != null) {
            mAdapter.setEmptyView(emptyView);
        }
        setAdaper(adapter);
    }

    /**
     * 设置任务请求监听  一般网络请求等操作使用这个回调
     */
    public void setTaskListener(TaskListener l) {
        this.mTaskListener = l;
    }

    /**
     * 设置起始页
     * 默认 起始页为1
     */
    public void setStartingPageNumber(int startPageNumber) {
        this.startingPageNumber = startPageNumber;
    }

    /**
     * 设置 数据的总个数
     */
    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    /**
     * 设置数据的总页数
     */
    public void setTotalPageNumber(int totalPageNumber) {
        this.totalPageNumber = totalPageNumber;
    }

    /**
     * 设置数据的总页数
     */
    public void setEachlPageNumber(int eachPageNumber) {
        this.eachPageNumber = eachPageNumber;
    }


    public boolean getRefreshStatus() {
        return isPullRefresh;
    }

    public void setPullRefresh(boolean isPullRefresh) {
        this.isPullRefresh = isPullRefresh;
    }

    /**
     * 获取当前页数
     * 一般服务器分页加载 需要传入加载的页面 用此方法
     */
    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    /**
     * 是否开启下拉刷新
     */
    public void setPullrefreshEnabled(boolean isEnable) {
        this.IS_PULLREFRESH_ENABLE = isEnable;
    }

    /**
     * 是否开启上拉加载更多
     */
    public void setLoadMoreEnabled(boolean isEnable) {
        this.IS_LOADMORE_ENABLE = isEnable;
    }

    /**
     * 设置条目的 子view点击事件
     */
    public void addOnItemChildClickListener(@Nullable BaseQuickAdapter.OnItemChildClickListener listener) {
        this.mOnItemChildClickListener = listener;
        if (mAdapter != null && mOnItemChildClickListener != null) {
            mAdapter.setOnItemChildClickListener(mOnItemChildClickListener);
        }
    }

    /**
     * 设置条目的点击事件
     */
    public void addOnItemClickListener(@Nullable BaseQuickAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
        if (mAdapter != null && mOnItemClickListener != null) {
            mAdapter.setOnItemClickListener(mOnItemClickListener);
        }
    }

    /**
     * 状态页面的点击回调
     * 默认点击后执行 下拉刷新
     */
    public void setPageStatusListener(PageStatusListener l) {
        this.mPageStatusListener = l;
    }

    /**
     * 给adapter设置数据
     */
    public <T> void setData(List<T> infos) {
        showContentView();
        closeRefresh();
        //如果数据是空的
        if (infos == null || infos.isEmpty()) {
            if (isPullRefresh) {//并且是下拉刷新
//                isLoadMore(false);  //没有更多
                //如果设置了自定义的空界面,则会显示自定的空界面
                if (emptyView == null) {
                    showEmptyView();
                }
            } else {
                completeLoadMore();
                setLoadMoreEnd();
            }
            mAdapter.setNewData(infos);
            return;
        }
        if (mAdapter != null) {
            //三种模式
            //1.总数据大小 判断
            //2.总页数大小 判断
            //3.请求数据与返回数据是否相等 判断
            if (totalPageNumber != -1 || totalNumber != -1 || eachPageNumber != -1) {  //总数据模式
                if (isPullRefresh) {
                    //下拉加载逻辑
                    mAdapter.setNewData(infos);
                    //当前数据小于服务器数据
                    if (mAdapter.getData().size() < totalNumber || getCurrentPageNumber() < totalPageNumber || eachPageNumber == infos.size()) {
                        isLoadMore(true);  //可以加载更多
                    } else {
                        isLoadMore(false);  //没有更多
                    }
                } else {
                    //设置加载更多完成
                    completeLoadMore();
                    //加载更多逻辑
                    mAdapter.addData(infos);
                    //下拉加载后开启下拉刷新
                    isPullRefresh(true);
                    //标记没有更多数据
                    //总页数模式
//                    if (getCurrentStyle() == NATIVE_MODE) {
                    if (totalPageNumber != -1 && getCurrentPageNumber() >= totalPageNumber) {
                        setLoadMoreEnd();
                    }
//                    }
//                    // 总数据模式
//                    if (totalNumber != -1 && mAdapter.getData().size() >= totalNumber) {
//                        setLoadMoreEnd();
//                    }
//                    //eachPageNumber 模式
//                    if (eachPageNumber != -1 && eachPageNumber != infos.size()) {
//                        setLoadMoreEnd();
//                    }
                }
            } else {
                if (isPullRefresh) {
                    mAdapter.setNewData(infos);
                    setEnableLoadMore(false);
                } else {
                    //加载更多逻辑
                    mAdapter.addData(infos);
                    completeLoadMore();
                    isPullRefresh(true);
                    setEnableLoadMore(false);
                }
            }
        }
    }

    /**
     * 标记完成加载更多
     */
    private void completeLoadMore() {
        if (getCurrentStyle() == NATIVE_MODE) {
            mAdapter.loadMoreComplete();
        } else {
            if (mSmartRefreshLayout != null)
                mSmartRefreshLayout.finishRefresh();
        }
    }

    /**
     * 是否开启下拉加载更多
     * 这里是在设置数据的时候用于二次判断
     */
    private void isLoadMore(boolean isLoadMore) {
        if (!IS_LOADMORE_ENABLE) {
            return;
        }
        setEnableLoadMore(isLoadMore);
    }

    /**
     * 下拉刷新完成后的启动上拉刷新
     */
    private void isPullRefresh(boolean isPullRefresh) {
        if (!IS_PULLREFRESH_ENABLE) {
            return;
        }
        setEnablePullRefresh(isPullRefresh);
    }

    /**
     * 设置是否启用下拉刷新
     */
    public void setEnablePullRefresh(boolean isEnable) {
        if (getCurrentStyle() == CLASSIC_MODE && mSmartRefreshLayout != null) {
            mSmartRefreshLayout.setEnableRefresh(isEnable);
        } else {
            if (mAutoSwipeRefreshLayout != null)
                mAutoSwipeRefreshLayout.setEnabled(isEnable);
        }
    }

    /**
     * 设置是否启用加载更多
     */
    private void setEnableLoadMore(boolean isEnable) {
        if (getCurrentStyle() == CLASSIC_MODE) {
            mSmartRefreshLayout.setNoMoreData(false);
            mSmartRefreshLayout.setEnableLoadMore(isEnable);
        } else {
            mAdapter.setEnableLoadMore(isEnable);
        }
    }


    /**
     * 设置条目分割线
     */
    public void addItemDecoration(RecyclerView.ItemDecoration decor) {
        this.mDecor = decor;
        if (mRecyclerView != null)
            mRecyclerView.addItemDecoration(mDecor);
    }

    /**
     * 关闭刷新
     */
    public void closeRefresh() {
        @StyleMode int mode = getCurrentStyle();
        if (mode == CLASSIC_MODE) {
            if (mSmartRefreshLayout != null) {
                if (mSmartRefreshLayout.isRefreshing())
                    mSmartRefreshLayout.finishRefresh();
                if (mSmartRefreshLayout.isEnableLoadMore())
                    mSmartRefreshLayout.finishLoadMore();
            }
        } else {
            if (mAutoSwipeRefreshLayout != null && mAutoSwipeRefreshLayout.isRefreshing()) {
                mAutoSwipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    /**
     * 设置加载更多结束  并标记没有更多数据
     */
    private void setLoadMoreEnd() {
        if (getCurrentStyle() == CLASSIC_MODE) {
            if (mSmartRefreshLayout != null)
                mSmartRefreshLayout.finishLoadMore();
//                mSmartRefreshLayout.setNoMoreData(true);
            mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
        } else {
            mAdapter.loadMoreEnd();
        }
    }


    /**
     * 设置 下拉与上下 刷新监听
     */
    private void setListener() {
        @StyleMode int mode = getCurrentStyle();
        if (mode == CLASSIC_MODE) {
            if (mSmartRefreshLayout != null) {
//                if (IS_PULLREFRESH_ENABLE)
                mSmartRefreshLayout.setOnRefreshListener(this);
//                if (IS_LOADMORE_ENABLE)
                mSmartRefreshLayout.setOnLoadMoreListener(this);
            }
        } else {
            if (mAutoSwipeRefreshLayout != null && IS_PULLREFRESH_ENABLE) {
                mAutoSwipeRefreshLayout.setOnRefreshListener(this);
            }
            if (IS_LOADMORE_ENABLE && mAdapter != null) {
                mAdapter.setOnLoadMoreListener(this, mRecyclerView);
            }
        }
        setEnablePullRefresh(IS_PULLREFRESH_ENABLE);
        setEnableLoadMore(IS_PULLREFRESH_ENABLE);
    }

    public void setAutoRefresh() {
        if (mSmartRefreshLayout != null)
            mSmartRefreshLayout.autoRefresh();
    }


    /**
     * 下拉刷新
     */
    public void pullRefresh() {
        isPullRefresh = true;
        currentPageNumber = startingPageNumber;
        executeTask();
        //TODO 这里要禁止上拉刷新  当刷新结束后 判断是否有禁止, 然后去开启
        setEnableLoadMore(false);
    }

    /**
     * 上拉加载更多
     */
    public void Loadmore() {
        isPullRefresh = false;
        currentPageNumber++;
        if (getCurrentStyle() == CLASSIC_MODE) {
            if (totalPageNumber != -1 && getCurrentPageNumber() > totalPageNumber) {
                setLoadMoreEnd();
                return;
            }
        }
        executeTask();
        //TODO 这里要关闭下拉刷新
        setEnablePullRefresh(false);
    }

    private void executeTask() {
        if (mTaskListener != null) {
            mTaskListener.task();
        }
    }

    /**
     * 加载失败
     */
    public void loadFail() {
        loadFail("", 0);
    }

    /**
     * 加载失败
     */
    public void loadFail(String str, int imgId) {
        if (isPullRefresh) {
            closeRefresh();
            if (mAdapter.getData().size() <= 0) {
                showErrorView(str, imgId);
            } else {
                showContentView();
            }
        } else {
            //TODO 如果是上拉加载更多
            if (getCurrentStyle() == CLASSIC_MODE) {
                mSmartRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSmartRefreshLayout.finishLoadMore(false);//结束加载（加载失败）
                    }
                }, 400);
            } else {
                mAdapter.loadMoreFail();
            }
        }
    }


    @Override
    public void onRefresh() {
        pullRefresh();
    }

    @Override
    public void onLoadMoreRequested() {
        Loadmore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pullRefresh();
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        Loadmore();
    }

    /**
     * 错误页面的点击回调
     */
    private void retry(int type) {
        if (mPageStatusListener == null) {
            showLoadingView();
            pullRefresh();
        } else {
            mPageStatusListener.retry(type);
        }
    }

    /**
     * 页面状态
     */

    private StatusLayoutManager mStatusLayoutManager;


    public void notifyDataSetChanged() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void initStatusPage(View content) {
        //错误页面回调
        mStatusLayoutManager = StatusLayoutManager.newBuilder(mContext)
                .addContentView(content)
                .setEmptyText(emptyStr)
                .emptyImg(emptyImg)
                .addOtherErrorView(otherErrorView)
                .onRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetry(int type) {
                        retry(type);
                    }
                })
                .build();
    }

    public void showLoadingView() {
        if (mStatusLayoutManager != null)
            mStatusLayoutManager.showLoadingView();
    }


    public void showContentView() {
        if (mStatusLayoutManager != null)
            mStatusLayoutManager.showContentView();
    }


    /**
     * 显示其他的异常
     *
     * @param str   不传则使用默认值
     * @param imgId 不传则使用默认值
     */
    /**
     */
    public void showOtherErrorView() {
        showOtherErrorView("", 0);
    }

    public void showOtherErrorView(String str, int imgId) {
        if (mStatusLayoutManager != null)
            mStatusLayoutManager.showOtherErrorView(str, imgId);
    }


    public void showOtherErrorView(String str) {
        showOtherErrorView(str, 0);
    }


    /**
     * 显示网络错误
     */
    public void showErrorView() {
        showErrorView("", 0);
    }

    public void showErrorView(String str, int imgId) {
        if (mStatusLayoutManager != null)
            mStatusLayoutManager.showNetworkErrorView(str, imgId);
    }

    /**
     * 是用默认的空页面
     */
    public void showEmptyView() {
        showEmptyView("", 0);
    }

    /**
     * 显示空页面 如果有自定的空页面则使用自定用的空页面
     */
    public void showEmptyView(String str, int imgId) {
        if (emptyView == null) {
            if (mStatusLayoutManager != null)
                mStatusLayoutManager.showEmptyView(str, imgId);
        } else {
            if (mStatusLayoutManager != null)
                mStatusLayoutManager.showContentView();
        }
    }

    public void setOtherErrorView(int otherErrorViewId) {
        this.otherErrorView = otherErrorViewId;
    }


    /**
     * 风格枚举
     */
    @IntDef({CLASSIC_MODE, NATIVE_MODE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StyleMode {
    }

    @StyleMode
    int currentMode = CLASSIC_MODE;

    /**
     * 设置风格  1 ios
     * 2 原生
     */
    public void setLayoutStyle(@StyleMode int currentMode) {
        this.currentMode = currentMode;
    }

    @StyleMode
    public int getCurrentStyle() {
        return currentMode;
    }

    /**
     * 任务
     */
    public interface TaskListener {
        void task();
    }

    /**
     * 页面状态
     */
    public interface PageStatusListener {
        void retry(int type);
    }


}
