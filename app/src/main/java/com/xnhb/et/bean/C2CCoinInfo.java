package com.xnhb.et.bean;

/**
 * 作者 oneway on 2018/10/19
 * 描述:
 * 参考链接:
 */
public class C2CCoinInfo {

    /**
     * buyPrice : 1
     * sellPrice : 0.99
     * wallet : {"id":73,"createTime":"2018-09-08 12:37:29","updateTime":"2018-09-08 12:37:29","remark":null,"version":0,"userId":13,"currencyId":1,"using":0,"freeze":0,"phone":null,"realName":null,"currencyName":null,"extract":null,"rechargeSwitch":null,"total":null}
     */

    private String buyPrice;
    private String sellPrice;
    private WalletBean wallet;

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public WalletBean getWallet() {
        return wallet;
    }

    public void setWallet(WalletBean wallet) {
        this.wallet = wallet;
    }

    public static class WalletBean {
        /**
         * id : 73
         * createTime : 2018-09-08 12:37:29
         * updateTime : 2018-09-08 12:37:29
         * remark : null
         * version : 0
         * userId : 13
         * currencyId : 1
         * using : 0.0
         * freeze : 0.0
         * phone : null
         * realName : null
         * currencyName : null
         * extract : null
         * rechargeSwitch : null
         * total : null
         */

        private String id;
        private String createTime;
        private String updateTime;
        private String remark;
        private String version;
        private String userId;
        private String currencyId;
        private String using;
        private String freeze;
        private String phone;
        private String realName;
        private String currencyName;
        private String extract;
        private String rechargeSwitch;
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

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public String getExtract() {
            return extract;
        }

        public void setExtract(String extract) {
            this.extract = extract;
        }

        public String getRechargeSwitch() {
            return rechargeSwitch;
        }

        public void setRechargeSwitch(String rechargeSwitch) {
            this.rechargeSwitch = rechargeSwitch;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }
}
