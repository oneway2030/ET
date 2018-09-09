package com.oneway.ui.widget.status;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.oneway.tool.utils.common.Network;


/**
 * @Description: 各种状态视图显示管理，可配置网络异常显示视图、无数据显示视图等
 * 使用示例：
 * mStatusLayoutManager = StatusLayoutManager.newBuilder(mContext)
 * .contentView(R.layout.status_switch_content_layout)//配置内容视图
 * .loadingView(R.layout.loading_layout)//配置加载视图
 * .emptyView(R.layout.empty_layout)//配置空视图
 * .networkErrorView(R.layout.network_error_layout)//配置网络异常视图
 * .otherErrorView(R.layout.other_error_layout)//配置其他异常视图
 * .retryViewId(R.id.reload_view)//配置重试ViewID
 * .onStatusViewListener(new OnStatusViewListener() {//配置状态监听
 * @Override public void onShowView(View view, int id) {//显示
 * }
 * @Override public void onHideView(View view, int id) {//隐藏
 * }
 * })
 * .onRetryListener(new OnRetryListener() {//配置重试监听
 * @Override public void onRetry() {
 * }
 * }).build();
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 2017-03-08 15:54
 */
public class StatusLayoutManager {
    private final Context mContext;
    private final ViewStub mEmptyView;
    private final int mEmptyRetryViewId;
    private final ViewStub mNetworkErrorView;
    private final int mNetworkErrorRetryViewId;
    private final ViewStub mOtherErrorView;
    private final int mOtherErrorRetryViewId;
    private final int mLoadingLayoutResId;
    private final View mContentView;
    private int mRetryViewId;
    private final StatusLayout mStatusLayout;
    private final OnStatusViewListener mStatusViewListener;
    private final OnRetryListener mRetryListener;
    private final boolean isEmptyViewClick;
    private String mEmptyText = "";
    private String mErrorText = "";
    private String mOtherText = "";
    private int mEmptyImg;
    private int mErrorImg;
    private int mOtherImg;

    public StatusLayoutManager(Builder builder) {
        this.mContext = builder.mContext;
        this.mContentView = builder.mContentView;
        if (builder.mEmptyView == null) {
            this.mEmptyView = new ViewStub(mContext);
            mEmptyView.setLayoutResource(StatusConfig.mEmptyViewId);
        } else {
            this.mEmptyView = builder.mEmptyView;
        }
        //添加空界面文本
        if (!TextUtils.isEmpty(builder.mEmptyText)) {
            this.mEmptyText = builder.mEmptyText;
        }
        //添加空界面图片
        if (builder.mEmptyImg != 0) {
            this.mEmptyImg = builder.mEmptyImg;
        }
        //添加错误界面文本
        if (!TextUtils.isEmpty(builder.mErrorText)) {
            this.mErrorText = builder.mErrorText;
        }
        //添加错误界面图片
        if (builder.mErrorImg != 0) {
            this.mErrorImg = builder.mErrorImg;
        }

        //添加错误界面文本
        if (!TextUtils.isEmpty(builder.mOtherText)) {
            this.mOtherText = builder.mOtherText;
        }
        //添加错误界面图片
        if (builder.mOtherImg != 0) {
            this.mOtherImg = builder.mOtherImg;
        }
        this.isEmptyViewClick = builder.isEmptyViewClick;
        this.mEmptyRetryViewId = builder.mEmptyRetryViewId;
        if (builder.mNetworkErrorView == null) {
            this.mNetworkErrorView = new ViewStub(mContext);
            mNetworkErrorView.setLayoutResource(StatusConfig.mNetworkErrorId);
        } else {
            this.mNetworkErrorView = builder.mNetworkErrorView;
        }
        this.mNetworkErrorRetryViewId = builder.mNetworkErrorRetryViewId;
        if (builder.mOtherErrorView == null) {
            this.mOtherErrorView = new ViewStub(mContext);
            mOtherErrorView.setLayoutResource(StatusConfig.mOtherErrorViewId);
        } else {
            this.mOtherErrorView = builder.mOtherErrorView;
        }
        this.mOtherErrorRetryViewId = builder.mOtherErrorRetryViewId;
        if (builder.mLoadingLayoutResId == 0) {
            this.mLoadingLayoutResId = StatusConfig.mLoadingLayoutResId;
        } else {
            this.mLoadingLayoutResId = builder.mLoadingLayoutResId;
        }
        this.mRetryViewId = builder.mRetryViewId;
        this.mStatusViewListener = builder.mStatusViewListener;
        this.mRetryListener = builder.mRetryListener;

        mStatusLayout = new StatusLayout(mContext);
        mStatusLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mStatusLayout.setStatusLayoutManager(this);
        ((ViewGroup) mContentView.getParent()).addView(mStatusLayout);
    }


