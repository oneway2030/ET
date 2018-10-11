package com.xnhb.et.ui.ac.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.btn.StateButton;
import com.oneway.ui.widget.dialog.TipLabelBottomSelectDialog;
import com.xnhb.et.R;
import com.xnhb.et.bean.UserInfo;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.interfaces.CallBack;
import com.xnhb.et.ui.ac.setting.p.BankInfoPresenter;
import com.xnhb.et.ui.ac.setting.p.IBankView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/19
 * 描述:
 * 参考链接:
 */
public class BankCardInfoActivity extends BaseTitleActivity<BankInfoPresenter> implements IBankView {
    @BindView(R.id.et_bank_name)
    TextView tvBankName;
    @BindView(R.id.et_bankcard_id)
    EditText etBankcardId;
    @BindView(R.id.et_bank_address)
    EditText etBankAddress;
    @BindView(R.id.btn_save)
    StateButton btnSave;
    ArrayList<String> bankNames = new ArrayList<String>() {{
        add("中国工商银行");
        add("中国建设银行");
        add("中国农业银行");
        add("中国邮政储蓄银行");
        add("交通银行");
        add("招商银行");
        add("中国银行");
        add("中国光大银行");
        add("中信银行");
        add("浦发银行");
        add("中国民生银行");
        add("兴业银行");
        add("平安银行");
        add("广发银行");
        add("华夏银行");
    }};

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BankCardInfoActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    public BankInfoPresenter newP() {
        return new BankInfoPresenter();
    }

    @Override
    protected String getTitleText() {
        return "银行信息";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_bank_card_info;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tvBankName.setOnClickListener(mPerfectClickListener);
        btnSave.setOnClickListener(mPerfectClickListener);
        //回显 信用卡信息
        UserInfoHelper.getInstance().getUserInfo(this, new CallBack() {
            @Override
            public void success(UserInfo userInfo) {
                tvBankName.setText(userInfo.getBankName());
                etBankcardId.setText(userInfo.getBankNo());
                etBankAddress.setText(userInfo.getBankAddress());
                etBankcardId.setSelection(etBankcardId.getText().length());
                etBankAddress.setFocusable(true);
                etBankAddress.setFocusableInTouchMode(true);
                etBankAddress.requestFocus();
                etBankAddress.setSelection(etBankAddress.getText().length());
            }
        });
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.et_bank_name) {
                showBankSelector();
            } else if (id == R.id.btn_save) {
                String bankName = tvBankName.getText().toString().trim();
                String bankcardId = etBankcardId.getText().toString().trim();
                String bankAddress = etBankAddress.getText().toString().trim();
                if (EmptyUtils.isEmpty(bankName)) {
                    ToastManager.info("请选择开户行");
                    return;
                }
                if (EmptyUtils.isEmpty(bankcardId)) {
                    ToastManager.info("请填写银行卡号");
                    return;
                }
                if (EmptyUtils.isEmpty(bankAddress)) {
                    ToastManager.info("请填写开户行地址");
                    return;
                }
                getP().saveBankInfo(bankName, bankcardId, bankAddress);
            }
        }

    };

    private int lastPosition = -1;

    private void showBankSelector() {
        TipLabelBottomSelectDialog<String> mDialog = new TipLabelBottomSelectDialog<String>(this, bankNames, lastPosition, "", "选择开户行", "");
        mDialog.setData(new TipLabelBottomSelectDialog.DataListener() {
            @Override
            public String setData(int position) {
                return bankNames.get(position);
            }
        }).setItemClick(new TipLabelBottomSelectDialog.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                tvBankName.setText(bankNames.get(position));
                lastPosition = position;
                mDialog.dismiss();
            }
        }).show();
    }

    @Override
    public void saveSuccess() {
        finish();
    }

    @Override
    public void saveFail() {
        finish();
    }
}
