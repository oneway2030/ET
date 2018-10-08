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

    private ArrayList<QuotationInfo> areaList;
    private ArrayList<NoticeInfo2> contentList;

    public ArrayList<QuotationInfo> getAreaList() {
        return areaList;
    }

    public void setAreaList(ArrayList<QuotationInfo> areaList) {
        this.areaList = areaList;
    }

    public static Creator<WrapNoticeInfo> getCREATOR() {
        return CREATOR;
    }

    public ArrayList<NoticeInfo2> getContentList() {
        return contentList;
    }

    public void setContentList(ArrayList<NoticeInfo2> contentList) {
        this.contentList = contentList;
    }

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
