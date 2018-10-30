package com.xnhb.et.net;

import com.lzy.okgo.callback.Callback;
import com.xnhb.et.bean.UserInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.okgo.OkGoHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者 oneway on 2018/9/19
 * 描述:  保存一些重复使用的api
 * 参考链接:
 */
public class ApiService {

    /**
     * 获取用户信息
     *
     * @param tag
     * @param callback
     */
    public static void getUserInfo(Object tag, Callback<ResultInfo<UserInfo>> callback) {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        OkGoHelper.postOkGo(Api.GET_USER_INFO, tag)
                .params(map)
                .execute(callback);
    }


    public static void buyAndSell(Object tag, boolean isBuy, String quantity, String price, String tradeModus, String currencyId, Callback<ResultInfo<Void>> callback) {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("quantity", quantity);//买入数量
        map.put("price", price); //买入价格
        map.put("tradeModus", tradeModus);//交易方式
        map.put("currencyId", currencyId);//币种id
        String url = "";
        if (isBuy) {
            url = Api.C2C_BUY_URL;
        } else {
            url = Api.C2C_SELL_URL;
        }
        OkGoHelper.postOkGo(url, tag)
                .params(map)
                .execute(callback);
    }

    public static void buyOrSell(Object tag, boolean isBuy, String quantity, String price,  String tradeId, Callback<ResultInfo<Void>> callback) {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("quantity", quantity);//买入数量
        map.put("price", price); //买入价格
        map.put("tradeType", isBuy?"0":"1");//交易方式 0买 1卖
        map.put("currencyTradeId", tradeId);//币种id
        map.put("tradePassword", "");
        OkGoHelper.postOkGo( Api.BUY_OR_SELL_URL, tag)
                .params(map)
                .execute(callback);
    }
}
