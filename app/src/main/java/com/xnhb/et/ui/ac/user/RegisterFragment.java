package com.xnhb.et.ui.ac.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oneway.tool.utils.convert.RegexUtils;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.base.fragment.BaseFragment;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.CountDownButton;
import com.oneway.ui.widget.btn.StateButton;
import com.oneway.ui.widget.et.XEditText;
import com.xnhb.et.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者 oneway on 2018/9/12
 * 描述:
 * 参考链接:
 */
public class RegisterFragment extends BaseFragment {
//    @BindView(R.id.et_account)
//    XEditText etAccount;
    @BindView(R.id.btn_countdown)
    CountDownButton btnCountdown;
    @BindView(R.id.et_verification_code)
    XEditText etVerificationCode;
    @BindView(R.id.et_mail)
    XEditText etMail;
    @BindView(R.id.et_login_pwd)
    XEditText etLoginPwd;
    @BindView(R.id.et_reinput_login_pwd)
    XEditText etReinputLoginPwd;
    @BindView(R.id.et_transaction_pwd)
    XEditText etTransactionPwd;
    @BindView(R.id.et_referee)
    XEditText etReferee;
    @BindView(R.id.btn_register)
    StateButton btnRegister;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView() {
        btnRegister.setOnClickListener(mPerfectClickListener);
        btnCountdown.setOnClickListener(mPerfectClickListener);
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_register) {//注册

            } else if (id == R.id.btn_countdown) {//获取验证码
//                String account = etAccount.getTextEx().toString().trim();
//                if (!RegexUtils.isMobileSimple(account) && !RegexUtils.isEmail(account)) {
//                    ToastManager.info("请填写正确手机号或者邮箱");
//                    return;
//                }
//                btnCountdown.start();
            }
        }
    };
}
