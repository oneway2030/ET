package com.xnhb.et.ui.ac.user.register;

import com.lzy.okgo.model.Response;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.convert.RegexUtils;
import com.oneway.tool.utils.log.LogUtil;
import com.oneway.ui.base.in.XPresent;
import com.oneway.ui.toast.ToastManager;
import com.xnhb.et.bean.LoginInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者 oneway on 2018/9/14
 * 描述:
 * 参考链接:
 */
public class RegsiterPresenter extends XPresent<IRegisterView> {

    /**
     * 获取短信验证码
     */
    public void getVerificationCode(String mobile) {
        Map map = new HashMap();
        map.put("mobile", mobile);
        OkGoHelper.postOkGo(Api.VERIFICATION_CODEURL, getV().getAc())
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
     * 登录
     */
    public void login(String account, String pwd) {
        if (!RegexUtils.isMobileSimple(account) && !RegexUtils.isEmail(account)) {
            ToastManager.info("请填写正确手机号或者邮箱");
            return;
        }
        if (EmptyUtils.isEmpty(pwd)) {
            ToastManager.info("请输入登录密码");
            return;
        }
        Map map = new HashMap();
        map.put("phone", account);  //手机号或者邮箱
        map.put("password", pwd); //验证码
        OkGoHelper.postOkGo(Api.ACCOUNT_LOGIN, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<LoginInfo>>(getV().getAc()) {
                    @Override
                    public void onSuccess(Response<ResultInfo<LoginInfo>> response) {
                        ResultInfo<LoginInfo> body = response.body();
                        LoginInfo info = body.getResult();
                        if (EmptyUtils.isNotEmpty(info)) {
                            info.setPwd(pwd);
                            UserInfoHelper.getInstance().saveLoginInfo(info);
                            ToastManager.success(body.getMsg());
                            getV().submit();
                        }
                    }

                    @Override
                    protected void handleLoginExpires() {
                        super.handleLoginExpires();
                    }
                });
    }

    /**
     * 注册账户
     *
     * @param account
     * @param verificationCode
     * @param eamil
     * @param loginPwd
     * @param tradePassword
     * @param referee
     */
    public void registerAccount(String account, String verificationCode, String eamil, String loginPwd, String tradePassword, String referee) {
        Map map = new HashMap();
        map.put("phone", account);  //手机号或者邮箱
        map.put("code", verificationCode); //验证码
        map.put("email", eamil); //邮箱
        map.put("pwd", loginPwd); //登录密码
        map.put("tradePassword", tradePassword);//交易密码
        map.put("leaderName", referee);//推荐人
        OkGoHelper.postOkGo(Api.REGISTER_ACCOUNT, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<Void>>(getV().getAc()) {
                    @Override
                    public void onSuccess(Response<ResultInfo<Void>> response) {
                        ResultInfo body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            ToastManager.success(body.getMsg());
                            getV().submit();
                        }
                        LogUtil.i("body=>" + body);
                    }
                });
    }


    /**
     * 找回密码(忘记密码)
     */
    public void getRetrievePwd(String account, String verificationCode, String loginPwd) {
        Map map = new HashMap();
        map.put("phone", account);
        map.put("code", verificationCode);
        map.put("pwd", loginPwd);
        OkGoHelper.postOkGo(Api.FORGET_PWD, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<Void>>(getV().getAc()) {
                    @Override
                    public void onSuccess(Response<ResultInfo<Void>> response) {
                        ResultInfo body = response.body();
                        getV().submit();
                    }
                });
    }
}
