package com.xnhb.et.ui.ac.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.fragment.FragmentBaseAdapter;
import com.xnhb.et.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/12
 * 描述: 注册 登录 忘记密码
 * 参考链接:
 */
public class RegisterAndLoginActivity extends BaseTitleActivity implements LoginFragment.OnClickForgetPwdPageListener {
    @BindView(R.id.xTablayout)
    XTabLayout mTabLayout;
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.forget_pwd_layout)
    View forgetPwdLayout;
    String[] mTitles = {"登录", "注册"};
    private FragmentBaseAdapter mFragmentAdapter;


    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, RegisterAndLoginActivity.class);
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
        mFragmentAdapter = new FragmentBaseAdapter(getSupportFragmentManager(), getFragmentPage(), mTitles);
//        mViewPager.setOffscreenPageLimit(mTitles.length);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                forgetPwdLayout.setVisibility(View.GONE);
                mViewPager.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {
                forgetPwdLayout.setVisibility(View.GONE);
                mViewPager.setVisibility(View.VISIBLE);
            }
        });
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
        forgetPwdLayout.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.GONE);
    }
}
