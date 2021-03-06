package com.xnhb.et.ui.fragment.search;

import java.util.List;

/**
 * 作者 oneway on 2018/10/15
 * 描述:
 * 参考链接:
 */
class SearchInfoResult {

    /**
     * name : ECNY
     * trade : [{"tradeMaxPrice":0,"tradeMinPrice":0,"tradeNums":0,"currentPrice":1.018,"currencyName":"ECNY","tradeCurrencyName":"ET","tradeId":1,"rise_num":0,"rise":"+0.00","con":null,"usings":true,"tradeMoney":0,"encyMoeny":1.018,"showEncyMoeny":1.018,"area":1,"open":1.018,"recordTime":"2018-10-15 21:45:58"},{"tradeMaxPrice":0,"tradeMinPrice":0,"tradeNums":0,"currentPrice":0,"currencyName":"ECNY","tradeCurrencyName":"Bitecny","tradeId":6,"rise_num":0,"rise":"+0","con":null,"usings":true,"tradeMoney":0,"encyMoeny":0,"showEncyMoeny":0,"area":1,"open":0,"recordTime":"2018-10-15 21:45:58"},{"tradeMaxPrice":0,"tradeMinPrice":0,"tradeNums":0,"currentPrice":0,"currencyName":"ECNY","tradeCurrencyName":"BTC","tradeId":8,"rise_num":0,"rise":"+0","con":null,"usings":false,"tradeMoney":0,"encyMoeny":0,"showEncyMoeny":0,"area":2,"open":0,"recordTime":"2018-10-15 21:45:58"},{"tradeMaxPrice":0,"tradeMinPrice":0,"tradeNums":0,"currentPrice":0,"currencyName":"ECNY","tradeCurrencyName":"BTC","tradeId":2,"rise_num":0,"rise":"+0","con":null,"usings":false,"tradeMoney":0,"encyMoeny":0,"showEncyMoeny":0,"area":1,"open":0,"recordTime":"2018-10-15 21:45:58"},{"tradeMaxPrice":0,"tradeMinPrice":0,"tradeNums":0,"currentPrice":3099,"currencyName":"ECNY","tradeCurrencyName":"ETH","tradeId":3,"rise_num":0,"rise":"+0.00","con":null,"usings":true,"tradeMoney":0,"encyMoeny":3099,"showEncyMoeny":3099,"area":1,"open":3099,"recordTime":"2018-10-15 21:45:58"},{"tradeMaxPrice":0,"tradeMinPrice":0,"tradeNums":0,"currentPrice":0.12,"currencyName":"ECNY","tradeCurrencyName":"NWC","tradeId":4,"rise_num":0,"rise":"+0.00","con":null,"usings":true,"tradeMoney":0,"encyMoeny":0.12,"showEncyMoeny":0.12,"area":1,"open":0.12,"recordTime":"2018-10-15 21:45:58"},{"tradeMaxPrice":0,"tradeMinPrice":0,"tradeNums":0,"currentPrice":0,"currencyName":"ECNY","tradeCurrencyName":"WIZ","tradeId":7,"rise_num":0,"rise":"+0","con":null,"usings":false,"tradeMoney":0,"encyMoeny":0,"showEncyMoeny":0,"area":1,"open":0,"recordTime":"2018-10-15 21:45:58"}]
     */

    private String name;
    private List<TradeBean> trade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TradeBean> getTrade() {
        return trade;
    }

    public void setTrade(List<TradeBean> trade) {
        this.trade = trade;
    }

    public static class TradeBean {
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
         * area : 1
         * open : 1.018
         * recordTime : 2018-10-15 21:45:58
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
        private Object con;
        private boolean usings;
        private int tradeMoney;
        private double encyMoeny;
        private double showEncyMoeny;
        private int area;
        private double open;
        private String recordTime;

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

        public Object getCon() {
            return con;
        }

        public void setCon(Object con) {
            this.con = con;
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

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public String getRecordTime() {
            return recordTime;
        }

        public void setRecordTime(String recordTime) {
            this.recordTime = recordTime;
        }
    }
}
