package com.xnhb.et.net.okgo;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;
import com.xnhb.et.net.Api;

/**
 * 作者 oneway on 2018/9/14
 * 描述:
 * 参考链接:
 */
public class OkGoHelper {


    public static PostRequest<String> getOkGo(String url, Object tag) {
        return OkGo.<String>post(getNetWorkUrl(url))
                .tag(tag);
    }

    public static String getNetWorkUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            StringBuffer sb = new StringBuffer(Api.domain);
            sb.append(url);
            url = sb.toString();
        }
        return url;
    }
}
