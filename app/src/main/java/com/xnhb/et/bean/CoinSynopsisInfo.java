package com.xnhb.et.bean;

/**
 * 作者 oneway on 2018/10/25
 * 描述:
 * 参考链接:
 */
public class CoinSynopsisInfo {

    /**
     * currencyName : ECNY
     * currencyId : 1
     * currencyTradeName : ET
     * currencyTradeId : 5
     */

    private String currencyName;
    private int currencyId;
    private String currencyTradeName;
    private int currencyTradeId;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyTradeName() {
        return currencyTradeName;
    }

    public void setCurrencyTradeName(String currencyTradeName) {
        this.currencyTradeName = currencyTradeName;
    }

    public int getCurrencyTradeId() {
        return currencyTradeId;
    }

    public void setCurrencyTradeId(int currencyTradeId) {
        this.currencyTradeId = currencyTradeId;
    }
}
