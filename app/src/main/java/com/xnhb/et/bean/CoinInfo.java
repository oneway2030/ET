package com.xnhb.et.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 作者 oneway on 2018/9/28
 * 描述: 币信息
 * 参考链接:
 */
public class CoinInfo implements Parcelable {

    /**
     * id : 73
     * createTime : 2018-09-08 12:37:29
     * updateTime : 2018-09-08 12:37:29
     * remark : null
     * version : 0
     * userId : 13
     * currencyId : 1
     * using : 0
     * freeze : 0
     * phone : 15171446097
     * realName : null
     * currencyName : ECNY
     * extract : 1
     * rechargeSwitch : 1
     * total : null
     */

    private String id;
    private String createTime;
    private String updateTime;
    //    private Object remark;
    private String version;
    private String userId;
    private String currencyId;
    private String using;
    private String freeze;
    private String phone;
    private String realName;
    private String currencyName;
    private int extract;
    private int rechargeSwitch;
    private String total;

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

    public String getUsing() {
        return using;
    }

    public void setUsing(String using) {
        this.using = using;
    }

    public String getFreeze() {
        return freeze;
    }

    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getRealName() {
        return realName;
    }


    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public int getExtract() {
        return extract;
    }

    public void setExtract(int extract) {
        this.extract = extract;
    }

    public int getRechargeSwitch() {
        return rechargeSwitch;
    }

    public void setRechargeSwitch(int rechargeSwitch) {
        this.rechargeSwitch = rechargeSwitch;
    }

    public Object getTotal() {
        return total;
    }


    public CoinInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.createTime);
        dest.writeString(this.updateTime);
        dest.writeString(this.version);
        dest.writeString(this.userId);
        dest.writeString(this.currencyId);
        dest.writeString(this.using);
        dest.writeString(this.freeze);
        dest.writeString(this.phone);
        dest.writeString(this.realName);
        dest.writeString(this.currencyName);
        dest.writeInt(this.extract);
        dest.writeInt(this.rechargeSwitch);
        dest.writeString(this.total);
    }

    protected CoinInfo(Parcel in) {
        this.id = in.readString();
        this.createTime = in.readString();
        this.updateTime = in.readString();
        this.version = in.readString();
        this.userId = in.readString();
        this.currencyId = in.readString();
        this.using = in.readString();
        this.freeze = in.readString();
        this.phone = in.readString();
        this.realName = in.readString();
        this.currencyName = in.readString();
        this.extract = in.readInt();
        this.rechargeSwitch = in.readInt();
        this.total = in.readString();
    }

    public static final Creator<CoinInfo> CREATOR = new Creator<CoinInfo>() {
        @Override
        public CoinInfo createFromParcel(Parcel source) {
            return new CoinInfo(source);
        }

        @Override
        public CoinInfo[] newArray(int size) {
            return new CoinInfo[size];
        }
    };
}
