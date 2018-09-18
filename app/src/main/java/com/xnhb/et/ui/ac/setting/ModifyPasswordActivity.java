package com.xnhb.et.ui.ac.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.oneway.ui.base.ac.BaseTitleActivity;
import com.xnhb.et.R;

/**
 * 作者 oneway on 2018/9/18
 * 描述:
 * 参考链接:
 */
public class ModifyPasswordActivity extends BaseTitleActivity {
    public static String PAGE_TITLE = "page_title";
    public static String TITLE_LOGIN_PWD = "登陆密码";
    public static String TITLE_TRANSACTION_PWD = "交易密码";
    private String pageType;

    @Override
    protected String getTitleText() {
        pageType = getIntent().getStringExtra(PAGE_TITLE);
        return pageType;
    }

    public static void launch(Context context, String title) {
        Intent intent = new Intent();
        intent.setClass(context, SecurityCenterActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra(PAGE_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_modify_password;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
