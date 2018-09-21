package com.oneway.ui.widget.status;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.oneway.tool.utils.common.Network;


/**
 * @Description: 各种状态视图显示管理，可配置网络异常显示视图、无数据显示视图等
 * 使用示例：
 * mStatusLayoutManager = StatusLayoutManager.newBuilder(mContext)
 * .addContentView(R.layout.status_switch_content_layout)//配置内容视图
 * .loadingView(R.layout.loading_layout)//配置加载视图
 * .addEmptyView(R.layout.empty_layout)//配置空视图
 * .networkErrorView(R.layout.network_error_layout)//配置网络异常视图
 * .otherErrorView(R.layout.other_error_layout)//配置其他异常视图
 * .setRetryViewId(R.id.reload_view)//配置重试ViewID
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
    private final ViewStub mLoadingView;
    private final ViewStub mNetworkErrorView;
    private final ViewStub mOtherErrorView;
    private final View mContentView;
    private int mRetryIdCommon;
    private final int mRetryIdEmpty;
    private final int mRetryIdNetworkError;
    private final int mRetryIdOtherError;
    //    private final int mLoadingLayoutResId;
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
        //添加空界面文本
        this.mEmptyText = builder.mEmptyText;
        //添加空界面图片
        this.mEmptyImg = builder.mEmptyImg;
        //添加错误界面文本
        this.mErrorText = builder.mErrorText;
        //添加错误界面图片
        this.mErrorImg = builder.mErrorImg;
        //添加错误界面文本
        this.mOtherText = builder.mOtherText;
        //添加错误界面图片
        this.mOtherImg = builder.mOtherImg;
        //空页面是否可以点击
        this.isEmptyViewClick = builder.isEnableEmptyClick;
        //添加  加载页面   如果未设置自定义界面则使用默认界面
        if (builder.mLoadingView == null) {
            this.mLoadingView = new ViewStub(mContext);
            mLoadingView.setLayoutResource(StatusConfig.DEF_VIEW_ID_LOADING);
        } else {
            this.mLoadingView = builder.mLoadingView;
        }
        //添加  空页面
        if (builder.mEmptyView == null) {
            this.mEmptyView = new ViewStub(mContext);
            mEmptyView.setLayoutResource(StatusConfig.DEF_VIEW_ID_EMPTY);
        } else {
            this.mEmptyView = builder.mEmptyView;
        }
        //添加  错误页面
        if (builder.mNetworkErrorView == null) {
            this.mNetworkErrorView = new ViewStub(mContext);
            mNetworkErrorView.setLayoutResource(StatusConfig.DEF_VIEW_ID_NETWORKERROR);
        } else {
            this.mNetworkErrorView = builder.mNetworkErrorView;
        }
        //添加  其他错误页面
        if (builder.mOtherErrorView == null) {
            this.mOtherErrorView = new ViewStub(mContext);
            mOtherErrorView.setLayoutResource(StatusConfig.DEF_VIEW_ID_OTHERERROR);
        } else {
            this.mOtherErrorView = builder.mOtherErrorView;
        }
//        if (builder.mLoadingLayoutResId == 0) {
//            this.mLoadingLayoutResId = StatusConfig.DEF_VIEW_ID_LOADING;
//        } else {
//            this.mLoadingLayoutResId = builder.mLoadingLayoutResId;
//        }
        //设置重试按钮id
        this.mRetryIdCommon = builder.mRetryIdCommon;
        this.mRetryIdOtherError = builder.mRetryIdOtherError;
        this.mRetryIdEmpty = builder.mRetryIdEmpty;
        this.mRetryIdNetworkError = builder.mRetryIdNetworkError;
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


    public ViewStub getView(StatusType type) {
        if (type.getType() == StatusType.LOADING.getType()) {
            return mLoadingView;
        } else if (type.getType() == StatusType.EMPTY.getType()) {
            return mEmptyView;
        } else if (type.getType() == StatusType.NETWORK_ERROR.getType()) {
            return mNetworkErrorView;
        } else if (type.getType() == StatusType.OTHER_ERROR.getType()) {
            return mOtherErrorView;
        }
        return null;
    }

    /**
     * 获取loading
     */
    public ViewStub getLoadingView() {
        return mLoadingView;
    }

    /**
     * 获取空视图
     */
    public ViewStub getEmptyView() {
        return mEmptyView;
    }

    /**
     * 获取其他视图
     */
    public ViewStub getOtherErrorView() {
        return mOtherErrorView;
    }

    /**
     * 获取网络错误视图
     */
    public ViewStub getNetworkErrorView() {
        return mNetworkErrorView;
    }

    public int getEmptyRetryViewId() {
        return mRetryIdEmpty;
    }


    public int getNetworkErrorRetryViewId() {
        return mRetryIdNetworkError;
    }


    public int getOtherErrorRetryViewId() {
        return mRetryIdOtherError;
    }

