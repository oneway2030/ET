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
    /**
     * 添加 自选
     */
    String ADD_CUSTOM_SELECT = "/front/collect/collect";
    /**
     * 取消 自选
     */
    String CANCEL_ADD_CUSTOM_SELECT = "/front/collect/cancel";

    /**
     * 钱包列表信息
     */
    String WALLET_INFO = "/front/financial/assets";
    /**
     * 图片 拼接 地址
     */
    String GET_IMAGE_URL = "/search/currency?name=";
    /**
     * 充值
     */
    String RECHARGE_URL = "/front/financial/getRechargeAddress";
    /**
     * 提现的币 的条件 信息  如是否需要输入安全吗
     */
    String WITHDRAWAL_CONDITION_INFO_URL = "/front/financial/extractPoundageAndAddress";
    /**
     * 提现
     */
    String WITHDRAWAL_URL = "/front/financial/coin/apply";
    /**
     * 提现的短信验证码
     */
    String WITHDRAWAL_SMS_CODE_URL = "/ajax/sendMyCode";
    /**
     * 充提记录  type（记录类型，1 充值 2提现 7买入手续费 8卖出手续费 10系统赠送）
     */
    String RECHARGE_AND_WITHDRAWALS_HISTORICAL_URL = "/front/financial/queryLog";
    /**
     * 订单  type: 0 委托   1成交
     */
    String ORDER_INFO_URL = "/front/trade/userAll";
    /**
     * 取消挂单
     */
    String ORDER_CANCEL_URL = "/front/trade/cancel";
    /**
     * 提交用户信息表单
     */
    String SUMIT_USER_INFO_FORM_URL = "/front/approveOne";
    /**
     * 提交身份证照片
     */
    String SUMIT_IDCARD_IAMGE_URL = "/front/approveTwo";
    /**
     * 搜索
     */
    String SEARCH_URL = "/search/huntArea";
//    /**
//     * 订单  type: 0 委托   1成交
//     */
//    String ORDER_INFO_URL = "/front/ctc/list";
    /**
     * c2c  的币种信息
     */
    String C2C_COIN_INFO_URL = "/front/ctc/search";
    /**
     * c2c  的币种列表
     */
    String C2C_COIN_LIST_URL = "/front/ctc/search/currencyList";
    /**
     * 买
     */
    String C2C_BUY_URL = "/front/ctc/trade/0";
    /**
     * 卖
     */
    String C2C_SELL_URL = "/front/ctc/trade/1";


}
