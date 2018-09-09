package com.oneway.tool.test;

/**
 * Created by oneway on 2017/12/25.
 */

public class HttpRuslt<T> {
    public String status;
    public String msg;
    public T t;

    @Override
    public String toString() {
        return "HttpRuslt{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
