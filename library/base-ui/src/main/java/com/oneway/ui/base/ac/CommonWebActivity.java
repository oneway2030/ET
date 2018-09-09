package com.oneway.ui.base.ac;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.oneway.tool.router.Router;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.R;
import com.oneway.ui.R2;
import com.oneway.ui.widget.webview.WebLayout;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/17.
 */

public class CommonWebActivity extends BaseTitleActivity {

    @BindView(R2.id.WebLayout)
    WebLayout mWebLayout;
    public static String PARAM_URL = "url";
    public static String PARAM_TITLE = "title";
    public static String PARAM_POST_CODE = "post_code";
    public static String PARAM_IS_SHOWS_NATIVE_PB = "is_show_pb";
    private String url;
    private String title;
    private String postCode;
    private boolean isNativtePb;

    @Override
    protected String getTitleText() {
        title = getIntent().getStringExtra(PARAM_TITLE);
        return EmptyUtils.isEmpty(title) ? "" : title;
    }

    @Override
    public int setLayoutId() {
        return R.layout.base_activity_web;
    }



    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        url = getIntent().getStringExtra(PARAM_URL);
        postCode = getIntent().getStringExtra(PARAM_POST_CODE);
        isNativtePb = getIntent().getBooleanExtra(PARAM_IS_SHOWS_NATIVE_PB, true);
        //TODO 启用跳转服务 如在webview里打开外连应用
        mWebLayout.isEnableJumpSystemService(this);
        mWebLayout.setProgressSytle(isNativtePb);//默认true 显示系统pb
//        mWebLayout.setEnableProgress(true);//默认true
        if (postCode == null) {
            mWebLayout.LoadUrl(url);
        } else {
            mWebLayout.LoadUrl(url, postCode);
        }
    }

    /**
     * get请求方式
     */
    public static void launch(Activity activity, String title, String url) {
        launch(activity, title, url, null, true);
    }

    public static void launch(Activity activity, String title, String url, boolean isNativtePb) {
        launch(activity, title, url, null, isNativtePb);
    }

    /**
     * post请求方式
     */
    public static void launch(Activity activity, String title, String url, String postCode, boolean isNativtePb) {
        Router.newIntent(activity)
                .to(CommonWebActivity.class)
                .putString(PARAM_URL, url)
                .putString(PARAM_POST_CODE, postCode)
                .putString(PARAM_TITLE, title)
                .putBoolean(PARAM_IS_SHOWS_NATIVE_PB, isNativtePb)
                .launch();
    }

    @Override
    public void onBackPressed() {
        if (mWebLayout != null && !mWebLayout.setBackPressed())
            super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebLayout != null)
            mWebLayout.onDestroy();
    }

}
