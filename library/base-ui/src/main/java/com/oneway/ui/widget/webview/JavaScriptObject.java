package com.oneway.ui.widget.webview;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;

/**
 * 作者 oneway on 2018/7/5
 * 描述:
 * 参考链接:
 */
public class JavaScriptObject {
    Context mContext;
    WebLayout mCustomWebView;

    public JavaScriptObject(Context mContext, WebLayout customWebView) {
        this.mContext = mContext;
        this.mCustomWebView = customWebView;
    }

    @JavascriptInterface
    public void errorReload() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {

            @Override
            public void run() {
                mCustomWebView.onRetry(0);
            }
        });

    }

}
