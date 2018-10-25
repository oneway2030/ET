package com.xnhb.et.ui.ac.detail;

import com.lzy.okgo.model.Response;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.convert.RegexUtils;
import com.oneway.tool.utils.log.LogUtil;
import com.oneway.ui.base.in.XPresent;
import com.oneway.ui.toast.ToastManager;
import com.xnhb.et.bean.LoginInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者 oneway on 2018/9/14
 * 描述:
 * 参考链接:
 */
public class CoinDetailsPresenter extends XPresent<ICoinDetailsView> {

    /**
     * 找回密码(忘记密码)
     */
    public void getData(String tradeId) {
        Map map = new HashMap();
        map.put("tradeId ", tradeId);
        OkGoHelper.postOkGo(Api.COIN_DETAILS, getV().getAc())
                .isMultipart(true)
                .params(map)
                .execute(new DialogCallback<ResultInfo<Void>>(getV().getAc()) {
                    @Override
                    public void onSuccess(Response<ResultInfo<Void>> response) {
                        ResultInfo body = response.body();
                        getV().setBaseUi();
                    }
                });
    }
}
