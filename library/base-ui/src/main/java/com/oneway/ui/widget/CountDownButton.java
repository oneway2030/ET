package com.oneway.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.R;


/**
 * 作者 oneway on 2018/6/25
 * 描述: 按钮倒计时
 * 参考链接:
 */
public class CountDownButton extends AppCompatButton {
    //倒计时时候的 后缀, 如 51s后重新获取
    private final String COUNT_DOWN_SUFFIX = "s后重新获取";
    //开始的文本
    private final String START_TEXT = "获取验证码";
    //默认总倒计时时常 默认60秒
    private final int DEF_TIME = 60 * 1000;
    //倒计时间隔时间 默认1秒一跳
    private final int DEF_INTERVAL_TIME = 1 * 1000;
    //总时长
    private long millisinfuture;
    //间隔时长
    private long countdowninterva;
    //默认背景颜色 为0则不现实
    private int normalBgColor;
    //倒计时 背景颜色
    private int countDownBgColor;
    //默认文本颜色
    private int normalTextColor;
    //倒计时 文本颜色
    private int countDownTextColor;

    //是否结束
    private boolean isFinish;

    //定时器
    private CountDownTimer countDownTimer;

    public CountDownButton(Context context) {
        this(context, null);
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("ResourceAsColor")
    public CountDownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CountDownButton, defStyleAttr, 0);
        //文字颜色
        normalTextColor = typedArray.getColor(R.styleable.CountDownButton_android_textColor,R.color.text);
        countDownTextColor = typedArray.getColor(R.styleable.CountDownButton_countdown_textColor, normalTextColor);
        //背景颜色
        normalBgColor = typedArray.getResourceId(R.styleable.CountDownButton_android_background, 0);
        //设置倒计时 背景色
        countDownBgColor = typedArray.getColor(R.styleable.CountDownButton_countDownBgColor, normalBgColor);
        //设置默认时长
        millisinfuture = (long) typedArray.getInt(R.styleable.CountDownButton_millisinfuture, DEF_TIME);
        //设置默认间隔时长
        countdowninterva = (long) typedArray.getInt(R.styleable.CountDownButton_countdown_interva, DEF_INTERVAL_TIME);
        typedArray.recycle();
        //默认文字和背景色
        endSetupUi();
        //设置定时器
        countDownTimer = new CountDownTimer(millisinfuture, countdowninterva) {
            @Override
            public void onTick(long millisUntilFinished) {
                //未结束
                countDownSetupUi(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                //结束
                endSetupUi();
            }
        };
    }

    /**
     * 设置初始和倒计时结束时的ui
     */
    private void endSetupUi() {
        isFinish = true;
        CountDownButton.this.setEnabled(true);
        setText(START_TEXT);
        setTextColor(normalTextColor);
        setBackgroundResource(normalBgColor);
    }

    /**
     * 设置倒计时开始和正在倒计时, 的ui
     */
    private void countDownSetupUi(double millisUntilFinished) {
        isFinish = false;
        setText(getCountDownTime(millisUntilFinished));
        setTextColor(countDownTextColor);
        setBackgroundResource(countDownBgColor);
    }

    /**
     * 获取倒计时时间
     * 這里是做一个倒计时 时间的叫校准
     *
     * @param millisUntilFinished 倒计时的总时长
     * @return 剩余时间 如  42s后重新获取
     */
    @NonNull
    private String getCountDownTime(double millisUntilFinished) {
        return (Math.round(millisUntilFinished / 1000) - 1) + COUNT_DOWN_SUFFIX;
    }


    /**
     * 设置背景颜色
     *
     * @param normalBg    初始状态的背景颜色
     * @param countDownBg 倒计时背景颜色
     */
    public void setBackground(int normalBg, int countDownBg) {
        this.normalBgColor = normalBg;
        this.countDownBgColor = countDownBg;
        setBackgroundResource(normalBg);
    }

    /**
     * 设置文字颜色
     *
     * @param normalTextColor    字体初始状态颜色
     * @param countDownTextColor 字体倒计时颜色
     */
    public void setTextColor(int normalTextColor, int countDownTextColor) {
        this.normalTextColor = UiUtils.getColor(normalTextColor);
        this.countDownTextColor = UiUtils.getColor(countDownTextColor);
        setTextColor(this.normalTextColor);
    }

    public void setTextColor(String normalTextColor, String countDownTextColor) {
        this.normalTextColor = Color.parseColor(normalTextColor);
        this.countDownTextColor = Color.parseColor(countDownTextColor);
        setTextColor(this.countDownTextColor);
    }


    /**
     * 是否完成倒计时
     *
     * @return
     */
    public boolean isFinish() {
        this.setEnabled(true);
        return isFinish;
    }

    /**
     * 取消倒计时
     */
    public void cancel() {
        this.setEnabled(true);
        endSetupUi();
        isFinish = true;
        countDownTimer.cancel();
    }

    /**
     * 开始倒计时
     */
    public void start() {
        this.setEnabled(false);
        isFinish = false;
        countDownTimer.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancel();
    }
}
