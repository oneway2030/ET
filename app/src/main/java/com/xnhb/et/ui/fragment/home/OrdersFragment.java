package com.xnhb.et.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.oneway.ui.base.fragment.BaseBarLazyFragment;
import com.oneway.ui.base.fragment.BaseFragment;
import com.oneway.ui.base.fragment.BaseLazyFragment;
import com.xnhb.et.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class OrdersFragment extends BaseFragment {

    @BindView(R.id.title_layout)
    TextView mTitleLayout;


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
