package com.oneway.ui.widget.tv;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.R;

import java.util.ArrayList;

/**
 * 作者 oneway on 2018/9/11
 * 描述: 垂直滚动的文本
 * 参考链接:
 */
public class AutoVerticalTextView extends LinearLayout {

    private Context mContext;
    private ViewFlipper mViewFlipper;
    private int def_color = Color.BLACK;
    private int textColor;
    private int textSize;
    private int side;
    private OnItemClickListener listener;

    public AutoVerticalTextView(Context context) {
        this(context, null);
    }

    public AutoVerticalTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoVerticalTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.AutoVerticalTextView);
        side = a.getInteger(R.styleable.AutoVerticalTextView_android_gravity, Gravity.LEFT);
        textColor = a.getColor(R.styleable.AutoVerticalTextView_textColor, def_color);
        textSize = a.getDimensionPixelSize(R.styleable.AutoVerticalTextView_textSise, UiUtils.sp2px(12));
        a.recycle();

        this.mContext = context;
        initView(context);
    }

    private void initView(Context context) {
        mViewFlipper = new ViewFlipper(context);
        LinearLayout.LayoutParams vfParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mViewFlipper.setLayoutParams(vfParams);
        addView(mViewFlipper);
    }

    public void setTextList(ArrayList<String> data) {
        if (EmptyUtils.isEmpty(data)) {
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            TextView tv = new TextView(mContext);
            ViewFlipper.LayoutParams params = new ViewFlipper.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = side;
            tv.setTextSize(UiUtils.px2sp(textSize));
            tv.setEllipsize(TextUtils.TruncateAt.END);
            tv.setMaxLines(1);
            tv.setTextColor(textColor);
            tv.setLayoutParams(params);
            tv.setText(data.get(i));
            int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(v, finalI);
                    }
                }
            });
            mViewFlipper.addView(tv);
        }
        //是否自动开始滚动
        mViewFlipper.setAutoStart(true);
        //滚动时间
        mViewFlipper.setFlipInterval(2000);
        //开始滚动
        mViewFlipper.startFlipping();
        //出入动画
        mViewFlipper.setOutAnimation(mContext, R.anim.push_up_out);
        mViewFlipper.setInAnimation(mContext, R.anim.push_down_in);
    }

    public void startAutoScroll() {
        mViewFlipper.setAutoStart(true);
    }

    public void stopAutoScroll() {
        mViewFlipper.stopFlipping();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int posstion);
    }
}
