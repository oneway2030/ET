package com.oneway.ui.helper;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.oneway.ui.R;
import com.oneway.ui.widget.status.OnRetryListener;
import com.oneway.ui.widget.status.StatusLayoutManager;

/**
 * 作者 oneway on 2018/7/11
 * 描述:
 * 参考链接:
 */
public class PageStateHelper {


    protected StatusLayoutManager mStatusLayoutManager;

    public PageStateHelper(Context context, View contentView) {
        this(context, contentView, null);
    }

    public PageStateHelper(Context context, View content, OnRetryListener mRetryListener) {
        if (mStatusLayoutManager == null) {
            //错误页面回调
            mStatusLayoutManager = StatusLayoutManager.newBuilder(context)
                    .addContentView(content)
                    .isEnableEmptyRetry(false)
                    .onRetryListener(mRetryListener)
                    .build();
        }
    }

    public PageStateHelper(Context context, View content, @LayoutRes int mOtherErrorViewId, OnRetryListener mRetryListener) {
        if (mStatusLayoutManager == null) {
            //错误页面回调
            mStatusLayoutManager = StatusLayoutManager.newBuilder(context)
                    .addContentView(content)
                    .addOtherErrorView(mOtherErrorViewId)
                    .isEnableEmptyRetry(false)
                    .onRetryListener(mRetryListener)
                    .build();
        }
    }

    /**
     * 加载页面
     */
    public void showLoadingView() {
        if (mStatusLayoutManager != null)
            mStatusLayoutManager.showLoadingView();
    }

    /**
     * 内容页面
     */
    public void showContentView() {
        if (mStatusLayoutManager != null)
            mStatusLayoutManager.showContentView();
    }

    /**
     * 是用默认的空页面
     */
    public void showEmptyView() {
        showEmptyView("", 0);
    }

    /**
     *
     */
    public void showEmptyView(String str, int imgId) {
        if (mStatusLayoutManager != null)
            mStatusLayoutManager.showEmptyView(str, imgId);
    }

    /**
     * 显示其他的异常
     *
     * @param str   不传则使用默认值
     * @param imgId 不传则使用默认值
     */
    public void showOtherErrorView(String str, int imgId) {
        if (mStatusLayoutManager != null)
            mStatusLayoutManager.showOtherErrorView(str, imgId);
    }

    public void showOtherErrorView(String str) {
        showOtherErrorView(str, 0);
    }

    public void showOtherErrorView() {
        showOtherErrorView("", 0);
    }


    /**
     * 显示网络错误
     */
    public void showErrorView() {
        showErrorView("", 0);
    }

    public void showErrorView(String str, int imgId) {
        if (mStatusLayoutManager != null)
            mStatusLayoutManager.showNetworkErrorView(str, imgId);
    }


    public void remove() {

    }
}
