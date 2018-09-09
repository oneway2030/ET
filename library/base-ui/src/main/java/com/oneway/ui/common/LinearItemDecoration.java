package com.oneway.ui.common;

import android.graphics.Color;

import com.oneway.tool.utils.ui.UiUtils;


/**
 * Created by Administrator on 2018/01/10.
 */

public class LinearItemDecoration extends UniversalItemDecoration {
    int space = UiUtils.dp2px(0.5f);
    int decorationColor = Color.GRAY;

    /**
     * 使用默认灰色 高0.5dp. 灰色分割线
     */
    public LinearItemDecoration() {

    }

    public LinearItemDecoration(int space) {
        this.space = space;
    }

    public LinearItemDecoration(int space, int color) {
        this.space = UiUtils.dp2px(space);
        this.decorationColor = UiUtils.getColor(color);
    }

    @Override
    public Decoration getItemOffsets(int position, int childCount) {
        //这里应该是你的判断逻辑  判断当前position 需要上下左右的分割线到底是多少 以及颜色
        ColorDecoration decoration = new ColorDecoration();
        //这是一个示例代码  这里要根据你自己的逻辑判断当前item需要上下左右的分割线到底是多少 以及颜色
        decoration.top = space;
        decoration.decorationColor = decorationColor;
        return decoration;
    }

}
