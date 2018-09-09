package com.oneway.ui.widget.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.oneway.tool.utils.common.TimeUtils;
import com.oneway.tool.utils.log.LogUtil;
import com.oneway.ui.R;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.util.PageStateHelper;
import com.oneway.ui.widget.status.OnRetryListener;
import com.oneway.ui.widget.status.StatusLayoutManager;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by ww on 2016/10/29.
 * 描述: WebView等简单封装
 * 参考链接:
 * 两种模式:
 * 1.显示系统原生的pb进度条. 错误页面的时候,
 * 使用  FAILING_URL 替代系统的错误页面
 * 点击重新加载的时候, 先显示空页面 EMPTY_URL 和系统pb然后加载
 * 2. 显示statePage 的加载进度和错误页面
 * 显示错误页面的时候 使用 showErrorView替代,并使用 EMPTY_URL 替换web的错误页面
 */
public class WebLayout extends RelativeLayout implements OnRetryListener {
    private static final String APP_CACAHE_DIRNAME = "/webcache";
    private final String FAILING_URL = "file:///android_asset/error.html";
    private final String EMPTY_URL = "file:///android_asset/empty.html";
    private long TIME_OUT = 40 * 1000; //默认40秒超时
    private Context mContext;
    private Activity mActivity; //是否
    private RelativeLayout mContent;
    private ProgressBar mWebPb;
    private WebView mWebView;
    private WebSettings mWebSettings;
    private boolean isEnableProgressBar = true;  //是否显示加载进度条 默认显示  为false时候mWebPb与status的lading都不显示
    private boolean isShowPb = true;  //是否显示mWebPb, 如果不显示mWebPb则使用 status的lading页面样式
    //    private boolean isBackPressed = true; //按back是否返回上一级页面  此控件没有titile 不影响左上角返回回
    private boolean isPost = false; //默认get请求
    private String mTitle;  //web里的标题
    private String url;    //请求连接地址
    private String urlParameter;  //post请求的参数
    private boolean isInit = false;// 是否初始化
    private MyWebViewClient l;   //加载状态回调监听
    private ProgressListener mProgressListener;  //加载进度监听
    private PageStateHelper mPageStateHelper;
    private boolean isErrorCallBack = false;    //错误回掉
    private boolean isEmptyCallBack = false;    //空页面的回掉
    private boolean isErrorAndEmptyCallBack = false;  //在状态页面加载失败, 会设置一个空页面覆盖调web的错误页面

    public WebLayout(Context context) {
        this(context, null);
    }

    public WebLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public int getLayoutId() {
        return R.layout.base_web_layout;
    }

    /**
     * 校验初始化
     */
    public void checkInit() {
        if (!isInit) {
            init();
        }
    }

    private void init() {
        if (isInit) { //已经初始化过就不用初始化
            return;
        }
        View inflate = View.inflate(mContext, getLayoutId(), this);
        mContent = inflate.findViewById(R.id.content);
        mWebPb = inflate.findViewById(R.id.web_pb);
        mWebView = inflate.findViewById(R.id.webview);
        initSettings();
        isInit = true;
    }

    private void initSettings() {
        WebViewHelper mWebViewHelper = new WebViewHelper();
        mWebSettings = mWebViewHelper.initSettings(mWebView, mContext);
        mWebView.setWebChromeClient(mWebChromeClient);
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setDownloadListener(mDownloadListener);
        mWebView.addJavascriptInterface(new JavaScriptObject(mContext, this), "jsinterface");
    }


