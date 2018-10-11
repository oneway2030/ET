package com.xnhb.et.bean;

import java.util.List;

/**
 * 作者 oneway on 2018/10/11
 * 描述: 提现条件信息
 * 参考链接:
 */
public class Withdrawalslnfo {

    /**
     * poundage : 50.0
     * isPercentage : false
     * using : 0.0
     * addressList : []
     * booleanMap : {"isCodeInput":false,"isTradeInput":true}
     */

    private String poundage;
    private boolean isPercentage;
    private double using;
    private BooleanMapBean booleanMap;
    private List<?> addressList;

    public String getPoundage() {
        return poundage;
    }

    public void setPoundage(String poundage) {
        this.poundage = poundage;
    }

    public boolean isIsPercentage() {
        return isPercentage;
    }

    public void setIsPercentage(boolean isPercentage) {
        this.isPercentage = isPercentage;
    }

    public double getUsing() {
        return using;
    }

    public void setUsing(double using) {
        this.using = using;
    }

    public BooleanMapBean getBooleanMap() {
        return booleanMap;
    }

    public void setBooleanMap(BooleanMapBean booleanMap) {
        this.booleanMap = booleanMap;
    }

    public List<?> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<?> addressList) {
        this.addressList = addressList;
    }

    public static class BooleanMapBean {
        /**
         * isCodeInput : false
         * isTradeInput : true
         */

        private boolean isCodeInput;
        private boolean isTradeInput;

        public boolean isIsCodeInput() {
            return isCodeInput;
        }

        public void setIsCodeInput(boolean isCodeInput) {
            this.isCodeInput = isCodeInput;
        }

        public boolean isIsTradeInput() {
            return isTradeInput;
        }

        public void setIsTradeInput(boolean isTradeInput) {
            this.isTradeInput = isTradeInput;
        }
    }
}
