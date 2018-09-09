package com.oneway.ui.dialog.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.oneway.ui.R;

/**
 * Created by wanwei on 2018/5/22.
 */

public abstract class BaseDailog extends Dialog {
    protected boolean isCanceledOnTouchOutside = true;
    protected boolean isCancelKeyDown = true;
    protected OnCloseListener mCloseListener;
    protected Window mWindow;

    public BaseDailog(@NonNull Context context) {
        super(context, R.style.Custom_Progress);
    }

    public BaseDailog(@NonNull Context context, boolean cancelable, OnCloseListener listener) {
        super(context);
        this.isCancelKeyDown = cancelable;
        this.mCloseListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        init();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        mWindow = getWindow();
        // 设置背景层透明度
        lp.dimAmount = 0.2f;
        getWindow().setAttributes(lp);
    }

    protected void init() {
        //点击外部关闭
        setCanceledOnTouchOutside(isCanceledOnTouchOutside);
        // 按返回键是否取消
        setCancelable(isCancelKeyDown);
        // 设置居中
        getWindow().getAttributes().gravity = Gravity.CENTER;
    }

    public abstract int initLayout();


    /**
     * 设置关闭监听
     */
    public BaseDailog setOnCloseListener(OnCloseListener listener) {
        this.mCloseListener = listener;
        return this;
    }


    public void dismiss(boolean isConfirm) {
        super.dismiss();
        if (mCloseListener != null) {
            mCloseListener.onDailogClose(this, isConfirm);
        }
    }
}
