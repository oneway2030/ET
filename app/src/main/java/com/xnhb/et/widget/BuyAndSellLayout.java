package com.xnhb.et.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oneway.tool.event.BusManager;
import com.oneway.tool.utils.common.KeyboardUtils;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.base.ac.ActivityManager;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.btn.StateButton;
import com.xnhb.et.MainActivity;
import com.xnhb.et.MainActivity2;
import com.xnhb.et.R;
import com.xnhb.et.bean.TradePairInfo;
import com.xnhb.et.bean.TradeUserInfo;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.ui.ac.money.RechargeActivity;
import com.xnhb.et.util.MoneyUtils;

/**
 * 作者 oneway on 2018/10/23
 * 描述:
 * 参考链接:
 */
public class BuyAndSellLayout extends FrameLayout {
    private Context mContext;
    private boolean upUi;
    private StateButton mbtn;
    private LinearLayout llCount;
    private EditText etCount;
    private EditText etPrice;
    private OnBuyOrSellListener listener;
    private TradeUserInfo mTradeUserInfo;
    private TradePairInfo mTradePairInfo;
    private TextView tvTotalMoney;
    private TextView tvUsing;
    private boolean isBuy;

    public BuyAndSellLayout(@NonNull Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public BuyAndSellLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public BuyAndSellLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        View rooView = UiUtils.newInstance(mContext, R.layout.custom_layout_buy_and_sell);
        addView(rooView);
        mbtn = findViewById(R.id.btn);
        llCount = findViewById(R.id.ll_price);
        etPrice = findViewById(R.id.et_count);
        etCount = findViewById(R.id.et_price);
        //
        //总额
        tvTotalMoney = findViewById(R.id.tv_total_money);
        //0.000000  BTC   可用
        tvUsing = findViewById(R.id.tv_using);

        TextView tvRecharge = findViewById(R.id.tv_recharge); //充值
        TextView tv21 = findViewById(R.id.tv_21);  // 2分之1
        TextView tv41 = findViewById(R.id.tv_41);// 4分之1
        TextView tvAll = findViewById(R.id.tv_all);// 全部
        RelativeLayout llReduce = findViewById(R.id.ll_reduce);  //减
        RelativeLayout llAad = findViewById(R.id.ll_aad); //加
        tvRecharge.setOnClickListener(mPerfectClickListener);
        tv21.setOnClickListener(mPerfectClickListener);
        tv41.setOnClickListener(mPerfectClickListener);
        tvAll.setOnClickListener(mPerfectClickListener);
        llReduce.setOnClickListener(mPerfectClickListener);
        llAad.setOnClickListener(mPerfectClickListener);
        llCount.setOnClickListener(mPerfectClickListener);
        mbtn.setOnClickListener(mPerfectClickListener);
//        mbtn = findViewById(R.id.btn);
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.ll_price) {
                KeyboardUtils.showSoftInput(etPrice);
            } else if (id == R.id.btn) {//点击提交
                String count = etCount.getText().toString();
                String price = etPrice.getText().toString();
                if (EmptyUtils.isEmpty(count)) {
                    ToastManager.info("数量不能为空");
                    return;
                }
                if (EmptyUtils.isEmpty(price)) {
                    ToastManager.info("单价不能为空");
                    return;
                }
                KeyboardUtils.hideSoftInput(etCount);
                KeyboardUtils.hideSoftInput(etPrice);
                if (listener != null) {
                    listener.buyOrSell(count, price);
                }
            } else if (id == R.id.tv_recharge) {//充值
                if (isBuy) {
                    //切换到c2c 并关闭所有
                    ActivityManager.getInstance().finishAll(MainActivity2.class);
                    BusManager.getBus().post(EventBusTags.TAG_HOME_SWTICH_PAGE, MainActivity.FRAGMENT_C2C);
                } else {
                    RechargeActivity.launch(mContext, mTradePairInfo.getCurrencyName(), mTradePairInfo.getTradeId());
                }
            } else if (id == R.id.tv_21) {//2分之1

            } else if (id == R.id.tv_41) {//4分之1

            } else if (id == R.id.tv_all) {//全部

            } else if (id == R.id.ll_reduce) {//减

            } else if (id == R.id.ll_aad) {//加

            }

        }
    };


    public void setupUi(boolean isBuy) {
        mbtn.setNormalBackgroundColor(isBuy ? UiUtils.getColor(R.color.green_dark) : UiUtils.getColor(R.color.red_btn));
    }


    public void setBaseData(boolean isBuy, TradeUserInfo mTradeUserInfo, TradePairInfo mTradePairInfo) {
        this.mTradeUserInfo = mTradeUserInfo;
        this.mTradePairInfo = mTradePairInfo;
        this.isBuy = isBuy;
        String using = isBuy ? mTradeUserInfo.getTradeWallet().getUsing() : mTradeUserInfo.getWallet().getUsing();
        tvUsing.setText(MoneyUtils.scaleMoney4(using) + " " + mTradePairInfo.getCurrencyName() + "可用");//
        tvTotalMoney.setText("0.0000" + mTradePairInfo.getCurrencyName());
    }


    public void setBuyOrSellListener(OnBuyOrSellListener l) {
        this.listener = l;
    }


    public interface OnBuyOrSellListener {
        void buyOrSell(String count, String price);
    }

}
