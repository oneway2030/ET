package com.xnhb.et.bean;

import java.util.List;

/**
 * 作者 oneway on 2018/10/9
 * 描述:
 * 参考链接:
 */
public class WrapCoinInfo {
    /**
     * total : 0
     * currencyList : [{"id":73,"createTime":"2018-09-08 12:37:29","updateTime":"2018-09-08 12:37:29","remark":null,"version":0,"userId":13,"currencyId":1,"using":0,"freeze":0,"phone":"15171446097","realName":null,"currencyName":"ECNY","extract":1,"rechargeSwitch":1,"total":null},{"id":74,"createTime":"2018-09-08 12:37:29","updateTime":"2018-09-08 12:37:29","remark":null,"version":0,"userId":13,"currencyId":5,"using":0,"freeze":0,"phone":"15171446097","realName":null,"currencyName":"ET","extract":1,"rechargeSwitch":1,"total":null},{"id":75,"createTime":"2018-09-08 12:37:29","updateTime":"2018-09-08 12:37:29","remark":null,"version":0,"userId":13,"currencyId":6,"using":0,"freeze":0,"phone":"15171446097","realName":null,"currencyName":"BTC","extract":0,"rechargeSwitch":0,"total":null},{"id":76,"createTime":"2018-09-08 12:37:29","updateTime":"2018-09-08 12:37:29","remark":null,"version":0,"userId":13,"currencyId":7,"using":0,"freeze":0,"phone":"15171446097","realName":null,"currencyName":"ETH","extract":1,"rechargeSwitch":1,"total":null},{"id":77,"createTime":"2018-09-08 12:37:29","updateTime":"2018-09-08 12:37:29","remark":null,"version":0,"userId":13,"currencyId":8,"using":0,"freeze":0,"phone":"15171446097","realName":null,"currencyName":"NWC","extract":1,"rechargeSwitch":1,"total":null},{"id":78,"createTime":"2018-09-08 12:37:29","updateTime":"2018-09-08 12:37:29","remark":null,"version":0,"userId":13,"currencyId":9,"using":0,"freeze":0,"phone":"15171446097","realName":null,"currencyName":"WIZ","extract":0,"rechargeSwitch":0,"total":null},{"id":126,"createTime":"2018-09-29 10:45:25","updateTime":"2018-09-29 10:45:25","remark":null,"version":0,"userId":13,"currencyId":10,"using":0,"freeze":0,"phone":"15171446097","realName":null,"currencyName":"Bitecny","extract":0,"rechargeSwitch":0,"total":null}]
     */

    private String total;
    private List<CoinInfo> currencyList;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<CoinInfo> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<CoinInfo> currencyList) {
        this.currencyList = currencyList;
    }
}
