package com.xnhb.et.helper;

import android.app.Activity;
import android.content.DialogInterface;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Setting;

import java.util.List;

/**
 * 作者 oneway on 2018/8/15
 * 描述:
 * 参考链接:
 */
public class AlbumHelper {

    public void checkPermissionAndOpenCamera(final Activity activity) {
        final PermissionHelper permissionHelper = new PermissionHelper();
        permissionHelper.requestPermission(activity, new Action<List<String>>() {
            @Override
            public void onAction(List<String> data) {
                openCamera(activity);
            }
        }, new Action<List<String>>() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(activity, permissions)) {
                    permissionHelper.showSettingDialog(activity, permissions, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //点击取消
                            try {
                                openCamera(activity);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Setting.Action() {
                        @Override
                        public void onAction() {
                            //从设置界面返回
                            try {
                                openCamera(activity);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE);


    }

    public void checkPermissionAndOpenAlbum(final Activity activity) {
        final PermissionHelper permissionHelper = new PermissionHelper();
        permissionHelper.requestPermission(activity, new Action<List<String>>() {
            @Override
            public void onAction(List<String> data) {
                openAlbum(activity);
            }
        }, new Action<List<String>>() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(activity, permissions)) {
                    permissionHelper.showSettingDialog(activity, permissions, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //点击取消
                            try {
                                openAlbum(activity);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Setting.Action() {
                        @Override
                        public void onAction() {
                            //从设置界面返回
                            try {
                                openAlbum(activity);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, Permission.WRITE_EXTERNAL_STORAGE);
    }

    public void checkPermissionAndOpenAlbumAndCamera(final Activity activity, final List<LocalMedia> selectionMedia) {
        final PermissionHelper permissionHelper = new PermissionHelper();
        permissionHelper.requestPermission(activity, new Action<List<String>>() {
            @Override
            public void onAction(List<String> data) {
                openAlbumAndCamera(activity,selectionMedia);
            }
        }, new Action<List<String>>() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(activity, permissions)) {
                    permissionHelper.showSettingDialog(activity, permissions, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //点击取消
                            try {
                                openAlbumAndCamera(activity,selectionMedia);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Setting.Action() {
                        @Override
                        public void onAction() {
                            //从设置界面返回
                            try {
                                openAlbumAndCamera(activity,selectionMedia);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 打开相机
     */
    private void openCamera(Activity activity) {
        // 单独拍照
        PictureSelector.create(activity)
                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
//                .theme(R.style.picture_white_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .isCamera(true)// 是否显示拍照按钮
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(true)// 是否圆形裁剪
                .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    /**
     * 打开相册 不带相机
     */
    private void openAlbum(Activity activity) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                .theme(R.style.picture_white_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .previewImage(false)// 是否可预览图片
                .isCamera(false)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }


    /**
     * 打开相册带相机
     */
    private void openAlbumAndCamera(Activity activity, List<LocalMedia> selectionMedia) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                .theme(R.style.picture_white_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .imageSpanCount(4)// 每行显示个数
                .maxSelectNum(6)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(false)// 是否可预览图片
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
                .selectionMedia(selectionMedia)// 是否传入已选图片
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

}
