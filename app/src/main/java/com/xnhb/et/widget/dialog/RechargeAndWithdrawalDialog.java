package com.xnhb.et.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.dialog.base.BaseDailog;
import com.oneway.ui.toast.ToastManager;
import com.xnhb.et.R;
import com.xnhb.et.bean.CoinInfo;
import com.xnhb.et.ui.ac.money.RechargeActivity;
import com.xnhb.et.ui.ac.money.WithdrawalActivity;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/28
 * 描述: 充提dialog
 * 参考链接:
 */
public class RechargeAndWithdrawalDialog extends BaseDailog {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.tv_withdrawal)
    TextView tvWithdrawal;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    //    String coinType;
    CoinInfo info;

    public RechargeAndWithdrawalDialog(@NonNull Context context, CoinInfo info) {
        super(context);
        this.info = info;
    }

    @Override
    protected void initData() {
        mWindow.setGravity(Gravity.BOTTOM);
        mWindow.setWindowAnimations(R.style.BottomAnim);
        tvTitle.setText(UiUtils.getString(R.string.hint_dialog_title, info.getCurrencyName()));
        tvRecharge.setOnClickListener(mPerfectClickListener);
        tvWithdrawal.setOnClickListener(mPerfectClickListener);
        tvCancel.setOnClickListener(mPerfectClickListener);
    }


    @Override
    public int setLayoutId() {
        return R.layout.dialog_recharge_and_withdrawal;
    }


    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.tv_recharge) {//充值
                if (info.getRechargeSwitch() == 1) {
                    RechargeActivity.launch(mContext, info);
                    dismiss(false);
                } else {
                    ToastManager.info("暂未开放");
                }
            } else if (id == R.id.tv_withdrawal) {//提现
                if (info.getExtract() == 1) {
                    WithdrawalActivity.launch(mContext, info);
                    dismiss(false);
                } else {
                    ToastManager.info("暂未开放");
                }
            } else if (id == R.id.tv_cancel) {
                dismiss(false);
            }
        }
    };
}
