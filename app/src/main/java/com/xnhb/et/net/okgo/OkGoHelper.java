package com.xnhb.et.net.okgo;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.xnhb.et.net.Api;

/**
 * 作者 oneway on 2018/9/14
 * 描述:
 * 参考链接:
 */
public class OkGoHelper<T> {


    public static <T> PostRequest<T> postOkGo(String url, Object tag) {
        return OkGo.<T>post(getNetWorkUrl(url))
                .tag(tag);
    }

    public static <T> GetRequest<T> getOkGo(String url, Object tag) {
        return OkGo.<T>get(getNetWorkUrl(url))
                .tag(tag);
    }

    public static String getNetWorkUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            StringBuffer sb = new StringBuffer(Api.DOMAIN);
            sb.append(url);
            url = sb.toString();
        }
        return url;
    }

    //TODO 设置对应参数   currencyName    using  freeze
    //TODO 這里测试暂时写死 图片名字为ET
    public static String getImageUrl(String iamgeName) {
//        return getNetWorkUrl(Api.GET_IMAGE_URL + iamgeName);
        return getNetWorkUrl(Api.GET_IMAGE_URL + "ET");
    }

}
