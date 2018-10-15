package com.xnhb.et.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;

/**
 * 作者 oneway on 2018/10/15
 * 描述:
 * 参考链接:
 */
public class SearchListInfo implements MultiItemEntity {

    public static final int TYPE_TITLE = 1;
    public static final int  TYPE_CONTENT = 2;
    private int itemType;
    private String title;
    private ArrayList<SearchInfo> content;

    public SearchListInfo(int itemType, String title) {
        this.itemType = itemType;
        this.title = title;
    }

    public SearchListInfo(int itemType, ArrayList<SearchInfo> content) {
        this.itemType = itemType;
        this.content = content;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<SearchInfo> getContent() {
        return content;
    }

    public void setContent(ArrayList<SearchInfo> content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
