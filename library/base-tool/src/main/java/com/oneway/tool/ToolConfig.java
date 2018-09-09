package com.oneway.tool;


import android.content.Context;

import com.oneway.tool.loader.ILoader;
import com.oneway.tool.router.Router;

/**
 * Created by wanglei on 2016/12/4.
 */

public class ToolConfig {
    private static Context mContext;
    /**
     * #router
     * 配置路由的动画
     * 默认无动画
     */
    public static int ROUTER_ANIM_ENTER = Router.RES_NONE;   //进入
    public static int ROUTER_ANIM_EXIT = Router.RES_NONE;    //退出
    /**
     * #imageloader
     * 图片加载展位图片
     * 默认无站位
     */
    public static int IL_LOADING_RES = ILoader.Options.RES_NONE;  //正在加载
    public static int IL_ERROR_RES = ILoader.Options.RES_NONE;    //加载失败
    /**
     * #cache
     */
    public static final String CACHE_SP_NAME = "sp_cache";//默认SharedPreferences缓存文件名
    public static final String CACHE_DISK_DIR = "disk_cache";//默认磁盘缓存目录
    public static final String CACHE_HTTP_DIR = "http_cache";//默认HTTP缓存目录
    public static final long CACHE_NEVER_EXPIRE = -1;//永久不过期
//    // #log
//    public static final boolean LOG = true;
//    public static final String LOG_TAG = "XDroid";


//    // #dev model
//    public static final boolean DEV = true;

    private static ToolConfig instance;

    private ToolConfig() {
    }


    public static ToolConfig getInstance() {
        if (instance == null) {
            synchronized (ToolConfig.class) {
                if (instance == null) {
                    instance = new ToolConfig();
                }
            }
        }
        return instance;
    }

    public ToolConfig init(Context context) {
        mContext = context.getApplicationContext();
        return this;
    }

    public ToolConfig setRouterAnimation(int animEnterId, int animExitId) {
        this.ROUTER_ANIM_ENTER = animEnterId;
        this.ROUTER_ANIM_EXIT = animExitId;
        return this;
    }


    public ToolConfig setDefLoadImg(int loadImgId, int errorImgId) {
        this.IL_LOADING_RES = loadImgId;
        this.IL_ERROR_RES = errorImgId;
        return this;
    }

    public static Context getContext() {
        return mContext;
    }
}
