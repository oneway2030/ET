package com.xnhb.et.bean;

/**
 * 作者 oneway on 2018/9/29
 * 描述: 首页横向列表数据
 * 参考链接:
 */
public class HomeHDataInfo {

    /**
     * tradeMaxPrice : 0
     * tradeMinPrice : 0
     * tradeNums : 0
     * currentPrice : 1.018
     * currencyName : ECNY
     * tradeCurrencyName : ET
     * tradeId : 1
     * rise_num : 0
     * rise : +0.00
     * con : null
     * usings : true
     * tradeMoney : 0
     * encyMoeny : 1.018
     * showEncyMoeny : 1.018
     * area : 0
     */

    private String tradeMaxPrice;
    private String tradeMinPrice;
    private String tradeNums;
    private double currentPrice;
    private String currencyName;
    private String tradeCurrencyName;
    private String tradeId;
    private String rise_num;
    private String rise;
    private String con;  //跌: down   升 null
    private boolean usings;
    private String tradeMoney;
    private double encyMoeny;
    private double showEncyMoeny;
    private String area;

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public String getTradeMaxPrice() {
        return tradeMaxPrice;
    }

    public void setTradeMaxPrice(String tradeMaxPrice) {
        this.tradeMaxPrice = tradeMaxPrice;
    }

    public String getTradeMinPrice() {
        return tradeMinPrice;
    }

    public void setTradeMinPrice(String tradeMinPrice) {
        this.tradeMinPrice = tradeMinPrice;
    }

    public String getTradeNums() {
        return tradeNums;
    }

    public void setTradeNums(String tradeNums) {
        this.tradeNums = tradeNums;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
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

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getRise_num() {
        return rise_num;
    }

    public void setRise_num(String rise_num) {
        this.rise_num = rise_num;
    }

    public String getRise() {
        return rise;
    }

    public void setRise(String rise) {
        this.rise = rise;
    }


    public boolean isUsings() {
        return usings;
    }

    public void setUsings(boolean usings) {
        this.usings = usings;
    }

    public String getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(String tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public double getEncyMoeny() {
        return encyMoeny;
    }

    public void setEncyMoeny(double encyMoeny) {
        this.encyMoeny = encyMoeny;
    }

    public double getShowEncyMoeny() {
        return showEncyMoeny;
    }

    public void setShowEncyMoeny(double showEncyMoeny) {
        this.showEncyMoeny = showEncyMoeny;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
