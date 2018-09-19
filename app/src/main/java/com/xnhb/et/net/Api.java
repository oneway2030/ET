package com.xnhb.et.net;

/**
 * @author oneway
 * @describe
 * @since 2018/9/9 0009
 */


public interface Api {
    String domain = "http://47.106.38.200:8080";

    /**
     * 获取验证码 (注册,忘记密码 )
     */
    String VERIFICATION_CODEURL = "/ajax/sendCode";
    /**
     * 登录
     */
    String ACCOUNT_LOGIN = "/front/login";
    /**
     * 注册账户
     */
    String REGISTER_ACCOUNT = "/front/register";
    /**
     * 忘记密码
     */
    String FORGET_PWD = "/front/forgetPassword";
    /**
     * 修改登录密码
     */
    String MODIFY_LOGIN_PWD = "/front/changePwd";
    /**
     * 修改交易密码
     */
    String MODIFY_TRANSACTION_PWD = "/front/changeTradePwd";

}
