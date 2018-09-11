package com.xnhb.et.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者 oneway on 2018/9/11
 * 描述:
 * 参考链接:
 */
public class NoticeInfo implements Parcelable {
    private String title;
    private String time;
    private String content;

    public NoticeInfo() {
    }

    public NoticeInfo(String title, String time, String content) {
        this.title = title;
        this.time = time;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
        dest.writeString(this.title);
        dest.writeString(this.time);
        dest.writeString(this.content);
    }

    protected NoticeInfo(Parcel in) {
        this.title = in.readString();
        this.time = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<NoticeInfo> CREATOR = new Parcelable.Creator<NoticeInfo>() {
        @Override
        public NoticeInfo createFromParcel(Parcel source) {
            return new NoticeInfo(source);
        }

        @Override
        public NoticeInfo[] newArray(int size) {
            return new NoticeInfo[size];
        }
    };
}
