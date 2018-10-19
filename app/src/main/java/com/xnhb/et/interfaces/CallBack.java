package com.xnhb.et.interfaces;

import com.xnhb.et.bean.UserInfo;

/**
 * 作者 oneway on 2018/9/19
 * 描述:
 * 参考链接:
 */
public interface CallBack<T> {
    void success(T t);

    default void fail() {
    }
}
