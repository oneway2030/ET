package com.xnhb.et.bean;

import java.util.List;

/**
 * 作者 oneway on 2018/10/25
 * 描述: 交易信息
 * 参考链接:
 */
public class TradeInfo {

    /**
     * buyList : [{"nums":1,"price":1.017},{"nums":1,"price":1.016},{"nums":1,"price":1.015},{"nums":1,"price":1.014},{"nums":1,"price":1.013345},{"nums":1,"price":1.01334},{"nums":1,"price":1.0133},{"nums":1,"price":1.013},{"nums":1,"price":1.012},{"nums":1,"price":1.011},{"nums":1,"price":1.01},{"nums":10000.00522283,"price":1}]
     * recordList : [{"buyPrice":1.018,"buyRate":0.002,"buyTrusteeId":98,"buyUserId":6,"createTime":1536311938000,"id":14,"sellPrice":1.018,"sellRate":0.002036,"sellTrusteeId":107,"sellUserId":2,"tradeId":1,"tradePrice":1.018,"tradeQuantity":1,"tradeType":1,"typeStr":"卖","updateTime":1536311938000,"version":0},{"buyPrice":1.019,"buyRate":0.002,"buyTrusteeId":99,"buyUserId":6,"createTime":1536114944000,"id":12,"sellPrice":1.019,"sellRate":0.002038,"sellTrusteeId":105,"sellUserId":2,"tradeId":1,"tradePrice":1.019,"tradeQuantity":1,"tradeType":1,"typeStr":"卖","updateTime":1536114944000,"version":0},{"buyPrice":1.02,"buyRate":1,"buyTrusteeId":8,"buyUserId":1,"createTime":1532675384000,"id":5,"sellPrice":1.02,"sellRate":1.02,"sellTrusteeId":9,"sellUserId":1,"tradeId":1,"tradePrice":1.02,"tradeQuantity":500,"tradeType":1,"typeStr":"卖","updateTime":1532675384000,"version":0},{"buyPrice":1,"buyRate":20,"buyTrusteeId":6,"buyUserId":1,"createTime":1532675328000,"id":4,"sellPrice":1,"sellRate":20,"sellTrusteeId":7,"sellUserId":1,"tradeId":1,"tradePrice":1,"tradeQuantity":10000,"tradeType":1,"typeStr":"卖","updateTime":1532675328000,"version":0}]
     * tradeInfo : {"area":1,"currencyName":"ECNY","currentPrice":1.018,"encyMoeny":1.018,"open":1.018,"recordTime":"2018-10-25 20:37:27","rise":"+0.00","rise_num":0,"showEncyMoeny":1.018,"tradeCurrencyName":"ET","tradeId":1,"tradeMaxPrice":0,"tradeMinPrice":0,"tradeMoney":0,"tradeNums":0,"usings":true}
     * sellList : [{"nums":1,"price":1.0401}]
     */

    private TradePairInfo tradeInfo; //基础信息
    private List<RecordListBean> recordList;//成交
    private List<TradeListBean> buyList; //挂单买
    private List<TradeListBean> sellList;//挂单卖

    public TradePairInfo getTradeInfo() {
        return tradeInfo;
    }

    public void setTradeInfo(TradePairInfo tradeInfo) {
        this.tradeInfo = tradeInfo;
    }

    public List<TradeListBean> getBuyList() {
        return buyList;
    }

    public void setBuyList(List<TradeListBean> buyList) {
        this.buyList = buyList;
    }

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }

    public List<TradeListBean> getSellList() {
        return sellList;
    }

    public void setSellList(List<TradeListBean> sellList) {
        this.sellList = sellList;
    }


    public static class RecordListBean {
        /**
         * buyPrice : 1.018
         * buyRate : 0.002
         * buyTrusteeId : 98
         * buyUserId : 6
         * createTime : 1536311938000
         * id : 14
         * sellPrice : 1.018
         * sellRate : 0.002036
         * sellTrusteeId : 107
         * sellUserId : 2
         * tradeId : 1
         * tradePrice : 1.018
         * tradeQuantity : 1.0
         * tradeType : 1
         * typeStr : 卖
         * updateTime : 1536311938000
         * version : 0
         */

        private String buyPrice;
        private String buyRate;
        private String buyTrusteeId;
        private String buyUserId;
        private long createTime;
        private String id;
        private String sellPrice;
        private String sellRate;
        private String sellTrusteeId;
        private String sellUserId;
        private String tradeId;
        private String tradePrice;
        private String tradeQuantity;
        private String tradeType;
        private String typeStr;
        private long updateTime;
        private String version;

        public String getBuyPrice() {
            return buyPrice;
        }

        public void setBuyPrice(String buyPrice) {
            this.buyPrice = buyPrice;
        }

        public String getBuyRate() {
            return buyRate;
        }

        public void setBuyRate(String buyRate) {
            this.buyRate = buyRate;
        }

        public String getBuyTrusteeId() {
            return buyTrusteeId;
        }

        public void setBuyTrusteeId(String buyTrusteeId) {
            this.buyTrusteeId = buyTrusteeId;
        }

        public String getBuyUserId() {
            return buyUserId;
        }

        public void setBuyUserId(String buyUserId) {
            this.buyUserId = buyUserId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSellPrice() {
            return sellPrice;
        }

        public void setSellPrice(String sellPrice) {
            this.sellPrice = sellPrice;
        }

        public String getSellRate() {
            return sellRate;
        }

        public void setSellRate(String sellRate) {
            this.sellRate = sellRate;
        }

        public String getSellTrusteeId() {
            return sellTrusteeId;
        }

        public void setSellTrusteeId(String sellTrusteeId) {
            this.sellTrusteeId = sellTrusteeId;
        }

        public String getSellUserId() {
            return sellUserId;
        }

        public void setSellUserId(String sellUserId) {
            this.sellUserId = sellUserId;
        }

        public String getTradeId() {
            return tradeId;
        }

        public void setTradeId(String tradeId) {
            this.tradeId = tradeId;
        }

        public String getTradePrice() {
            return tradePrice;
        }

        public void setTradePrice(String tradePrice) {
            this.tradePrice = tradePrice;
        }

        public String getTradeQuantity() {
            return tradeQuantity;
        }

        public void setTradeQuantity(String tradeQuantity) {
            this.tradeQuantity = tradeQuantity;
        }

        public String getTradeType() {
            return tradeType;
        }

        public void setTradeType(String tradeType) {
            this.tradeType = tradeType;
        }

        public String getTypeStr() {
            return typeStr;
        }

        public void setTypeStr(String typeStr) {
            this.typeStr = typeStr;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }


    public static class TradeListBean {
        /**
         * nums : 1.0
         * price : 1.0401
         */
        private String nums;
        private String price;

        public String getNums() {
            return nums;
        }

        public void setNums(String nums) {
            this.nums = nums;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
