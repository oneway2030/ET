package com.xnhb.et.ui.ac.money;

import com.oneway.ui.base.in.IView;
import com.xnhb.et.bean.Withdrawalslnfo;

/**
 * 作者 oneway on 2018/10/11
 * 描述:
 * 参考链接:
 */
public interface IWithdrawalView extends IView<WithdrawalPresenter> {
    /**
     * 设置提现 条件布局
     */
    void setWithdrawalsInfo(Withdrawalslnfo data);

    /**
     * 启动倒计时
     */
    void startCountDown();

    /**
     * 取消倒计时
     */
    void cancelCountDown();


}
