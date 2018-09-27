package com.xnhb.et.ui.ac.bill;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.oneway.ui.base.ac.BaseTitleActivity;
import com.xnhb.et.R;

/**
 * 作者 oneway on 2018/9/27
 * 描述: 充值记录详细信息
 * 参考链接:
 */
public class HistoricalDetailsActivity extends BaseTitleActivity {

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, HistoricalDetailsActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected String getTitleText() {
        return "充值记录详细信息";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_historical_details;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
