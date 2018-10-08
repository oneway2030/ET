package com.xnhb.et.bean;

import java.io.Serializable;

/**
 * 作者 oneway on 2018/10/8
 * 描述: 启用的行情 信息
 * 参考链接:
 */
public class QuotationInfo implements Serializable {

    public QuotationInfo(String name) {
        this.name = name;
    }
    public QuotationInfo( ) {
    }

    /**
     * id : 1
     * createTime : 2018-07-23 16:51:58
     * updateTime : 2018-07-23 16:51:58
     * remark : 2
     * version : null
     * name : ECNY
     * position : 1
     * status : 1
     * statusStr : 启用
     */

    
    private String id;
    private String createTime;
    private String updateTime;
    private String remark;
    private Object version;
    private String name;
    private String position;
    private String status;
    private String statusStr;

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

    public Object getVersion() {
        return version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
}
