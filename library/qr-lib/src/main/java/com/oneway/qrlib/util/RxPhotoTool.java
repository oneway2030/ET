package com.oneway.qrlib.util;

import android.app.Activity;
import android.content.Intent;

/**
 * 作者 oneway on 2018/9/28
 * 描述:
 * 参考链接:
 */
public class RxPhotoTool {
    public static final int GET_IMAGE_FROM_PHONE = 5002;

    public static void openLocalImage(final Activity activity) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intent, GET_IMAGE_FROM_PHONE);
    }
}
