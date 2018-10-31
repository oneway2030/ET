package com.xnhb.et.ui.fragment.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.OnKeyboardListener;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.base.fragment.FragmentBaseAdapter;
import com.oneway.ui.base.fragment.XFragment;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.helper.PageStateHelper;
import com.oneway.ui.widget.dialog.TipLabelBottomSelectDialog;
import com.oneway.ui.widget.status.OnRetryListener;
import com.oneway.ui.widget.status.StatusType;
import com.oneway.ui.widget.vp.CustomViewPager;
import com.xnhb.et.MainFragment;
import com.xnhb.et.R;
import com.xnhb.et.adapter.C2CAdapter;
import com.xnhb.et.bean.C2CCoinInfo;
import com.xnhb.et.bean.C2CListInfo;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.interfaces.CallBack;
import com.xnhb.et.ui.ac.c2c.C2CBillActivity;
import com.xnhb.et.ui.ac.user.LoginAndRegisterActivity;
import com.xnhb.et.ui.fragment.home.page.C2CSubFragment;
import com.xnhb.et.ui.fragment.home.presenter.C2CPresenter;
import com.xnhb.et.ui.fragment.home.view.IC2CView;
import com.xnhb.et.util.MoneyUtils;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class C2CFragment extends XFragment<C2CPresenter> implements TabLayout.OnTabSelectedListener, OnRetryListener, IC2CView {
    @BindView(R.id.title_layout)
    LinearLayout mTitleLayout;
    @BindView(R.id.tv_bill)
    ImageView tvBill;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.content)
    NestedScrollView contentView;
    @BindView(R.id.vp)
    CustomViewPager vp;
    String[] titles = {"我要买", "我要卖"};
    @BindView(R.id.tv_select_coin)
    TextView tvSelectCoin;
    List<C2CSubFragment> fragments = new ArrayList<>();
    @BindView(R.id.tv_using)
    TextView tvUsing;
    @BindView(R.id.tv_freeze)
    TextView tvFreeze;
    private PageStateHelper mPageStateHelper;
    boolean isInitData = false;


    public static C2CFragment newInstance() {
        Bundle args = new Bundle();
        C2CFragment fragment = new C2CFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public C2CPresenter newP() {
        return new C2CPresenter();
    }

    @Override
    protected boolean isBarEnabled() {
        return true;
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
    protected int setLayoutId() {
        return R.layout.fragment_mian_c2c;
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (!isInitData) {
            isInitData = true;
            onRetry(0);
        }
    }


    @Override
    protected void initView() {
        mPageStateHelper = new PageStateHelper(getActivity(), contentView, R.layout.unlogin_layout, this);
        initFragments();
        tablayout.addOnTabSelectedListener(this);
        tablayout.setupWithViewPager(vp);
        FragmentBaseAdapter mFragmentAdapter = new FragmentBaseAdapter(getChildFragmentManager(), fragments, titles);
        vp.setOffscreenPageLimit(2);
        vp.setAdapter(mFragmentAdapter);
//        C2CAdapter mAdapter = new C2CAdapter();
//        vp.setAdapter(mAdapter);
        tvBill.setOnClickListener(mPerfectClickListener);
        tvSelectCoin.setOnClickListener(mPerfectClickListener);
        mPageStateHelper.showContentView();
    }


    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.tv_bill) {//跳转c2c账单
                C2CBillActivity.launch(getActivity());
            } else if (id == R.id.tv_select_coin) {//弹出选择币类型
                showCoinSelector();
            }
        }
    };


    //TODO 网络获取单价 及种类
    public void initFragments() {
        for (int i = 0; i < 2; i++) {
            C2CSubFragment frament = C2CSubFragment.newInstance(titles[i]);
            fragments.add(frament);
        }
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        setTabColor(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        setTabColor(tab.getPosition());
    }


    public void setTabColor(int position) {
        tablayout.setSelectedTabIndicatorColor(position == 0 ? UiUtils.getColor(R.color.green_dark) : UiUtils.getColor(R.color.logout_color));
        tablayout.setTabTextColors(UiUtils.getColor(R.color.text8c), position == 0 ? UiUtils.getColor(R.color.green_dark) : UiUtils.getColor(R.color.logout_color));
    }

    private int lastPosition = 0;


    private void showCoinSelector() {
        ArrayList<C2CListInfo> coinListInfo = getP().getCoinListInfo(new CallBack<ArrayList<C2CListInfo>>() {
            @Override
            public void success(ArrayList<C2CListInfo> infos) {
                showDialog(infos);
            }
        });
        if (EmptyUtils.isEmpty(coinListInfo)) {
            return;
        }
        showDialog(coinListInfo);
    }

    public void showDialog(ArrayList<C2CListInfo> infos) {
        TipLabelBottomSelectDialog<C2CListInfo> mDialog = new TipLabelBottomSelectDialog<C2CListInfo>(getAc(), infos, lastPosition);
        mDialog.setData(new TipLabelBottomSelectDialog.DataListener() {
            @Override
            public String setData(int position) {
                return infos.get(position).getCurrencyName();
            }
        }).setItemClick(new TipLabelBottomSelectDialog.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                lastPosition = position;
                mDialog.dismiss();
                tvSelectCoin.setText(infos.get(position).getCurrencyName());
                // 请求币种具体信息
                getP().reqCionInfoData(infos.get(position), true);
            }

        }).show();
    }


    @Override
    public void onRetry(int type) {
        if (type == StatusType.OTHER_ERROR.getType()) {
            //跳转到登陆界面
            LoginAndRegisterActivity.launch(getActivity());
        } else {
            getP().getData();
        }
    }

    /**
     * 登陆后 刷新
     */
    @Subscriber(tag = EventBusTags.TAG_LOGIN_SUCDESS)
    public void remoteSwtichPage(int position) {
        //TODO 如果已初始化过,又退出了,在进来回刷新数据
        if (isInitData)
            getP().getData();
    }


    @Override
    public void showOtherError() {
        mPageStateHelper.showOtherErrorView();
    }

    @Override
    public void showLoadingPage() {
//        if(mPageStateHelper!=null)
        mPageStateHelper.showLoadingView();
    }

    @Override
    public void showContentPage() {
        mPageStateHelper.showContentView();
    }


    @Override
    public void setupUi(C2CListInfo c2cListInfo, C2CCoinInfo c2CCoinInfo) {
        showContentPage();
        tvSelectCoin.setText(c2cListInfo.getCurrencyName());
        C2CCoinInfo.WalletBean wallet = c2CCoinInfo.getWallet();
        if (wallet != null) {
            tvUsing.setText(MoneyUtils.check0(wallet.getUsing()));
            tvFreeze.setText(MoneyUtils.check0(wallet.getFreeze()));
        } else {
            tvUsing.setText("");
            tvFreeze.setText("");
        }
        //更新子界面
        for (C2CSubFragment fragment : fragments) {
            fragment.updataUi(c2cListInfo, c2CCoinInfo);
        }
    }

    @Override
    public void loginExpires() {
        mPageStateHelper.showOtherErrorView("");
    }
}
