package com.xnhb.et.ui.fragment.home.view;

import com.oneway.ui.base.in.IView;
import com.xnhb.et.bean.C2CCoinInfo;
import com.xnhb.et.bean.C2CListInfo;
import com.xnhb.et.bean.HomeHDataInfo;
import com.xnhb.et.bean.NoticeInfo2;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.ui.fragment.home.presenter.C2CPresenter;
import com.xnhb.et.ui.fragment.home.presenter.HomePresenter;

import java.util.ArrayList;

/**
 * 作者 oneway on 2018/9/14
 * 描述:
 * 参考链接:
 */
public interface IC2CView extends IView<C2CPresenter> {
    /**
     * @param c2cListInfo 列表信息
     * @param c2CCoinInfo 币的具体信息
     */
    void setupUi(C2CListInfo c2cListInfo, C2CCoinInfo c2CCoinInfo);

    void loginExpires();
}
