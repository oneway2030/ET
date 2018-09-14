package com.xnhb.et.ui.ac.user.register;

import com.oneway.ui.base.in.IView;
import com.xnhb.et.ui.ac.user.register.RegsiterPresent;

/**
 * 作者 oneway on 2018/9/14
 * 描述:
 * 参考链接:
 */
public interface IRegisterView extends IView<RegsiterPresent> {
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
