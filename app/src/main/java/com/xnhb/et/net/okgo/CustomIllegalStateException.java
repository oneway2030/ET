package com.xnhb.et.net.okgo;

/**
 * 作者 oneway on 2018/8/21
 * 描述:
 * 参考链接:
 */
public class CustomIllegalStateException extends IllegalStateException {
    private int errorCode;

    public CustomIllegalStateException( int errorCode,String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