//    public int getLoadingLayoutResId() {
//        return mLoadingLayoutResId;
//    }

    public View getContentView() {
        return mContentView;
    }

    public int getRetryViewId() {
        if (mRetryIdCommon == 0) {
            mRetryIdCommon = StatusConfig.mRetryViewId;
        }
        return mRetryIdCommon;
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
        private String mEmptyText;
        private String mErrorText;
        private String mOtherText;
        private int mEmptyImg;
        private int mErrorImg;
        private int mOtherImg;
        private int mLoadingLayoutResId;
        //视图
        private View mContentView;
        private ViewStub mLoadingView;
        private ViewStub mEmptyView;
        private ViewStub mNetworkErrorView;
        private ViewStub mOtherErrorView;
        //重试按钮id
        private int mRetryIdEmpty;
        private int mRetryIdNetworkError;
        private int mRetryIdOtherError;
        private int mRetryIdCommon;

        private OnStatusViewListener mStatusViewListener;
        private OnRetryListener mRetryListener;
        private boolean isEnableEmptyClick = true; //默认启用空页面点击加载

        public Builder(Context context) {
            this.mContext = context;
        }

        /**
         * 添加内容视图
         *
         * @param mContentView 内容视图
         */
        public Builder addContentView(View mContentView) {
            this.mContentView = mContentView;
            return this;
        }

        /**
         * 添加loading页面
         */
        public Builder addloadingView(@LayoutRes int loadingViewId) {
            mLoadingView = new ViewStub(mContext);
            mLoadingView.setLayoutResource(loadingViewId);
            return this;
        }

        /**
         * 添加新的 空页面
         */
        public Builder addEmptyView(@LayoutRes int mEmptyViewId) {
            mEmptyView = new ViewStub(mContext);
            mEmptyView.setLayoutResource(mEmptyViewId);
            return this;
        }

        /**
         * 添加新的 网络错误页面
         */
        public Builder addNetworkErrorView(@LayoutRes int mNetworkErrorId) {
            mNetworkErrorView = new ViewStub(mContext);
            mNetworkErrorView.setLayoutResource(mNetworkErrorId);
            return this;
        }

        /**
         * 添加新的 网络错误页面
         */
        public Builder addOtherErrorView(@LayoutRes int mOtherErrorViewId) {
            mOtherErrorView = new ViewStub(mContext);
            mOtherErrorView.setLayoutResource(mOtherErrorViewId);
            return this;
        }

        /**
         * 添加新的 加载界面
         *
         * @param mLoadingLayoutResId
         * @return
         */
        public Builder loadingView(@LayoutRes int mLoadingLayoutResId) {
            this.mLoadingLayoutResId = mLoadingLayoutResId;
            return this;
        }

        /**
         * 添加空页面 重试id
         */
        public Builder setRetryIdEmpty(int mEmptyRetryViewId) {
            this.mRetryIdEmpty = mEmptyRetryViewId;
            return this;
        }

        /**
         * 添加网络错误页面 重试id
         */
        public Builder setRetryIdNetworkError(int mNetworkErrorRetryViewId) {
            this.mRetryIdNetworkError = mNetworkErrorRetryViewId;
            return this;
        }

        /**
         * 添加其他错误页面 重试id
         */
        public Builder setRetryIdOtherError(int mOtherErrorRetryViewId) {
            this.mRetryIdOtherError = mOtherErrorRetryViewId;
            return this;
        }

        /**
         * 添加所有页面的通用 重试按钮,
         * 优先级低于addRetryIdEmpty,setRetryIdOtherError,ddRetryIdNetworkError
         */
        public Builder setRetryViewId(int commonRetryViewId) {
            this.mRetryIdCommon = commonRetryViewId;
            return this;
        }

        /**
         * @param isEnableEmptyClick 是否启用空页面 重试点击
         *                           默认true 启用空页面点击加载
         */
        public Builder isEnableEmptyRetry(boolean isEnableEmptyClick) {
            this.isEnableEmptyClick = isEnableEmptyClick;
            return this;
        }

        /**
         * 视图状态监听
         */
        public Builder onStatusViewListener(OnStatusViewListener mStatusViewListener) {
            this.mStatusViewListener = mStatusViewListener;
            return this;
        }

        /**
         * 视图 重试点击按钮监听
         */
        public Builder onRetryListener(OnRetryListener mRetryListener) {
            this.mRetryListener = mRetryListener;
            return this;
        }

        public Builder setEmptyText(String mEmptyText) {
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


        public StatusLayoutManager build() {
            return new StatusLayoutManager(this);
        }
    }


    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }


}
