package com.oneway.ui.adapter.vp;

import android.content.Context;
import android.view.View;

import com.oneway.tool.utils.ui.UiUtils;

import butterknife.ButterKnife;

/**
 * 作者 oneway on 2018/10/31
 * 描述: viewpage的子页面
 * 参考链接:
 */
public abstract class BaseSubPage {

    protected View rootView;
    protected Context mContext;
    protected int position;

    public BaseSubPage(Context context, int position) {
        this.mContext = context;
        this.position = position;
        rootView = UiUtils.newInstance(context, getlayou());
        ButterKnife.bind(this, rootView);
        init();
    }

    protected abstract int getlayou();

    protected abstract void init();


    public View getView() {
        return rootView;
    }

    protected void onDestroy() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void updata() {
    }

}
