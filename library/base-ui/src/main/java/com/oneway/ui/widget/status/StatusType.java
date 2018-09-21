package com.oneway.ui.widget.status;

/**
 * @Description: 视图显示类型
 * @date: 2017-03-08 15:53
 */
public enum StatusType {
    CONTENT(0),//显示内容视图
    LOADING(1),//显示加载视图
    EMPTY(2),//显示空视图
    NETWORK_ERROR(3),//显示网络错误视图
    OTHER_ERROR(4);//显示其他错误视图

    private int type;

    StatusType(int type) {
        this.type = type;
    }

    public static StatusType fromTypeName(int type) {
        for (StatusType t : StatusType.values()) {
            if (t.getType() == type) {
                return t;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }
}
