package com.xnhb.et.bean;

/**
 * 作者 oneway on 2018/9/29
 * 描述:首页排行榜信息
 * 参考链接:
 */
public class RankingInfo {


    /**
     * tradeMaxPrice : 0
     * tradeMinPrice : 0
     * tradeNums : 0
     * currentPrice : 0
     * currencyName : ECNY
     * tradeCurrencyName : Bitecny
     * tradeId : 6
     * rise_num : 0
     * rise : +0
     * con : null
     * usings : true
     * tradeMoney : 0
     * encyMoeny : 0
     * showEncyMoeny : 0
     * area : 1
     */
    private double currentPrice;//当前成交价格
    private double encyMoeny; //交易相对ENCY价格
    private String currencyName; //简称
    private String tradeCurrencyName;//商业全明
    private String rise; //24h涨跌幅
    private String tradeMoney;//24h成交额
    //
//    private double tradeMaxPrice;
//    private double tradeMinPrice;
//    private double tradeNums;
//    private long tradeId;
//    private int rise_num;
//    private boolean usings;
//    private int showEncyMoeny;
//    private int area;


    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getEncyMoeny() {
        return encyMoeny;
    }

    public void setEncyMoeny(double encyMoeny) {
        this.encyMoeny = encyMoeny;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getTradeCurrencyName() {
        return tradeCurrencyName;
    }

    public void setTradeCurrencyName(String tradeCurrencyName) {
        this.tradeCurrencyName = tradeCurrencyName;
    }

    public String getRise() {
        return rise;
    }

    public void setRise(String rise) {
        this.rise = rise;
    }

    public String getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(String tradeMoney) {
        this.tradeMoney = tradeMoney;
    }
}
