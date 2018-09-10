package com.oneway.ui.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.oneway.ui.base.in.IPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 当使用viewpager加载Fragment，沉浸式的使用，原理懒加载
 * Created by geyifeng on 2017/4/7.
 */
public abstract class BaseBarLazyFragment<P extends IPresenter> extends BaseLazyFragment<P> {
    //    @Override
//    protected void initImmersionBar() {
//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar
//                .titleBar(mTitleLayout)
//                .keyboardEnable(true)
//                .navigationBarWithKitkatEnable(false)
//                .init();
//    }

    public void initTitleBar(View titleView) {
        if (mImmersionBar != null)
            mImmersionBar
                    .titleBar(titleView)
                    .keyboardEnable(true)
                    .navigationBarWithKitkatEnable(false)
                    .init();
    }


}
