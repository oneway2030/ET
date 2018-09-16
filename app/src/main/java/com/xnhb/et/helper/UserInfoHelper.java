package com.xnhb.et.helper;

import android.content.Context;

import com.oneway.tool.cache.SpCache;
import com.xnhb.et.bean.UserInfo;
import com.xnhb.et.ui.ac.user.LoginAndRegisterActivity;

/**
 * @author oneway
 * @describe
 * @since 2018/9/15 0015
 */


public class UserInfoHelper {
    private static UserInfoHelper instance;
    public final static String SP_KEY_USER_ACCOUNT_NAME = "user_accountName";
    public final static String SP_KEY_USER_TOKEN = "user_token";
    public final static String SP_KEY_USER_STUTAS = "user_status";

    private UserInfoHelper() {
    }

    public static UserInfoHelper getInstance() {
        if (instance == null) {
            synchronized (UserInfoHelper.class) {
                if (instance == null) {
                    instance = new UserInfoHelper();
                }
            }
        }
        return instance;
    }


    /**
     * 保存用户信息
     */
    public void saveUserInfo(UserInfo info) {
        SpCache.getInstance().put(SP_KEY_USER_ACCOUNT_NAME, info.getPhone());
        SpCache.getInstance().put(SP_KEY_USER_TOKEN, info.getToken());
        SpCache.getInstance().put(SP_KEY_USER_STUTAS, info.getStatus());
    }


    /**
     * 校验是否登录 ,没有则跳转到登录界面
     */
    public boolean checkLogin(Context context) {
        if (!isLogin()) {
            LoginAndRegisterActivity.launch(context);
            return true;
//            return false;
        }
        return true;
    }

    /**
     * 是否登录
     */
    public boolean isLogin() {
        String account = SpCache.getInstance().getString(SP_KEY_USER_ACCOUNT_NAME);
        String token = SpCache.getInstance().getString(SP_KEY_USER_TOKEN);
//        return EmptyUtils.isNotEmpty(account) && EmptyUtils.isNotEmpty(token);
        return true;
    }

    /**
     * 获取用户名
     */
    public String getAccountName() {
        return SpCache.getInstance().getString(SP_KEY_USER_ACCOUNT_NAME, "");
    }


    /**
     * 获取token
     */
    public String getToken() {
        return SpCache.getInstance().getString(SP_KEY_USER_TOKEN);
    }

    /**
     * 退出登录
     */
    public void logout() {
        cleanUpUserInfo();
    }

    /**
     * 清除用户信息
     */
    public void cleanUpUserInfo() {
        SpCache.getInstance().clear();
    }
}
