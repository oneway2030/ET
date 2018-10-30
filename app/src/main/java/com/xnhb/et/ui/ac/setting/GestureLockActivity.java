package com.xnhb.et.ui.ac.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.oneway.tool.event.BusManager;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.base.ac.ActivityManager;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.btn.StateButton;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;
import com.wangnan.library.painter.JDFinancePainter;
import com.xnhb.et.R;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.ui.ac.detail.CoinDetailsActivity;
import com.xnhb.et.ui.ac.user.LoginAndRegisterActivity;
import com.xnhb.et.ui.ac.user.register.IRegisterView;
import com.xnhb.et.ui.ac.user.register.RegsiterPresenter;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/10/24
 * 描述: 手势解锁
 * 参考链接:
 */
public class GestureLockActivity extends BaseTitleActivity<RegsiterPresenter> implements OnGestureLockListener, IRegisterView {
    @BindView(R.id.glv)
    GestureLockView mGestureLockView;
    @BindView(R.id.tv_switch_login)
    TextView tvSwitchLogin;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.btn_submit)
    StateButton btnSubmit;
    public final static int LOGIN = 0;//0.密码登录
    public final static int RESET_PWD = 1;//1.重新设置手势解锁
    private int pageType = 0; //
    private String pwd = "";
    private int errorCount = 0;

    public static void launch(Context context, int pageType) {
        Intent intent = new Intent();
        intent.setClass(context, GestureLockActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra("pageType", pageType);
        context.startActivity(intent);
    }

    @Override
    public RegsiterPresenter newP() {
        return new RegsiterPresenter();
    }

    @Override
    protected boolean getIntent(Intent intent) {
        pageType = intent.getIntExtra("pageType", -1);
        return pageType < 0;
    }

    @Override
    protected String getTitleText() {
        return pageType == LOGIN ? "解锁登陆" : "手势解锁";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_gesture_lock;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mGestureLockView.setPainter(new JDFinancePainter());
        mGestureLockView.setGestureLockListener(this);
        if (pageType == 0) {//登录
            tvSwitchLogin.setOnClickListener(mPerfectClickListener);
            tvSwitchLogin.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.GONE);
            tvHint.setVisibility(View.GONE);
            tvHint.setText("");
        } else {
            tvHint.setText("请设置手势密码");
            btnSubmit.setOnClickListener(mPerfectClickListener);
            tvHint.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.VISIBLE);
            tvSwitchLogin.setVisibility(View.GONE);
        }
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.tv_switch_login) {
                ActivityManager.getInstance().removeActivity(GestureLockActivity.this);
                LoginAndRegisterActivity.launch(GestureLockActivity.this);
            } else if (id == R.id.btn_submit) {//重置
                pwd = "";
                tvHint.setText("请设置手势密码");
            }
        }
    };

    /**
     * 解锁开始监听方法
     */
    @Override
    public void onStarted() {

    }

    /**
     * 解锁密码改变监听方法
     */
    @Override
    public void onProgress(String progress) {

    }

    /**
     * 解锁完成监听方法
     */
    @Override
    public void onComplete(String result) {
        if (TextUtils.isEmpty(result)) {
            return;
        }
        switch (pageType) {
            case LOGIN:
                checkPwd(result);
                break;
            case RESET_PWD:
                setPwd(result);
                break;
            default:
                break;
        }
//         if ("012345678".equals(result)) {
//            mGestureLockView.clearView();
//        } else {
//            mGestureLockView.showErrorStatus(400);
//        }
    }


    /**
     * 1.如果在设置密码第一步,则缓存密码信息
     * 2.如果第一步已完成,那么校验密码是否相同, 如果相同,则缓存在sp配置文件中
     *
     * @param result
     */
    private void setPwd(String result) {
        if (EmptyUtils.isEmpty(pwd)) {
            tvHint.setText("请再设置一次手势密码");
            pwd = result;
            mGestureLockView.clearView();
        } else {
            if (pwd.equals(result)) {
                ToastManager.info("手势密码设置成功");
                tvHint.setText("手势密码设置成功");
                UserInfoHelper.getInstance().saveGestureLockPwd(pwd);
                mGestureLockView.clearView();
                ActivityManager.getInstance().removeActivity(SettingGestureLockActivity.class);
                finish();
            } else {//两次设置不一致
                pwd = "";
                mGestureLockView.showErrorStatus(400);
                tvHint.setText("两次设置手势不一致,请重新设置");
                tvHint.setVisibility(View.VISIBLE);
            }
        }
    }


    /**
     * 校验密码,并登录
     */
    private void checkPwd(String result) {
        mGestureLockView.clearView();
        String gestureLockPwd = UserInfoHelper.getInstance().getGestureLockPwd();
        if (gestureLockPwd.equals(result)) {
            ToastManager.info("正在登录...");
            String accountName = UserInfoHelper.getInstance().getAccountName();
            String accountPwd = UserInfoHelper.getInstance().getAccountPwd();
            getP().login(accountName, accountPwd);
        } else {
            ToastManager.info(errorCount == 2 ? "手势密码错误3次,请使用密码登录" : "手势密码错误");
            mGestureLockView.showErrorStatus(400);
            errorCount++;
            if (errorCount == 3) {
                ActivityManager.getInstance().removeActivity(this);
                LoginAndRegisterActivity.launch(this);
            }
        }
    }

    @Override
    public void startCountDown() {

    }

    @Override
    public void cancelCountDown() {

    }

    @Override
    public void submit() {
        //登录成功
        ActivityManager.getInstance().removeActivity(this);
        BusManager.getBus().post(EventBusTags.TAG_LOGIN_SUCDESS, 0);
    }
}
