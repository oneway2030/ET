package com.oneway.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.R;


/**
 * @author oneway
 * @describe
 * @since 2018/9/15 0015
 */


public class CommomHorizontalLayout extends FrameLayout {
    private Context mContext;
    private int leftTextColor;
    private int underlineColor;
    private int rightTextColor;
    private String leftText;
    private String rightText;
    private int rightTextSize;
    private int leftTextSize;
    private int moreIcon;
    private boolean isRightTextIcon;

    public CommomHorizontalLayout(@NonNull Context context) {
        this(context, null);
    }

    public CommomHorizontalLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CommomHorizontalLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        View.inflate(context, R.layout.widget_commom_horizontal_layout, this);
        initAttrs(attrs);
        initView();
    }


    @SuppressLint("ResourceAsColor")
    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CommomHorizontalLayout);
        leftText = typedArray.getString(R.styleable.CommomHorizontalLayout_left_text);
        leftTextColor = typedArray.getColor(R.styleable.CommomHorizontalLayout_left_text_color, R.color.text);
        leftTextSize = typedArray.getDimensionPixelSize(R.styleable.CommomHorizontalLayout_left_text_size, UiUtils.sp2px(12));
        rightText = typedArray.getString(R.styleable.CommomHorizontalLayout_right_text);
        rightTextColor = typedArray.getColor(R.styleable.CommomHorizontalLayout_right_text_color, R.color.text);
        rightTextSize = typedArray.getDimensionPixelSize(R.styleable.CommomHorizontalLayout_right_text_size, UiUtils.sp2px(12));
        underlineColor = typedArray.getColor(R.styleable.CommomHorizontalLayout_underline_color, R.color.text);
        moreIcon = typedArray.getResourceId(R.styleable.CommomHorizontalLayout_moreIcon, 0);
        typedArray.recycle();
    }

    //    R.mipmap.more_right_black
    private void initView() {
        TextView tvLeft = findViewById(R.id.tv_left);
        TextView tvRight = findViewById(R.id.tv_right);
        ImageView ivMore = findViewById(R.id.iv_more);
        tvLeft.setText(leftText);
        tvLeft.setTextColor(leftTextColor);
        tvLeft.setTextSize(UiUtils.px2sp(leftTextSize));
        ivMore.setVisibility(moreIcon > 0 ? View.VISIBLE : View.GONE);
        tvRight.setVisibility(TextUtils.isEmpty(rightText) ? View.GONE : View.VISIBLE);
        ivMore.setImageResource(moreIcon);
        if (!TextUtils.isEmpty(rightText)) {
            tvRight.setText(rightText);
            tvRight.setTextColor(rightTextColor);
            tvRight.setTextSize(UiUtils.px2sp(rightTextSize));
        }
    }


}
