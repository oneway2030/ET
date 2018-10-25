package com.xnhb.et.ui.ac.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.oneway.tool.utils.shape.DevShapeUtils;
import com.oneway.tool.utils.shape.shape.DevShape;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.in.TitleContainer;
import com.oneway.ui.base.title.RightViewType;
import com.oneway.ui.base.title.TitleLayoutHelper;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.widget.btn.StateButton;
import com.xnhb.et.R;
import com.xnhb.et.widget.dialog.BuyAndSellDailog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 oneway on 2018/10/20
 * 描述: 币详情
 * 参考链接:
 */
public class CoinDetailsActivity extends BaseTitleActivity {
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_range)
    TextView tvRange;
    @BindView(R.id.tv_price2)
    TextView tvPrice2;
    @BindView(R.id.tv_max)
    TextView tvMax;
    @BindView(R.id.tv_min)
    TextView tvMin;
    @BindView(R.id.tv_volume)
    TextView tvVolume;
    @BindView(R.id.xTablayout)
    XTabLayout xTablayout;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.xTablayout2)
    XTabLayout xTablayout2;
    @BindView(R.id.vp2)
    ViewPager vp2;
    @BindView(R.id.btn_buy)
    StateButton btnBuy;
    @BindView(R.id.btn_sell)
    StateButton btnSell;
    @BindView(R.id.ll_collection)
    LinearLayout llCollection;

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
        btnBuy.setOnClickListener(mPerfectClickListener);
        btnSell.setOnClickListener(mPerfectClickListener);
        DevShapeUtils
                .shape(DevShape.RECTANGLE)
                //TODO 這里判断颜色 green_dark or
                .solid(R.color.red_btn)
                .radius(3)
                .into(tvRange);
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_buy) {
                BuyAndSellDailog mDailog = new BuyAndSellDailog(CoinDetailsActivity.this);
                mDailog.showDialog();
            } else if (id == R.id.btn_sell) {

            }
        }
    };
}
