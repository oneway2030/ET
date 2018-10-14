package com.xnhb.et.helper;

import com.lzy.okgo.model.Response;
import com.oneway.tool.ToolConfig;
import com.oneway.tool.cache.SpCache;
import com.oneway.tool.event.BusManager;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.base.ac.ActivityManager;
import com.xnhb.et.MainActivity;
import com.xnhb.et.net.ApiService;
import com.xnhb.et.bean.LoginInfo;
import com.xnhb.et.bean.UserInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.interfaces.CallBack;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.ui.ac.user.LoginAndRegisterActivity;

/**
 * @author oneway
 * @describe
 * @since 2018/9/15 0015
 */


public class UserInfoHelper {
    private static UserInfoHelper instance;
    public final static String SP_KEY_USER_ACCOUNT_NAME = "user_accountName";//用户名字
    public final static String SP_KEY_USER_TOKEN = "user_token"; //token
    public final static String SP_KEY_USER_STUTAS = "user_status"; //状态


    public final static String SP_KEY_USER_IDENTITY_AUTHENTICATION_STATUS = "user_identity authentication_status"; //身份认证
    public final static String SP_KEY_USER_BANK_NAME = "bank_name"; //开户行信息
    public final static String SP_KEY_USER_BANK_NO = "bank_no"; //银行卡卡号
    public final static String SP_KEY_USER_BANK_ADDRESS = "bank_address"; //开户行地址
    private UserInfo userInfo;


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
     * 保存 登录 信息
     */
    public void saveLoginInfo(LoginInfo info) {
        SpCache.getInstance().put(SP_KEY_USER_ACCOUNT_NAME, info.getPhone());
        SpCache.getInstance().put(SP_KEY_USER_TOKEN, info.getToken());
        SpCache.getInstance().put(SP_KEY_USER_STUTAS, info.getStatus());
    }


    /**
     * 校验是否登录 ,没有则跳转到登录界面
     */
    public boolean checkLogin() {
        if (!isLogin()) {
            LoginAndRegisterActivity.launch(ToolConfig.getContext());
            return false;
        }
        return true;
    }

    /**
     * 是否登录
     * 登陆了 true
     * 未登录 false
     */
    public boolean isLogin() {
        String account = SpCache.getInstance().getString(SP_KEY_USER_ACCOUNT_NAME);
        String token = SpCache.getInstance().getString(SP_KEY_USER_TOKEN);
//        return EmptyUtils.isNotEmpty(account) && EmptyUtils.isNotEmpty(token);
        return EmptyUtils.isNotEmpty(token);
//        return true;
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
     * 退出登录并关闭所有界面 主界面除外
     * 清空登录信息--->跳转登录界面-->关闭所有界面-->home切换到第一页
     */
    public void logoutAndfinishAll() {
        cleanUpUserInfo();
        ActivityManager.getInstance().finishAll(MainActivity.class);
        LoginAndRegisterActivity.launch(ToolConfig.getContext());
        BusManager.getBus().post(EventBusTags.TAG_HOME_SWTICH_PAGE, MainActivity.FRAGMENT_HOME);
    }

    /**
     * 清除用户信息
     */
    public void cleanUpUserInfo() {
        //
//        SpCache.newInstance().remove(SP_KEY_USER_ACCOUNT_NAME);
        SpCache.getInstance().remove(SP_KEY_USER_TOKEN);
        SpCache.getInstance().remove(SP_KEY_USER_STUTAS);
    }

    /**
     * 保存用户信息
     */
    public void cacheUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * 获取用户信息, 如果内存中没有,则取服务器获取
     *
     * @param tag
     * @param callBack
     */
    public void getUserInfo(Object tag, CallBack callBack) {
        if (userInfo == null) {
            gotoRemoteServerGetUserInfo(tag, callBack);
        } else {
            if (callBack != null)
                callBack.success(userInfo);
        }
    }

    /**
     * 获取服务器上的用户信息
     */
    public void gotoRemoteServerGetUserInfo(Object tag, CallBack callBack) {
        ApiService.getUserInfo(tag, new DialogCallback<ResultInfo<UserInfo>>() {
            @Override
            public void onSuccess(Response<ResultInfo<UserInfo>> response) {
                ResultInfo<UserInfo> body = response.body();
                if (EmptyUtils.isEmpty(body)) {
                    if (callBack != null)
                        callBack.fail();
                    return;
                }
                UserInfo userInfo = body.getResult();
                if (EmptyUtils.isEmpty(userInfo)) {
                    if (callBack != null)
                        callBack.fail();
                    return;
                }
                cacheUserInfo(userInfo);
                if (callBack != null)
                    callBack.success(userInfo);
            }

            @Override
            public void onError(Response<ResultInfo<UserInfo>> response) {
                super.onError(response);
                if (callBack != null)
                    callBack.fail();
            }
        });
    }


    /**
     * 更新银行卡信息
     *
     * @param bankName
     * @param bankcardId
     * @param bankAddress
     */
    public void updateBankInfo(String bankName, String bankcardId, String bankAddress) {
        if (userInfo != null) {
            userInfo.setBankName(bankName);
            userInfo.setBankNo(bankcardId);
            userInfo.setBankAddress(bankAddress);
        }
    }
}
