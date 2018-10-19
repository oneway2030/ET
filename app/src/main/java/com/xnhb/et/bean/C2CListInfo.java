package com.xnhb.et.bean;

/**
 * 作者 oneway on 2018/10/18
 * 描述:C2C可买卖的数量
 * 参考链接:
 */
public class C2CListInfo {

    /**
     * id : 1
     * createTime : 2018-07-26 12:25:03
     * updateTime : 2018-10-17 16:14:33
     * remark : null
     * version : 0
     * currencyId : 1
     * currencyName : ENCY
     * price : null
     * sellMin : 100
     * buyMin : 500
     * sellMax : 1000
     * buyMax : 500
     */

    private String id;
    private String createTime;
    private String updateTime;
    private String remark;
    private String version;
    private String currencyId;
    private String currencyName;
    private String price;
    private String sellMin;
    private String buyMin;
    private String sellMax;
    private String buyMax;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSellMin() {
        return sellMin;
    }

    public void setSellMin(String sellMin) {
        this.sellMin = sellMin;
    }

    public String getBuyMin() {
        return buyMin;
    }

    public void setBuyMin(String buyMin) {
        this.buyMin = buyMin;
    }

    public String getSellMax() {
        return sellMax;
    }

    public void setSellMax(String sellMax) {
        this.sellMax = sellMax;
    }

    public String getBuyMax() {
        return buyMax;
    }

    public void setBuyMax(String buyMax) {
        this.buyMax = buyMax;
    }
}
