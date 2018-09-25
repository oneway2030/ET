package com.xnhb.et.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.OnKeyboardListener;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.base.fragment.FragmentBaseAdapter;
import com.oneway.ui.base.fragment.XFragment;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.widget.dialog.TipLabelBottomSelectDialog;
import com.oneway.ui.widget.vp.CustomViewPager;
import com.xnhb.et.MainFragment;
import com.xnhb.et.R;
import com.xnhb.et.ui.fragment.home.page.C2CSubFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class C2CFragment extends XFragment implements TabLayout.OnTabSelectedListener {
    @BindView(R.id.title_layout)
    LinearLayout mTitleLayout;
    @BindView(R.id.tv_bill)
    ImageView tvBill;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    CustomViewPager vp;
    String[] titles = {"我要买", "我要卖"};
    @BindView(R.id.tv_select_coin)
    TextView tvSelectCoin;
    List<C2CSubFragment> fragments = new ArrayList<>();

    public static C2CFragment newInstance() {
        Bundle args = new Bundle();
        C2CFragment fragment = new C2CFragment();
        fragment.setArguments(args);
        return fragment;
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
    protected void initView() {
        initFragments();
        tablayout.addOnTabSelectedListener(this);
        tablayout.setupWithViewPager(vp);
        FragmentBaseAdapter mFragmentAdapter = new FragmentBaseAdapter(getChildFragmentManager(), fragments, titles);
        vp.setOffscreenPageLimit(2);
        vp.setAdapter(mFragmentAdapter);
        tvBill.setOnClickListener(mPerfectClickListener);
        tvSelectCoin.setOnClickListener(mPerfectClickListener);
    }


    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.tv_bill) {//跳转c2c账单

            } else if (id == R.id.tv_select_coin) {//弹出选择币类型
                showCoinSelector();
            }
        }
    };

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }


    //TODO 网络获取单价 及种类
    public void initFragments() {
        for (int i = 0; i < 2; i++) {
            C2CSubFragment frament = C2CSubFragment.newInstance(titles[i], 1.00);
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

    ArrayList<String> coinTypes = new ArrayList<String>() {{
        add("ENCY");
        add("ETH");
    }};

    private void showCoinSelector() {
        TipLabelBottomSelectDialog<String> mDialog = new TipLabelBottomSelectDialog<String>(getAc(), coinTypes, lastPosition);
        mDialog.setData(new TipLabelBottomSelectDialog.DataListener() {
            @Override
            public String setData(int position) {
                return coinTypes.get(position);
            }
        }).setItemClick(new TipLabelBottomSelectDialog.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                lastPosition = position;
                mDialog.dismiss();
                tvSelectCoin.setText(coinTypes.get(position));
                updateCoinType(coinTypes.get(position));
            }


        }).show();
    }

    private void updateCoinType(String coinType) {
        for (C2CSubFragment fragment : fragments) {
            fragment.updateCoinType(coinType);
        }
    }
}
