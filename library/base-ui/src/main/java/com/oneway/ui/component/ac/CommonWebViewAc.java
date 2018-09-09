package com.oneway.ui.component.ac;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.oneway.tool.router.Router;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.fragment.BaseLazyFragment;
import com.oneway.ui.component.WebViewFragment;

/**
 * 作者 oneway on 2018/7/12
 * 描述:
 * 参考链接:
 */
public class CommonWebViewAc extends BaseTitleActivity {

    @Override
    public String getTitleText() {
        String title = getIntent().getStringExtra(WebViewFragment.PARAM_TITLE);
        return EmptyUtils.isEmpty(title)?"":title;
    }

    @Override
    public int setLayoutId() {
        return 0;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mFragment = (BaseLazyFragment) Fragment.instantiate(this, WebViewFragment.class.getName(),
                getIntent().getExtras());
        replaceFragment(mFragment);
    }

    public static void launch(Activity activity, String title, String url) {
        launch(activity, title, url, null, true, false);
    }

    public static void launch(Activity activity, String title, String url, boolean isNativtePb, boolean isGoBack) {
        launch(activity, title, url, null, isNativtePb, isGoBack);
    }

    public static void launch(Activity activity, String title, String url, boolean isNativtePb) {
        launch(activity, title, url, null, isNativtePb, false);
    }

    public static void launch(Activity activity, String title, String url, String postCode, boolean isNativtePb, boolean isGoBack) {
        Router.newIntent(activity)
                .to(CommonWebViewAc.class)
                .putString(WebViewFragment.PARAM_URL, url)
                .putString(WebViewFragment.PARAM_POST_CODE, postCode)
                .putString(WebViewFragment.PARAM_TITLE, title)
                .putBoolean(WebViewFragment.PARAM_IS_SHOWS_NATIVE_PB, isNativtePb)
                .putBoolean(WebViewFragment.PARAM_IS_CAN_GOBACK, isGoBack)
                .launch();
    }

}
