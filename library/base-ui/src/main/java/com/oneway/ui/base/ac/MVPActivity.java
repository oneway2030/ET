package com.oneway.ui.base.ac;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.oneway.ui.base.in.IPresenter;
import com.oneway.ui.base.in.IView;
import com.oneway.ui.util.ScreenUtil;

/**
 * Created by ww on 2018/9/9.
 */

public abstract class MVPActivity<P extends IPresenter> extends AppCompatActivity implements IView<P> {
    protected P p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //屏幕适配
//        ScreenUtil.setCustomDensity(this, getApplication());
        super.onCreate(savedInstanceState);
        getP();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getP() != null) {
            getP().detachV();
        }
        p = null;
    }

    protected P getP() {
        if (p == null) {
            p = newP();
        }
        if (p != null) {
            if (!p.hasV()) {
                p.attachV(this);
            }
        }
        return p;
    }

    @Override
    public P newP() {
        return null;
    }

    @Override
    public Activity getAc() {
        return this;
    }

    @Override
    public void showLoadingPage() {

    }

    @Override
    public void showErrorPage() {
    }

    @Override
    public void showContentPage() {
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void closeProgress() {
    }


}
