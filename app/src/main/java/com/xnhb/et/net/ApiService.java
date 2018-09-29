package com.xnhb.et.net;

import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Response;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.xnhb.et.bean.UserInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
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
        OkGoHelper.getOkGo(Api.GET_USER_INFO, tag)
                .params(map)
                .execute(callback);
    }
}
