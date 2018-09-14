package com.oneway.ui.base.in;

import com.lzy.okgo.OkGo;

import java.lang.ref.WeakReference;

/**
 * 作者 oneway on 2018/9/9
 * 描述: mvp  Present的base实现类，所有Persent应继承这个类
 * 参考链接:
 */
public class XPresent<V extends IView> implements IPresenter<V> {
    private WeakReference<V> v;

    @Override
    public void attachV(V view) {
        v = new WeakReference<V>(view);
    }

    @Override
    public void detachV() {
        OkGo.getInstance().cancelTag(getV().getAc());
        if (v != null && v.get() != null) {
            v.clear();
        }
        v = null;
    }

    protected V getV() {
        if (v == null || v.get() == null) {
            throw new IllegalStateException("v can not be null");
        }
        return v.get();
    }


    @Override
    public boolean hasV() {
        return v != null && v.get() != null;
    }
}
