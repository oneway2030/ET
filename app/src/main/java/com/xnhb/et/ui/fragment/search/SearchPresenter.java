package com.xnhb.et.ui.fragment.search;

import com.lzy.okgo.model.Response;
import com.oneway.ui.base.in.XPresent;
import com.xnhb.et.bean.SearchInfo;
import com.xnhb.et.bean.WrapSearchResult;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者 oneway on 2018/9/14
 * 描述: 银行卡信息
 * 参考链接:
 */
public class SearchPresenter extends XPresent<ISearchView> {

    /**
     * 搜索信息
     */
    public void searchKey(String keyWord) {
        getV().showProgress();
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("param ", keyWord);
        OkGoHelper.postOkGo(Api.SEARCH_URL, this)
                .params(map)
                .execute(new DialogCallback<ResultInfo<WrapSearchResult<SearchInfo>>>() {

                    @Override
                    public void onSuccess(Response<ResultInfo<WrapSearchResult<SearchInfo>>> response) {

                    }

                    @Override
                    public void onError(Response<ResultInfo<WrapSearchResult<SearchInfo>>> response) {
                        super.onError(response);
                    }
                });
    }


}
