package com.xnhb.et.ui.fragment.home.presenter;

import com.lzy.okgo.model.Response;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.base.in.XPresent;
import com.xnhb.et.bean.C2CCoinInfo;
import com.xnhb.et.bean.C2CListInfo;
import com.xnhb.et.bean.WrapCoinInfo;
import com.xnhb.et.bean.base.ArrayResultInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.interfaces.CallBack;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.CustomIllegalStateException;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.ui.fragment.home.view.IC2CView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者 oneway on 2018/9/29
 * 描述:
 * 参考链接:
 */
public class C2CPresenter extends XPresent<IC2CView> {


    private ArrayList<C2CListInfo> infos;

    /**
     * 获取列表
     */
    public ArrayList<C2CListInfo> getCoinListInfo(CallBack<ArrayList<C2CListInfo>> callBack) {
        if (infos == null) {
            reqC2CListData(callBack);
        } else {
            return infos;
        }
        return null;
    }

    public void getData() {
        if (UserInfoHelper.getInstance().isLogin()) {
            reqC2CListData(null);
        } else {
            getV().showOtherError();
        }
    }

    /**
     * 获取具体币种信息
     */
    public void reqCionInfoData(C2CListInfo info, boolean isShoaDialog) {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("id", info.getId() + "");
        OkGoHelper.postOkGo(Api.C2C_COIN_INFO_URL, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<C2CCoinInfo>>(getV().getAc(), isShoaDialog) {
                    @Override
                    public void onSuccess(Response<ResultInfo<C2CCoinInfo>> response) {
//                        ResultInfo<WrapNoticeInfo> body = response.body();
                        ResultInfo<C2CCoinInfo> body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            return;
                        }
                        C2CCoinInfo result = body.getResult();
                        //获取到具体数据后,显示数据
                        getV().setupUi(info, result);
                    }

                    @Override
                    public void onError(Response<ResultInfo<C2CCoinInfo>> response) {
                        super.onError(response);
                        getV().showErrorPage();
                    }


                    @Override
                    public void onCustomError(CustomIllegalStateException customException) {
                        int errorCode = customException.getErrorCode();
                        if (errorCode == -2) {//登录过期
                            UserInfoHelper.getInstance().cleanUpUserInfo();
                            getV().loginExpires();
                            return;
                        }
                    }

                });


    }

    /**
     * 请求C2C列表数据
     */
    private void reqC2CListData(CallBack<ArrayList<C2CListInfo>> callBack) {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        OkGoHelper.postOkGo(Api.C2C_COIN_LIST_URL, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ArrayResultInfo<C2CListInfo>>() {
                    @Override
                    public void onSuccess(Response<ArrayResultInfo<C2CListInfo>> response) {
                        ArrayResultInfo<C2CListInfo> body = response.body();
                        infos = body.getObj();
                        if (EmptyUtils.isNotEmpty(infos)) {
                            //请求列表数据后,缓存在本地,然后根据列表数据的id,默认请求第一个数据
                            reqCionInfoData(infos.get(0), false);
                            if (callBack != null) {
                                callBack.success(infos);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<ArrayResultInfo<C2CListInfo>> response) {
                        super.onError(response);
                        getV().showErrorPage();
                    }


                    @Override
                    public void onCustomError(CustomIllegalStateException customException) {
                        int errorCode = customException.getErrorCode();
                        if (errorCode == -2) {//登录过期
                            UserInfoHelper.getInstance().cleanUpUserInfo();
                            getV().loginExpires();
                            return;
                        }
                    }
                });
    }


}