    /**
     * 设置空视图文本
     */
    public String getEmptyText() {
        return mEmptyText;
    }

    /**
     * 设置错误视图文本
     */
    public String getErrorText() {
        return mEmptyText;
    }

    /**
     * 设置其他视图文本
     */
    public String getOtherText() {
        return mEmptyText;
    }


    /**
     * 设置空视图图片
     */
    public int getEmptyImg() {
        return mEmptyImg;
    }

    /**
     * 设置错误视图图片
     */
    public int getErrorImg() {
        return mEmptyImg;
    }

    /**
     * 设置其他视图图片
     */
    public int getOtherImg() {
        return mEmptyImg;
    }

    /**
     * 空页面点击是否启用
     */
    public boolean getEmptyViewClickEnable() {
        return isEmptyViewClick;
    }

    /**
     * **************************   下面是显示 start ********************************
     */

    /**
     * 显示加载视图
     */
    public void showLoadingView() {
        mStatusLayout.showLoadingView();
    }

    /**
     * 显示内容视图
     */
    public void showContentView() {
        mStatusLayout.showContentView();
    }

    /**
     * 显示空视图
     */
    public void showEmptyView() {
        showEmptyView("", 0);
    }

    public void showEmptyView(String str, int id) {
        mStatusLayout.showEmptyView(str, id);
    }

    public void showEmptyView(String str) {
        showEmptyView(str, 0);
    }

    /**
     * 自动显示异常
     */
    //TODO 这里是判断是否有网
    public void showErrorView() {
        if (Network.isConnected()) {
            mStatusLayout.showOtherErrorView();
        } else {
            //没网
            mStatusLayout.showNetworkErrorView();
        }
    }

    /**
     * 显示网络异常
     */
    public void showNetworkErrorView(String str, int id) {
        mStatusLayout.showNetworkErrorView(str, id);
    }

    public void showNetworkErrorView(String str) {
        showNetworkErrorView(str, 0);
    }

    public void showNetworkErrorView() {
        showNetworkErrorView("", 0);
    }

    /**
     * 显示其他异常
     */
    public void showOtherErrorView(String str, int id) {
        mStatusLayout.showOtherErrorView(str, id);
    }

    public void showOtherErrorView(String str) {
        showOtherErrorView(str, 0);
    }

    public void showOtherErrorView() {
        showOtherErrorView("", 0);
    }


    /**
     * **************************  显示 end ********************************
     */


    public Context getContext() {
        return mContext;
    }

    public ViewStub getEmptyView() {
        return mEmptyView;
    }

    public int getEmptyRetryViewId() {
        return mEmptyRetryViewId;
    }

    public ViewStub getNetworkErrorView() {
        return mNetworkErrorView;
    }

    public int getNetworkErrorRetryViewId() {
        return mNetworkErrorRetryViewId;
    }

    public ViewStub getOtherErrorView() {
        return mOtherErrorView;
    }

    public int getOtherErrorRetryViewId() {
        return mOtherErrorRetryViewId;
    }

    public int getLoadingLayoutResId() {
        return mLoadingLayoutResId;
    }

    public View getContentView() {
        return mContentView;
    }

