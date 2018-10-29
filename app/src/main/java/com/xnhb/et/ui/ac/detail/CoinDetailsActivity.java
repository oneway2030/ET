package com.xnhb.et.ui.ac.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.shape.DevShapeUtils;
import com.oneway.tool.utils.shape.shape.DevShape;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.fragment.FragmentBaseAdapter;
import com.oneway.ui.base.in.TitleContainer;
import com.oneway.ui.base.title.RightViewType;
import com.oneway.ui.base.title.TitleLayoutHelper;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.widget.btn.StateButton;
import com.xnhb.et.R;
import com.xnhb.et.bean.TradeInfo;
import com.xnhb.et.bean.TradePairInfo;
import com.xnhb.et.bean.TradeUserInfo;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.ui.ac.detail.fragment.CoinBuyAndSellRecordFragment;
import com.xnhb.et.ui.ac.detail.fragment.CoinTransactionRecordFragment;
import com.xnhb.et.util.MoneyUtils;
import com.xnhb.et.widget.dialog.BuyAndSellDailog;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.iv_collection)
    ImageView ivCollection;
    private String tradeId;
    private String title;
    String[] mTitles2 = {"挂单薄", "近期交易"};
    private CoinBuyAndSellRecordFragment buyAndSellFragment;
    private CoinTransactionRecordFragment transactionFragment;
    private boolean isShowCollection; //是否显示都藏按钮
    private boolean isCollection; //是否已收藏
    private TradePairInfo mTradePairInfo;  //币基础信息
    private TradeUserInfo mTradeUserInfo;  //用户信息
    private boolean isBuy;

    public static void launch(Context context, String tradeId, String title, boolean isShowCollection, boolean isCollection) {
        Intent intent = new Intent();
        intent.setClass(context, CoinDetailsActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra("tradeId", tradeId);
        intent.putExtra("title", title);
        intent.putExtra("isShowCollection", isShowCollection);
        intent.putExtra("isCollection", isCollection);
        context.startActivity(intent);
    }

    @Override
    protected boolean getIntent(Intent intent) {
        tradeId = intent.getStringExtra("tradeId");
        title = intent.getStringExtra("title");
        isShowCollection = intent.getBooleanExtra("isShowCollection", false);
        isCollection = intent.getBooleanExtra("isCollection", false);
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
        if (isShowCollection) {
            ivCollection.setImageResource(isCollection ? R.mipmap.stars_selected : R.mipmap.stars_unselect);
        }
        btnBuy.setOnClickListener(mPerfectClickListener);
        btnSell.setOnClickListener(mPerfectClickListener);
        llCollection.setVisibility(isShowCollection ? View.VISIBLE : View.INVISIBLE);
        if (isShowCollection) {
            llCollection.setOnClickListener(mPerfectClickListener);
        }
//        getP().getCoinSynopsis(tradeId);
        FragmentBaseAdapter mFragmentAdapter = new FragmentBaseAdapter(getSupportFragmentManager(), getFragmentPage2(), mTitles2);
        vp2.setOffscreenPageLimit(mTitles2.length);
        vp2.setAdapter(mFragmentAdapter);
        xTablayout2.setupWithViewPager(vp2);
        getP().connectWebSocket();
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_buy) {
                if (!UserInfoHelper.getInstance().checkLogin()) {
                    return;
                }
                 isBuy= true;
                if (mTradeUserInfo != null) {
                    BuyAndSellDailog mDailog = new BuyAndSellDailog(CoinDetailsActivity.this, mTradeUserInfo, true);
                    mDailog.showDialog();
                } else {
                    //查询用户信息
                    getP().senUserInfo(true);
                }
            } else if (id == R.id.btn_sell) {
                if (!UserInfoHelper.getInstance().checkLogin()) {
                    return;
                }
                isBuy= false;
                if (mTradeUserInfo != null) {
                    BuyAndSellDailog mDailog = new BuyAndSellDailog(CoinDetailsActivity.this, mTradeUserInfo, false);
                    mDailog.showDialog();
                } else {
                    //查询用户信息
                    getP().senUserInfo(true);
                }
            } else if (id == R.id.ll_collection) {//收藏
                //TODO 收藏
                getP().reqCollection(mTradePairInfo);
            }
        }
    };


    @Override
    public String getTradeId() {
        return tradeId;
    }


    @Override
    public void updateTradeInfoUi(TradeInfo tradeInfo) {
        //TODO 更新Trade 信息
        //TODO 1更新coin信息
        mTradePairInfo = tradeInfo.getTradeInfo();
        mTradePairInfo.setCollection(isCollection);
        String rise = mTradePairInfo.getRise();
        tvRange.setText(rise + "%");
        if (EmptyUtils.isNotEmpty(rise)) {
            DevShapeUtils
                    .shape(DevShape.RECTANGLE)
                    .solid(mTradePairInfo.getRise().startsWith("+") ? R.color.red_btn : R.color.green_dark)
                    .radius(3)
                    .into(tvRange);
        }
        tvPrice.setTextColor(mTradePairInfo.getRise().startsWith("+") ? UiUtils.getColor(R.color.red_btn) : UiUtils.getColor(R.color.green_dark));
        tvPrice.setText(mTradePairInfo.getCurrentPrice() + "");
        tvPrice2.setText("≈" + mTradePairInfo.getCurrentPrice() + " CNY");
        tvMax.setText(MoneyUtils.scaleMoney4(mTradePairInfo.getTradeMaxPrice()));
        tvMin.setText(MoneyUtils.scaleMoney4(mTradePairInfo.getTradeMinPrice()));
        tvVolume.setText(MoneyUtils.scaleMoney4(mTradePairInfo.getTradeMoney()));
        //TODO 2更新挂单 和  近期交易
        List<TradeInfo.TradeListBean> buyList = tradeInfo.getBuyList();
        List<TradeInfo.TradeListBean> sellList = tradeInfo.getSellList();
        List<TradeInfo.RecordListBean> recordList = tradeInfo.getRecordList();
        buyAndSellFragment.update(buyList, sellList);
        transactionFragment.update(recordList);
    }

    @Override
    public void updateTradeUserInfoUi(TradeUserInfo tradeUserInfo, boolean isCallBack) {
        //TODO 更新 底部 点击买卖的用户信息 缓存在本地
        this.mTradeUserInfo = tradeUserInfo;
        if (isCallBack) {
            BuyAndSellDailog mDailog = new BuyAndSellDailog(CoinDetailsActivity.this, mTradeUserInfo, isBuy);
            mDailog.showDialog();
        }
    }

    /**
     * 显示收藏状态
     */
    @Override
    public void setCollection(boolean isCollection) {
        ivCollection.setImageResource(isCollection ? R.mipmap.stars_selected : R.mipmap.stars_unselect);
    }

    public List<Fragment> getFragmentPage2() {
        List<Fragment> fragments = new ArrayList<>();
        buyAndSellFragment = CoinBuyAndSellRecordFragment.newInstance();
        transactionFragment = CoinTransactionRecordFragment.newInstance();
        fragments.add(buyAndSellFragment);
        fragments.add(transactionFragment);
        return fragments;
    }
}
