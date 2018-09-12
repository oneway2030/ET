package com.xnhb.et.ui.ac.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

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
public class RegisterAndLoginActivity extends BaseTitleActivity {
    @BindView(R.id.xTablayout)
    XTabLayout mTabLayout;
    @BindView(R.id.vp)
    ViewPager mViewPager;
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
        mViewPager.setOffscreenPageLimit(mTitles.length);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public List<Fragment> getFragmentPage() {
        List<Fragment> fragments = new ArrayList<>();
        //登录
        LoginFragment loginFragment = new LoginFragment();
        //注册
        RegisterFragment registerFragment = new RegisterFragment();
        fragments.add(loginFragment);
        fragments.add(registerFragment);
        return fragments;
    }
}
