package com.xnhb.et.ui.ac.setting;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.dialog.HintDialog;
import com.oneway.ui.dialog.TipsDialog;
import com.oneway.ui.dialog.base.OnCloseListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.CommomHorizontalLayout;
import com.xnhb.et.R;
import com.xnhb.et.helper.UserInfoHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 oneway on 2018/10/24
 * 描述: 开启手势解锁界面
 * 参考链接:
 */
public class SettingGestureLockActivity extends BaseTitleActivity {

    @BindView(R.id.item_enable)
    CommomHorizontalLayout itemEnable;
    @BindView(R.id.item_close)
    CommomHorizontalLayout itemClose;

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SettingGestureLockActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected String getTitleText() {
        return "手势";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_setting_gesture_lock;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        itemEnable.setOnClickListener(mPerfectClickListener);
        itemClose.setOnClickListener(mPerfectClickListener);
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.item_enable) {
                GestureLockActivity.launch(SettingGestureLockActivity.this, GestureLockActivity.RESET_PWD);
            } else if (id == R.id.item_close) {//关闭
                new TipsDialog(SettingGestureLockActivity.this, "是否取消交易?", new OnCloseListener() {
                    @Override
                    public void onDailogClose(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            UserInfoHelper.getInstance().unEnabledGestureLock();
                            ToastManager.info("手势解锁已关闭");
                            finish();
                        }
                    }
                }).showDialog();

            }
        }
    };


}
