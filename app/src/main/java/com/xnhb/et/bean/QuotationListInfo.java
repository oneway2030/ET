package com.xnhb.et.bean;

/**
 * 作者 oneway on 2018/10/8
 * 描述: 行情列表
 * 参考链接:
 */
public class QuotationListInfo {

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
    private String rise;  //涨跌幅
    private String tradeMaxPrice;
    private String tradeMinPrice;
    private String tradeNums;
    private String currentPrice;
    private String currencyName;
    private String tradeCurrencyName;
    private String tradeId;
    private String rise_num;
    private String con;  //跌: down   升 null
    private boolean usings;
    private String tradeMoney;
    private String encyMoeny;
    private String showEncyMoeny;
    private String area;
    private boolean isCollection;//是否自选

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
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

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
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

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
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

    public String getEncyMoeny() {
        return encyMoeny;
    }

    public void setEncyMoeny(String encyMoeny) {
        this.encyMoeny = encyMoeny;
    }

    public String getShowEncyMoeny() {
        return showEncyMoeny;
    }

    public void setShowEncyMoeny(String showEncyMoeny) {
        this.showEncyMoeny = showEncyMoeny;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
