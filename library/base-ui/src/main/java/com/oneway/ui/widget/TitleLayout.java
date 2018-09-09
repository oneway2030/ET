package com.oneway.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.R;
import com.oneway.ui.common.PerfectClickListener;


/**
 * Created by Administrator on 2017/11/18.
 */

public class TitleLayout extends Toolbar {

    private static Context mContext;
    private TextView titleLeft;
    private LinearLayout titleCenterLayout;
    private TextView titleCenter;
    private ImageView ivTitleIcon;
    private TextView titleRight;

    public TitleLayout(Context context) {
        this(context, null);
    }

    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView(context);
        parseStyle(context, attrs);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_toolbar, this);
        titleLeft = (TextView) findViewById(R.id.title_left);
        titleCenterLayout = (LinearLayout) findViewById(R.id.title_center_layout);
        titleCenter = (TextView) findViewById(R.id.title_center);
        ivTitleIcon = (ImageView) findViewById(R.id.iv_title_icon);
        titleRight = (TextView) findViewById(R.id.title_right);
    }


    public void setDefConfig(final Activity ac) {
        setLeftImage(R.mipmap.back_left_white);
        setLeftClick(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                ac.finish();
            }
        });
    }


    /**
     * 设置文字
     */
    public void setLeftText(int strId) {
        String str = mContext.getResources().getString(strId);
        setLeftText(str);
    }

    public void setLeftText(String leftStr) {
        if (leftStr == null) return;
        titleLeft.setText(leftStr + "");
    }


    public void setRightText(int strId) {
        String str = mContext.getResources().getString(strId);
        setRightText(str);
    }

    public void setRightText(String rightStr) {
        if (rightStr == null) return;
        titleRight.setText(rightStr + "");
    }

    public void setCenterTitle(int strId) {
        String str = mContext.getResources().getString(strId);
        setCenterTitle(str);
    }

    public void setCenterTitle(String centerStr) {
        if (centerStr == null) return;
        titleCenter.setText(centerStr + "");
    }

    /**
     * 文字颜色
     */
    public void setLeftTextColor(@ColorInt int color) {
        titleLeft.setTextColor(UiUtils.getColor(color));
    }

    public void setCenterTextColor(@ColorInt int color) {
        titleCenter.setTextColor(UiUtils.getColor(color));
    }

    public void setRightTextColor(@ColorInt int color) {
        titleRight.setTextColor(UiUtils.getColor(color));
    }

    /**
     * 设置图片
     */
    public void setLeftImage(int leftDrawableId) {
        if (leftDrawableId > -1)
            titleLeft.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, leftDrawableId), null, null, null);
    }

    public void setRightImage(int leftDrawableId) {
        if (leftDrawableId > -1)
            titleRight.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(mContext, leftDrawableId), null);
    }

    public void setCenterImage(int leftDrawableId) {
        if (leftDrawableId > -1) {
            ivTitleIcon.setImageResource(leftDrawableId);
            ivTitleIcon.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置点击
     */
    public void setLeftClick(OnClickListener l) {
        titleLeft.setOnClickListener(l);
    }

    public void setCenterClick(OnClickListener l) {
        titleCenterLayout.setOnClickListener(l);
    }

    public void setRithtClick(OnClickListener l) {
        titleRight.setOnClickListener(l);
    }

    protected void parseStyle(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleLayout);
        String CustomToolbar_lt_text = ta.getString(R.styleable.TitleLayout_lt_text);
        String CustomToolbar_center_text = ta.getString(R.styleable.TitleLayout_center_text);
        String CustomToolbar_right_text = ta.getString(R.styleable.TitleLayout_right_text);
        int CustomToolbar_lt_image = ta.getResourceId(R.styleable.TitleLayout_lt_image, -1);
        int CustomToolbar_center_image = ta.getResourceId(R.styleable.TitleLayout_center_image, -1);
        int CustomToolbar_right_image = ta.getResourceId(R.styleable.TitleLayout_right_image, -1);
        setLeftText(CustomToolbar_lt_text);
        setCenterTitle(CustomToolbar_center_text);
        setRightText(CustomToolbar_right_text);
        setLeftImage(CustomToolbar_lt_image);
        setCenterImage(CustomToolbar_center_image);
        setRightImage(CustomToolbar_right_image);
        ta.recycle();
    }


    /**
     * dp转px
     */
    public static int dp2px(int dp) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

}
