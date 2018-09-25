package com.oneway.ui.dialog.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.oneway.ui.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wanwei on 2018/5/22.
 */

public abstract class BaseDailog extends Dialog {
    protected boolean isCanceledOnTouchOutside = true;
    protected boolean isCancelKeyDown = true;
    protected OnCloseListener mCloseListener;
    protected Window mWindow;
    protected Context mContext;
    private Unbinder bind;

    public BaseDailog(@NonNull Context context) {
        super(context, R.style.Custom_Progress);
        this.mContext = context;
    }

    public BaseDailog(@NonNull Context context, boolean cancelable, OnCloseListener listener) {
        super(context);
        this.mContext = context;
        this.isCancelKeyDown = cancelable;
        this.mCloseListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        bind = ButterKnife.bind(this);
        //点击外部关闭
        setCanceledOnTouchOutside(setIsCanceledOnTouchOutside());
        // 按返回键是否取消
        setCancelable(setIsCancelKeyDown());
        // 设置居中
        getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        mWindow = getWindow();
        // 设置背景层透明度
        lp.dimAmount = 0.2f;
        getWindow().setAttributes(lp);
        initData();
    }

    protected <E extends View> E F(@IdRes int viewId) {
        return (E) super.findViewById(viewId);
    }

    protected abstract void initData();

    /**
     * 点击外部是否消失
     */
    public boolean setIsCanceledOnTouchOutside() {
        return isCanceledOnTouchOutside;
    }

    /**
     * 点击返回键取消
     */
    public boolean setIsCancelKeyDown() {
        return isCancelKeyDown;
    }


    public abstract int setLayoutId();


    /**
     * 设置关闭监听
     */
    public BaseDailog setOnCloseListener(OnCloseListener listener) {
        this.mCloseListener = listener;
        return this;
    }

    public BaseDailog showDialog() {
        show();
        return this;
    }


    public void dismiss(boolean isConfirm) {
        super.dismiss();
        if (mCloseListener != null) {
            mCloseListener.onDailogClose(this, isConfirm);
        }
        if (bind != null)
            bind.unbind();
    }
}
