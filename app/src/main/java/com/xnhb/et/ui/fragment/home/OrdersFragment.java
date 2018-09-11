package com.xnhb.et.ui.fragment.home;

import android.widget.TextView;

import com.oneway.ui.base.fragment.BaseFragment;
import com.xnhb.et.R;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class OrdersFragment extends BaseFragment {

    @BindView(R.id.title_layout)
    TextView mTitleLayout;

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mian_orders;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar
                .titleBar(mTitleLayout)
                .init();
    }
}
