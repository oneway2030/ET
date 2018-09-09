package com.oneway.tool.loader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.oneway.tool.ToolConfig;
import com.oneway.tool.utils.ui.UiUtils;

import java.io.File;

/**
 * Glide 详细使用参考下面
 * https://mrfu.me/2016/02/27/Glide_Getting_Started/
 * https://muyangmin.github.io/glide-docs-cn/
 * 圆形 渲染库  https://github.com/wasabeef/glide-transformations
 * jp.wasabeef:glide-transformations:2.0.2
 */
public class GlideLoader implements ILoader {
    private String asset_paht = "file:///android_asset/";

    @Override
    public void init(Context context) {
        try {
            Class.forName("com.bumptech.glide.Glide");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Must be dependencies Glide!");
        }
    }

    private RequestManager getRequestManager() {
        return Glide.with(ToolConfig.getContext());
    }

    @Override
    public void load(String url, ImageView target) {
        getRequestManager().load(url).into(target);
    }

    @Override
    public void load(String url, ImageView target, RequestOptions options) {
        getRequestManager()
                .load(url)
                .apply(options)
                .into(target);
    }

    @Override
    public void loadCorner(String url, ImageView target, int corners) {
        RoundCornersTransformation transformation =
                new RoundCornersTransformation(ToolConfig.getContext(), UiUtils.dp2px(5), RoundCornersTransformation.CornerType.ALL);
        RequestOptions options = new RequestOptions()
                .transform(transformation);
        getRequestManager()
                .load(url)
                .apply(options)
                .into(target);
    }

    @Override
    public void loadCorner(String url, ImageView target, int corners, RequestOptions options) {
        RoundCornersTransformation transformation =
                new RoundCornersTransformation(ToolConfig.getContext(), UiUtils.dp2px(5), RoundCornersTransformation.CornerType.ALL);
        options.transform(transformation);
        getRequestManager()
                .load(url)
                .apply(options)
                .into(target);
    }

    @Override
    public void loadRound(String url, ImageView target) {
        getRequestManager()
                .load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(target);
    }

    @Override
    public void loadRound(String url, ImageView target, int def_icon) {
        RequestOptions requestOptions = RequestOptions.circleCropTransform()
                .error(def_icon)
                .placeholder(def_icon);
        getRequestManager()
                .load(url)
                .apply(requestOptions)
                .into(target);
    }

    @Override
    public void loadResource(int resId, ImageView target, RequestOptions options) {
        getRequestManager()
                .load(target)
                .apply(options)
                .into(target);
    }


    @Override
    public void loadAssets(String assetName, ImageView target, RequestOptions options) {
        getRequestManager()
                .load(asset_paht + assetName)
                .apply(options)
                .into(target);
    }

    @Override
    public void loadFile(File file, ImageView target, RequestOptions options) {
        getRequestManager()
                .load(file)
                .apply(options)
                .into(target);
    }

    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }


}