    private WebChromeClient mWebChromeClient = new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (mProgressListener != null) {
                mProgressListener.OnProgress(view, newProgress);
            }
            //显示加载
            showPb(newProgress);
        }


        //权限设置
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }


        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            mTitle = title;
        }

    };

    /**
     * 显示加载进度
     *
     * @param newProgress
     */
    private void showPb(int newProgress) {
        if (isEnableProgressBar) {
            if (isShowPb) {
                //显示pb
                nativeProgressChanged(newProgress);
            } else {
                stateProgressChanged(newProgress);
            }
        } else {
            mWebPb.setVisibility(View.GONE);
            removeStatePage();
        }
    }

    /**
     * 显示系统进度条
     *
     * @param newProgress
     */
    private void nativeProgressChanged(int newProgress) {
        if (newProgress == 100) {
            mWebPb.setVisibility(View.GONE);
        } else {
            mWebPb.setProgress(newProgress);
        }
    }

    /**
     * 显示状态进度条
     */
    private void stateProgressChanged(int newProgress) {
        initStatePage();
        if (!isShowPb && newProgress >= 40 && !isErrorCallBack && !isErrorAndEmptyCallBack) {
            showContentView();
        }
    }

    /**
     * 初始化状态页面
     */
    public void initStatePage() {
        if (mPageStateHelper == null) {
            mPageStateHelper = new PageStateHelper(mContext, mContent, WebLayout.this);
            mPageStateHelper.showLoadingView();
        }
    }


    private WebViewClient mWebViewClient = new WebViewClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            LogUtil.i("重定向:   " + url);
            //TODO 这里拦截一些不必要的重定向
            if (!TextUtils.isEmpty(url))
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                    return false;
                }
            // se allow the OS to handle things like tel, mailto, etc.
            if (mActivity != null && !mActivity.isFinishing()) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    mActivity.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        long nowMills;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            //开始加载的准备工作
            isErrorCallBack = false;
            nowMills = TimeUtils.getNowMills();
            if (l != null) {
                l.onPageStarted();
            }
            progressStarted();
        }


        // 网页加载完成
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (!isEmptyCallBack || !isErrorAndEmptyCallBack) {
                if (l != null) {
                    l.onPageFinished();
                }
                hideLoadProgress();
                long endMills = TimeUtils.getNowMills();
                if (endMills - nowMills >= TIME_OUT) {
                    ToastManager.error("请求超时");
                    showErrorView();
                }
            }
            if (isEmptyCallBack) {
                if (EMPTY_URL.equals(url)) {
                    isEmptyCallBack = false;
                    reload();
                    return;
                }
            }
            if (isErrorAndEmptyCallBack) {
                if (EMPTY_URL.equals(url)) {
                    isErrorAndEmptyCallBack = false;
                    showErrorView();
                    return;
                }
            }
            //隐藏广告
