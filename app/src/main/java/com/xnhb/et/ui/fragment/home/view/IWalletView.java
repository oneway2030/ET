package com.xnhb.et.ui.fragment.home.view;

import com.oneway.ui.base.in.IView;
import com.xnhb.et.bean.WrapCoinInfo;
import com.xnhb.et.ui.fragment.home.presenter.WalletPresenter;

/**
 * 作者 oneway on 2018/10/9
 * 描述:
 * 参考链接:
 */
public interface IWalletView extends IView<WalletPresenter> {

    void showListInfo(WrapCoinInfo data);

    void loginExpires();
}
