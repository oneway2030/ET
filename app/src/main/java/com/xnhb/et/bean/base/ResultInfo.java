package com.xnhb.et.bean.base;

import java.io.Serializable;

/**
 * @author oneway
 * @describe
 * @since 2018/9/9 0009
 */


public class ResultInfo<T> implements Serializable {

    private T obj;
    private int code;
    private String msg;

    @Override
    public String toString() {
        return "ResultInfo{" +
                "result=" + obj +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public boolean isSuccess() {
        return 0 == code;
    }

    public T getResult() {
        return obj;
    }

    public void setResult(T result) {
        this.obj = result;
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
