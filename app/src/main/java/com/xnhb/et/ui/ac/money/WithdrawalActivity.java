package com.xnhb.et.ui.ac.money;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oneway.qrlib.ScanerCodeActivity;
import com.oneway.tool.loader.ImageLoaderManager;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.convert.StringUtil;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.in.TitleContainer;
import com.oneway.ui.base.title.RightViewType;
import com.oneway.ui.base.title.TitleLayoutHelper;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.helper.PageStateHelper;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.CountDownButton;
import com.oneway.ui.widget.btn.StateButton;
import com.oneway.ui.widget.et.XEditText;
import com.oneway.ui.widget.status.OnRetryListener;
import com.xnhb.et.R;
import com.xnhb.et.bean.CoinInfo;
import com.xnhb.et.bean.Withdrawalslnfo;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.ui.ac.bill.HistoricalActivity;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/28
 * 描述: 提现
 * 参考链接:
 */
public class WithdrawalActivity extends BaseTitleActivity<WithdrawalPresenter> implements IWithdrawalView, OnRetryListener {
    private static String ARG_COIN_INFO = "coinInfo";
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
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
    @BindView(R.id.btn_total)
    TextView btnTotal;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_count)
    EditText etCount;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.submit)
    StateButton submit;
    @BindView(R.id.ll_qr_layout)
    RelativeLayout llQrLayout;
    @BindView(R.id.tv_hint_safe_code)
    TextView tvHintSafeCode;
    @BindView(R.id.ll_safe_code_layout)
    LinearLayout llSafeCodeLayout;
    @BindView(R.id.tv_hint_sms)
    TextView tvHintSms;
    @BindView(R.id.et_sms)
    XEditText etSms;
    @BindView(R.id.btn_countdown)
    CountDownButton btnCountdown;
    @BindView(R.id.ll_sms_code_layout)
    LinearLayout llSmsCodeLayout;
    @BindView(R.id.contentView)
    LinearLayout contentView;
    private CoinInfo mCoinInfo;
    private PageStateHelper mPageStateHelper;

    @Override
    public int setLayoutId() {
        return R.layout.activity_withdrawal;
    }

    public static void launch(Context context, CoinInfo info) {
        Intent intent = new Intent();
        intent.setClass(context, WithdrawalActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra(ARG_COIN_INFO, info);
        context.startActivity(intent);
    }

    @Override
    public WithdrawalPresenter newP() {
        return new WithdrawalPresenter();
    }

    @Override
    protected boolean getIntent(Intent intent) {
        mCoinInfo = getIntent().getParcelableExtra(ARG_COIN_INFO);
        return mCoinInfo == null;
    }

    @Override
    protected String getTitleText() {
        return "充值" + mCoinInfo.getCurrencyName();
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
    protected void initData(Bundle savedInstanceState) {
        mPageStateHelper = new PageStateHelper(this, contentView, this);
        mPageStateHelper.showLoadingView();
        ImageLoaderManager.getLoader().load(OkGoHelper.getImageUrl(mCoinInfo.getCurrencyName()), ivIcon);
        getP().reqWithdrawalCoinInfo(mCoinInfo.getCurrencyId());
    }


    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.ll_qr_layout) {//扫描二维码
                ScanerCodeActivity.launch(WithdrawalActivity.this);
            } else if (id == R.id.submit) {//提现
                withdrawals();
            } else if (id == R.id.btn_total) {//全部
                etCount.setText(mCoinInfo.getUsing());
            } else if (id == R.id.btn_countdown) {//短信验证码
                getP().reqSmsCode();
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


    @Override
    public void onRetry(int type) {
        mPageStateHelper.showLoadingView();
        getP().reqWithdrawalCoinInfo(mCoinInfo.getCurrencyId());
    }

    @Override
    public void showErrorPage() {
        mPageStateHelper.showContentView();
    }


    @Override
    public void showContentPage() {
        mPageStateHelper.showContentView();
    }

    @Override
    public void setWithdrawalsInfo(Withdrawalslnfo data) {
        tvCoinName.setText(mCoinInfo.getCurrencyName());
        tvCoinSubName.setText(mCoinInfo.getCurrencyName());
        tvAvailable.setText(StringUtil.htmlFromat(R.string.item_withdrawal_text1, mCoinInfo.getUsing(), mCoinInfo.getCurrencyName()));
        tvFrozen.setText(StringUtil.htmlFromat(R.string.item_withdrawal_text2, mCoinInfo.getFreeze(), mCoinInfo.getCurrencyName()));
//        tvTotal.setText(StringUtil.htmlFromat(R.string.item_withdrawal_text3, "0.000000", mCoinInfo.getCurrencyName()));
        llQrLayout.setOnClickListener(mPerfectClickListener);
        submit.setOnClickListener(mPerfectClickListener);
        btnTotal.setOnClickListener(mPerfectClickListener);
        btnCountdown.setOnClickListener(mPerfectClickListener);
        //短信验证码条目
        llSmsCodeLayout.setVisibility(data.getBooleanMap().isIsCodeInput() ? View.VISIBLE : View.GONE);
        tvHintSms.setVisibility(data.getBooleanMap().isIsCodeInput() ? View.VISIBLE : View.GONE);
        //安全码条目
        tvHintSafeCode.setVisibility(data.getBooleanMap().isIsTradeInput() ? View.VISIBLE : View.GONE);
        llSafeCodeLayout.setVisibility(data.getBooleanMap().isIsTradeInput() ? View.VISIBLE : View.GONE);
    }

    /**
     * 提现
     */
    private void withdrawals() {
        String totalCount = etCount.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String smsCode = etSms.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        if (checkNum(totalCount)) {
            ToastManager.info("提现数量必须大于0");
            return;
        }
        if (EmptyUtils.isEmpty(address)) {
            ToastManager.info("提现地址不能为空");
            return;
        }
        if (llSmsCodeLayout.getVisibility() == View.VISIBLE) {
            if (EmptyUtils.isEmpty(smsCode)) {
                ToastManager.info("请输入短信验证码");
                return;
            }
        }
        if (llSafeCodeLayout.getVisibility() == View.VISIBLE) {
            if (EmptyUtils.isEmpty(pwd)) {
                ToastManager.info("请输入安全码");
                return;
            }
        }
        getP().reqWithdrawal(mCoinInfo.getCurrencyId(), totalCount, address, pwd, smsCode);
    }

    /**
     * 校验提现数量是否大于0
     */
    public boolean checkNum(String totalCount) {
        if (EmptyUtils.isEmpty(totalCount)) {
            return true;
        }
        try {
            Integer count = Integer.valueOf(totalCount);
            return count <= 0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public void startCountDown() {
        btnCountdown.start();
    }

    @Override
    public void cancelCountDown() {
        btnCountdown.cancel();
    }

}
