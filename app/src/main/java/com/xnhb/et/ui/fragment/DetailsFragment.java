package com.xnhb.et.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.oneway.ui.base.fragment.BaseBarLazyFragment;
import com.xnhb.et.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class DetailsFragment extends BaseBarLazyFragment {

    @BindView(R.id.title_layout)
    LinearLayout mTitleLayout;

    @Override
    protected boolean isLazyLoad() {
        return false;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.setTitleBar(getActivity(), mTitleLayout);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mian_details;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        initTitleBar(mTitleLayout);
    }
}
