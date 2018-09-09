package com.xnhb.et.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.xnhb.et.R;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.Setting;

import java.util.List;

/**
 * 作者 oneway on 2018/8/14
 * 描述:
 * 参考链接:
 */
public class PermissionHelper {

    /**
     * 请求权限不管回调
     */
    public void requestPermission(final Context content, String... permissions) {
        AndPermission.with(content)
                .runtime()
                .permission(permissions)
                .rationale(new RuntimeRationale())
                .start();
    }

    /**
     * 请求权限需要回掉
     */
    public void requestPermission(final Context content, Action<List<String>> granted, Action<List<String>> denied, String... permissions) {
        AndPermission.with(content)
                .runtime()
                .permission(permissions)
                .rationale(new RuntimeRationale())
                .onGranted(granted)
                .onDenied(denied)
                .start();
    }

    /**
     * 请求权限只要成功回调, 失败启用默认dialog提示 跳转设置界面
     */
    public void requestPermission(final Context content, Action<List<String>> granted, String... permissions) {
        AndPermission.with(content)
                .runtime()
                .permission(permissions)
                .rationale(new RuntimeRationale())
                .onGranted(granted)
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(content, permissions)) {
                            showSettingDialog(content, permissions, null, null);
                        }
                    }
                })
                .start();
    }

    /**
     * 弹出跳转app
     */
    public void showSettingDialog(final Context context, final List<String> permissions, final DialogInterface.OnClickListener listener, final Setting.Action comeback) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_always_failed, TextUtils.join("\n", permissionNames));

        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle("提示")
                .setMessage(message)
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPermission(context, comeback);
                    }
                })
                .setNegativeButton("取消", listener)
                .show();
    }

    /**
     * Set permissions.
     */
    public void setPermission(Context context, Setting.Action comeback) {
        AndPermission.with(context)
                .runtime()
                .setting()
                .onComeback(comeback)//用户从设置界面返回监听
                .start();
    }


    public class RuntimeRationale implements Rationale<List<String>> {

        @Override
        public void showRationale(Context context, List<String> permissions, final RequestExecutor executor) {
            List<String> permissionNames = Permission.transformText(context, permissions);
            String message = context.getString(R.string.message_permission_rationale, TextUtils.join("\n", permissionNames));

            new AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setTitle("提示")
                    .setMessage(message)
                    .setPositiveButton("继续", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            executor.execute();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            executor.cancel();
                        }
                    })
                    .show();
        }
    }

}
