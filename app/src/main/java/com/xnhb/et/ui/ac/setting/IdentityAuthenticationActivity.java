package com.xnhb.et.ui.ac.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.convert.RegexUtils;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.btn.StateButton;
import com.xnhb.et.R;

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

    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_next_step) {//下一步
                String name = etName.getText().toString().trim();
                String idCard = etIdcard.getText().toString().trim();
                if (EmptyUtils.isEmpty(name)) {
                    ToastManager.info(R.string.hint_input_name);
                    return;
                }
                if (!RegexUtils.isIDCard18(idCard)) {
                    ToastManager.info(R.string.hint_input_idcard);
                    return;
                }
                IdentityAuthenticationActivity2.launch(IdentityAuthenticationActivity.this, name, idCard);
            }
        }
    };

}
