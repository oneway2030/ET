package com.xnhb.et.ui.ac.user.register;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.convert.RegexUtils;
import com.oneway.ui.base.fragment.BaseFragment;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.CountDownButton;
import com.oneway.ui.widget.btn.StateButton;
import com.oneway.ui.widget.et.XEditText;
import com.xnhb.et.R;
import com.xnhb.et.ui.ac.user.LoginAndRegisterActivity;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/12
 * 描述:
 * 参考链接:
 */
public class RegisterFragment extends BaseFragment<RegsiterPresenter> implements IRegisterView {
    @BindView(R.id.et_sms)
    XEditText etAccount;
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
    @BindView(R.id.et_trade_pwd)
    XEditText etTradePwd;
    @BindView(R.id.et_referee)
    XEditText etReferee;
    @BindView(R.id.btn_register)
    StateButton btnRegister;

    @Override
    public RegsiterPresenter newP() {
        return new RegsiterPresenter();
    }

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
                registerAccount();
            } else if (id == R.id.btn_countdown) {//获取验证码
                sendVerificationCode();
            }
        }


    };

    /**
     * 注册账户
     */
    private void registerAccount() {
        String account = etAccount.getTextEx().toString().trim();
        String verificationCode = etVerificationCode.getTextEx().toString().trim();
        String eamil = etMail.getTextEx().toString().trim();
        String loginPwd = etLoginPwd.getTextEx().toString().trim();
        String reInputLoginPwd = etReinputLoginPwd.getTextEx().toString().trim();
        String tradePassword = etTradePwd.getTextEx().toString().trim();
        String referee = etReferee.getTextEx().toString().trim();
        if (!RegexUtils.isMobileSimple(account) && !RegexUtils.isEmail(account)) {
            ToastManager.info(R.string.toast_hint_account);
            return;
        }
        if (!RegexUtils.isVerificationCode(verificationCode)) {
            ToastManager.info(R.string.toast_hint_verification_code);
            return;
        }
        if (!RegexUtils.isEmail(eamil)) {
            ToastManager.info("请填写正确的邮箱");
            return;
        }
        if (EmptyUtils.isEmpty(loginPwd)) {
            ToastManager.info("请输入登录密码");
            return;
        }
        if (!loginPwd.equals(reInputLoginPwd)) {
            ToastManager.info(R.string.toast_hint_pwd_inconsistent);
            return;
        }
        if (EmptyUtils.isEmpty(tradePassword)) {
            ToastManager.info("请输入交易密码");
            return;
        }
        getP().registerAccount(account, verificationCode, eamil, loginPwd, tradePassword, referee);

    }

    /**
     * 发送短信验证码
     */
    private void sendVerificationCode() {
        String account = etAccount.getTextEx().toString().trim();
        if (!RegexUtils.isMobileSimple(account) && !RegexUtils.isEmail(account)) {
            ToastManager.info("请填写正确手机号或者邮箱");
            return;
        }
        getP().getVerificationCode(account);
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
        etAccount.setTextEx("");
        etVerificationCode.setTextEx("");
        etMail.setTextEx("");
        etLoginPwd.setTextEx("");
        etReinputLoginPwd.setTextEx("");
        etTradePwd.setTextEx("");
        etReferee.setTextEx("");
        btnCountdown.cancel();
        //跳转到登录界面
        FragmentActivity activity = getActivity();
        if (activity instanceof LoginAndRegisterActivity) {
            LoginAndRegisterActivity registerAndLoginActivity = (LoginAndRegisterActivity) activity;
            registerAndLoginActivity.setCurrentItem(0);
        }
    }

}
