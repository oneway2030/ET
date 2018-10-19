package com.xnhb.et.ui.ac.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Response;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.convert.RegexUtils;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.btn.StateButton;
import com.oneway.ui.widget.dialog.TipLabelBottomSelectDialog;
import com.xnhb.et.R;
import com.xnhb.et.bean.UserInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.interfaces.CallBack;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.ApiService;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author oneway
 * @describe 身份认证 录入信息界面
 * @since 2018/9/15 0015
 */


public class IdentityAuthenticationActivity extends BaseTitleActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_idcard)
    EditText etIdcard;
    @BindView(R.id.btn_next_step)
    StateButton btnNextStep;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_bankname)
    TextView etBankname;
    @BindView(R.id.et_bankcard_id)
    EditText etBankcardId;
    @BindView(R.id.et_bank_address)
    EditText etBankAddress;
    @BindView(R.id.ll_bankname)
    LinearLayout llBankname;
    @BindView(R.id.rootView)
    LinearLayout rootView;

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, IdentityAuthenticationActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected String getTitleText() {
        return "身份认证";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_identity_authentication;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        btnNextStep.setOnClickListener(mPerfectClickListener);
        llBankname.setOnClickListener(mPerfectClickListener);
        UserInfoHelper.getInstance().getUserInfo(this, new CallBack<UserInfo>() {
            @Override
            public void success(UserInfo userInfo) {
                etName.setText(userInfo.getRealName());
                etIdcard.setText(userInfo.getIdcart());
                etEmail.setText(userInfo.getEmail());
                etBankname.setText(userInfo.getBankName());
                etBankcardId.setText(userInfo.getBankNo());
                etBankAddress.setText(userInfo.getBankAddress());
                rootView.setFocusable(true);
                rootView.setFocusableInTouchMode(true);
            }
        });
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_next_step) {//下一步
                nextStep();
            } else if (id == R.id.ll_bankname) {//选择银行卡
                showBankSelector();
            }
        }
    };

    private void nextStep() {
        String realName = etName.getText().toString().trim();
        String idCard = etIdcard.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String bankName = etBankname.getText().toString().trim();
        String bankCardId = etBankcardId.getText().toString().trim();
        String bankAddress = etBankAddress.getText().toString().trim();
        if (EmptyUtils.isEmpty(realName)) {
            ToastManager.info(R.string.hint_input_name);
            return;
        }
        if (!RegexUtils.isIDCard18(idCard)) {
            ToastManager.info(R.string.hint_input_idcard);
            return;
        }
        if (!RegexUtils.isEmail(email)) {
            ToastManager.info("请填写正确的邮箱");
            return;
        }
        if (EmptyUtils.isEmpty(bankName)) {
            ToastManager.info("开户行名字不能为空");
            return;
        }
        if (EmptyUtils.isEmpty(bankCardId)) {
            ToastManager.info("银行卡号不能为空");
            return;
        }
        if (EmptyUtils.isEmpty(bankAddress)) {
            ToastManager.info("开户行地址不能为空");
            return;
        }
        addUserInfo(realName, idCard, email, bankName, bankCardId, bankAddress);
    }

    public void addUserInfo(String realName, String idcart, String email, String bankName, String bankNo, String bankAddress) {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("realName", realName);
        map.put("email", email);
        map.put("idcart", idcart);
        map.put("bankName", bankName);
        map.put("bankAddress", bankAddress);
        map.put("bankNo", bankNo);
        OkGoHelper.postOkGo(Api.SUMIT_USER_INFO_FORM_URL, this)
                .params(map)
                .execute(new DialogCallback<ResultInfo<UserInfo>>(this) {
                    @Override
                    public void onSuccess(Response<ResultInfo<UserInfo>> response) {
                        ResultInfo<UserInfo> body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            return;
                        }
                        UserInfo userInfo = body.getResult();
                        if (EmptyUtils.isEmpty(userInfo)) {
                            return;
                        }
                        UserInfoHelper.getInstance().cacheUserInfo(userInfo);
                        //跳转到下一页
                        IdentityAuthenticationActivity2.launch(IdentityAuthenticationActivity.this, realName, idcart);
                    }
                });
    }


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
                etBankname.setText(bankNames.get(position));
                lastPosition = position;
                mDialog.dismiss();
            }
        }).show();
    }

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
}
