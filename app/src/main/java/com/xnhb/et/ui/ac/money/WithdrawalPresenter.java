package com.xnhb.et.ui.ac.money;

import com.lzy.okgo.model.Response;
import com.oneway.tool.event.BusManager;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.base.in.XPresent;
import com.oneway.ui.toast.ToastManager;
import com.xnhb.et.bean.Withdrawalslnfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者 oneway on 2018/10/11
 * 描述:
 * 参考链接:
 */
public class WithdrawalPresenter extends XPresent<IWithdrawalView> {

    /**
     * 提现 的条件信息
     */
    public void reqWithdrawalCoinInfo(String currencyId) {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("currencyId", currencyId);
        OkGoHelper.postOkGo(Api.WITHDRAWAL_CONDITION_INFO_URL, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<Withdrawalslnfo>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<Withdrawalslnfo>> response) {
                        ResultInfo<Withdrawalslnfo> body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            return;
                        }
                        Withdrawalslnfo result = body.getResult();
                        if (EmptyUtils.isEmpty(result)) {
                            return;
                        }
                        getV().showContentPage();
                        getV().setWithdrawalsInfo(result);
                        //显示内容
                    }

                    @Override
                    public void onError(Response<ResultInfo<Withdrawalslnfo>> response) {
                        super.onError(response);
                        getV().showErrorPage();
                    }
                });
    }

    /**
     * 提现的短信验证码
     */
    public void reqSmsCode() {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        OkGoHelper.postOkGo(Api.WITHDRAWAL_SMS_CODE_URL, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<Void>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<Void>> response) {
                        getV().startCountDown();
                        ResultInfo<Void> body = response.body();
                        ToastManager.info("已发送短信到您的手机");
                    }

                    @Override
                    public void onError(Response<ResultInfo<Void>> response) {
                        super.onError(response);
                        getV().cancelCountDown();
                    }
                });
    }

    /**
     * 提现接口
     */
    public void reqWithdrawal(String currencyId, String count, String address, String pwd, String smsCode) {
//        currencyId :币种id,quantity:提现数量,address:提现地址，tradePassword:交易密码，code:短信验证码
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("currencyId", currencyId);//币种id
        map.put("quantity", count);//提现数量
        map.put("address", address);//提现地址
        if (EmptyUtils.isNotEmpty(pwd)) {
            map.put("tradePassword", pwd);//交易密码
        }
        if (EmptyUtils.isNotEmpty(smsCode)) {
            map.put("code", smsCode);//短信验证码
        }
        OkGoHelper.postOkGo(Api.WITHDRAWAL_URL, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<String>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<String>> response) {
                        //TODO 提现成功后刷新钱包 并跳转到充值记录
                        ToastManager.success(response.body().getMsg());
                        BusManager.getBus().post(EventBusTags.TAG_WITHDRAWALS_SUCCESS, "");
                        getV().getAc().finish();
                    }

                    @Override
                    public void onError(Response<ResultInfo<String>> response) {
                        super.onError(response);
                    }
                });
    }
}
