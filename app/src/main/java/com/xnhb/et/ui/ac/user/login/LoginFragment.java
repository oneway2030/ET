package com.xnhb.et.ui.ac.user.login;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oneway.ui.base.fragment.BaseFragment;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.widget.btn.StateButton;
import com.oneway.ui.widget.et.XEditText;
import com.xnhb.et.R;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/12
 * 描述:
 * 参考链接:
 */
public class LoginFragment extends BaseFragment {

    @BindView(R.id.et_account)
    XEditText etAccount;
    @BindView(R.id.et_pwd)
    XEditText etPwd;
    @BindView(R.id.btn_login)
    StateButton btnLogin;
    @BindView(R.id.iv_check)
    ImageView ivCheck;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.remember_account_layout)
    RelativeLayout rememberAccountLayout;
    private boolean isChecked;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        rememberAccountLayout.setOnClickListener(mPerfectClickListener);
        tvForgetPwd.setOnClickListener(mPerfectClickListener);
        btnLogin.setOnClickListener(mPerfectClickListener);
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.remember_account_layout) {
                ivCheck.setImageResource(isChecked ? R.mipmap.checkbox_unchecked : R.mipmap.checkbox_checked);
                isChecked = !isChecked;
            } else if (id == R.id.tv_forget_pwd) {//忘记密码
                if (listener != null) {
                    listener.OnLaunchForgetPwdPage(v);
                }
            } else if (id == R.id.btn_login) {//登录

            }
        }
    };
    OnClickForgetPwdPageListener listener;

    public void setOnClickForgetPwdPageListener(OnClickForgetPwdPageListener listener) {
        this.listener = listener;
    }

    public interface OnClickForgetPwdPageListener {
        void OnLaunchForgetPwdPage(View v);
    }
}
