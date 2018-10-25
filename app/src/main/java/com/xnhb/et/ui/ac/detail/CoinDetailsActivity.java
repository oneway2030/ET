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
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.shape.DevShapeUtils;
import com.oneway.tool.utils.shape.shape.DevShape;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.in.TitleContainer;
import com.oneway.ui.base.title.RightViewType;
import com.oneway.ui.base.title.TitleLayoutHelper;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.widget.btn.StateButton;
import com.xnhb.et.R;
import com.xnhb.et.bean.CoinSynopsisInfo;
import com.xnhb.et.bean.TradeInfo;
import com.xnhb.et.bean.TradeUserInfo;
import com.xnhb.et.util.MoneyUtils;
import com.xnhb.et.widget.dialog.BuyAndSellDailog;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/10/20
 * 描述: 币详情
 * 参考链接:
 */
public class CoinDetailsActivity extends BaseTitleActivity<CoinDetailsPresenter> implements ICoinDetailsView {
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
    private String tradeId;
    private String title;

    public static void launch(Context context, String tradeId, String title) {
        Intent intent = new Intent();
        intent.setClass(context, CoinDetailsActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra("tradeId", tradeId);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected boolean getIntent(Intent intent) {
        tradeId = intent.getStringExtra("tradeId");
        title = intent.getStringExtra("title");
        return EmptyUtils.isEmpty(tradeId);
    }

    @Override
    public CoinDetailsPresenter newP() {
        return new CoinDetailsPresenter();
    }

    @Override
    protected String getTitleText() {
        return title;
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
//        getP().getCoinSynopsis(tradeId);
        getP().connectWebSocket();
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


    @Override
    public String getTradeId() {
        return tradeId;
    }

    @Override
    public void setBaseUi(CoinSynopsisInfo coinSynopsisInfo) {
        //TODO
        String currencyName = coinSynopsisInfo.getCurrencyName();
        String currencyTradeName = coinSynopsisInfo.getCurrencyTradeName();

    }

    @Override
    public void updateTradeInfoUi(TradeInfo tradeInfo) {
        //TODO 更新Trade 信息

        //TODO 1更新coin信息
        TradeInfo.TradeInfoBean realTradeInfo = tradeInfo.getTradeInfo();
        String rise = realTradeInfo.getRise();
        tvRange.setText(rise + "%");
        if (EmptyUtils.isNotEmpty(rise)) {
            DevShapeUtils
                    .shape(DevShape.RECTANGLE)
                    .solid(realTradeInfo.getRise().startsWith("+") ? R.color.red_btn : R.color.green_dark)
                    .radius(3)
                    .into(tvRange);
        }
        tvPrice.setTextColor(realTradeInfo.getRise().startsWith("+") ? UiUtils.getColor(R.color.red_btn) : UiUtils.getColor(R.color.green_dark));
        tvPrice.setText(realTradeInfo.getCurrentPrice() + "");
        tvPrice2.setText("≈" + realTradeInfo.getCurrentPrice() + " CNY");
        tvMax.setText(MoneyUtils.scaleMoney4(realTradeInfo.getTradeMaxPrice()));
        tvMin.setText(MoneyUtils.scaleMoney4(realTradeInfo.getTradeMinPrice()));
        tvVolume.setText(MoneyUtils.scaleMoney4(realTradeInfo.getTradeMoney()));
        //TODO 2更新挂单 和  近期交易


    }

    @Override
    public void updateTradeUserInfoUi(TradeUserInfo tradeUserInfo) {
        //TODO 更新 底部 点击买卖的用户信息 缓存在本地
    }
}
