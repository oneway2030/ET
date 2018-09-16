package com.xnhb.et.ui.ac.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.oneway.ui.base.ac.BaseTitleActivity;
import com.xnhb.et.R;

/**
 * @author oneway
 * @describe
 * @since 2018/9/15 0015
 */


public class SecurityCenterActivity extends BaseTitleActivity {

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SecurityCenterActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected String getTitleText() {
        return null;
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_security_center;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
