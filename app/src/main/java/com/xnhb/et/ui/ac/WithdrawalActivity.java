package com.xnhb.et.ui.ac;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oneway.qrlib.ScanerCodeActivity;
import com.oneway.tool.utils.convert.StringUtil;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.in.TitleContainer;
import com.oneway.ui.base.title.RightViewType;
import com.oneway.ui.base.title.TitleLayoutHelper;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.widget.btn.StateButton;
import com.xnhb.et.R;
import com.xnhb.et.ui.ac.bill.HistoricalActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 oneway on 2018/9/28
 * 描述: 提现
 * 参考链接:
 */
public class WithdrawalActivity extends BaseTitleActivity {
    private static String ARG_COIN_TYPE = "coinType";
    @BindView(R.id.tv_coin_name)
    TextView tvCoinName;
    @BindView(R.id.tv_coin_sub_name)
    TextView tvCoinSubName;
    @BindView(R.id.tv_available)
    TextView tvAvailable;
    @BindView(R.id.tv_frozen)
    TextView tvFrozen;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_count)
    EditText etCount;
    @BindView(R.id.et_remarks)
    EditText etRemarks;
    @BindView(R.id.submit)
    StateButton submit;
    @BindView(R.id.ll_qr_layout)
    RelativeLayout llQrLayout;
    private String coinType;


    public static void launch(Context context, String coinType) {
        Intent intent = new Intent();
        intent.setClass(context, WithdrawalActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra(ARG_COIN_TYPE, coinType);
        context.startActivity(intent);
    }

    @Override
    protected String getTitleText() {
        coinType = getIntent().getStringExtra(ARG_COIN_TYPE);
        return "充值" + coinType;
    }

    @Override
    protected void customTitle(TitleContainer toolbar) {
        TitleLayoutHelper titleLayoutHelper = new TitleLayoutHelper();
        titleLayoutHelper.setOneTextLayout(toolbar, this, "充提历史", R.color.color_text_press, 12, new TitleLayoutHelper.RightViewClickListener() {
            @Override
            public void onRightViewClickListener(View view, RightViewType Type) {
                HistoricalActivity.launch(WithdrawalActivity.this);
            }
        });
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_withdrawal;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tvCoinName.setText(coinType);
        tvCoinSubName.setText("BitCoin");
        tvAvailable.setText(StringUtil.htmlFromat(R.string.item_withdrawal_text1, "0.000000", coinType));
        tvFrozen.setText(StringUtil.htmlFromat(R.string.item_withdrawal_text2, "0.000000", coinType));
        tvTotal.setText(StringUtil.htmlFromat(R.string.item_withdrawal_text3, "0.000000", coinType));
        llQrLayout.setOnClickListener(mPerfectClickListener);
        submit.setOnClickListener(mPerfectClickListener);
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.ll_qr_layout) {
                ScanerCodeActivity.launch(WithdrawalActivity.this);
            } else if (id == R.id.submit) {

            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String result = data.getStringExtra(ScanerCodeActivity.SCANER_RUSLT);
            if (!TextUtils.isEmpty(result)) {
                etAddress.setText(result);
                etAddress.setSelection(etAddress.getText().toString().length());
            }
        }
    }
}
