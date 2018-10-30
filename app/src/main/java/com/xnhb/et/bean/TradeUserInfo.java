package com.xnhb.et.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 oneway on 2018/10/25
 * 描述:
 * 参考链接:
 */
public class TradeUserInfo {

    /**
     * tradeWallet : {"createTime":1536381449000,"currencyId":5,"freeze":303,"id":74,"updateTime":1539242325000,"userId":13,"using":49697,"version":3}
     * wallet : {"createTime":1536381449000,"currencyId":1,"freeze":0,"id":73,"updateTime":1536381449000,"userId":13,"using":0,"version":0}
     * phone : 15171446097
     * hasTradePassword : false
     * tradeRecordList : []
     * tradeList : []
     */

    private TradeWalletBean tradeWallet;
    private WalletBean wallet;
    private String phone;
    private boolean hasTradePassword;
    private ArrayList<OrderInfo> tradeRecordList;
    private ArrayList<OrderInfo> tradeList;

    public TradeWalletBean getTradeWallet() {
        return tradeWallet;
    }

    public void setTradeWallet(TradeWalletBean tradeWallet) {
        this.tradeWallet = tradeWallet;
    }

    public WalletBean getWallet() {
        return wallet;
    }

    public void setWallet(WalletBean wallet) {
        this.wallet = wallet;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isHasTradePassword() {
        return hasTradePassword;
    }

    public void setHasTradePassword(boolean hasTradePassword) {
        this.hasTradePassword = hasTradePassword;
    }

    public ArrayList<OrderInfo> getTradeRecordList() {
        return tradeRecordList;
    }

    public void setTradeRecordList(ArrayList<OrderInfo> tradeRecordList) {
        this.tradeRecordList = tradeRecordList;
    }

    public ArrayList<OrderInfo> getTradeList() {
        return tradeList;
    }

    public void setTradeList(ArrayList<OrderInfo> tradeList) {
        this.tradeList = tradeList;
    }

    public static class TradeWalletBean {
        /**
         * createTime : 1536381449000
         * currencyId : 5
         * freeze : 303.0
         * id : 74
         * updateTime : 1539242325000
         * userId : 13
         * using : 49697.0
         * version : 3
         */

        private long createTime;
        private String currencyId;
        private String freeze;
        private String id;
        private long updateTime;
        private String userId;
        private String using;
        private String version;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(String currencyId) {
            this.currencyId = currencyId;
        }

        public String getFreeze() {
            return freeze;
        }

        public void setFreeze(String freeze) {
            this.freeze = freeze;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsing() {
            return using;
        }

        public void setUsing(String using) {
            this.using = using;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static class WalletBean {
        /**
         * createTime : 1536381449000
         * currencyId : 1
         * freeze : 0.0
         * id : 73
         * updateTime : 1536381449000
         * userId : 13
         * using : 0.0
         * version : 0
         */

        private long createTime;
        private String currencyId;
        private double freeze;
        private String id;
        private long updateTime;
        private String userId;
        private String using;
        private String version;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(String currencyId) {
            this.currencyId = currencyId;
        }

        public double getFreeze() {
            return freeze;
        }

        public void setFreeze(double freeze) {
            this.freeze = freeze;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsing() {
            return using;
        }

        public void setUsing(String using) {
            this.using = using;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
