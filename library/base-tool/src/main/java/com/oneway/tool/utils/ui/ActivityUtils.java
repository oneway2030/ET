package com.oneway.tool.utils.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * 作者 oneway on 2018/8/24
 * 描述:
 * 参考链接:
 */
public class ActivityUtils {
    public ActivityUtils() {
    }

    public static Activity findActivity(View view) {
        if (view == null) {
            return null;
        } else {
            View targetView;
            for (targetView = view; !(targetView.getContext() instanceof Activity); targetView = (View) targetView.getParent()) {
                if (!(targetView.getParent() instanceof View)) {
                    return null;
                }
            }

            return (Activity) targetView.getContext();
        }
    }

    public static boolean isActivityContext(Context context) {
        return context instanceof Activity;
    }
}
