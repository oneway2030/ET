package com.xnhb.et.bean;

/**
 * 作者 oneway on 2018/10/20
 * 描述:
 * 参考链接:
 */
public class C2COrderDetailsInfo {

    /**
     * realName : 12323
     * money : 3129.99
     * bankNo : 2324242
     * bankName : 中国工商银行
     * remark : 65010
     * status : 处理中
     * remainingTime : -476269
     */

    private String realName;
    private String money;
    private String bankNo;
    private String bankName;
    private String remark;
    private String status;
    private long remainingTime;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(long remainingTime) {
        this.remainingTime = remainingTime;
    }
}
