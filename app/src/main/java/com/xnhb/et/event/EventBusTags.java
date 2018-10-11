package com.xnhb.et.event;

/**
 * 放置 AndroidEventBus 的 Tag, 便于检索
 */
public interface EventBusTags {


    //登录,注册,忘记密码界面切换
    String LOGIN_AND_REGISTER_PAGE_SWITCH = "login_and_register_page_switch";
    String REGISTER_SUCCESS_TAG = "register_success_tag";
    String RETRIEVE_PWD_TAG = "retrieve_pwd_tag";

    //首页切换页面
    String TAG_HOME_SWTICH_PAGE = "home_swtich_page";
    //登录
    String TAG_LOGIN_SUCDESS = "login_sucdess";
    //搜索结果
    String TAG_SEARCH_RESULT = "search_result";
    //自选 刷新
    String TAG_CUSTOM_SELECT = "custom_select";
    //提现成功 刷新
    String TAG_WITHDRAWALS_SUCCESS = "Withdrawals_success";
}