//            removeAd();
        }


        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            isErrorCallBack = true;
            if (l != null) {
                l.onReceivedError();
            }
            //如果加载的地址错误会造成重复回掉onReceivedError, 所以这里判断一下, 是否是本地配置错误造成的错误
            if (!FAILING_URL.equals(failingUrl)) {
                if (isShowPb) {
                    view.loadUrl(FAILING_URL);
                } else {
                    //系统的界面太丑.先把界面置为空, 在显示自定义错误页面
                    isErrorAndEmptyCallBack = true;
                    view.loadUrl(EMPTY_URL);
                }
                mWebView.clearHistory();
            }
        }


    };

    /**
     * 进度条初始化
     */
    private void progressStarted() {
        if (isEnableProgressBar) {
            mWebPb.setVisibility(isShowPb ? View.VISIBLE : View.GONE);
            if (isShowPb) {
                mWebPb.setMax(100);
            } else {
                initStatePage();
                showLoadingView();
            }
        } else {
            //不显示加载
            mWebPb.setVisibility(View.GONE);
            removeStatePage();
        }
    }

    DownloadListener mDownloadListener = new DownloadListener() {
        @Override
        public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
            if (mActivity != null && !mActivity.isFinishing()) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(s));
                mActivity.startActivity(intent);
            }
        }
    };

    private void hideLoadProgress() {
        if (isShowPb) {
            Animation hiddenAction = new AlphaAnimation(1, 0);
            hiddenAction.setDuration(1000);
            mWebPb.setAnimation(hiddenAction);
            mWebPb.setVisibility(View.GONE);
        } else {
            if (!isErrorCallBack) {
                showContentView();
            }
        }
    }


    /**
     * 隐藏淘宝天猫的广告
     */
    private void removeAd() {
    }

    private void setTimeOut(long timeout) {
        this.TIME_OUT = timeout * 1000;
    }


    /**
     * 设置进度样式
     *
     * @param isShowPb 默认为true 系统的Progress
     *                 false为status 的加载样式 进度在40才会显示内容
     */
    public void setProgressSytle(boolean isShowPb) {
        this.isShowPb = isShowPb;
    }

    /**
     * 是否显示加载进度条
     *
     * @param isEnable 默认true   显示加载进度
     *                 为false时候mWebPb与status的lading都不显示
     */
    public void setEnableProgress(boolean isEnable) {
        this.isEnableProgressBar = isEnable;
    }


    public WebView getWebView() {
        checkInit();
        return mWebView;
    }

    public PageStateHelper getPageStateHelper() {
        if (mPageStateHelper == null) {
            initStatePage();
        }
        return mPageStateHelper;
    }

    public WebSettings getWebSetting() {
        checkInit();
        return mWebSettings;
    }

    /**
     * @param ac 传入ac 则 启用跳转系统服务
     *           如 下载跳转系统下载  从webview启动app等 外连服务
     */
    public void isEnableJumpSystemService(Activity ac) {
        this.mActivity = ac;
    }


    /**
     * get请求
     *
     * @param url
     */
    public void LoadUrl(String url) {
        isPost = false;
        this.url = url;
        checkInit();
        Map<String, String> extraHeaders = new HashMap<String, String>();
        extraHeaders.put("Cache-Control", "max-age=3600"); //设置缓存
        mWebView.loadUrl(url, extraHeaders);
    }

    /**
     * post请求
     *
     * @param url
     * @param urlParameter 为null的时候 post方法
     *                     例如参数 "txtName=zzz&QueryTypeLst=1&CertificateTxt=dsds";
     */
    public void LoadUrl(String url, String urlParameter) {
        isPost = true;
        this.url = url;
        this.urlParameter = urlParameter;
        checkInit();
        if (urlParameter == null) {
            ToastManager.error("错误:参数为空");
            showEmptyView();
        } else {
            mWebView.postUrl(url, urlParameter.getBytes());
        }
    }

    public void showLoadingView() {
        if (mPageStateHelper != null) {
            mPageStateHelper.showLoadingView();
        } else {
            mPageStateHelper = new PageStateHelper(mContext, mContent, WebLayout.this);
            showLoadingView();
        }
    }

    public void showContentView() {
        if (mPageStateHelper != null)
            mPageStateHelper.showContentView();
    }

    public void showEmptyView() {
        if (mPageStateHelper != null) {
            mPageStateHelper.showEmptyView();
        } else {
            mPageStateHelper = new PageStateHelper(mContext, mContent, WebLayout.this);
            showEmptyView();
        }
    }

    public void showErrorView() {
        if (mPageStateHelper != null) {
            mPageStateHelper.showErrorView();
        } else {
            mPageStateHelper = new PageStateHelper(mContext, mContent, WebLayout.this);
            showErrorView();
        }
    }

    public void removeStatePage() {
        if (mPageStateHelper != null) {
            mPageStateHelper.remove();
        }
    }


    /**
     * 获得web里的标题
     *
     * @param defTitleStr 默认标题
     */
    public String getTitle(String defTitleStr) {
        checkInit();
        return TextUtils.isEmpty(mTitle) ? defTitleStr : mTitle;
    }


    /**
     * 错误页面的点击回调
     */
    @Override
    public void onRetry(int type) {
        progressStarted();
        //显示空页面
        isEmptyCallBack = true;
        mWebView.loadUrl(EMPTY_URL);
    }

    /**
     * 重新加载
     */
    public void reload() {
        if (isPost) {
            LoadUrl(url, urlParameter);
        } else {
            LoadUrl(url);
        }
    }

    /**
     * 加载进度监听
     */
    public void setOnMyWebViewClient(MyWebViewClient l) {
        this.l = l;
    }

    /**
     * 加载状态回调监听
     */
    public void setProgressListener(ProgressListener mProgressListener) {
        this.mProgressListener = mProgressListener;
    }

    private StatusLayoutManager mStatusLayoutManager;


    /**
     * 加载进度监听
     */
    public interface ProgressListener {
        void OnProgress(View v, int progress);
    }

    /**
     * web加载状态回调监听
     */
    public interface MyWebViewClient {
        void onPageStarted();

        void onPageFinished();

        void onReceivedError();
    }


    /**
     * 设置回退到上一页
     *
     * @return 回退了返回true 没有上一页返回false
     */
    public boolean setBackPressed() {
        if (FAILING_URL.equals(mWebView.getUrl()) || EMPTY_URL.equals(mWebView.getUrl())) {
            return false;
        }
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return false;
    }

    /**
     * 销毁
     */
    public void onDestroy() {
        if (mWebView != null) {
            mActivity = null;
            mWebView.loadUrl("about:blank");
            mWebView.setWebChromeClient(null);
            mWebView.setWebViewClient(null);
            mWebView.destroy();
            mWebView = null;
        }
    }

}
