package com.xnhb.et.ui.ac.setting.p;

import com.lzy.okgo.model.Response;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.convert.RegexUtils;
import com.oneway.tool.utils.log.LogUtil;
import com.oneway.ui.base.in.XPresent;
import com.oneway.ui.toast.ToastManager;
import com.xnhb.et.bean.UserInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.ui.ac.user.register.IRegisterView;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者 oneway on 2018/9/14
 * 描述: 安全中心->修改密码
 * 参考链接:
 */
public class ModifyPwdPresent extends XPresent<IModifyView> {

    /**
     * 获取短信验证码
     */
    public void getVerificationCode(String mobile) {
        Map map = new HashMap();
        map.put("mobile", mobile);
        OkGoHelper.getOkGo(Api.VERIFICATION_CODEURL, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<Void>>(getV().getAc()) {
                    @Override
                    public void onSuccess(Response<ResultInfo<Void>> response) {
                        ResultInfo body = response.body();
                        LogUtil.i("body=>" + body);
                        getV().startCountDown();
                    }

                    @Override
                    public void onError(Response<ResultInfo<Void>> response) {
                        super.onError(response);
                        getV().cancelCountDown();
                    }
                });
    }

    /**
     * 修改登录密码
     *
     * @param oldPwd           旧密码
     * @param newPwd           新密码
     * @param reinputNewPwd    重新输入的新密码
     * @param verificationCode 短信验证码
     */
    public void modifyLoginPwd(String oldPwd, String newPwd, String reinputNewPwd, String verificationCode) {
        Map map = new HashMap();
        map.put("pwd", oldPwd);
        map.put("newPwd", newPwd);
        map.put("reNewPwd", reinputNewPwd);
        map.put("code", verificationCode);
        OkGoHelper.getOkGo(Api.MODIFY_LOGIN_PWD, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<Void>>(getV().getAc()) {
                    @Override
                    public void onSuccess(Response<ResultInfo<Void>> response) {
                        ResultInfo body = response.body();
                        LogUtil.i("body=>" + body);
                        getV().submit();
                    }
                });
    }

    /**
     * 找回 登录 密码(忘记密码)
     */
    public void getModifyLoginPwd(String newLoginPwd, String verificationCode) {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("phone", UserInfoHelper.getInstance().getAccountName());
        map.put("code", verificationCode);
        map.put("pwd", newLoginPwd);
        OkGoHelper.getOkGo(Api.FORGET_PWD, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<Void>>(getV().getAc()) {
                    @Override
                    public void onSuccess(Response<ResultInfo<Void>> response) {
                        ResultInfo body = response.body();
                        getV().submit();
                    }
                });
    }

    /**
     * 修改 交易 密码
     */
    public void getModifyTransactionPwd(String oldTradePassword, String newTradePassword, String reNewTradePassword, String verificationCode) {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("tradePassword", oldTradePassword);
        map.put("newTradePassword", newTradePassword);
        map.put("reNewTradePassword", reNewTradePassword);
        map.put("code", verificationCode);
        OkGoHelper.getOkGo(Api.MODIFY_TRANSACTION_PWD, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<Void>>(getV().getAc()) {
                    @Override
                    public void onSuccess(Response<ResultInfo<Void>> response) {
                        ResultInfo body = response.body();
                        getV().submit();
                    }
                });
    }

    /**
     * 登录
     */
    public void login() {

    }


}
