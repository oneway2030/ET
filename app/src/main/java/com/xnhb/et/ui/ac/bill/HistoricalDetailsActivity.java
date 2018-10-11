package com.xnhb.et.ui.ac.bill;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.oneway.tool.utils.common.ClipboardUtils;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.xnhb.et.R;
import com.xnhb.et.bean.HistoricalInfo;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/27
 * 描述: 充值记录详细信息
 * 参考链接:
 */
public class HistoricalDetailsActivity extends BaseTitleActivity {
    public final static String TAG_ORDER_INFO = "order_Info";
    public final static String TAG_ORDER_TYPE = "order_type";
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_type_name)
    TextView tvTypeName;
    @BindView(R.id.tv_coin_name)
    TextView tvCoinName;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_transaction_id)
    TextView tvTransactionId;
    @BindView(R.id.tv_remarks)
    TextView tvRemarks;
    private HistoricalInfo mOrderInfo;
    private String orderType;

    public static void launch(Context context, HistoricalInfo info, String orderType) {
        Intent intent = new Intent();
        intent.setClass(context, HistoricalDetailsActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra(TAG_ORDER_INFO, info);
        intent.putExtra(TAG_ORDER_TYPE, orderType);
        context.startActivity(intent);
    }

    @Override
    protected boolean getIntent(Intent intent) {
        mOrderInfo = intent.getParcelableExtra(TAG_ORDER_INFO);
        orderType = intent.getStringExtra(TAG_ORDER_TYPE);
        return mOrderInfo == null;
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
        tvOrderId.setText(mOrderInfo.getId());
        tvCreateTime.setText(mOrderInfo.getCreateTime());
        tvTypeName.setText(orderType);//订单类型
        tvCoinName.setText(mOrderInfo.getCurrencyName());
        tvTotal.setText(mOrderInfo.getRealQuantity() + "");
        tvStatus.setText(mOrderInfo.getStatusStr());
        if (EmptyUtils.isEmpty(mOrderInfo.getHash())) {
            tvTransactionId.setVisibility(View.GONE);
        } else {
            tvTransactionId.setVisibility(View.VISIBLE);
            tvTransactionId.setText(mOrderInfo.getHash());
            tvTransactionId.setOnClickListener(mPerfectClickListener);
        }
        tvRemarks.setText(mOrderInfo.getRemark());
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.tv_transaction_id) {
                ToastManager.success("已复制地址到剪切板");
                ClipboardUtils.copyText(mOrderInfo.getHash());
            }
        }
    };
}
