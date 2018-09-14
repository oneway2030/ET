package com.xnhb.et.event;

/**
 * 放置 AndroidEventBus 的 Tag, 便于检索
 */
public interface EventBusTags {
    //登录,注册,忘记密码界面切换
    String LOGIN_AND_REGISTER_PAGE_SWITCH = "login_and_register_page_switch";
    String REGISTER_SUCCESS_TAG = "register_success_tag";
    String RETRIEVE_PWD_TAG = "retrieve_pwd_tag";
}
