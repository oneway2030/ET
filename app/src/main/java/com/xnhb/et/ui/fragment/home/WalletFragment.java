package com.xnhb.et.ui.fragment.home;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oneway.ui.base.fragment.BaseFragment;
import com.xnhb.et.R;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class WalletFragment extends BaseFragment {

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
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mian_wallet;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mTitleLayout)
                .init();
    }
}
