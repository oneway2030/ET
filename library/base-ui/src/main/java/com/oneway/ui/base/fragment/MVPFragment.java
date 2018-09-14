package com.oneway.ui.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.oneway.ui.base.in.IPresenter;
import com.oneway.ui.base.in.IView;

/**
 * Created by ww on 2018/9/9.
 */

public abstract class MVPFragment<P extends IPresenter> extends Fragment implements IView<P> {

    private P p;
    protected Activity context;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getP();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }
}
