package com.xnhb.et.net.okgo;

import android.app.Activity;
import android.content.DialogInterface;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.base.Request;
import com.oneway.tool.utils.ui.UiUtils;
import com.xnhb.et.constant.GlobalConfiguration;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by wanwei on 2018/5/2.
 */

public abstract class DialogCallback<T> extends JsonCallback<T> {

    public SweetAlertDialog mWaitDialog;
    private Activity mActivity;

    private void initDialog(Activity activity, boolean isLoading, boolean canCancel) {
        this.mActivity = activity;
        if (activity != null && isLoading) {
            mWaitDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE);
            mWaitDialog.getProgressHelper().setBarColor(UiUtils.getColor(GlobalConfiguration.PROGRESS_COLOR));
            mWaitDialog.setTitleText("加载中...");
            mWaitDialog.setCancelable(canCancel);
            mWaitDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    OkGo.getInstance().cancelTag(mActivity);
                }
            });
        }
    }


    public DialogCallback() {
        this(null, false, false);
    }

    /**
     * 只传activity 显示 dialog 可取消
     */
    public DialogCallback(Activity activity) {
        this(activity, true, true);
    }

    public DialogCallback(Activity activity, boolean isLoading) {
        this(activity, isLoading, true);
    }

    /**
     * @param activity  必传
     * @param canCancel true  可取消
     *                  false 不可取消
     * @param isLoading true  显示dialog
     *                  false 不显示dialog
     */
    public DialogCallback(Activity activity, boolean isLoading, boolean canCancel) {
        super();
        initDialog(activity, isLoading, canCancel);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if (mActivity != null && mWaitDialog != null && !mWaitDialog.isShowing()) {
            mWaitDialog.show();
        }
    }


    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        if (mWaitDialog != null && mWaitDialog.isShowing()) {
            mWaitDialog.dismiss();
        }
        mActivity = null;
    }

}
