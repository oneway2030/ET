package com.xnhb.et.bean.base;

import java.util.ArrayList;

/**
 * 作者 oneway on 2018/10/18
 * 描述:
 * 参考链接:
 */
public class ArrayResultInfo<T> {

    private ArrayList<T> obj;
    private int code; //0成功 -1错误  -2 登录失效 -3未认证 -4冻结
    private String msg;

    public ArrayList<T> getObj() {
        return obj;
    }

    public void setObj(ArrayList<T> obj) {
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
