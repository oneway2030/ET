package com.oneway.ui.util;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

/**
 * 作者 oneway on 2018/7/11
 * 描述: 屏幕适配方案
 * 参考链接: https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA
 */
public class ScreenUtil {
    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;
    //自己设计图片的尺寸 dip尺寸 我這里是720的图纸
    private static int DEF_WIDTH = 360;

    /**
     * 在Activity中的onCreate中调用
     * 默认图纸按照720px & 360dpi进行计算
     */
    public static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application) {
        setCustomDensity(activity, application, DEF_WIDTH);
    }

    /**
     * 在Activity中的onCreate中调用
     *
     * @param designWith 设计图纸的大小
     */
    public static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application, int designWith) {
        DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        float targetDensity = appDisplayMetrics.widthPixels / designWith;
        float targetScaleDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        int targetDensityDpi = (int) (160 * targetDensity);
        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaleDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }


}
