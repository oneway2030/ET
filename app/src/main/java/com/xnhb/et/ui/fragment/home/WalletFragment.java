package com.xnhb.et.ui.fragment.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.OnKeyboardListener;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.base.fragment.XFragment;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.helper.PageStateHelper;
import com.oneway.ui.widget.status.OnRetryListener;
import com.xnhb.et.MainFragment;
import com.xnhb.et.R;
import com.xnhb.et.adapter.MyCoinListAdapter;
import com.xnhb.et.bean.CoinInfo;
import com.xnhb.et.bean.WrapCoinInfo;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.ui.ac.bill.HistoricalActivity;
import com.xnhb.et.ui.ac.setting.SettingActivity;
import com.xnhb.et.ui.fragment.home.present.HomePresent;
import com.xnhb.et.ui.fragment.home.present.WalletPresent;
import com.xnhb.et.ui.fragment.home.view.IWalletView;
import com.xnhb.et.widget.dialog.RechargeAndWithdrawalDialog;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class WalletFragment extends XFragment<WalletPresent> implements BaseQuickAdapter.OnItemClickListener, OnRetryListener, IWalletView {

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
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.check_box)
    ImageView checkBox;
    @BindView(R.id.tv_tip_hint_money)
    TextView tvTipHintMoney;
    @BindView(R.id.ll_tip_hint_money)
    LinearLayout llTipHintMoney;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.content)
    LinearLayout content;
    private MyCoinListAdapter mAdapter;
    private PageStateHelper mPageStateHelper;

    //    RegisterAndLoginActivity
    @Override
    protected boolean isBarEnabled() {
        return true;
    }

    @Override
    public WalletPresent newP() {
        return new WalletPresent();
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
        mImmersionBar
                .titleBar(mTitleLayout)
                .setOnKeyboardListener(new OnKeyboardListener() {
                    @Override
                    public void onKeyboardChange(boolean isPopup, int keyboardHeight) {
                        //isPopup为true，软键盘弹出，为false，软键盘关闭
                        Fragment parentFragment = getParentFragment();
                        if (parentFragment instanceof MainFragment) {
                            ((MainFragment) getParentFragment()).showAndHideBottomBar(isPopup);
                        }
                    }
                })
                .init();
    }

    @Override
    protected void initView() {
        mPageStateHelper = new PageStateHelper(getActivity(), content, this);
        mPageStateHelper.showLoadingView();
        titleIvSetting.setOnClickListener(mPerfectClickListener);
        titleTvHistorical.setOnClickListener(mPerfectClickListener);
        String accountName = UserInfoHelper.getInstance().getAccountName();
        titleTvName.setText(accountName);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MyCoinListAdapter();
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        getP().reqWalletInfo();
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //弹出底部dialog
        CoinInfo info = (CoinInfo) adapter.getData().get(position);
        RechargeAndWithdrawalDialog mDialog = new RechargeAndWithdrawalDialog(getActivity(), info.getCurrencyName());
        mDialog.showDialog();

    }

    @Override
    public void onRetry(int type) {
        getP().reqWalletInfo();
    }


    @Override
    public void showErrorPage() {
        mPageStateHelper.showErrorView();
    }

    @Override
    public void showLoadingPage() {
        mPageStateHelper.showLoadingView();
    }

    @Override
    public void showListInfo(WrapCoinInfo data) {
        totalBtcMoney.setText(data.getTotal());
        if (EmptyUtils.isNotEmpty(data.getCurrencyList())) {
            mPageStateHelper.showContentView();
            mAdapter.setNewData(data.getCurrencyList());
        } else {
            mPageStateHelper.showEmptyView();
        }
    }

    /**
     * 登陆后 刷新
     */
    @Subscriber(tag = EventBusTags.TAG_LOGIN_SUCDESS)
    public void loginRefresh(int position) {
        getP().reqWalletInfo();
    }

}
