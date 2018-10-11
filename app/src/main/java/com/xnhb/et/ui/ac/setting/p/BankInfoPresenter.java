package com.xnhb.et.ui.ac.setting.p;

import com.lzy.okgo.model.Response;
import com.oneway.ui.base.in.XPresent;
import com.oneway.ui.toast.ToastManager;
import com.xnhb.et.bean.UserInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.interfaces.CallBack;
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
public class BankInfoPresenter extends XPresent<IBankView> {

    /**
     * 保存银行卡信息到 服务器
     *
     * @param bankName
     * @param bankcardId
     * @param bankAddress
     */
    public void saveBankInfo(String bankName, String bankcardId, String bankAddress) {
        getV().showProgress();
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("bankName", bankName);
        map.put("bankNo", bankcardId);
        map.put("bankAddress", bankAddress);
        OkGoHelper.postOkGo(Api.SAVE_BANK_CARD_INFO, this)
                .params(map)
                .execute(new DialogCallback<ResultInfo<Void>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<Void>> response) {
                        //保存成功 -->重新获取用户信息
                        getUserInfo(bankName, bankcardId, bankAddress);
                    }

                    @Override
                    public void onError(Response<ResultInfo<Void>> response) {
                        super.onError(response);
                        getV().closeProgress();
                    }
                });
    }

    /**
     * 重新获取用户信息, 如果获取失败则,更新内存种的用户信息
     *
     * @param bankName
     * @param bankcardId
     * @param bankAddress
     */
    private void getUserInfo(String bankName, String bankcardId, String bankAddress) {
        UserInfoHelper.getInstance().gotoRemoteServerGetUserInfo(getV().getAc(), new CallBack() {
            @Override
            public void success(UserInfo userInfo) {
                ToastManager.info("保存成功");
                getV().closeProgress();
                getV().saveSuccess();
            }

            @Override
            public void fail() {
                UserInfoHelper.getInstance().updateBankInfo(bankName, bankcardId, bankAddress);
                ToastManager.info("保存成功");
                getV().closeProgress();
                getV().saveFail();
            }
        });
    }


}
