package com.xnhb.et.ui.ac.setting;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.oneway.tool.event.BusManager;
import com.oneway.ui.base.ac.ActivityManager;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.dialog.TipsDialog;
import com.oneway.ui.dialog.base.OnCloseListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.CommomHorizontalLayout;
import com.xnhb.et.MainActivity;
import com.xnhb.et.R;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.ui.ac.user.LoginAndRegisterActivity;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class SettingActivity extends BaseTitleActivity {
    @BindView(R.id.about_layout)
    CommomHorizontalLayout aboutLayout;
    @BindView(R.id.service_agreement_layout)
    CommomHorizontalLayout serviceAgreementLayout;
    @BindView(R.id.identity_authentication_layout)
    CommomHorizontalLayout identityAuthenticationLayout;
    @BindView(R.id.security_center_layout)
    CommomHorizontalLayout securityCenterLayout;
    @BindView(R.id.tv_logout)
    TextView tvLogout;

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SettingActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected String getTitleText() {
        return "设置";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        aboutLayout.setOnClickListener(mPerfectClickListener);
        serviceAgreementLayout.setOnClickListener(mPerfectClickListener);
        identityAuthenticationLayout.setOnClickListener(mPerfectClickListener);
        securityCenterLayout.setOnClickListener(mPerfectClickListener);
        tvLogout.setOnClickListener(mPerfectClickListener);
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.about_layout) {//关于我们
                ToastManager.info("暂未开放");
            } else if (id == R.id.service_agreement_layout) {//关于服务协议
                ToastManager.info("暂未开放");
            } else if (id == R.id.identity_authentication_layout) {//身份认证
                IdentityAuthenticationActivity.launch(SettingActivity.this);
            } else if (id == R.id.security_center_layout) {//安全中心
                SecurityCenterActivity.launch(SettingActivity.this);
            } else if (id == R.id.tv_logout) {//退出登录
                logout();
            }
        }


    };

    /**
     * 退出登录
     * 关闭当前界面-->跳转到登录界面-->首页切换到第一页
     */
    private void logout() {
        new TipsDialog(this, "确定退出登录吗?", new OnCloseListener() {
            @Override
            public void onDailogClose(Dialog dialog, boolean confirm) {
                if (confirm) {
                    ToastManager.success("退出登录成功");
                    UserInfoHelper.getInstance().logoutAndfinishAll();
                }
            }
        }).showDialog();
    }

}
