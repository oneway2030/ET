package com.oneway.ui.base.ac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.oneway.tool.event.BusManager;
import com.oneway.ui.R;
import com.oneway.ui.base.fragment.BaseLazyFragment;
import com.oneway.ui.base.in.IPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者 oneway on 2018/6/8
 * 描述:
 * 参考链接:
 */
public abstract class XBaseActivity<P extends IPresenter> extends MVPActivity<P> {
    protected Context mContext = XBaseActivity.this;
    private Unbinder bind;
    protected BaseLazyFragment mFragment;
    private InputMethodManager imm;
    private boolean mIsDestroyed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //去标题
        super.onCreate(savedInstanceState);
        try {
            int layoutResID = bindView(savedInstanceState);
            //如果 setLayoutView 返回 0, 框架则不会调用 setContentView(), 当然也不会 Bind ButterKnife
            if (layoutResID != 0) {
                setContentView(layoutResID);
                //绑定到butterknife
            }
            if (getIntent(getIntent())) {
                finish();
                return;
            }
            bindBefor();
            if (isBindView()) {
                bind = ButterKnife.bind(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        initStatusBar();
//        initDialog();
        if (isEneableBus())
            BusManager.getBus().register(this);
//        BusInstant.getBus().register(this);
        registerCommonButton();
        initData(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
    }


    protected boolean isEneableBus() {
        return true;
    }


    protected void initStatusBar() {
    }

    protected abstract int bindView(Bundle savedInstanceState);

    protected abstract void initData(Bundle savedInstanceState);


    /**
     * 一般用于接收其他界面过来的参数,
     * 默认 false 如果 该函数返回true则不继续走下面的逻辑
     */

    protected boolean getIntent(Intent intent) {
        return false;
    }

    protected void bindBefor() {

    }

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
//        ActivityManager.getInstance().addActivity(this);
    }

    /**
     * 注册多个界面共享的按钮的点击监听
     */
    protected void registerCommonButton() {
        View view = findViewById(R.id.back);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
//                    ActivityManager.getInstance().killSingleActivity(XBaseActivity.this);
                }
            });
        }
    }

    protected void addOnClickListener(View.OnClickListener l, View... v) {
        for (View view : v) {
            view.setOnClickListener(l);
        }
    }


    /**
     * 返回当前 Activity 使用的布局
     */
    public boolean isBindView() {
        return true;
    }

    private static Toast mToast = null;

    /**
     * 全局吐司
     */
    protected void toast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * 全局跳转
     */
    protected void goToActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mFragment != null && mFragment.onKeyDown(keyCode, event))
            return true;
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        try {
            if (onBackPressedConsumedByFragment()) {
                return;
            }
            super.onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean onBackPressedConsumedByFragment() {
        return mFragment != null && mFragment.isAdded() && mFragment.onBackPressed();
    }

    public void replaceFragment(Fragment newFragment) {
        replaceFragment(newFragment, 0, null, false);
    }

    public void replaceFragment(Fragment newFragment, int id) {
        replaceFragment(newFragment, id, null, false);
    }

    public void replaceFragment(Fragment newFragment, int id, Bundle arguments) {
        replaceFragment(newFragment, id, null, false);
    }

    public void replaceFragment(Fragment newFragment, int id, Bundle arguments, boolean isAddStack) {
        if (isFinishing()) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (arguments != null) {
            newFragment.setArguments(arguments);
        }
        if (id == 0) {
            id = R.id.fragment_container;
        }
        transaction.replace(id, newFragment);
        if (isAddStack) {
            transaction.addToBackStack(null);
        }
        if (!isDestroyed()) {
            transaction.commitAllowingStateLoss();
        }
    }


    @Override
    public boolean isDestroyed() {
        try {
            return super.isDestroyed();
        } catch (NoSuchMethodError e) {
            return mIsDestroyed;
        }
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusManager.getBus().unregister(this);
        mIsDestroyed = true;
        hideSoftKeyBoard();
        if (bind != null) {
            bind.unbind();
        }
        ActivityManager.getInstance().removeActivity(this);
    }


    //    private void initDialog() {
//        dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
//        dialog.getProgressHelper().setBarColor(Color.parseColor(AppConstant.PROGRESS_COLOR));
//        dialog.setTitleText("加载中...");
//        dialog.setCancelable(false);
//    }
//
//    public void setDialogCancel(boolean isCancel) {
//        if (dialog != null) {
//            dialog.setCancelable(isCancel);
//        }
//    }
//    public void showLoading() {
//        if (dialog != null) {
//            dialog.show();
//        }
//    }
//
//    public void dismissLoading() {
//        if (dialog != null && dialog.isShowing()) {
//            dialog.dismiss();
//        }
//    }
}
