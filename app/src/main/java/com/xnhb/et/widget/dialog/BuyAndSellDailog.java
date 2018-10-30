package com.xnhb.et.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.dialog.base.BaseDailog;
import com.xnhb.et.R;
import com.xnhb.et.bean.TradePairInfo;
import com.xnhb.et.bean.TradeUserInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.interfaces.CallBack;
import com.xnhb.et.net.ApiService;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.widget.BuyAndSellLayout;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/10/23
 * 描述:
 * 参考链接:
 */
public class BuyAndSellDailog extends BaseDailog {

    @BindView(R.id.ll_buy)
    LinearLayout llBuy;
    @BindView(R.id.ll_sell)
    LinearLayout llSell;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.view_buy_line)
    View viewBuyLine;
    @BindView(R.id.tv_sell)
    TextView tvSell;
    @BindView(R.id.view_sell_line)
    View viewSellLine;
    @BindView(R.id.buy_layout)
    BuyAndSellLayout buyLayout;
    @BindView(R.id.sell_layout)
    BuyAndSellLayout sellLayout;
    private TradeUserInfo mTradeUserInfo;
    private TradePairInfo mTradePairInfo;
    private boolean isBuy;

    public BuyAndSellDailog(@NonNull Context context, TradeUserInfo mTradeUserInfo, TradePairInfo mTradePairInfo, boolean isBuy) {
        super(context);
        this.mTradeUserInfo = mTradeUserInfo;
        this.mTradePairInfo = mTradePairInfo;
        this.isBuy = isBuy;
    }

    @Override
    protected void initData() {
        mWindow.setGravity(Gravity.BOTTOM);
        mWindow.setWindowAnimations(R.style.BottomAnim);
        llBuy.setOnClickListener(mPerfectClickListener);
        llSell.setOnClickListener(mPerfectClickListener);
        tvCancel.setOnClickListener(mPerfectClickListener);
        buyLayout.setBaseData(true, mTradeUserInfo, mTradePairInfo);
        sellLayout.setBaseData(false, mTradeUserInfo, mTradePairInfo);
        buyLayout.setBuyOrSellListener(new BuyAndSellLayout.OnBuyOrSellListener() {
            @Override
            public void buyOrSell(String count, String price) {
                goToBuyOrSell(true, count, price);
            }
        });
        sellLayout.setBuyOrSellListener(new BuyAndSellLayout.OnBuyOrSellListener() {
            @Override
            public void buyOrSell(String count, String price) {
                goToBuyOrSell(false, count, price);
            }
        });
        setupBtnUi(isBuy);
        buyLayout.setupUi(true);
        sellLayout.setupUi(false);
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.ll_buy) {
                setupBtnUi(true);
            } else if (id == R.id.ll_sell) {
                setupBtnUi(false);
            } else if (id == R.id.tv_cancel) {
                dismiss(false);
            }
        }
    };

    public void setupBtnUi(boolean isBuy) {
        tvBuy.setTextColor(isBuy ? UiUtils.getColor(R.color.green_dark) : UiUtils.getColor(R.color.gray62));
        tvSell.setTextColor(isBuy ? UiUtils.getColor(R.color.gray62) : UiUtils.getColor(R.color.red_btn));
        viewBuyLine.setVisibility(isBuy ? View.VISIBLE : View.INVISIBLE);
        viewSellLine.setVisibility(isBuy ? View.INVISIBLE : View.VISIBLE);
        buyLayout.setVisibility(isBuy ? View.VISIBLE : View.GONE);
        sellLayout.setVisibility(isBuy ? View.GONE : View.VISIBLE);
    }

    @Override
    public int setLayoutId() {
        return R.layout.dailog_buy_and_sell;
    }


    public void goToBuyOrSell(boolean isBuy, String quantity, String price) {
        ApiService.buyOrSell(this, isBuy, quantity, price, mTradePairInfo.getTradeId(), new DialogCallback<ResultInfo<Void>>() {
            @Override
            public void onSuccess(Response<ResultInfo<Void>> response) {
                dismiss(false);
                if (listener != null) {
                    listener.success("");
                }
            }
        });
    }

    private CallBack listener;

    public void setOnTradeSuccessListener(CallBack l) {
        this.listener = l;
    }

}
