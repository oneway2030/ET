package com.oneway.tool.parse;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @Description: Gson单例操作
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 17/1/7 19:20.
 */
public class GsonUtil {

    public static GsonUtil mGsonUtil;
    private static Gson mGson;


    private GsonUtil() {
        mGson = new Gson();
    }

    public static GsonUtil getInstance() {
        if (mGsonUtil == null || mGson == null) {
            synchronized (Gson.class) {
                if (mGsonUtil == null) {
                    mGsonUtil = new GsonUtil();
                }
            }
        }
        return mGsonUtil;
    }


    /**
     * 第一种格式：
     * {"code":1,"data":[{"children":[{"code":"11","depth":2,"id":2,"isclose":0,"name":"数学史","parent_code":"110","state":"","status":0,"total":0},{"code":"14","depth":2,"id":3,"isclose":0,"name":"数理逻辑与数学基础","parent_code":"110","state":"","status":0,"total":0},{"code":"1410","depth":2,"id":4,"isclose":0,"name":"演绎逻辑学(亦称符号逻辑学)","parent_code":"110","state":"","status":0,"total":0},{"code":"17","depth":2,"id":5,"isclose":0,"name":"数论","parent_code":"110","state":"","status":0,"total":0},{"code":"qeqwe","depth":2,"id":16,"isclose":0,"name":"qqqq","parent_code":"110","state":"","status":0,"total":0}],"code":"110","depth":1,"id":1,"isclose":0,"name":"数学","parent_code":"1","state":"closed","status":0,"total":0},{"children":[{"code":"10","depth":2,"id":7,"isclose":0,"name":"信息科学与系统科学基础学科","parent_code":"120","state":"","status":0,"total":0},{"code":"1010","depth":2,"id":8,"isclose":0,"name":"信息论","parent_code":"120","state":"","status":0,"total":0},{"code":"1099","depth":2,"id":9,"isclose":0,"name":" 信息科学与系统科学基础学科其他学科","parent_code":"120","state":"","status":0,"total":0}],"code":"120","depth":1,"id":6,"isclose":0,"name":"信息科学与系统科学","parent_code":"1","state":"closed","status":0,"total":0},{"children":[{"code":"2534","depth":2,"id":11,"isclose":0,"name":"多相流体力学","parent_code":"130","state":"","status":0,"total":0},{"code":"2537","depth":2,"id":12,"isclose":0,"name":"渗流力学","parent_code":"130","state":"","status":0,"total":0}],"code":"130","depth":1,"id":10,"isclose":0,"name":"力学","parent_code":"1","state":"closed","status":0,"total":0},{"children":[{"code":"121","depth":2,"id":18,"isclose":0,"name":"qqq1","parent_code":"111","state":"","status":0,"total":0}],"code":"111","depth":1,"id":17,"isclose":0,"name":"物理学","parent_code":"1","state":"closed","status":0,"total":0},{"children":[{"code":"weweew","depth":2,"id":27,"isclose":0,"name":"ewewwwewe","parent_code":"ewe","state":"","status":0,"total":0}],"code":"ewe","depth":1,"id":26,"isclose":0,"name":"生物学","parent_code":"1","state":"closed","status":0,"total":0},{"children":[{"code":"40101","depth":2,"id":76,"isclose":0,"name":"教育学","parent_code":"401","state":"","status":0,"total":0},{"code":"40102","depth":2,"id":77,"isclose":0,"name":"科学教育","parent_code":"401","state":"","status":0,"total":0},{"code":"40103","depth":2,"id":78,"isclose":0,"name":"人文教育","parent_code":"401","state":"","status":0,"total":0},{"code":"40104","depth":2,"id":79,"isclose":0,"name":"教育技术学","parent_code":"401","state":"","status":0,"total":0}],"code":"401","depth":1,"id":75,"isclose":0,"name":"教育学类","parent_code":"1","state":"closed","status":0,"total":0},{"children":[{"code":"50101","depth":2,"id":81,"isclose":0,"name":"文艺学","parent_code":"501","state":"","status":0,"total":0},{"code":"50102","depth":2,"id":82,"isclose":0,"name":"语言学及应用语言学","parent_code":"501","state":"","status":0,"total":0},{"code":"50103","depth":2,"id":83,"isclose":0,"name":"汉语言文字学","parent_code":"501","state":"","status":0,"total":0}],"code":"501","depth":1,"id":80,"isclose":0,"name":"中国语言文学","parent_code":"1","state":"closed","status":0,"total":0}],"total":7}
     *
     * @param string
     * @param cls
     * @return
     */
    public static <T> T get(String string, Class<T> cls) {
        T t = null;
        if (TextUtils.isEmpty(string) || mGson == null) {
            return null;
        }
        try {
            t = mGson.fromJson(string, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * new TypeToken<List<T>>() {}.getType()
     */
    public <T> T get(String string, Type type) {
        T t = null;
        if (string == null || mGson == null) {
            return null;
        }
        try {
            t = mGson.fromJson(string, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public String toJson(Object obj) {
        if (mGson == null) {
            return "";
        }
        return mGson.toJson(obj);
    }

    /**
     * @param clzz1  如 HttpResult<T>   这里传 HttpResult.class
     * @param clzz2  这里就是泛型T 的calss
     */
    protected <T> T getObject(String json, Class<T> clzz1, Class<T> clzz2) {
        Type type = new ParameterizedTypeImpl(clzz1, new Class[]{clzz2});
        return mGson.fromJson(json, type);
    }

    /**
     * @param clzz1  如 HttpResult<T>   这里传 HttpResult.class
     * @param clzz2  这里就是泛型T 的calss
     */
    public <T> T getList(String json, Class<T> clzz1, Class<T> clzz2) {
        Type listType = new ParameterizedTypeImpl(ArrayList.class, new Class[]{clzz2});
        Type type = new ParameterizedTypeImpl(clzz1, new Type[]{listType});
        return mGson.fromJson(json, listType);
    }
}
