package com.xnhb.et.ui.fragment;

import com.oneway.ui.base.fragment.BaseLazyFragment;
import com.xnhb.et.R;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class DetailsFragment extends BaseLazyFragment {

    @Override
    protected boolean isLazyLoad() {
        return false;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mian_details;
    }
}
