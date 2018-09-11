package com.oneway.tool.loader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.oneway.tool.ToolConfig;

import java.io.File;

/**
 * @Description: 图片加载接口
 * @author: <a href="http://xiaoyaoyou1212.360doc.com">DAWI</a>
 * @date: 2016-12-19 15:04
 */
public interface ILoader {
    void init(Context context);

    void load(Object url, ImageView target);

    void load(Object url, ImageView target, RequestOptions options);

    void loadCorner(Object url, ImageView target, int corners);

    void loadCorner(Object url, ImageView target, int corners, RequestOptions options);

    void loadRound(Object url, ImageView target);

    void loadRound(Object url, ImageView target, int def_icon);

    void loadAssets(String assetName, ImageView target, RequestOptions options);

    void loadFile(File file, ImageView target, RequestOptions options);

    void clearMemoryCache(Context context);

    void clearDiskCache(Context context);

    class Options {

        public static final int RES_NONE = -1;
        public int loadingResId = RES_NONE;//加载中的资源id
        public int loadErrorResId = RES_NONE;//加载失败的资源id

        public static Options defaultOptions() {
            return new Options(ToolConfig.IL_LOADING_RES, ToolConfig.IL_ERROR_RES);
        }

        public Options(int loadingResId, int loadErrorResId) {
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
        }
    }
}
