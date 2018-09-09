package com.oneway.ui.widget.webview;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 作者 oneway on 2018/7/5
 * 描述:
 * 参考链接:
 */
public class WebViewHelper {


    public WebSettings initSettings(WebView webView, Context context) {
        //TODO 经测试 只有硬件解码  才能播放视频
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//软件解码
        }
        webView.setHorizontalScrollBarEnabled(false);//水平不显示
        webView.setVerticalScrollBarEnabled(false); //垂直不显示
        webView.setSaveEnabled(true);
        WebSettings webSettings = webView.getSettings();
        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);//图片自动缩放 打开
        } else {
            webSettings.setLoadsImagesAutomatically(false);//图片自动缩放 关闭
        }
//        webSetting.setTextSize(WebSettings.TextSize.NORMAL);
        webSettings.setSupportZoom(true);// 设置可以支持缩放
        webSettings.setBuiltInZoomControls(true);//启用缩放
        webSettings.setDisplayZoomControls(false);//隐藏自带缩放图标

        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);//适应分辨率
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true); //是否开启本地DOM存储  鉴于它的安全特性（任何人都能读取到它，尽管有相应的限制，将敏感数据存储在这里依然不是明智之举），Android 默认是关闭该功能的。
        webSettings.setGeolocationEnabled(true);
        webSettings.setSavePassword(true);//保存密码
        // 设置可以使用localStrorage
        webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setAllowFileAccess(true);
        webSettings.setDatabaseEnabled(true);
        //开启定位权限
        webSettings.setGeolocationEnabled(true);
        //设置数据库缓存路径
        String databaseDir = context.getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setDatabasePath(databaseDir);
        //设置  Application Caches 缓存目录
        String cacheDir = context.getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(cacheDir);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setNeedInitialFocus(true);
        if (Build.VERSION.SDK_INT < 19) {
            if (Build.VERSION.SDK_INT > 8) {
                //是否启用插件
                webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            }
        }
        return webSettings;
    }


}
