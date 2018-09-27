package com.xnhb.et.ui.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oneway.ui.base.fragment.BaseFragment;
import com.oneway.ui.base.fragment.XFragment;
import com.oneway.ui.common.PerfectClickListener;
import com.xnhb.et.R;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.ui.ac.bill.HistoricalActivity;
import com.xnhb.et.ui.ac.setting.SettingActivity;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class WalletFragment extends XFragment {

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

    //    RegisterAndLoginActivity
    @Override
    protected boolean isBarEnabled() {
        return true;
    }

    public static WalletFragment newInstance() {
        Bundle args = new Bundle();
        WalletFragment fragment = new WalletFragment();
        fragment.setArguments(args);
        return fragment;
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

    @Override
    protected void initView() {
        titleIvSetting.setOnClickListener(mPerfectClickListener);
        titleTvHistorical.setOnClickListener(mPerfectClickListener);
        String accountName = UserInfoHelper.getInstance().getAccountName();
        titleTvName.setText(accountName);
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.title_iv_setting) { //设置
                SettingActivity.launch(getActivity());
            } else if (id == R.id.title_tv_historical) {//充提历史
                HistoricalActivity.launch(getActivity());
            }
        }
    };
}
