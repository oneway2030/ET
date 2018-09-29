package com.xnhb.et.ui.fragment.home.present;

import com.lzy.okgo.model.Response;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.base.in.XPresent;
import com.xnhb.et.bean.BannerInfo;
import com.xnhb.et.bean.HomeHDataInfo;
import com.xnhb.et.bean.NoticeInfo2;
import com.xnhb.et.bean.WrapNoticeInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.ui.fragment.home.view.IHomeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者 oneway on 2018/9/29
 * 描述:
 * 参考链接:
 */
public class HomePresent extends XPresent<IHomeView> {

    /**
     * 横向列表数据
     */
    public void getHData() {
        Map map = new HashMap();
        OkGoHelper.getOkGo(Api.HOME_HORIZONTAL_LIST_DATA, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<ArrayList<HomeHDataInfo>>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<ArrayList<HomeHDataInfo>>> response) {
//                        ResultInfo<WrapNoticeInfo> body = response.body();
                        ResultInfo<ArrayList<HomeHDataInfo>> body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            return;
                        }
                        ArrayList<HomeHDataInfo> result = body.getResult();
                        if (EmptyUtils.isEmpty(result)) {
                            return;
                        }
                        getV().setHorizontalList(result);
                    }
                });
    }

    public void getNotice() {
        Map map = new HashMap();
        OkGoHelper.getOkGo(Api.NOTICE_INFO, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<WrapNoticeInfo>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<WrapNoticeInfo>> response) {
                        ResultInfo<WrapNoticeInfo> body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            return;
                        }
                        WrapNoticeInfo result = body.getResult();
                        if (EmptyUtils.isEmpty(result)) {
                            return;
                        }
                        ArrayList<NoticeInfo2> contentList = result.getContentList();
                        if (EmptyUtils.isEmpty(contentList)) {
                            return;
                        }
                        getV().setNotice(contentList);
                    }
                });
    }

    /**
     * 获取轮播图
     */
    public void getBanner() {
        Map map = new HashMap();
        OkGoHelper.getOkGo(Api.BANNER_INFO, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<ArrayList<BannerInfo>>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<ArrayList<BannerInfo>>> response) {
                        ResultInfo<ArrayList<BannerInfo>> body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            return;
                        }
                        ArrayList<BannerInfo> result = body.getResult();
                        if (EmptyUtils.isEmpty(result)) {
                            return;
                        }
                        ArrayList<String> infos = new ArrayList();
                        for (BannerInfo bannerInfo : result) {
                            infos.add(bannerInfo.getId());
                        }
                        getV().setBanner(infos);
                    }
                });
    }

}
