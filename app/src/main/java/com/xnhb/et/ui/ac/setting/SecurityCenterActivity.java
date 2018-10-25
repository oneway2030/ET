package com.xnhb.et.ui.ac.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.widget.CommomHorizontalLayout;
import com.xnhb.et.R;
import com.xnhb.et.helper.UserInfoHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author oneway
 * @describe 安全中心
 * @since 2018/9/15 0015
 */


public class SecurityCenterActivity extends BaseTitleActivity {

    @BindView(R.id.modify_login_pwd)
    CommomHorizontalLayout modifyLoginPwd;
    @BindView(R.id.modify_transaction_pwd)
    CommomHorizontalLayout modifyTransactionPwd;
    @BindView(R.id.modify_fingerprint)
    CommomHorizontalLayout modifyFingerprint;
    @BindView(R.id.modify_gesture)
    CommomHorizontalLayout modifyGesture;

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
        return "安全中心";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_security_center;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        modifyLoginPwd.setOnClickListener(mPerfectClickListener);
        modifyTransactionPwd.setOnClickListener(mPerfectClickListener);
        modifyFingerprint.setOnClickListener(mPerfectClickListener);
        modifyGesture.setOnClickListener(mPerfectClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        modifyGesture.setTextRight(UserInfoHelper.getInstance().isEnableGestureLock() ? "开启" : "关闭");
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.modify_login_pwd) { //修改登录密码
                ModifyPasswordActivity.launch(SecurityCenterActivity.this, ModifyPasswordActivity.TITLE_MODIFY_LOGIN_PWD);
            } else if (id == R.id.modify_transaction_pwd) {//修改交易密码
                ModifyPasswordActivity.launch(SecurityCenterActivity.this, ModifyPasswordActivity.TITLE_MODIFY_TRANSACTION_PWD);
            } else if (id == R.id.modify_fingerprint) { //开启关闭 指纹

            } else if (id == R.id.modify_gesture) {//开启关闭 手势
                //TODO  开启手势登录  需求
                SettingGestureLockActivity.launch(SecurityCenterActivity.this);
                // 1.每次登录后這里 设置是否开启
                // 2.每次登录后,在退出后,重新登录的时候,判断是否开启了手势
                // 3.如果开启,则跳转手势界面,3次错误后,跳转到登录界面
                // 4.如果不开启,则跳转登录界面
            }
        }
    };

}
