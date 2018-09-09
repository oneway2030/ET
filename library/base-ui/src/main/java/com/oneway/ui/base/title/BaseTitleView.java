package com.oneway.ui.base.title;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oneway.tool.utils.ui.ActivityUtils;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.R;
import com.oneway.ui.base.in.TitleContainer;

/**
 * 作者 oneway on 2018/7/27
 * 描述:  通用title
 * 参考链接:
 */
public class BaseTitleView extends RelativeLayout implements TitleContainer {

    protected TextView mTitle;
    protected ImageView mBackImageView;
    private LinearLayout mLeftLayout;
    private LinearLayout mCenterLayout;
    private LinearLayout mRightLayout;
    private ImageView mBottomDivide;
    private Context mContext;

    public BaseTitleView(Context context) {
        this(context, null);
    }

    public BaseTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        View inflate = View.inflate(mContext, R.layout.base_title_view_layout, this);
        initView();
    }

    /**
     * 获取实例
     */
    public static BaseTitleView newInstance(ViewGroup parent) {
        return (BaseTitleView) UiUtils.newInstance(parent, R.layout.base_title_view_layout);
    }

    /**
     * 获取实例
     */
    public static BaseTitleView newInstance(Context context) {
        return (BaseTitleView) UiUtils.newInstance(context, R.layout.base_title_view_layout);
    }


    private void initView() {
        mTitle = findViewById(R.id.common_title_view_layout_title);
        mBackImageView = findViewById(R.id.common_title_view_layout_left_arrow);
        mLeftLayout = findViewById(R.id.common_title_view_layout_left_container);
        mCenterLayout = findViewById(R.id.common_title_view_layout_center_container);
        mRightLayout = findViewById(R.id.common_title_view_layout_right_container);
        mBottomDivide = findViewById(R.id.common_title_view_layout_divide);
        initListener();
        setTitleStyle();
    }

    private void initListener() {
        mLeftLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLeftViewClickListener != null) {
                    mLeftViewClickListener.onLeftViewClickListener(v);
                } else {
                    Activity activity = ActivityUtils.findActivity(v);
                    if (activity != null) {
                        try {
                            activity.onBackPressed();
                        } catch (Exception e) {
                            activity.finish();
                        }
                    }
                }
            }
        });
    }

    /**
     * 设置标题
     */
    @Override
    public TitleContainer setTitle(CharSequence title) {
        mTitle.setText(title);
        return this;
    }

    @Override
    public TitleContainer setTitle(int resId) {
        mTitle.setText(UiUtils.getString(resId));
        return this;
    }

    public TextView getTitle() {
        return mTitle;
    }


    @Override
    public TitleContainer hideBack() {
        mBackImageView.setVisibility(View.GONE);
        return this;
    }

    @Override
    public TitleContainer showDivideLine() {
        mBottomDivide.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置左边返回键
     */
    @Override
    public TitleContainer setBackImage(int resId) {
        mBackImageView.setImageResource(resId);
        return this;
    }

    public ImageView getBackImageView() {
        return mBackImageView;
    }

    /**
     * 显示底部阴影分割线
     */
    public void showShadow() {
        mBottomDivide.setVisibility(View.VISIBLE);
    }

    @Override
    public TitleContainer setCenterView(View v, ViewGroup.LayoutParams layoutParams) {
        if (v == null) {
            return this;
        }
        mCenterLayout.removeAllViews();
        if (layoutParams == null) {
            mCenterLayout.addView(v);
        } else {
            mCenterLayout.addView(v, layoutParams);
        }
        return this;
    }

    @Override
    public TitleContainer setLeftView(View v, ViewGroup.LayoutParams layoutParams) {
        if (v == null) {
            return this;
        }
        mLeftLayout.removeAllViews();
        if (layoutParams == null) {
            mLeftLayout.addView(v);
        } else {
            mLeftLayout.addView(v, layoutParams);
        }
        return this;
    }

    @Override
    public TitleContainer setRightView(View v, ViewGroup.LayoutParams layoutParams) {
        if (v == null) {
            return this;
        }
        mRightLayout.removeAllViews();
        if (layoutParams == null) {
            mRightLayout.addView(v);
        } else {
            mRightLayout.addView(v, layoutParams);
        }
        return this;
    }

    @Override
    public View getRightView() {
        return mRightLayout;
    }

    protected void setTitleStyle() {
    }

    private LeftViewClickListener mLeftViewClickListener;

    public interface LeftViewClickListener {
        void onLeftViewClickListener(View view);
    }


    public void setOnLeftViewClickListener(LeftViewClickListener listener) {
        mLeftViewClickListener = listener;
    }

    public TitleLayoutHelper getHelper() {
        return new TitleLayoutHelper();
    }

}

