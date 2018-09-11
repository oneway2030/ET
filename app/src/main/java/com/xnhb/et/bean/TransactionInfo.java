package com.xnhb.et.bean;

/**
 * @author oneway
 * @describe 首页交易信息
 * @since 2018/9/12 0012
 */


public class TransactionInfo {
    private String name;  //名字
    private String unitPrice; //单价
    private String rmbUnitPrice; //RMB单价
    private String increase; //涨幅
    private boolean isAdd;

    public TransactionInfo() {
    }

    public TransactionInfo(String name, String unitPrice, String rmbUnitPrice, String increase, boolean isAdd) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.rmbUnitPrice = rmbUnitPrice;
        this.increase = increase;
        this.isAdd = isAdd;
    }

    public String getRmbUnitPrice() {
        return rmbUnitPrice;
    }

    public void setRmbUnitPrice(String rmbUnitPrice) {
        this.rmbUnitPrice = rmbUnitPrice;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getIncrease() {
        return increase;
    }

    public void setIncrease(String increase) {
        this.increase = increase;
    }
}
