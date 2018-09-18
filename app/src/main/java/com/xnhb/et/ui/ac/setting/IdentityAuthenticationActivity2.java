package com.xnhb.et.ui.ac.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.oneway.tool.loader.ImageLoaderManager;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.base.ac.ActivityManager;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.btn.StateButton;
import com.xnhb.et.R;
import com.xnhb.et.common.GlideImageLoader;
import com.xnhb.et.helper.AlbumHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author oneway
 * @describe 身份认证 上传身份证界面
 * @since 2018/9/15 0015
 */


public class IdentityAuthenticationActivity2 extends BaseTitleActivity {
    public final static int CHOOSE_REQUEST1 = 9001;  //选择反面身份证照片
    public final static int CHOOSE_REQUEST2 = 9002; //选择正面身份证照片
    public final static int CHOOSE_REQUEST3 = 9003; //选择手持身份证照片
    @BindView(R.id.iv_idcard1)
    ImageView ivIdcard1;
    @BindView(R.id.iv_idcard2)
    ImageView ivIdcard2;
    @BindView(R.id.iv_idcard3)
    ImageView ivIdcard3;
    @BindView(R.id.btn_submit)
    StateButton btnSubmit;
    private String name;
    private String idCard;
    String image_path_other_side; //反面照片
    String image_path_positive;  //正面照片
    String image_path_hold_idcard;//手持照片

    public static void launch(Context context, String name, String idCard) {
        Intent intent = new Intent();
        intent.setClass(context, IdentityAuthenticationActivity2.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra("name", name);
        intent.putExtra("idCard", idCard);
        context.startActivity(intent);
    }

    @Override
    protected boolean getIntent(Intent intent) {
        name = intent.getStringExtra("name");
        idCard = intent.getStringExtra("idCard");
        return EmptyUtils.isEmpty(name) || EmptyUtils.isEmpty(idCard);
    }

    @Override
    protected String getTitleText() {
        return "身份认证";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_identity_authentication2;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        ivIdcard1.setOnClickListener(mPerfectClickListener);
        ivIdcard2.setOnClickListener(mPerfectClickListener);
        ivIdcard3.setOnClickListener(mPerfectClickListener);
        btnSubmit.setOnClickListener(mPerfectClickListener);
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_submit) {//提交审核
                if (EmptyUtils.isEmpty(image_path_other_side)) {
                    ToastManager.info(R.string.hint_idcard_select1);
                    return;
                }
                if (EmptyUtils.isEmpty(image_path_positive)) {
                    ToastManager.info(R.string.hint_idcard_select2);
                    return;
                }
                if (EmptyUtils.isEmpty(image_path_hold_idcard)) {
                    ToastManager.info(R.string.hint_idcard_select3);
                    return;
                }
                submit();
            } else if (id == R.id.iv_idcard1) { //选择反面身份证照片
                image_path_other_side = "";
                AlbumHelper.getInstance().checkPermissionAndOpenSingleAlbumAndCamera(IdentityAuthenticationActivity2.this, CHOOSE_REQUEST1);
            } else if (id == R.id.iv_idcard2) {   //选择正面身份证照片
                image_path_positive = "";
                AlbumHelper.getInstance().checkPermissionAndOpenSingleAlbumAndCamera(IdentityAuthenticationActivity2.this, CHOOSE_REQUEST2);
            } else if (id == R.id.iv_idcard3) {//选择手持身份证照片
                image_path_hold_idcard = "";
                AlbumHelper.getInstance().checkPermissionAndOpenSingleAlbumAndCamera(IdentityAuthenticationActivity2.this, CHOOSE_REQUEST3);
            }
        }

    };

    private void submit() {
//         String name;
//         String idCard;
//        String image_path_other_side; //反面照片
//        String image_path_positive;  //正面照片
//        String image_path_hold_idcard;//手持照片
        //TODO 上传照片 到服务器
        ToastManager.success("上传成功");
        //关闭 两个身份认证界面 并刷新 设置界面
        ActivityManager.getInstance().removeActivity(IdentityAuthenticationActivity2.class);
        ActivityManager.getInstance().removeActivity(this);
        //TODO  去刷新设置界面的显示状态
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            String cutPath = selectList.get(0).getCutPath();
            switch (requestCode) {
                // 图片、视频、音频选择结果回调
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                case CHOOSE_REQUEST1://反面照片
                    image_path_other_side = selectList.get(0).getPath();
                    ImageLoaderManager.getLoader().load(image_path_other_side, ivIdcard1);
                    break;
                case CHOOSE_REQUEST2://正面照片
                    image_path_positive = selectList.get(0).getPath();
                    ImageLoaderManager.getLoader().load(image_path_positive, ivIdcard2);
                    break;
                case CHOOSE_REQUEST3://手持照片
                    image_path_hold_idcard = selectList.get(0).getPath();
                    ImageLoaderManager.getLoader().load(image_path_hold_idcard, ivIdcard3);
                    break;
                default:

            }
        }
    }
}
