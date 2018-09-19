package com.oneway.ui.base.in;

import android.app.Activity;

/**
 * 作者 oneway on 2018/9/9
 * 描述: mvp  view的父接口
 * 参考链接:
 */
public interface IView<P> {

    P newP();

    Activity getAc();

    void showLoadingPage();

    void showErrorPage();

    void showContentPage();

    void showProgress();

    void showProgress(boolean isCancel);

    void closeProgress();
}
