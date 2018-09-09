package com.oneway.ui.base.ac;

import android.support.annotation.ColorRes;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.oneway.ui.R;
import com.oneway.ui.base.in.IPresenter;

/**
 * 作者 oneway on 2018/7/27
 * 描述: 沉静状态栏
 * 参考链接:
 */
public abstract class BaseStatusBarActivity<P extends IPresenter> extends XBaseActivity<P> {
    protected ImmersionBar mImmersionBar;

    @Override
    protected void initStatusBar() {
        if (isImmersionBarEnabled()) {
            mImmersionBar = ImmersionBar.with(this);
            mImmersionBar.init();
            setStatusBar();
        }
    }

    protected abstract void setStatusBar();

    /**
     * 白色titile  黑色导航字体
     *
     * @param titleBar
     */
    public void setWhitetStatusBar(View titleBar) {
        if (mImmersionBar != null)
            mImmersionBar.titleBar(titleBar)
                    .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                    .keyboardEnable(true)
                    .navigationBarColor(R.color.white) //导航栏颜色，不写默认黑色
                    .init();
    }

    /**
     * 自定义颜色
     */
    public void setStatusBarColor(View titleBar, @ColorRes int statusBarColor) {
        if (mImmersionBar != null)
            mImmersionBar.titleBar(titleBar)
                    .keyboardEnable(true)
                    .statusBarColor(statusBarColor)
                    .init();
    }

    /**
     * 透明
     */
    public void setTransparentStatusBar() {
        if (mImmersionBar != null)
            mImmersionBar.transparentStatusBar()
                    .keyboardEnable(true)
                    .init();
    }

    /**
     * 半透明
     */
    public void setTranslucentStatusBar(View titleBar) {
        if (mImmersionBar != null)
            mImmersionBar
                    .titleBar(titleBar)
                    .statusBarColor(R.color.TRANSPARENT)
                    .statusBarAlpha(0.5f)
                    .keyboardEnable(true)
                    .init();
    }


    /**
     * 是否启用沉浸式状态栏
     * 默认启用
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
    }
}
