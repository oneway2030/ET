package com.xnhb.et.ui.ac.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.oneway.tool.event.BusManager;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.convert.RegexUtils;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.fragment.FragmentBaseAdapter;
import com.oneway.ui.base.in.TitleContainer;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.CountDownButton;
import com.oneway.ui.widget.btn.StateButton;
import com.oneway.ui.widget.et.XEditText;
import com.xnhb.et.MainActivity;
import com.xnhb.et.R;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.ui.ac.user.login.LoginFragment;
import com.xnhb.et.ui.ac.user.register.IRegisterView;
import com.xnhb.et.ui.ac.user.register.RegisterFragment;
import com.xnhb.et.ui.ac.user.register.RegsiterPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/12
 * 描述: 注册 登录 忘记密码
 * 参考链接:
 */
public class LoginAndRegisterActivity extends BaseTitleActivity<RegsiterPresent> implements LoginFragment.OnClickForgetPwdPageListener, IRegisterView {
    @BindView(R.id.xTablayout)
    XTabLayout mTabLayout;
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.forget_pwd_layout)
    View forgetPwdLayout;
    String[] mTitles = {"登录", "注册"};
    @BindView(R.id.et_account)
    XEditText etAccount;
    @BindView(R.id.btn_countdown)
    CountDownButton btnCountdown;
    @BindView(R.id.et_verification_code)
    XEditText etVerificationCode;
    @BindView(R.id.et_login_pwd)
    XEditText etLoginPwd;
    @BindView(R.id.et_reinput_login_pwd)
    XEditText etReinputLoginPwd;
    @BindView(R.id.btn_retrieve_pwd)
    StateButton btnRetrievePwd;
    private FragmentBaseAdapter mFragmentAdapter;



    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginAndRegisterActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void setStatusBar() {
        mToolbar.setBackgroundResource(R.color.black);
        setTitleBar(mToolbar);
    }

    @Override
    protected String getTitleText() {
        return "";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_register_and_login;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        btnRetrievePwd.setOnClickListener(mPerfectClickListener);
        btnCountdown.setOnClickListener(mPerfectClickListener);
        mFragmentAdapter = new FragmentBaseAdapter(getSupportFragmentManager(), getFragmentPage(), mTitles);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                isShowViewPage(true);
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {
                isShowViewPage(true);
            }
        });
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_retrieve_pwd) {//忘记密码
                forgetPwd();
            } else if (id == R.id.btn_countdown) {//发送验证码
                String account = etAccount.getTextEx().toString().trim();
                if (!RegexUtils.isMobileSimple(account) && !RegexUtils.isEmail(account)) {
                    ToastManager.info("请填写正确手机号或者邮箱");
                    return;
                }
                getP().getVerificationCode(account);
            }
        }
    };

    @Override
    public RegsiterPresent newP() {
        return new RegsiterPresent();
    }

    public List<Fragment> getFragmentPage() {
        List<Fragment> fragments = new ArrayList<>();
        //登录
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setOnClickForgetPwdPageListener(this);
        //注册
        RegisterFragment registerFragment = new RegisterFragment();
        fragments.add(loginFragment);
        fragments.add(registerFragment);
        return fragments;
    }

    @Override
    public void OnLaunchForgetPwdPage(View v) {
        isShowViewPage(false);
    }

    public void setCurrentItem(int position) {
        mViewPager.setCurrentItem(position);
        isShowViewPage(true);
    }

    /**
     * 是否显示viewpage (登录注册界面)
     */
    public void isShowViewPage(boolean isShowViewPage) {
        mViewPager.setVisibility(isShowViewPage ? View.VISIBLE : View.GONE);
        forgetPwdLayout.setVisibility(isShowViewPage ? View.GONE : View.VISIBLE);
    }


    private void forgetPwd() {
        String account = etAccount.getTextEx().toString().trim();
        String verificationCode = etVerificationCode.getTextEx().toString().trim();
        String loginPwd = etLoginPwd.getTextEx().toString().trim();
        String reInputLoginPwd = etReinputLoginPwd.getTextEx().toString().trim();

        if (!RegexUtils.isMobileSimple(account) && !RegexUtils.isEmail(account)) {
            ToastManager.info("请填写正确手机号或者邮箱");
            return;
        }
        if (!RegexUtils.isVerificationCode(verificationCode)) {
            ToastManager.info("请输入正确的验证码");
            return;
        }
        if (EmptyUtils.isEmpty(loginPwd)) {
            ToastManager.info("请输入登录密码");
            return;
        }
        if (EmptyUtils.isEmpty(reInputLoginPwd)) {
            ToastManager.info("请再次输入登录密码");
            return;
        }
        if (!loginPwd.equals(reInputLoginPwd)) {
            ToastManager.info("两次输入的密码不一致");
            return;
        }
        getP().getRetrievePwd(account, verificationCode, loginPwd);
    }


    @Override
    public void submit() {
        etAccount.setTextEx("");
        etVerificationCode.setTextEx("");
        etLoginPwd.setTextEx("");
        etReinputLoginPwd.setTextEx("");
        btnCountdown.cancel();
        setCurrentItem(0);
    }

    @Override
    public void startCountDown() {
        btnCountdown.start();
    }

    @Override
    public void cancelCountDown() {
        btnCountdown.cancel();
    }

}
