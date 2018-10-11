package com.xnhb.et.ui.fragment.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.OnKeyboardListener;
import com.luck.picture.lib.tools.StringUtils;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.base.fragment.XFragment;
import com.oneway.ui.common.CommonDivider;
import com.oneway.ui.common.CommonDividerItemDecoration;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.helper.PageStateHelper;
import com.oneway.ui.widget.status.OnRetryListener;
import com.oneway.ui.widget.status.StatusType;
import com.xnhb.et.MainFragment;
import com.xnhb.et.R;
import com.xnhb.et.adapter.MyCoinListAdapter;
import com.xnhb.et.bean.CoinInfo;
import com.xnhb.et.bean.WrapCoinInfo;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.ui.ac.bill.HistoricalActivity;
import com.xnhb.et.ui.ac.setting.SettingActivity;
import com.xnhb.et.ui.ac.user.LoginAndRegisterActivity;
import com.xnhb.et.ui.fragment.home.presenter.WalletPresenter;
import com.xnhb.et.ui.fragment.home.view.IWalletView;
import com.xnhb.et.util.MoneyUtils;
import com.xnhb.et.widget.dialog.RechargeAndWithdrawalDialog;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class WalletFragment extends XFragment<WalletPresenter> implements BaseQuickAdapter.OnItemClickListener, OnRetryListener, IWalletView, TextWatcher {

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
    //    @BindView(R.id.content)
//    LinearLayout content;
    private MyCoinListAdapter mAdapter;
    private PageStateHelper mPageStateHelper;
    private List<CoinInfo> datas;
    private boolean isHideMoney = false;

    //    RegisterAndLoginActivity
    @Override
    protected boolean isBarEnabled() {
        return true;
    }

    @Override
    public WalletPresenter newP() {
        return new WalletPresenter();
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
        mPageStateHelper = new PageStateHelper(getActivity(), mRecyclerView, R.layout.unlogin_layout, this);
        mPageStateHelper.showLoadingView();
        etSearch.addTextChangedListener(this);
        llTipHintMoney.setOnClickListener(mPerfectClickListener);
        titleIvSetting.setOnClickListener(mPerfectClickListener);
        titleTvHistorical.setOnClickListener(mPerfectClickListener);
        String accountName = UserInfoHelper.getInstance().getAccountName();
        titleTvName.setText(accountName);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new CommonDivider(getActivity(), LinearLayoutManager.VERTICAL, UiUtils.dp2px(10), UiUtils.getColor(R.color.black)));
        mAdapter = new MyCoinListAdapter();
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        if (UserInfoHelper.getInstance().isLogin()) {
            getP().reqWalletInfo();
        } else {
            showErrorPage();
        }
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.title_iv_setting) { //设置
                SettingActivity.launch(getActivity());
            } else if (id == R.id.title_tv_historical) {//充提历史
                HistoricalActivity.launch(getActivity());
            } else if (id == R.id.ll_tip_hint_money) {//隐藏资产
                checkBox.setImageResource(isHideMoney ? R.mipmap.checkbox_unchecked_32 : R.mipmap.checkbox_checked_32);
                isHideMoney = !isHideMoney;
                mAdapter.hideMoney();
            }
        }
    };


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //弹出底部dialog
        CoinInfo info = (CoinInfo) adapter.getData().get(position);
        if ("ECNY".equals(info.getCurrencyName())) {
            //ENCY 默认不可充值提现
            return;
        }
        RechargeAndWithdrawalDialog mDialog = new RechargeAndWithdrawalDialog(getActivity(), info);
        mDialog.showDialog();

    }

    @Override
    public void onRetry(int type) {
        if (type == StatusType.OTHER_ERROR.getType()) {
            //跳转到登陆界面
            LoginAndRegisterActivity.launch(getActivity());
        } else {
            getP().reqWalletInfo();
        }

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
        totalBtcMoney.setText(MoneyUtils.formatMoney(data.getTotal()));
        if (EmptyUtils.isNotEmpty(data.getCurrencyList())) {
            datas = data.getCurrencyList();
            mPageStateHelper.showContentView();
            mAdapter.setNewData(data.getCurrencyList());
        } else {
            mPageStateHelper.showEmptyView();
        }
    }

    @Override
    public void loginExpires() {
        mPageStateHelper.showOtherErrorView("");
    }

    /**
     * 登陆后 刷新
     */
    @Subscriber(tag = EventBusTags.TAG_LOGIN_SUCDESS)
    public void loginRefresh(int position) {
        getP().reqWalletInfo();
    }

    /**
     * 提现成功
     */
    @Subscriber(tag = EventBusTags.TAG_WITHDRAWALS_SUCCESS)
    public void loginRefresh(String tag) {
        getP().reqWalletInfo();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String keyWord = etSearch.getText().toString().trim();
        if (EmptyUtils.isNotEmpty(etSearch)) {
            searchKeyWord(keyWord);
        } else {
            mAdapter.setNewData(datas);
        }

    }

    /**
     * 检索关键词
     */
    private void searchKeyWord(String keyWord) {
        if (datas == null) {
            return;
        }
        List<CoinInfo> tempData = new ArrayList<>();
        for (CoinInfo info : datas) {
            //TODO 最好处理不区分大小写
            if (info.getCurrencyName().contains(keyWord))
                tempData.add(info);
        }
        if (EmptyUtils.isEmpty(tempData)) {
            mPageStateHelper.showEmptyView();
        } else {
            mPageStateHelper.showContentView();
            mAdapter.setNewData(tempData);
        }
    }
}
