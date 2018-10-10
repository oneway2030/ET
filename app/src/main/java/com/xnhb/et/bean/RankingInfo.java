package com.xnhb.et.bean;

/**
 * 作者 oneway on 2018/9/29
 * 描述:首页排行榜信息
 * 参考链接:
 */
public class RankingInfo {

//    {"code":0,"msg":"查询成功","obj":[
// {"tradeMaxPrice":0E-8,"tradeMinPrice":0E-8,"tradeNums":0E-8,"currentPrice":1.50000000,"currencyName":"ECNY","tradeCurrencyName":"ET","tradeId":1,"rise_num":0.00,"rise":"+0.00","con":null,"usings":true,"tradeMoney":0E-16,"encyMoeny":1.50000000,"showEncyMoeny":1.500,"area":1,"open":1.50000000,"recordTime":"2018-10-10 11:02:39"},
// {"tradeMaxPrice":44387.00000000,"tradeMinPrice":44385.60000000,"tradeNums":3.91037100,"currentPrice":44386.90000000,"currencyName":"ECNY","tradeCurrencyName":"BTC","tradeId":2,"rise_num":0.00,"rise":"+0.00","con":null,"usings":true,"tradeMoney":173566.4258047000000000,"encyMoeny":44386.90000000,"showEncyMoeny":44386.900,"area":1,"open":44386.00000000,"recordTime":"2018-10-10 11:02:39"},
// {"tradeMaxPrice":1577.80000000,"tradeMinPrice":1560.99000000,"tradeNums":4.17545693,"currentPrice":1561.86000000,"currencyName":"ECNY","tradeCurrencyName":"ETH","tradeId":3,"rise_num":-1.00,"rise":"-1.00","con":"down","usings":true,"tradeMoney":6552.2417765146000000,"encyMoeny":1561.86000000,"showEncyMoeny":1561.860,"area":1,"open":1577.62000000,"recordTime":"2018-10-10 11:02:39"},
// {"tradeMaxPrice":0E-8,"tradeMinPrice":0E-8,"tradeNums":0E-8,"currentPrice":0.02870000,"currencyName":"ECNY","tradeCurrencyName":"NWC","tradeId":4,"rise_num":0.00,"rise":"+0.00","con":null,"usings":true,"tradeMoney":0E-16,"encyMoeny":0.02870000,"showEncyMoeny":0.028,"area":1,"open":0.02870000,"recordTime":"2018-10-10 11:02:39"}]}

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
    private String currentPrice;//当前成交价格
    private String encyMoeny; //交易相对ENCY价格
    private String currencyName; //简称
    private String tradeCurrencyName;//商业全明
    private String rise; //24h涨跌幅
    private String tradeMoney;//24h成交额
    //
//    private double tradeMaxPrice;
//    private String tradeMinPrice;
//    private double tradeNums;
//    private long tradeId;
//    private int rise_num;
//    private boolean usings;
//    private int showEncyMoeny;
//    private int area;


    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getEncyMoeny() {
        return encyMoeny;
    }

    public void setEncyMoeny(String encyMoeny) {
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
