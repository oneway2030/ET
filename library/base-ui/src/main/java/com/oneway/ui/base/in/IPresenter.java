package com.oneway.ui.base.in;

/**
 * 作者 oneway on 2018/9/9
 * 描述: mvp  Presenter的父接口
 * 参考链接:
 */

public interface IPresenter<V> {

    void attachV(V view);

    void detachV();

    boolean hasV();
}
