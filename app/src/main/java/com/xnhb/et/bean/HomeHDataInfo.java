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

    private int tradeMaxPrice;
    private int tradeMinPrice;
    private int tradeNums;
    private double currentPrice;
    private String currencyName;
    private String tradeCurrencyName;
    private int tradeId;
    private int rise_num;
    private String rise;
    private String con;  //跌: down   升 null
    private boolean usings;
    private int tradeMoney;
    private double encyMoeny;
    private double showEncyMoeny;
    private int area;

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public int getTradeMaxPrice() {
        return tradeMaxPrice;
    }

    public void setTradeMaxPrice(int tradeMaxPrice) {
        this.tradeMaxPrice = tradeMaxPrice;
    }

    public int getTradeMinPrice() {
        return tradeMinPrice;
    }

    public void setTradeMinPrice(int tradeMinPrice) {
        this.tradeMinPrice = tradeMinPrice;
    }

    public int getTradeNums() {
        return tradeNums;
    }

    public void setTradeNums(int tradeNums) {
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

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public int getRise_num() {
        return rise_num;
    }

    public void setRise_num(int rise_num) {
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

    public int getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(int tradeMoney) {
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

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}
