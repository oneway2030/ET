package com.oneway.ui.component;

import android.os.Bundle;

import com.oneway.ui.R;
import com.oneway.ui.base.fragment.BaseLazyFragment;
import com.oneway.ui.widget.webview.WebLayout;

/**
 * 作者 oneway on 2018/7/12
 * 描述:
 * 参考链接:
 */
public class WebViewFragment extends BaseLazyFragment {
    public static String PARAM_URL = "url";
    public static String PARAM_TITLE = "title";
    public static String PARAM_POST_CODE = "post_code";
    public static String PARAM_IS_SHOWS_NATIVE_PB = "is_show_pb";
    public static String PARAM_IS_CAN_GOBACK = "is_can_goback";
    private String url;
    private String title;
    private String postCode;
    private boolean isNativtePb;
    private boolean isCanGoBack;

    //    @BindView(R.id.WebLayout)
    WebLayout mWebLayout;

    @Override
    protected int setLayoutId() {
        return R.layout.base_activity_web;
    }

    @Override
    protected void initLazyData() {
        mWebLayout = mRootView.findViewById(R.id.WebLayout);
        Bundle arguments = getArguments();
        url = getArguments().getString(PARAM_URL);
        title = getArguments().getString(PARAM_TITLE);
        postCode = getArguments().getString(PARAM_POST_CODE);
        isNativtePb = getArguments().getBoolean(PARAM_IS_SHOWS_NATIVE_PB, true);
        isCanGoBack = getArguments().getBoolean(PARAM_IS_CAN_GOBACK, false);
        //TODO 启用跳转服务 如在webview里打开外连应用
        mWebLayout.isEnableJumpSystemService(getActivity());
        mWebLayout.setProgressSytle(isNativtePb);//默认true 显示系统pb
//        mWebLayout.setEnableProgress(true);//默认true
        if (postCode == null) {
            mWebLayout.LoadUrl(url);
        } else {
            mWebLayout.LoadUrl(url, postCode);
        }
    }

    @Override
    protected boolean isLazyLoad() {
        return false;
    }


    /**
     * 返回true 可以使用返回键
     * 返回false 不启用返回键
     *
     * @return
     */
    public boolean onBackPressed() {
        if (isCanGoBack) {
            if (mWebLayout != null)
                return mWebLayout.setBackPressed();
        }
        return false;
    }

}
