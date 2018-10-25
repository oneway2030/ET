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

    private TradeInfoBean tradeInfo;
    private List<BuyListBean> buyList;
    private List<RecordListBean> recordList;
    private List<SellListBean> sellList;

    public TradeInfoBean getTradeInfo() {
        return tradeInfo;
    }

    public void setTradeInfo(TradeInfoBean tradeInfo) {
        this.tradeInfo = tradeInfo;
    }

    public List<BuyListBean> getBuyList() {
        return buyList;
    }

    public void setBuyList(List<BuyListBean> buyList) {
        this.buyList = buyList;
    }

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }

    public List<SellListBean> getSellList() {
        return sellList;
    }

    public void setSellList(List<SellListBean> sellList) {
        this.sellList = sellList;
    }

    public static class TradeInfoBean {
        /**
         * area : 1
         * currencyName : ECNY
         * currentPrice : 1.018
         * encyMoeny : 1.018
         * showEncyMoeny : 1.018
         * open : 1.018
         * recordTime : 2018-10-25 20:37:27
         * rise : +0.00
         * rise_num : 0.0
         * tradeCurrencyName : ET
         * tradeId : 1
         * tradeMaxPrice : 0.0
         * tradeMinPrice : 0.0
         * tradeMoney : 0.0
         * tradeNums : 0.0
         * usings : true
         */

        private int area;
        private String currencyName;
        private double currentPrice; //最新价格
        private double encyMoeny;  //对应价格
        private String tradeMaxPrice;//24h  最大价格
        private String tradeMinPrice;//24h  最小价格
        private String rise;  //涨跌
        private String tradeMoney; //成交量
        private double tradeNums; //成交额
        private double open;
        private String recordTime;
        private double rise_num;
        private double showEncyMoeny;
        private String tradeCurrencyName;
        private int tradeId;
        private boolean usings;

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public double getEncyMoeny() {
            return encyMoeny;
        }

        public void setEncyMoeny(double encyMoeny) {
            this.encyMoeny = encyMoeny;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public String getRecordTime() {
            return recordTime;
        }

        public void setRecordTime(String recordTime) {
            this.recordTime = recordTime;
        }

        public String getRise() {
            return rise;
        }

        public void setRise(String rise) {
            this.rise = rise;
        }

        public double getRise_num() {
            return rise_num;
        }

        public void setRise_num(double rise_num) {
            this.rise_num = rise_num;
        }

        public double getShowEncyMoeny() {
            return showEncyMoeny;
        }

        public void setShowEncyMoeny(double showEncyMoeny) {
            this.showEncyMoeny = showEncyMoeny;
        }

        public String getTradeCurrencyName() {
            return tradeCurrencyName;
        }

        public void setTradeCurrencyName(String tradeCurrencyName) {
            this.tradeCurrencyName = tradeCurrencyName;
        }

        public int getTradeId() {
            return tradeId;
        }

        public void setTradeId(int tradeId) {
            this.tradeId = tradeId;
        }

        public String getTradeMaxPrice() {
            return tradeMaxPrice;
        }

        public void setTradeMaxPrice(String tradeMaxPrice) {
            this.tradeMaxPrice = tradeMaxPrice;
        }

        public String getTradeMinPrice() {
            return tradeMinPrice;
        }

        public void setTradeMinPrice(String tradeMinPrice) {
            this.tradeMinPrice = tradeMinPrice;
        }

        public String getTradeMoney() {
            return tradeMoney;
        }

        public void setTradeMoney(String tradeMoney) {
            this.tradeMoney = tradeMoney;
        }

        public double getTradeNums() {
            return tradeNums;
        }

        public void setTradeNums(double tradeNums) {
            this.tradeNums = tradeNums;
        }

        public boolean isUsings() {
            return usings;
        }

        public void setUsings(boolean usings) {
            this.usings = usings;
        }
    }

    public static class BuyListBean {
        /**
         * nums : 1.0
         * price : 1.017
         */

        private double nums;
        private double price;

        public double getNums() {
            return nums;
        }

        public void setNums(double nums) {
            this.nums = nums;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
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

        private double buyPrice;
        private double buyRate;
        private int buyTrusteeId;
        private int buyUserId;
        private long createTime;
        private int id;
        private double sellPrice;
        private double sellRate;
        private int sellTrusteeId;
        private int sellUserId;
        private int tradeId;
        private double tradePrice;
        private double tradeQuantity;
        private int tradeType;
        private String typeStr;
        private long updateTime;
        private int version;

        public double getBuyPrice() {
            return buyPrice;
        }

        public void setBuyPrice(double buyPrice) {
            this.buyPrice = buyPrice;
        }

        public double getBuyRate() {
            return buyRate;
        }

        public void setBuyRate(double buyRate) {
            this.buyRate = buyRate;
        }

        public int getBuyTrusteeId() {
            return buyTrusteeId;
        }

        public void setBuyTrusteeId(int buyTrusteeId) {
            this.buyTrusteeId = buyTrusteeId;
        }

        public int getBuyUserId() {
            return buyUserId;
        }

        public void setBuyUserId(int buyUserId) {
            this.buyUserId = buyUserId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getSellPrice() {
            return sellPrice;
        }

        public void setSellPrice(double sellPrice) {
            this.sellPrice = sellPrice;
        }

        public double getSellRate() {
            return sellRate;
        }

        public void setSellRate(double sellRate) {
            this.sellRate = sellRate;
        }

        public int getSellTrusteeId() {
            return sellTrusteeId;
        }

        public void setSellTrusteeId(int sellTrusteeId) {
            this.sellTrusteeId = sellTrusteeId;
        }

        public int getSellUserId() {
            return sellUserId;
        }

        public void setSellUserId(int sellUserId) {
            this.sellUserId = sellUserId;
        }

        public int getTradeId() {
            return tradeId;
        }

        public void setTradeId(int tradeId) {
            this.tradeId = tradeId;
        }

        public double getTradePrice() {
            return tradePrice;
        }

        public void setTradePrice(double tradePrice) {
            this.tradePrice = tradePrice;
        }

        public double getTradeQuantity() {
            return tradeQuantity;
        }

        public void setTradeQuantity(double tradeQuantity) {
            this.tradeQuantity = tradeQuantity;
        }

        public int getTradeType() {
            return tradeType;
        }

        public void setTradeType(int tradeType) {
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

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }

    public static class SellListBean {
        /**
         * nums : 1.0
         * price : 1.0401
         */

        private double nums;
        private double price;

        public double getNums() {
            return nums;
        }

        public void setNums(double nums) {
            this.nums = nums;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
