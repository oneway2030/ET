package com.oneway.ui.common;

import android.graphics.Color;

import com.oneway.tool.utils.ui.UiUtils;


/**
 * Created by Administrator on 2018/01/10.
 */

public class GridItemDecoration extends UniversalItemDecoration {
    int space = UiUtils.dp2px(5);
    int decorationColor = Color.parseColor("#edeff4");
    int itemCount;

    public GridItemDecoration() {

    }

    public GridItemDecoration(int itemCount) {
        this(itemCount, -1, -1);
    }

    public GridItemDecoration(int itemCount, int space) {
        this(itemCount, space, -1);
    }

    public GridItemDecoration(int itemCount, int space, int color) {
        this.itemCount = itemCount;
        if (space != -1)
            this.space = UiUtils.dp2px(space);
        if (color != -1)
            this.decorationColor = UiUtils.getColor(color);
    }

    @Override
    public Decoration getItemOffsets(int position, int childCount) {
        ColorDecoration decoration = new ColorDecoration();
        decoration.decorationColor = decorationColor;
        decoration.left = space;
        decoration.right = space;
        decoration.top = space;
        decoration.bottom = space;
        return decoration;
    }

}
