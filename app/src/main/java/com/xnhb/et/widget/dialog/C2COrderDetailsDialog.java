package com.xnhb.et.widget.dialog;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oneway.tool.utils.convert.StringUtil;
import com.oneway.ui.dialog.base.BaseDailog;
import com.xnhb.et.R;
import com.xnhb.et.bean.C2COrderDetailsInfo;
import com.xnhb.et.util.MoneyUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者 oneway on 2018/10/20
 * 描述:
 * 参考链接:
 */
public class C2COrderDetailsDialog extends BaseDailog {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_mark)
    TextView tvMark;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_time_layout)
    LinearLayout llTimeLayout;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    private C2COrderDetailsInfo mC2COrderDetailsInfo;

    public C2COrderDetailsDialog(@NonNull Context context, C2COrderDetailsInfo info) {
        super(context);
        this.mC2COrderDetailsInfo = info;
    }

    @Override
    public int setLayoutId() {
        return R.layout.dialog_c2c_order_details;
    }

    @Override
    protected void initData() {
        tvName.setText(mC2COrderDetailsInfo.getRealName());
        tvBankName.setText(mC2COrderDetailsInfo.getBankName());
        tvAccount.setText(mC2COrderDetailsInfo.getBankNo());
        tvMoney.setText(MoneyUtils.getPrettyNumber(mC2COrderDetailsInfo.getMoney()));
        tvMark.setText(StringUtil.htmlFromat(R.string.c2c_order_details_remark, mC2COrderDetailsInfo.getRemark()));
        tvStatus.setText(mC2COrderDetailsInfo.getStatus());
        //TODO 倒计时
        downTimer(mC2COrderDetailsInfo.getRemainingTime(), 1 * 1000);
    }

    @OnClick(R.id.close)
    public void onViewClicked() {
        dismiss(false);
    }

    /**
     * @param millisinfuture   总时长
     * @param countdowninterva 间隔时长 1 * 1000
     */
    public void downTimer(long millisinfuture, long countdowninterva) {
        if (millisinfuture <= 0) {
            tvTime.setText("0");
        }
        CountDownTimer mCountDownTimer = new CountDownTimer(millisinfuture, countdowninterva) {
            @Override
            public void onTick(long millisUntilFinished) {
                //未结束
                tvTime.setText(getCountDownTime(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                //结束
            }
        };
    }

    /**
     * 获取倒计时时间
     * 這里是做一个倒计时 时间的叫校准
     *
     * @param millisUntilFinished 倒计时的总时长
     * @return 剩余时间
     */
    @NonNull
    private String getCountDownTime(double millisUntilFinished) {
        return (Math.round(millisUntilFinished / 1000) - 1) + "";
    }
}
