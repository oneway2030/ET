package com.xnhb.et.ui.fragment.home.view;

import com.oneway.ui.base.in.IView;
import com.xnhb.et.bean.HomeHDataInfo;
import com.xnhb.et.bean.NoticeInfo2;
import com.xnhb.et.ui.fragment.home.presenter.HomePresenter;

import java.util.ArrayList;

/**
 * 作者 oneway on 2018/9/14
 * 描述:
 * 参考链接:
 */
public interface IHomeView extends IView<HomePresenter> {
    /**
     * 设置横向列表数据
     */
    void setHorizontalList(ArrayList<HomeHDataInfo> datas);
    /**
     * 设置轮播图数据
     */
    void setBanner(ArrayList<String> banners);
    /**
     * 设置滚动条数据
     */
    void setNotice(ArrayList<NoticeInfo2> banners);

}
