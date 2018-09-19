package com.xnhb.et.ui.ac.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.convert.RegexUtils;
import com.oneway.ui.base.ac.ActivityManager;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.CountDownButton;
import com.xnhb.et.R;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.ui.ac.setting.p.IModifyView;
import com.xnhb.et.ui.ac.setting.p.ModifyPwdPresent;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/18
 * 描述:
 * 参考链接:
 */
public class ModifyPasswordActivity extends BaseTitleActivity<ModifyPwdPresent> implements IModifyView {
    public static String PAGE_TITLE = "page_title";
    public static String TITLE_MODIFY_LOGIN_PWD = "修改登陆密码";
    public static String TITLE_MODIFY_TRANSACTION_PWD = "修改交易密码";
    //    public static String TITLE_MOdify_LOGIN_PWD = "修改登录密码";
    @BindView(R.id.et_oldPwd)
    EditText etOldPwd;
    @BindView(R.id.et_newPwd)
    EditText etNewPwd;
    @BindView(R.id.et_input_newpwd)
    EditText etInputNewpwd;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.ll_oldPwd_layout)
    LinearLayout llOldPwdLayout;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.btn_countdown)
    CountDownButton btnCountdown;
    private String pageType;

    @Override
    protected String getTitleText() {
        pageType = getIntent().getStringExtra(PAGE_TITLE);
        return pageType;
    }

    public static void launch(Context context, String title) {
        Intent intent = new Intent();
        intent.setClass(context, ModifyPasswordActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra(PAGE_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    public int setLayoutId() {
//        return R.layout.test;
        return R.layout.activity_modify_password;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        llOldPwdLayout.setVisibility(TITLE_MOdify_LOGIN_PWD.equals(pageType) ? View.VISIBLE : View.GONE);
        tvConfirm.setOnClickListener(mPerfectClickListener);
        btnCountdown.setOnClickListener(mPerfectClickListener);
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.tv_confirm) {
//                if (TITLE_MOdify_LOGIN_PWD.equals(pageType)) {//找回登录密码
//
//                }
                String oldPwd = etOldPwd.getText().toString().trim();
                String newPwd = etNewPwd.getText().toString().trim();
                String reInputNewPwd = etInputNewpwd.getText().toString().trim();
                String verificationCode = etVerificationCode.getText().toString().trim();
                if (EmptyUtils.isEmpty(oldPwd)) {
                    ToastManager.info(R.string.input_old_pwd);
                    return;
                }
                if (EmptyUtils.isEmpty(newPwd)) {
                    ToastManager.info(R.string.input_new_pwd);
                    return;
                }
                if (!newPwd.equals(reInputNewPwd)) {
                    ToastManager.info(R.string.toast_hint_pwd_inconsistent);//
                    return;
                }

                if (!RegexUtils.isVerificationCode(verificationCode)) {
                    ToastManager.info(R.string.toast_hint_verification_code);
                    return;
                }
                //TODO 点击后请
                //分发
                dispatchModifyEvent(oldPwd, newPwd, reInputNewPwd, verificationCode);
            } else if (id == R.id.btn_countdown) {//发送验证码
                String accountName = UserInfoHelper.getInstance().getAccountName();
                getP().getVerificationCode(accountName);
            }
        }
    };

    private void dispatchModifyEvent(String oldPwd, String newPwd, String reInputNewPwd, String verificationCode) {
        if (TITLE_MODIFY_LOGIN_PWD.equals(pageType)) {//修改登录密码
            getP().modifyLoginPwd(oldPwd, newPwd, reInputNewPwd, verificationCode);
        } else if (TITLE_MODIFY_TRANSACTION_PWD.equals(pageType)) { //修改交易密码
            getP().getModifyTransactionPwd(oldPwd, newPwd, reInputNewPwd, verificationCode);
        }
//        else if (TITLE_MODIFY_LOGIN_PWD.equals(pageType)) {//修改登录密码
//            getP().modifyLoginPwd(oldPwd, newPwd, reInputNewPwd, verificationCode);
//        }
    }

    @Override
    public ModifyPwdPresent newP() {
        return new ModifyPwdPresent();
    }

    @Override
    public void startCountDown() {
        btnCountdown.start();
    }

    @Override
    public void cancelCountDown() {
        btnCountdown.cancel();
    }

    @Override
    public void submit() {
        if (TITLE_MODIFY_LOGIN_PWD.equals(pageType)) {//修改登录密码
            ToastManager.success("登录密码修改成功,请重新登录");
            //清空登录信息--->跳转登录界面-->关闭所有界面-->home切换到第一页
            UserInfoHelper.getInstance().logoutAndfinishAll();
        } else if (TITLE_MODIFY_TRANSACTION_PWD.equals(pageType)) { //修改交易密码
            ToastManager.success("交易密码修改成!");
            ActivityManager.getInstance().finishActivity();
        }
    }

}
