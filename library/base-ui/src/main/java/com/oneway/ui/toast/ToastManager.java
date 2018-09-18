package com.oneway.ui.toast;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.Toast;

import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.BaseUiConfig;

/**
 * 作者 oneway on 2018/9/7
 * 描述:
 * 参考链接:
 */
public class ToastManager {

    public static void success(String msg) {
        Toasty.success(BaseUiConfig.getContext(), msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void error(String msg) {
        Toasty.error(BaseUiConfig.getContext(), msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void info(String msg) {
        Toasty.info(BaseUiConfig.getContext(), msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void info(int strId) {
        Toasty.info(BaseUiConfig.getContext(), UiUtils.getString(strId), Toast.LENGTH_SHORT, true).show();
    }

    public static void warning(String msg) {
        Toasty.warning(BaseUiConfig.getContext(), msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void normal(String msg) {
        Toasty.normal(BaseUiConfig.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void normal(String msg, @DrawableRes int id) {
        Drawable icon = BaseUiConfig.getContext().getResources().getDrawable(id);
        Toasty.normal(BaseUiConfig.getContext(), msg, icon).show();
        Toasty.normal(BaseUiConfig.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