    public int getRetryViewId() {
        if (mRetryViewId == 0) {
            mRetryViewId = StatusConfig.mRetryViewId;
        }
        return mRetryViewId;
    }

    public StatusLayout getStatusLayout() {
        return mStatusLayout;
    }

    public OnStatusViewListener getStatusViewListener() {
        return mStatusViewListener;
    }

    public OnRetryListener getRetryListener() {
        return mRetryListener;
    }

    public static final class Builder {
        private Context mContext;
        private ViewStub mEmptyView;
        private String mEmptyText;
        private String mErrorText;
        private String mOtherText;
        private int mEmptyImg;
        private int mErrorImg;
        private int mOtherImg;
        private int mEmptyRetryViewId;
        private ViewStub mNetworkErrorView;
        private int mNetworkErrorRetryViewId;
        private ViewStub mOtherErrorView;
        private int mOtherErrorRetryViewId;
        private int mLoadingLayoutResId;
        private int mRetryViewId;
        private View mContentView;
        private OnStatusViewListener mStatusViewListener;
        private OnRetryListener mRetryListener;
        private boolean isEmptyViewClick = true; //默认启用空页面点击加载

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder emptyView(@LayoutRes int mEmptyViewId) {
            if (mEmptyViewId > 0) {
                mEmptyView = new ViewStub(mContext);
                mEmptyView.setLayoutResource(mEmptyViewId);
            }
            return this;
        }

        public Builder emptyText(String mEmptyText) {
            this.mEmptyText = mEmptyText;
            return this;
        }

        public Builder errorText(String mErrorText) {
            this.mErrorText = mErrorText;
            return this;
        }

        public Builder otherText(String mOtherText) {
            this.mOtherText = mOtherText;
            return this;
        }

        public Builder emptyImg(int mEmptyImg) {
            this.mEmptyImg = mEmptyImg;
            return this;
        }

        public Builder errorImg(int mErrorImg) {
            this.mErrorImg = mErrorImg;
            return this;
        }

        public Builder otherImg(int mOtherImg) {
            this.mOtherImg = mOtherImg;
            return this;
        }

        public Builder emptyRetryViewId(int mEmptyRetryViewId) {
            this.mEmptyRetryViewId = mEmptyRetryViewId;
            return this;
        }

        public Builder networkErrorView(@LayoutRes int mNetworkErrorId) {
            mNetworkErrorView = new ViewStub(mContext);
            mNetworkErrorView.setLayoutResource(mNetworkErrorId);
            return this;
        }

        public Builder networkErrorRetryViewId(int mNetworkErrorRetryViewId) {
            this.mNetworkErrorRetryViewId = mNetworkErrorRetryViewId;
            return this;
        }

        public Builder otherErrorView(@LayoutRes int mOtherErrorViewId) {
            mOtherErrorView = new ViewStub(mContext);
            mOtherErrorView.setLayoutResource(mOtherErrorViewId);
            return this;
        }

        public Builder otherErrorRetryViewId(int mOtherErrorRetryViewId) {
            this.mOtherErrorRetryViewId = mOtherErrorRetryViewId;
            return this;
        }

        public Builder loadingView(@LayoutRes int mLoadingLayoutResId) {
            this.mLoadingLayoutResId = mLoadingLayoutResId;
            return this;
        }

        public Builder setEVClickEnable(boolean isEmptyViewClick) {
            this.isEmptyViewClick = isEmptyViewClick;
            return this;
        }

        public Builder contentView(View mContentView) {
            this.mContentView = mContentView;
            return this;
        }

        public Builder retryViewId(int mRetryViewId) {
            this.mRetryViewId = mRetryViewId;
            return this;
        }

        public Builder onStatusViewListener(OnStatusViewListener mStatusViewListener) {
            this.mStatusViewListener = mStatusViewListener;
            return this;
        }

        public Builder onRetryListener(OnRetryListener mRetryListener) {
            this.mRetryListener = mRetryListener;
            return this;
        }

        public StatusLayoutManager build() {
            return new StatusLayoutManager(this);
        }
    }


    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }


}
