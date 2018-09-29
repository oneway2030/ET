package com.xnhb.et.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者 oneway on 2018/9/29
 * 描述:
 * 参考链接:
 */
public class NoticeInfo2 implements Parcelable {

    /**
     * id : 3
     * createTime : 2018-08-29 10:55:11
     * updateTime : 2018-08-29 10:55:11
     * remark :
     * version : null
     * title : 三生三世
     * content : <p>阿达大神</p>
     * <p>
     * <p>奥术大师多爱仕达爱仕达爱仕达</p>
     * <p>
     * <p>&nbsp;</p>
     * <p>
     * <p>阿达框架的卡上爱仕达 asd sad&nbsp;</p>
     */

    private int id;
    private String createTime;
    private String updateTime;
    private String remark;
    private String title;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.createTime);
        dest.writeString(this.updateTime);
        dest.writeString(this.remark);
        dest.writeString(this.title);
        dest.writeString(this.content);
    }

    public NoticeInfo2() {
    }

    protected NoticeInfo2(Parcel in) {
        this.id = in.readInt();
        this.createTime = in.readString();
        this.updateTime = in.readString();
        this.remark = in.readString();
        this.title = in.readString();
        this.content = in.readString();
    }

    public static final Creator<NoticeInfo2> CREATOR = new Creator<NoticeInfo2>() {
        @Override
        public NoticeInfo2 createFromParcel(Parcel source) {
            return new NoticeInfo2(source);
        }

        @Override
        public NoticeInfo2[] newArray(int size) {
            return new NoticeInfo2[size];
        }
    };
}
