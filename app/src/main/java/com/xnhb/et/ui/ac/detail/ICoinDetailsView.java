package com.xnhb.et.ui.ac.detail;

import com.oneway.ui.base.in.IView;
import com.xnhb.et.bean.CoinSynopsisInfo;
import com.xnhb.et.bean.TradeInfo;
import com.xnhb.et.bean.TradeUserInfo;

/**
 * 作者 oneway on 2018/9/14
 * 描述:
 * 参考链接:
 */
public interface ICoinDetailsView extends IView<CoinDetailsPresenter> {
    void setBaseUi(CoinSynopsisInfo coinSynopsisInfo);

    String getTradeId();

    void updateTradeInfoUi(TradeInfo tradeInfo);

    void updateTradeUserInfoUi(TradeUserInfo tradeUserInfo);
}
