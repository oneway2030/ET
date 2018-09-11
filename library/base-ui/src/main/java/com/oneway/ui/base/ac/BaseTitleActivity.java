package com.oneway.ui.base.ac;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.BaseUiConfig;
import com.oneway.ui.R;
import com.oneway.ui.base.in.IPresenter;
import com.oneway.ui.base.in.TitleContainer;
import com.oneway.ui.base.title.BaseTitleView;

/**
 * Created by Administrator on 2018/1/16.
 */

public abstract class BaseTitleActivity<P extends IPresenter> extends BaseStatusBarActivity<P> {
    protected Toolbar mToolbar;
    protected FrameLayout mFragmentContainer;
    protected ActionBar mActionBar;
    protected TitleContainer mCustomTitleView;
    protected final int DEF_COLOR = R.color.colorPrimary;

    protected abstract String getTitleText();


    //自定义toolbar 重写改方法
    protected void customTitle(TitleContainer toolbar) {
    }

    @Override
    public int bindView(@Nullable Bundle savedInstanceState) {
        return R.layout.base_activity_title;
    }

    public abstract int setLayoutId();

    @Override
    protected void bindBefor() {
        mToolbar = findViewById(R.id.toolbar);
        mFragmentContainer = findViewById(R.id.fragment_container);
        mCustomTitleView = getMyTitleContainer();
        setDefaultUi(mCustomTitleView);
        customTitle(mCustomTitleView);
        initToolbar(mToolbar);
        setCustomTitleView(mCustomTitleView);
        if (setLayoutId() > 0) {
            View contentView = View.inflate(this, setLayoutId(), null);
            mFragmentContainer.addView(contentView);
        }
    }

    protected void setDefaultUi(TitleContainer mCustomTitleView) {
        if (BaseUiConfig.getInstance().getTitleDefBackImag() != -1)
            mCustomTitleView.setBackImage(BaseUiConfig.getInstance().getTitleDefBackImag());
        if (BaseUiConfig.getInstance().getTitleDefBgColor() != -1)
            mToolbar.setBackgroundColor(BaseUiConfig.getInstance().getTitleDefBgColor());
        if (BaseUiConfig.getInstance().getTitleDefTextColor() != -1)
            mCustomTitleView.getTitle().setTextColor(UiUtils.getColor(BaseUiConfig.getInstance().getTitleDefTextColor()));
    }


    @Override
    protected void setStatusBar() {
        //todo 默认白色状态栏
//        setWhitetStatusBar(mToolbar);
        mToolbar.setBackgroundResource(DEF_COLOR);
        setTitleBar(mToolbar);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected TitleContainer getMyTitleContainer() {
        return new BaseTitleView(this);
    }

    private void initToolbar(Toolbar toolbar) {
        customizeToolbar(toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setHomeAsUpIndicator(R.mipmap.back_left_white);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        setTitle(getTitleText());
    }

    /**
     * 设置自定义 view 使用
     */
    public <V extends TitleContainer> void setCustomTitleView(V view) {
        if (view instanceof View) {
            mActionBar.setDisplayHomeAsUpEnabled(false);
            mActionBar.setDisplayShowTitleEnabled(false);
            mActionBar.setDisplayShowHomeEnabled(false);
            mActionBar.setDisplayShowCustomEnabled(true);
            ActionBar.LayoutParams layoutParams =
                    new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                            ActionBar.LayoutParams.MATCH_PARENT);
            mActionBar.setCustomView((View) view, layoutParams);
            if (((View) view).getParent() instanceof Toolbar) {
                Toolbar parent = (Toolbar) ((View) view).getParent();
                parent.setContentInsetsAbsolute(0, 0);
            }
            mCustomTitleView = view;
        } else if (view == null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setDisplayShowTitleEnabled(true);
            mActionBar.setDisplayShowHomeEnabled(true);
            mActionBar.setDisplayShowCustomEnabled(false);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if (mCustomTitleView != null) {
            mCustomTitleView.setTitle(title);
            return;
        }
        super.setTitle(title);
    }


    protected void hideActionBar() {
        if (null != mActionBar) {
            mActionBar.hide();
        }
    }

    protected void customizeToolbar(Toolbar toolbar) {

    }
}
