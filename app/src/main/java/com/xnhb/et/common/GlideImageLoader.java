package com.xnhb.et.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.model.Response;
import com.oneway.tool.loader.ImageLoaderManager;
import com.xnhb.et.R;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.youth.banner.loader.ImageLoader;

import java.io.ByteArrayOutputStream;

/**
 * Created by 阿禹 on 2017/12/28.
 * GlideImageLoader banner类
 */

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
//        ImageLoaderManager.getLoader().load(path, imageView);
        if (path instanceof String) {
            String id = (String) path;
            getBitmap(imageView, id);
        }
    }

    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.mipmap.banner_bg);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return super.createImageView(context);
    }

    public void getBitmap(ImageView imageView, String id) {
        OkGoHelper.<Bitmap>getOkGo(Api.DOWNLOAD_IMAGE, this)
                .params("id", id)
                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(Response<Bitmap> response) {
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        imageView.setImageBitmap(response.body());
                    }
                });


    }


}
