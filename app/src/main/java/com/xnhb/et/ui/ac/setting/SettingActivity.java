package com.xnhb.et.ui.ac.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.luck.picture.lib.tools.ToastManage;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.in.TitleContainer;
import com.oneway.ui.toast.ToastManager;
import com.xnhb.et.MainActivity;
import com.xnhb.et.R;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class SettingActivity extends BaseTitleActivity {
    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SettingActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected String getTitleText() {
        return "设置";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    //自定义toolbar 重写改方法
    protected void customTitle(TitleContainer toolbar) {
        toolbar.setBackImage(R.mipmap.setting);
        toolbar.getLfteView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastManager.info("点击了");
            }
        });
    }
}
