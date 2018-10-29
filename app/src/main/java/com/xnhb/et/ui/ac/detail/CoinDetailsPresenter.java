package com.xnhb.et.ui.ac.detail;

import com.dhh.rxlifecycle2.RxLifecycle;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.oneway.tool.event.BusManager;
import com.oneway.tool.parse.GsonUtil;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.log.LogUtil;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.base.in.XPresent;
import com.oneway.ui.toast.ToastManager;
import com.oneway.websocket.RxWebSocket;
import com.oneway.websocket.WebSocketInfo;
import com.oneway.websocket.WebSocketSubscriber;
import com.xnhb.et.bean.CoinSynopsisInfo;
import com.xnhb.et.bean.TradeInfo;
import com.xnhb.et.bean.TradePairInfo;
import com.xnhb.et.bean.TradeUserInfo;
import com.xnhb.et.bean.WSSendInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.ui.fragment.home.DetailsFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.WebSocket;

/**
 * 作者 oneway on 2018/9/14
 * 描述:
 * 参考链接:
 */
public class CoinDetailsPresenter extends XPresent<ICoinDetailsView> {
    private WSSendInfo mSendInfo = new WSSendInfo("trade", "1");
    private WebSocket mWebSocket;
    private boolean isCallBack = false;

    @Override
    public void detachV() {
        detachWebSocket();
        super.detachV();
    }

    private void detachWebSocket() {
        Disposable disposable = RxWebSocket.get(Api.COIN_SYNOPSIS).subscribe();
        //注销
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    /**
     * 获取简介
     */
    public void getCoinSynopsis(String tradeId) {
        Map map = new HashMap();
        map.put("tradeId ", tradeId);
        OkGoHelper.postOkGo(Api.COIN_SYNOPSIS, getV().getAc())
                .isMultipart(true)
                .params(map)
                .execute(new DialogCallback<ResultInfo<CoinSynopsisInfo>>(getV().getAc()) {
                    @Override
                    public void onSuccess(Response<ResultInfo<CoinSynopsisInfo>> response) {
                        ResultInfo<CoinSynopsisInfo> body = response.body();
                        CoinSynopsisInfo result = body.getResult();
//                        getV().setBaseUi(result);
                    }
                });
    }

    /**
     * 链接websocket到服务器
     * 链接成功后,发送请求获取消息
     */
    public void connectWebSocket() {
        detachWebSocket();
        RxWebSocket.get(Api.WEBSOCKET_DOMAIN)
                //RxLifecycle : https://github.com/dhhAndroid/RxLifecycle
                .compose(RxLifecycle.with(getV().getAc()).<WebSocketInfo>bindToLifecycle())
                .subscribe(new WebSocketSubscriber() {
                    @Override
                    public void onOpen(@NonNull WebSocket webSocket) {
                        mWebSocket = webSocket;
                        if (mWebSocket != null) {
                            getAllData();
                        }
                        LogUtil.i("websocket==>开");
                    }

                    @Override
                    public void onMessage(@NonNull String text) {
                        LogUtil.i("websocket消息返回=============>" + text);
                        //TODO 解析消息
                        parseJson(text);
                    }

                    @Override
                    protected void onReconnect() {
                        LogUtil.i("websocket==>重连");
                    }

                    @Override
                    protected void onClose() {
                        LogUtil.i("websocket==>关闭");
                    }
                });

    }

    /**
     * 获取所有信息
     */
    public void getAllData() {
        if (mWebSocket != null) {
            senTradeInfo();
            //如果登录就请求用户信息
            if (UserInfoHelper.getInstance().isLogin()) {
                senUserInfo(false);
            }
        }
    }


    /**
     * 查询交易信息
     */
    public void senTradeInfo() {
        mSendInfo.reset();
        mSendInfo.setMethod("trade");
        mSendInfo.setTradeId(getV().getTradeId());
        if (mWebSocket != null) {
            mWebSocket.send(new Gson().toJson(mSendInfo));
        }
//        RxWebSocket.send(Api.WEBSOCKET_DOMAIN, new Gson().toJson(mSendInfo));
    }

    /**
     * 查询用户信息
     */
    public void senUserInfo(boolean isCallBack) {
        this.isCallBack = isCallBack;
        mSendInfo.reset();
        mSendInfo.setMethod("userinfo");
        mSendInfo.setTradeId(getV().getTradeId());
        mSendInfo.setToken(UserInfoHelper.getInstance().getToken());
        if (mWebSocket != null) {
            mWebSocket.send(new Gson().toJson(mSendInfo));
        }
//        RxWebSocket.send(Api.WEBSOCKET_DOMAIN, new Gson().toJson(mSendInfo));
    }

    /**
     * 解析websocket返回的数据
     *
     * @param json
     */
    private void parseJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String code = jsonObject.getString("code");
            String obj = jsonObject.getString("obj");
            if ("0".equals(code)) { //k线

            } else if ("1".equals(code)) {//交易
                convertTradeData(obj);
            } else if ("2".equals(code)) {//用户
                convertTradeUserData(obj);
            } else if ("3".equals(code)) {//深度

            } else { // "-1"异常
                LogUtil.i("异常json==>" + json);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转换 交易数据
     */
    private void convertTradeData(String json) {
        TradeInfo tradeInfo = GsonUtil.getInstance().get(json, TradeInfo.class);
        getV().updateTradeInfoUi(tradeInfo);
        //        DevShapeUtils
//                .shape(DevShape.RECTANGLE)
//                //TODO 這里判断颜色 green_dark or
//                .solid(R.color.red_btn)
//                .radius(3)
//                .into(tvRange);
    }

    /**
     * 转换 用户信息数据
     *
     * @param json
     */
    private void convertTradeUserData(String json) {
        TradeUserInfo tradeUserInfo = GsonUtil.getInstance().get(json, TradeUserInfo.class);
        getV().updateTradeUserInfoUi(tradeUserInfo, isCallBack);
    }


    public void reqCollection(TradePairInfo mTradePairInfo) {
        String url;
        url = mTradePairInfo.isCollection() ? Api.CANCEL_ADD_CUSTOM_SELECT : Api.ADD_CUSTOM_SELECT;
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("tradeId", mTradePairInfo.getTradeId());
        OkGoHelper.postOkGo(url, getV().getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<Void>>(getV().getAc()) {
                    @Override
                    public void onSuccess(Response<ResultInfo<Void>> response) {
                        updateCuotomSelectInfo(mTradePairInfo);
                        mTradePairInfo.setCollection(!mTradePairInfo.isCollection());
                        //通知其他界面刷新
                        BusManager.getBus().post(EventBusTags.TAG_CUSTOM_SELECT, "-1");
                        //更新当前页面收藏状态
                        getV().setCollection(mTradePairInfo.isCollection());
                        //
                        ToastManager.info(mTradePairInfo.isCollection() ? "已添加到自选列表" : "已取消收藏");
                    }
                });
    }


    public void updateCuotomSelectInfo(TradePairInfo updateInfo) {
        if (updateInfo.isCollection()) {//取消自选
            if (EmptyUtils.isNotEmpty(DetailsFragment.customListInfo)) {
                for (TradePairInfo info : DetailsFragment.customListInfo) {
                    if (info.getTradeId().equals(updateInfo.getTradeId())) {
                        DetailsFragment.customListInfo.remove(info);
                        return;
                    }
                }
            }
        } else {
            if (EmptyUtils.isEmpty(DetailsFragment.customListInfo)) {
                DetailsFragment.customListInfo = new ArrayList<>();
            }
            DetailsFragment.customListInfo.add(updateInfo);
        }
    }
}
