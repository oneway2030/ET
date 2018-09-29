package com.xnhb.et.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 oneway on 2018/9/29
 * 描述:
 * 参考链接:
 */
public class WrapNoticeInfo implements Parcelable {

    //    private List<AreaListBean> areaList;
    private ArrayList<NoticeInfo2> contentList;

    public ArrayList<NoticeInfo2> getContentList() {
        return contentList;
    }

    public void setContentList(ArrayList<NoticeInfo2> contentList) {
        this.contentList = contentList;
    }

//    public static class AreaListBean {
//        /**
//         * id : 1
//         * createTime : 2018-07-23 16:51:58
//         * updateTime : 2018-07-23 16:51:58
//         * remark : 2
//         * version : null
//         * name : ECNY
//         * position : 1
//         * status : 1
//         * statusStr : 启用
//         */
//
//        private int id;
//        private String createTime;
//        private String updateTime;
//        private String remark;
//        private Object version;
//        private String name;
//        private int position;
//        private int status;
//        private String statusStr;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getCreateTime() {
//            return createTime;
//        }
//
//        public void setCreateTime(String createTime) {
//            this.createTime = createTime;
//        }
//
//        public String getUpdateTime() {
//            return updateTime;
//        }
//
//        public void setUpdateTime(String updateTime) {
//            this.updateTime = updateTime;
//        }
//
//        public String getRemark() {
//            return remark;
//        }
//
//        public void setRemark(String remark) {
//            this.remark = remark;
//        }
//
//        public Object getVersion() {
//            return version;
//        }
//
//        public void setVersion(Object version) {
//            this.version = version;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getPosition() {
//            return position;
//        }
//
//        public void setPosition(int position) {
//            this.position = position;
//        }
//
//        public int getStatus() {
//            return status;
//        }
//
//        public void setStatus(int status) {
//            this.status = status;
//        }
//
//        public String getStatusStr() {
//            return statusStr;
//        }
//
//        public void setStatusStr(String statusStr) {
//            this.statusStr = statusStr;
//        }
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.contentList);
    }

    public WrapNoticeInfo() {
    }

    protected WrapNoticeInfo(Parcel in) {
        this.contentList = in.createTypedArrayList(NoticeInfo2.CREATOR);
    }

    public static final Parcelable.Creator<WrapNoticeInfo> CREATOR = new Parcelable.Creator<WrapNoticeInfo>() {
        @Override
        public WrapNoticeInfo createFromParcel(Parcel source) {
            return new WrapNoticeInfo(source);
        }

        @Override
        public WrapNoticeInfo[] newArray(int size) {
            return new WrapNoticeInfo[size];
        }
    };
}
