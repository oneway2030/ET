package com.xnhb.et.bean;

import java.util.List;

/**
 * 作者 oneway on 2018/10/15
 * 描述:
 * 参考链接:
 */
public class WrapSearchResult<T> {

    /**
     * name : ECNY
     * trade : [{"tradeMaxPrice":0,"tradeMinPrice":0,"tradeNums":0,"currentPrice":1.018,"currencyName":"ECNY","tradeCurrencyName":"ET","tradeId":1,"rise_num":0,"rise":"+0.00","con":null,"usings":true,"tradeMoney":0,"encyMoeny":1.018,"showEncyMoeny":1.018,"area":1,"open":1.018,"recordTime":"2018-10-15 21:45:58"},{"tradeMaxPrice":0,"tradeMinPrice":0,"tradeNums":0,"currentPrice":0,"currencyName":"ECNY","tradeCurrencyName":"Bitecny","tradeId":6,"rise_num":0,"rise":"+0","con":null,"usings":true,"tradeMoney":0,"encyMoeny":0,"showEncyMoeny":0,"area":1,"open":0,"recordTime":"2018-10-15 21:45:58"},{"tradeMaxPrice":0,"tradeMinPrice":0,"tradeNums":0,"currentPrice":0,"currencyName":"ECNY","tradeCurrencyName":"BTC","tradeId":8,"rise_num":0,"rise":"+0","con":null,"usings":false,"tradeMoney":0,"encyMoeny":0,"showEncyMoeny":0,"area":2,"open":0,"recordTime":"2018-10-15 21:45:58"},{"tradeMaxPrice":0,"tradeMinPrice":0,"tradeNums":0,"currentPrice":0,"currencyName":"ECNY","tradeCurrencyName":"BTC","tradeId":2,"rise_num":0,"rise":"+0","con":null,"usings":false,"tradeMoney":0,"encyMoeny":0,"showEncyMoeny":0,"area":1,"open":0,"recordTime":"2018-10-15 21:45:58"},{"tradeMaxPrice":0,"tradeMinPrice":0,"tradeNums":0,"currentPrice":3099,"currencyName":"ECNY","tradeCurrencyName":"ETH","tradeId":3,"rise_num":0,"rise":"+0.00","con":null,"usings":true,"tradeMoney":0,"encyMoeny":3099,"showEncyMoeny":3099,"area":1,"open":3099,"recordTime":"2018-10-15 21:45:58"},{"tradeMaxPrice":0,"tradeMinPrice":0,"tradeNums":0,"currentPrice":0.12,"currencyName":"ECNY","tradeCurrencyName":"NWC","tradeId":4,"rise_num":0,"rise":"+0.00","con":null,"usings":true,"tradeMoney":0,"encyMoeny":0.12,"showEncyMoeny":0.12,"area":1,"open":0.12,"recordTime":"2018-10-15 21:45:58"},{"tradeMaxPrice":0,"tradeMinPrice":0,"tradeNums":0,"currentPrice":0,"currencyName":"ECNY","tradeCurrencyName":"WIZ","tradeId":7,"rise_num":0,"rise":"+0","con":null,"usings":false,"tradeMoney":0,"encyMoeny":0,"showEncyMoeny":0,"area":1,"open":0,"recordTime":"2018-10-15 21:45:58"}]
     */

    private String name;
    private List<T> trade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getTrade() {
        return trade;
    }

    public void setTrade(List<T> trade) {
        this.trade = trade;
    }
}
