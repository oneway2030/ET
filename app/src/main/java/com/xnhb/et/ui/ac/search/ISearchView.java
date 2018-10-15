package com.xnhb.et.ui.ac.search;

import com.oneway.ui.base.in.IView;
import com.xnhb.et.bean.SearchInfo;
import com.xnhb.et.bean.SearchListInfo;
import com.xnhb.et.bean.WrapSearchResult;
import com.xnhb.et.ui.ac.setting.p.ModifyPwdPresenter;

import java.util.ArrayList;

/**
 * 作者 oneway on 2018/9/19
 * 描述:
 * 参考链接:
 */
public interface ISearchView extends IView<SearchPresenter> {

    void searchResult(ArrayList<WrapSearchResult<SearchInfo>> datas);

}
