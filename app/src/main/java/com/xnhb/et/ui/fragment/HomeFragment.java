package com.xnhb.et.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.oneway.ui.base.fragment.BaseLazyFragment;
import com.xnhb.et.R;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class HomeFragment extends BaseLazyFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ImmersionBar.setTitleBar(getActivity(), mTitleLayout);
    }
    @Override
    protected boolean isLazyLoad() {
        return false;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mian_home;
    }
}
