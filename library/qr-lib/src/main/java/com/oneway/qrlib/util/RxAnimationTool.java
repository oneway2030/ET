package com.oneway.qrlib.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;


/**
 * @author Vondear
 * @date 2017/3/15
 */

public class RxAnimationTool {

    public static void ScaleUpDowm(View view) {
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.RESTART);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(1200);
        view.startAnimation(animation);
    }

}
