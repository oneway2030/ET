package com.xnhb.et.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者 oneway on 2018/9/20
 * 描述: 充值和提现 实体
 * 参考链接:
 */
public class HistoricalInfo implements Parcelable {

    /**
     * id : 21
     * createTime : 2018-10-11 15:37:53
     * updateTime : 2018-10-11 15:37:53
     * remark : null
     * version : 0
     * userId : 13
     * currencyId : 5
     * quantity : 102.0
     * address : 11122
     * realQuantity : 152.0
     * rete : null
     * status : 0
     * dealDate : 2018-10-11 15:37:53
     * currencyName : ET
     * phone : 15171446097
     * realName : null
     * statusStr : 提现申请中
     * hash : null
     * realQuantityStr : 102.00000000
     * idStr : 21
     */

    private String id;
    private String hash;
    private String createTime;
    private String updateTime;
    private String remark;
    private String version;
    private String userId;
    private String currencyId;
    private double quantity;
    private String address;
    private double realQuantity;
    //    private Object rete;
    private String status;
    private String dealDate;
    private String currencyName;
    private String phone;
    private String realName;
    private String statusStr;

    private String realQuantityStr;
    private String idStr;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRealQuantity() {
        return realQuantity;
    }

    public void setRealQuantity(double realQuantity) {
        this.realQuantity = realQuantity;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }


    public String getRealQuantityStr() {
        return realQuantityStr;
    }

    public void setRealQuantityStr(String realQuantityStr) {
        this.realQuantityStr = realQuantityStr;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public HistoricalInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.hash);
        dest.writeString(this.createTime);
        dest.writeString(this.updateTime);
        dest.writeString(this.remark);
        dest.writeString(this.version);
        dest.writeString(this.userId);
        dest.writeString(this.currencyId);
        dest.writeDouble(this.quantity);
        dest.writeString(this.address);
        dest.writeDouble(this.realQuantity);
        dest.writeString(this.status);
        dest.writeString(this.dealDate);
        dest.writeString(this.currencyName);
        dest.writeString(this.phone);
        dest.writeString(this.realName);
        dest.writeString(this.statusStr);
        dest.writeString(this.realQuantityStr);
        dest.writeString(this.idStr);
    }

    protected HistoricalInfo(Parcel in) {
        this.id = in.readString();
        this.hash = in.readString();
        this.createTime = in.readString();
        this.updateTime = in.readString();
        this.remark = in.readString();
        this.version = in.readString();
        this.userId = in.readString();
        this.currencyId = in.readString();
        this.quantity = in.readDouble();
        this.address = in.readString();
        this.realQuantity = in.readDouble();
        this.status = in.readString();
        this.dealDate = in.readString();
        this.currencyName = in.readString();
        this.phone = in.readString();
        this.realName = in.readString();
        this.statusStr = in.readString();
        this.realQuantityStr = in.readString();
        this.idStr = in.readString();
    }

    public static final Creator<HistoricalInfo> CREATOR = new Creator<HistoricalInfo>() {
        @Override
        public HistoricalInfo createFromParcel(Parcel source) {
            return new HistoricalInfo(source);
        }

        @Override
        public HistoricalInfo[] newArray(int size) {
            return new HistoricalInfo[size];
        }
    };
}
