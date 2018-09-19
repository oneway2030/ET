package com.oneway.ui.base.ac;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lzy.okgo.OkGo;
import com.oneway.ui.base.in.IPresenter;
import com.oneway.ui.base.in.IView;
import com.oneway.ui.util.ScreenUtil;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ww on 2018/9/9.
 */

public abstract class MVPActivity<P extends IPresenter> extends AppCompatActivity implements IView<P> {
    protected P p;
    public SweetAlertDialog dialog;

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
        closeProgress();
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
        if (dialog == null) {
            initDialog();
        }
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void showProgress(boolean isCancel) {
        if (dialog == null) {
            initDialog();
        }
        dialog.setCancelable(isCancel);
        dialog.show();
    }

    void initDialog() {
        dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#5588FF"));
        dialog.setTitleText("加载中...");
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                OkGo.getInstance().cancelTag(getAc());
            }
        });
    }

    @Override
    public void closeProgress() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }


}
