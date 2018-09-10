package com.xnhb.et.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
public class WalletFragment extends BaseBarLazyFragment {
    @BindView(R.id.title_iv_setting)
    ImageView titleIvSetting;
    @BindView(R.id.title_tv_name)
    TextView titleTvName;
    @BindView(R.id.title_tv_historical)
    TextView titleTvHistorical;
    @BindView(R.id.total_btc_money)
    TextView totalBtcMoney;
    @BindView(R.id.total_cny_money)
    TextView totalCnyMoney;
    @BindView(R.id.title_layout)
    LinearLayout mTitleLayout;

    @Override
    protected boolean isLazyLoad() {
        return false;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mian_wallet;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.setTitleBar(getActivity(), mTitleLayout);
    }
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        initTitleBar(mTitleLayout);
    }
}
