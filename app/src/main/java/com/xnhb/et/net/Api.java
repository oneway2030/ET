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
    /**
     * 保存银行卡信息
     */
    String SAVE_BANK_CARD_INFO = "/front/modifyBank";
    /**
     * 获取用户信息
     * //front/personal
     */
    String GET_USER_INFO = "/front/userInfo";

    /**
     * 滚动公告
     */
    String NOTICE_INFO = "/search/indexContent";

    String NOTICE_LIST_INFO = "/search/getContent";


    /**
     * 涨幅榜  index  0涨幅  1成交
     */
    String HOME_RANKING = "/search/indexRanking";

    /**
     * 轮播图
     */
    String BANNER_INFO = "/search/indexSlides";
    /**
     * 下载图片
     */
    String DOWNLOAD_IMAGE = "/search/download";
    /**
     * 横向 列表数据 horizontal_list
     */
    String HOME_HORIZONTAL_LIST_DATA = "/search/indexCommTrade";

    /**
     * 行情 列表
     */
    String QUOTATION_LIST_DATA = "/search/indexTrade";
    /**
     * 自选 列表
     */
    String CUSTOM_SELECT_LIST = "/front/collect/tradeInfo";

}
