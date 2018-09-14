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
     * 注册账户
     */
    String REGISTER_ACCOUNT = "/front/register";
    /**
     * 忘记密码
     */
    String FORGET_PWD = "/front/forgetPassword";

}
