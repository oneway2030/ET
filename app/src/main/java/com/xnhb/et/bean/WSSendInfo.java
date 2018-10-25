package com.xnhb.et.bean;

/**
 * 作者 oneway on 2018/10/25
 * 描述: websocket 送的协议
 * 参考链接:
 */
public class WSSendInfo {
    private String method;
    private String tradeId;
    private String token;

    public void reset() {
        method="";
        tradeId="";
        token="";
    }

    public WSSendInfo() {
    }

    public WSSendInfo(String method, String trade) {
        this.method = method;
        this.tradeId = trade;
    }

    public WSSendInfo(String method, String trade, String token) {
        this.method = method;
        this.tradeId = trade;
        this.token = token;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
