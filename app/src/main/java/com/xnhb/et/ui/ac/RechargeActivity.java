package com.xnhb.et.ui.ac;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oneway.tool.utils.common.ClipboardUtils;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.in.TitleContainer;
import com.oneway.ui.base.title.RightViewType;
import com.oneway.ui.base.title.TitleLayoutHelper;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.btn.StateButton;
import com.xnhb.et.R;
import com.xnhb.et.ui.ac.bill.HistoricalActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 oneway on 2018/9/28
 * 描述: 充值
 * 参考链接:
 */
public class RechargeActivity extends BaseTitleActivity {
    private static String ARG_COIN_TYPE = "coinType";
    @BindView(R.id.iv_qr)
    ImageView ivQr;
    @BindView(R.id.tv_sub_title)
    TextView tvSubTitle;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.btn_copy)
    StateButton btnCopy;


    public static void launch(Context context, String coinType) {
        Intent intent = new Intent();
        intent.setClass(context, RechargeActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra(ARG_COIN_TYPE, coinType);
        context.startActivity(intent);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected String getTitleText() {
        String title = getIntent().getStringExtra(ARG_COIN_TYPE);
        return "充值" + title;
    }

    @Override
    protected void customTitle(TitleContainer toolbar) {
        TitleLayoutHelper titleLayoutHelper = new TitleLayoutHelper();
        titleLayoutHelper.setOneTextLayout(toolbar, this, "充提历史", R.color.color_text_press, 12, new TitleLayoutHelper.RightViewClickListener() {
            @Override
            public void onRightViewClickListener(View view, RightViewType Type) {
                HistoricalActivity.launch(RechargeActivity.this);
            }
        });
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        tvSubTitle.setText("BitCoin" + " 充值地址");
//        tvAddress.setText("");
        btnCopy.setOnClickListener(mPerfectClickListener);
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_copy) {
                ToastManager.success("已复制地址到剪切板");
                ClipboardUtils.copyText(tvAddress.getText().toString());
            }
        }
    };

}
