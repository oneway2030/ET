package com.xnhb.et.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者 oneway on 2018/10/11
 * 描述:
 * 参考链接:
 */
public class OrderInfo implements Parcelable {

    /**
     * id : 109
     * createTime : 2018-10-11 21:08:52
     * updateTime : 2018-10-11 21:08:52
     * remark : null
     * version : 0
     * userId : 13
     * realName : null
     * phone : null
     * currencyTradeId : 5
     * tradeType : 0
     * tradeTypeStr : 买
     * priority : 0
     * quantity : 10.0
     * price : 1.0
     * tradeQuantity : 0.0
     * status : 0
     * statusStr : 未交易
     * areaId : null
     * areaName : null
     * currencyId : null
     * currencyName : ETH
     * tradeCurrencyId : null
     * tradeCurrencyName : NWC
     * nums : null
     * robot : null
     * robotStr : null
     */

    private String id;
    private String createTime;
    private String updateTime;
    private String remark;
    private String version;
    private String userId;
    private String realName;
    private String phone;
    private String currencyTradeId;
    private String tradeType;
    private String tradeTypeStr; // 委托 买卖状态
    private String typeStr;//成交 买卖状态
    private String priority;
    private double quantity; //委托 挂单量
    private double tradeQuantity;//成交 挂单量
    private double price; //委托 价格
    private double tradePrice; //成交 价格
    private String status;
    private String statusStr;
    private String areaId;
    private String areaName;
    private String currencyId;
    private String tradeCurrencyId;
    private String nums;
    private String robot;
    private String robotStr;
    private String currencyName;
    private String tradeCurrencyName; //委托 交易名字
    private String trandeCurrencyName;//成交 交易名字

    public double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public static Creator<OrderInfo> getCREATOR() {
        return CREATOR;
    }

    public String getTrandeCurrencyName() {
        return trandeCurrencyName;
    }

    public void setTrandeCurrencyName(String trandeCurrencyName) {
        this.trandeCurrencyName = trandeCurrencyName;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurrencyTradeId() {
        return currencyTradeId;
    }

    public void setCurrencyTradeId(String currencyTradeId) {
        this.currencyTradeId = currencyTradeId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeTypeStr() {
        return tradeTypeStr;
    }

    public void setTradeTypeStr(String tradeTypeStr) {
        this.tradeTypeStr = tradeTypeStr;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTradeQuantity() {
        return tradeQuantity;
    }

    public void setTradeQuantity(double tradeQuantity) {
        this.tradeQuantity = tradeQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public String getTradeCurrencyId() {
        return tradeCurrencyId;
    }

    public void setTradeCurrencyId(String tradeCurrencyId) {
        this.tradeCurrencyId = tradeCurrencyId;
    }

    public String getTradeCurrencyName() {
        return tradeCurrencyName;
    }

    public void setTradeCurrencyName(String tradeCurrencyName) {
        this.tradeCurrencyName = tradeCurrencyName;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getRobot() {
        return robot;
    }

    public void setRobot(String robot) {
        this.robot = robot;
    }

    public String getRobotStr() {
        return robotStr;
    }

    public void setRobotStr(String robotStr) {
        this.robotStr = robotStr;
    }

    public OrderInfo() {
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
        dest.writeString(this.remark);
        dest.writeString(this.version);
        dest.writeString(this.userId);
        dest.writeString(this.realName);
        dest.writeString(this.phone);
        dest.writeString(this.currencyTradeId);
        dest.writeString(this.tradeType);
        dest.writeString(this.tradeTypeStr);
        dest.writeString(this.typeStr);
        dest.writeString(this.priority);
        dest.writeDouble(this.quantity);
        dest.writeDouble(this.tradeQuantity);
        dest.writeDouble(this.price);
        dest.writeDouble(this.tradePrice);
        dest.writeString(this.status);
        dest.writeString(this.statusStr);
        dest.writeString(this.areaId);
        dest.writeString(this.areaName);
        dest.writeString(this.currencyId);
        dest.writeString(this.tradeCurrencyId);
        dest.writeString(this.nums);
        dest.writeString(this.robot);
        dest.writeString(this.robotStr);
        dest.writeString(this.currencyName);
        dest.writeString(this.tradeCurrencyName);
        dest.writeString(this.trandeCurrencyName);
    }

    protected OrderInfo(Parcel in) {
        this.id = in.readString();
        this.createTime = in.readString();
        this.updateTime = in.readString();
        this.remark = in.readString();
        this.version = in.readString();
        this.userId = in.readString();
        this.realName = in.readString();
        this.phone = in.readString();
        this.currencyTradeId = in.readString();
        this.tradeType = in.readString();
        this.tradeTypeStr = in.readString();
        this.typeStr = in.readString();
        this.priority = in.readString();
        this.quantity = in.readDouble();
        this.tradeQuantity = in.readDouble();
        this.price = in.readDouble();
        this.tradePrice = in.readDouble();
        this.status = in.readString();
        this.statusStr = in.readString();
        this.areaId = in.readString();
        this.areaName = in.readString();
        this.currencyId = in.readString();
        this.tradeCurrencyId = in.readString();
        this.nums = in.readString();
        this.robot = in.readString();
        this.robotStr = in.readString();
        this.currencyName = in.readString();
        this.tradeCurrencyName = in.readString();
        this.trandeCurrencyName = in.readString();
    }

    public static final Creator<OrderInfo> CREATOR = new Creator<OrderInfo>() {
        @Override
        public OrderInfo createFromParcel(Parcel source) {
            return new OrderInfo(source);
        }

        @Override
        public OrderInfo[] newArray(int size) {
            return new OrderInfo[size];
        }
    };
}
