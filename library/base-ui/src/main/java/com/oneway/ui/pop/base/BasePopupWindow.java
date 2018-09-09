package com.oneway.ui.pop.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wanwei on 2018/5/8.
 */

public abstract class BasePopupWindow extends PopupWindow {
    protected int def_height = ViewGroup.LayoutParams.WRAP_CONTENT;
    WindowManager mWindowManager;
    Context mContext;
    ViewGroup mRootView;
    protected View inflateView;
    protected Unbinder bind;
    private MyAnimationListener animListener;
    boolean isStopAnim = false; //是否正在执行关闭动画,如果是则,不重复执行动画

    public View getView() {
        return mRootView;
    }

    public Context getContext() {
        return mContext;
    }

    public BasePopupWindow(Context context) {
        super(context);
        this.mContext = context;
        this.mWindowManager = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
    }

    public int getScreenWidth() {
        return mWindowManager.getDefaultDisplay().getWidth();
    }

    public int getScreenHeight() {
        return mWindowManager.getDefaultDisplay().getHeight();
    }

    protected <T extends View> T findViewById(@IdRes int id) {
        return (T) mRootView.findViewById(id);
    }

    protected void setContentView(@LayoutRes int resource) {
        //制作容器
        mRootView = new FrameLayout(getContext());
        //设置容器获得点击事件 否则容器下层视图会被触发
        mRootView.setClickable(true);
        //装载视图
        inflateView = LayoutInflater.from(mContext).inflate(resource, null);
        bind = ButterKnife.bind(this, inflateView);
        mRootView.addView(inflateView);
        inflateView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        inflateView.getLayoutParams().height = def_height;
        //设置SelectPicPopupWindow的View
        startAnim();
        setContentView(mRootView);
//        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(def_height);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
//        this.setOutsideTouchable(isMyOutsideTouchable());
        this.setBackgroundDrawable(new ColorDrawable());
        this.setOutsideTouchable(isCanceledOnTouchOutside());
        setTouchListener();
        //虚拟按键
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

    }

    /**
     * 点击外部是否消失 默认true
     */
    protected boolean isCanceledOnTouchOutside() {
        return true;
    }


    /**
     * 点击外部消失或者不消失的解决方案
     */
    protected void setTouchListener() {
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!isOutsideTouchable()) {
                    View mView = getContentView();
                    if (null != mView)
                        mView.dispatchTouchEvent(motionEvent);
                }
                return isFocusable() && !isOutsideTouchable();
            }
        });
    }

    /**
     * 关闭pop
     */
    private void close() {
        super.dismiss();
        backgroundAlpha((Activity) mContext, 1f);
        if (bind != null) {
            bind.unbind();
        }
    }

    /**
     * 关闭pop 判断是否先启动动画
     */
    @Override
    public void dismiss() {
        if (addStopAnim() != 0) {
            stopAnim();
            return;
        }
        close();
    }

    /**
     * 关闭动画
     */
    protected void stopAnim() {
        if (isStopAnim) {
            return;
        }
        Animation animation = AnimationUtils.loadAnimation(getContext(), addStopAnim());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (animListener != null) {
                    animListener.onAnimationStart(animation, false);
                }
//                llLayout.setBackgroundColor(Color.TRANSPARENT);
                isStopAnim = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (animListener != null) {
                    animListener.onAnimationEnd(animation, false);
                }
                isStopAnim = false;
                close();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if (animListener != null) {
                    animListener.onAnimationRepeat(animation, false);
                }
            }
        });
        inflateView.startAnimation(animation);
    }

    /**
     * 启动动画
     */
    protected void startAnim() {
        if (addStartAnim() != 0) {
            mRootView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    Animation animation = AnimationUtils.loadAnimation(getContext(), addStartAnim());
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            if (animListener != null) {
                                animListener.onAnimationStart(animation, true);
                            }
//                                llLayout.setBackgroundColor(Color.TRANSPARENT);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (animListener != null) {
                                animListener.onAnimationEnd(animation, true);
                            }
//                            llLayout.setBackgroundColor(UiUtil.getColor(R.color.translucent));
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            if (animListener != null) {
                                animListener.onAnimationRepeat(animation, true);
                            }
                        }
                    });
                    inflateView.startAnimation(animation);
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                }
            });
        }
    }

    protected int addStartAnim() {
        return 0;
    }


    protected int addStopAnim() {
        return 0;
    }

    /**
     * 设置布局的高度 默认 ViewGroup.LayoutParams.WRAP_CONTENT
     *
     * @param height
     */
    public void setLayoutHeight(int height) {
        def_height = height;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().setAttributes(lp);
    }

    /**
     * 显示显示popupWindow里面的内容；
     */
    public void show(View view) {
        showAsDropDown(view);
        backgroundAlpha((Activity) mContext, 0.5f);
    }

    //    public void showPop(View v) {
//        showAtLocation(v, Gravity.BOTTOM, 0, UiUtil.dp2px(76));
//    }
    public void setAnimListener(MyAnimationListener stopListener) {
        this.animListener = stopListener;
    }


    public interface MyAnimationListener {
        void onAnimationStart(Animation animation, boolean isStartAnim);

        void onAnimationEnd(Animation animation, boolean isStartAnim);

        void onAnimationRepeat(Animation animation, boolean isStartAnim);
    }
}
