package com.xnhb.et.ui.ac.setting.p;

import com.oneway.ui.base.in.IView;

/**
 * 作者 oneway on 2018/9/19
 * 描述:
 * 参考链接:
 */
public interface IModifyView extends IView<ModifyPwdPresent> {

    /**
     * 启动倒计时
     */
    void startCountDown();

    /**
     * 取消倒计时
     */
    void cancelCountDown();

    /**
     * 注册成功 忘记密码(找回密码成功)
     */
    void submit();

}
