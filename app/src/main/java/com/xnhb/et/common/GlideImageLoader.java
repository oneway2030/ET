package com.xnhb.et.common;

import android.content.Context;
import android.widget.ImageView;

import com.oneway.tool.loader.ImageLoaderManager;
import com.xnhb.et.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by 阿禹 on 2017/12/28.
 * GlideImageLoader banner类
 */

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ImageLoaderManager.getLoader().load(path, imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.mipmap.banner_bg);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return super.createImageView(context);
    }
}
