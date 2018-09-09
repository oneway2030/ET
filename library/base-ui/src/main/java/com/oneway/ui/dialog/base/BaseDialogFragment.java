package com.oneway.ui.dialog.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.oneway.ui.R;

/**
 * DialogFragment 实现沉浸式的基类
 * Created by geyifeng on 2017/8/26.
 */

public abstract class BaseDialogFragment extends DialogFragment {

    protected Activity mActivity;
    private View mRootView;
    private Dialog mDialog;

    protected ImmersionBar mImmersionBar;
    protected Window mWindow;
    protected int mWidth;  //屏幕宽度
    protected int mHeight;  //屏幕高度
    private Unbinder unbinder;
    private DialogDismissListener mDialogDismissListener;

    /**
     * @return dialog对应初始化视图
     */
    protected abstract int getLayout();

    /**
     * dialog初始化方法
     */
    protected abstract void init(Bundle savedInstanceState, View contentView);

    /**
     * 用于获取该tag对应对话框
     */
    protected String getDialogTag() {
        return getClass().getName();
    }

    /**
     * @return 物理返回键回调
     */
    protected boolean onKeyBack() {
        return false;
    }


    /**
     * @return 对话框以外的地方是否可以点击取消对话框
     */
    protected boolean getCanCancelOutSide() {
        return true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Custom_Dialog);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayout(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        if (isImmersionBarEnabled())
            initImmersionBar();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    return onKeyBack();
                }
                return false;
            }
        });
        init(savedInstanceState, mRootView);
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        dialog.setCanceledOnTouchOutside(getCanCancelOutSide());  //点击外部消失
        mWindow = dialog.getWindow();
        //测量宽高
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            mWidth = dm.widthPixels;
            mHeight = dm.heightPixels;
        } else {
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            mWidth = metrics.widthPixels;
            mHeight = metrics.heightPixels;
        }
        if (getActivity() != null && !getActivity().isFinishing()) {
            if (getDialog() == null) {
                return;
            }
            initWindow(mWindow);
        }
    }

    protected abstract void initWindow(Window window);

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    /**
     * @return 该对话是否正在展示
     */
    public boolean isShowing() {
        if (getActivity() == null) {
            return false;
        }
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(getDialogTag());
        return null != fragment;
    }

    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this, getDialog());
        mImmersionBar.init();
    }


    /**
     * 找到activity的控件
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findActivityViewById(@IdRes int id) {
        return (T) mActivity.findViewById(id);
    }

    /**
     * 通过fragmentManager展示对话框，避免默认commit方法stateLoss等问题
     *
     * @param fragmentManager 后期对于拿不到fm的调用地方，需要获取当前activity方法，从而扩展方法
     */

    public void showD(FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            return;
        }
        if (!isAdded()) {
            String tag = getDialogTag();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment != null) {
                ft.remove(fragment);
            }
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        }
    }


    public void setDialogDismissListener(DialogDismissListener dialogDismissListener) {
        mDialogDismissListener = dialogDismissListener;
    }

    public interface DialogDismissListener {
        void dismissAction();
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
        }
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (mDialogDismissListener != null) {
            mDialogDismissListener.dismissAction();
        }
    }
}
