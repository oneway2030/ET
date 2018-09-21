package com.oneway.ui.widget.status;


import com.oneway.ui.R;

/**
 * 创建日期：2017/8/17 0017 on 下午 4:01
 * 描述:
 * 作者:oneway
 */
public class StatusConfig {
    static final int DEF_VIEW_ID_EMPTY = R.layout.status_empty_layout;         //空页面
    static final int DEF_VIEW_ID_LOADING = R.layout.status_loading_layout;   //正在加载页面
    static final int DEF_VIEW_ID_NETWORKERROR = R.layout.status_network_error_layout;     //没有网页面
    static final int DEF_VIEW_ID_OTHERERROR = R.layout.status_other_error_layout;   //

    //文本
    static final int tv_empty = R.id.tv_empty_str;
    static final int tv_error = R.id.tv_error_str;
    static final int tv_other = R.id.tv_other_str;
    //图片
    static final int iv_empty = R.id.iv_empty;
    static final int iv_error = R.id.iv_error;
    static final int iv_other = R.id.iv_other;

    //重新加载按钮   //布局里一定要用reload 做btn的名字
    static final int mRetryViewId = R.id.reload; //默认的重试按钮

    static final int mEmptyRetryViewId = R.id.reload;
    static final int mNetworkErrorRetryViewId = R.id.reload;
    static final int mOtherErrorRetryViewId = R.id.reload; //其他错误页面

}
