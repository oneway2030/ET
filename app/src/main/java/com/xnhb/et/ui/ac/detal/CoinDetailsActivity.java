package com.xnhb.et.ui.ac.detal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.in.TitleContainer;
import com.oneway.ui.base.title.RightViewType;
import com.oneway.ui.base.title.TitleLayoutHelper;
import com.xnhb.et.R;

/**
 * 作者 oneway on 2018/10/20
 * 描述: 币详情
 * 参考链接:
 */
public class CoinDetailsActivity extends BaseTitleActivity {
    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CoinDetailsActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected String getTitleText() {
        return "ET/ECNY";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_coin_details;
    }

    @Override
    protected void customTitle(TitleContainer toolbar) {
        TitleLayoutHelper titleLayoutHelper = new TitleLayoutHelper();
        titleLayoutHelper.setImageLayout(toolbar, this, new TitleLayoutHelper.RightViewClickListener() {
            @Override
            public void onRightViewClickListener(View view, RightViewType Type) {
                CoinBillActivity.launch(CoinDetailsActivity.this);
            }
        }, R.mipmap.bill);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
