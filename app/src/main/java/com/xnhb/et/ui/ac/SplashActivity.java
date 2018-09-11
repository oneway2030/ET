package com.xnhb.et.ui.ac;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.oneway.ui.base.ac.BaseStatusBarActivity;
import com.xnhb.et.MainActivity;
import com.xnhb.et.R;

/**
 * 作者 oneway on 2018/9/6
 * 描述: 开屏界面
 * 参考链接:
 */
public class SplashActivity extends BaseStatusBarActivity {

    @Override
    protected void setStatusBar() {

    }

    @Override
    protected int bindView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        adTask();
    }

    private void adTask() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toNext();
            }
        }, 1000);
    }

    private void toNext() {
        MainActivity.launch(this);
        finish();
    }
}
