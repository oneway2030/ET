package com.xnhb.et.ui.fragment.home.presenter;

import com.lzy.okgo.model.Response;
import com.oneway.ui.base.in.XPresent;
import com.xnhb.et.bean.WrapCoinInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.CustomIllegalStateException;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.ui.fragment.home.view.IWalletView;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者 oneway on 2018/10/9
 * 描述:
 * 参考链接:
 */
public class WalletPresenter extends XPresent<IWalletView> {

    public void reqWalletInfo() {
        getV().showLoadingPage();
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("currencyName", "");
        OkGoHelper.postOkGo(Api.WALLET_INFO, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<WrapCoinInfo>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<WrapCoinInfo>> response) {
                        ResultInfo<WrapCoinInfo> body = response.body();
                        WrapCoinInfo result = body.getResult();
                        getV().showListInfo(result);
                    }

                    @Override
                    public void onError(Response<ResultInfo<WrapCoinInfo>> response) {
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
