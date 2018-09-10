package com.oneway.ui.base.in;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oneway.ui.base.title.TitleLayoutHelper;

/**
 * 作者 oneway on 2018/7/30
 * 描述:
 * 参考链接:
 */
public interface TitleContainer {
    /**
     * 设置标题
     */
    TitleContainer setTitle(CharSequence title);

    /**
     * 设置标题
     */
    TitleContainer setTitle(int resId);

    /**
     * 设置标题
     */
    TextView getTitle();

    /**
     * 设置返回图标
     */
    TitleContainer setBackImage(int resId);

    /**
     * 隐藏返回键
     * 默认显示
     */
    TitleContainer hideBack();

    /**
     * 显示分割线
     * 默认不现实
     */
    TitleContainer showDivideLine();

    /**
     * 设置左边布局
     *
     * @param view         左边父布局
     * @param layoutParams 布局参数 可为null
     */
    TitleContainer setLeftView(View view, ViewGroup.LayoutParams layoutParams);

    TitleContainer setRightView(View view, ViewGroup.LayoutParams layoutParams);

    TitleContainer setCenterView(View view, ViewGroup.LayoutParams layoutParams);


    /**
     * 获取左边布局
     */
    View getLfteView();

    /**
     * 获取右边布局
     */
    View getRightView();

    /**
     * 获取帮助类
     */
    TitleLayoutHelper getHelper();
}
