/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xnhb.et.net.okgo;

import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.request.base.Request;
import com.oneway.tool.ToolConfig;
import com.oneway.ui.toast.ToastManager;
import com.xnhb.et.helper.UserInfoHelper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Response;

/**
 * by ww
 * https://github.com/jeasonlzy/okhttp-OkGo
 */
public abstract class JsonCallback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;

    public JsonCallback() {
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
//        request.headers("header1", "HeaderValue1")//
//                .params("params1", "ParamsValue1")//
//                .params("token", "3215sdf13ad1f65asd4f3ads1f");
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T convertResponse(Response response) throws Throwable {
        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonConvert<T> convert = new JsonConvert<>(clazz);
                return convert.convertResponse(response);
            }
        }
        JsonConvert<T> convert = new JsonConvert<>(type);
        return convert.convertResponse(response);
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        Throwable exception = response.getException();
        if (response != null) exception.printStackTrace();
        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            ToastManager.error("网络链接失败,请链接网络!");
        } else if (exception instanceof SocketTimeoutException) {
            ToastManager.error("网络链接超时");
        } else if (exception instanceof HttpException) {
            ToastManager.error("404!找不到服务器!");
        } else if (exception instanceof CustomIllegalStateException) {
            onCustomError((CustomIllegalStateException) exception);
        } else if (exception instanceof JsonSyntaxException) {
            ToastManager.error("解析错误!");
        } else {
            ToastManager.error("未知错误,请联系管理员!");
        }
    }

    /**
     * 如果只是处理服务器错误回调,可以只重写该方法
     */
    public void onCustomError(CustomIllegalStateException customException) {
        int errorCode = customException.getErrorCode();
        //0成功 -1错误  -2 登录失效 -3未认证 -4冻结
        ToastManager.warning(customException.getMessage());
        if (errorCode == -2) {
            //去登录
            //清除本地信息,跳转到登录界面
            UserInfoHelper.getInstance().logoutAndfinishAll();
            return;
        }

    }
}
