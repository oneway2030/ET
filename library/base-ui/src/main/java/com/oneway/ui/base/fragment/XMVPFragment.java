package com.oneway.ui.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.lzy.okgo.OkGo;
import com.oneway.ui.base.in.IPresenter;
import com.oneway.ui.base.in.IView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ww on 2018/9/9.
 */

public abstract class XMVPFragment<P extends IPresenter> extends SupportFragment implements IView<P> {

    private P p;
    protected Activity context;
    public SweetAlertDialog dialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getP();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getP() != null) {
            getP().detachV();
        }
        p = null;
    }

    public Activity getAc() {
        return getActivity();
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
    public void showLoadingPage() {

    }

    @Override
    public void showErrorPage() {

    }

    @Override
    public void showContentPage() {

    }

    @Override
    public void showOtherError() {

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
        dialog = new SweetAlertDialog(getAc(), SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#5588FF"));
        dialog.setTitleText("加载中...");
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                OkGo.getInstance().cancelTag(XMVPFragment.this);
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
