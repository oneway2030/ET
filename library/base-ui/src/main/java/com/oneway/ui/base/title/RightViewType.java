package com.oneway.ui.base.title;

/**
 * 作者 oneway on 2018/8/27
 * 描述:
 * 参考链接:
 */
public enum RightViewType {
    LEFT("left"), //
    CENTER("center"),//
    RIGHT("right"), //
    TEXT("text"); //
    String type;

    RightViewType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
