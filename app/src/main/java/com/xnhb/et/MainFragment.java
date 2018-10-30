package com.xnhb.et;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.base.fragment.XFragment;
import com.xnhb.et.bean.TabEntity;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.ui.fragment.home.C2CFragment;
import com.xnhb.et.ui.fragment.home.DetailsFragment;
import com.xnhb.et.ui.fragment.home.HomeFragment;
import com.xnhb.et.ui.fragment.home.OrdersFragment;
import com.xnhb.et.ui.fragment.home.WalletFragment;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by geyifeng on 2017/8/12.
 */

public class MainFragment extends XFragment implements OnTabSelectListener {

    private SupportFragment[] mFragments = new SupportFragment[5];

    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_DETAILS = 1;
    public static final int FRAGMENT_C2C = 2;
    public static final int FRAGMENT_ORDERS = 3;
    public static final int FRAGMENT_WALLET = 4;
    @BindView(R.id.tabLayout)
    CommonTabLayout mTabLayout;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {UiUtils.getString(R.string.title_home), UiUtils.getString(R.string.title_details), UiUtils.getString(R.string.title_c2c), UiUtils.getString(R.string.title_order), UiUtils.getString(R.string.title_wallet)};
    private int[] mIconUnselectIds = {
            R.mipmap.navigation_home_unselected, R.mipmap.navigation_details_unselected,
            R.mipmap.navigation_c2c_unselected, R.mipmap.navigation_order_unselected, R.mipmap.navigation_wallet_unselected};
    private int[] mIconSelectIds = {
            R.mipmap.navigation_home_selected, R.mipmap.navigation_details_selected,
            R.mipmap.navigation_c2c_selected, R.mipmap.navigation_order_selected, R.mipmap.navigation_wallet_selected};
    private int lastPosition = FRAGMENT_HOME;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment homeFragment = findChildFragment(HomeFragment.class);
        if (homeFragment == null) {
            mFragments[FRAGMENT_HOME] = HomeFragment.newInstance();
            mFragments[FRAGMENT_DETAILS] = DetailsFragment.newInstance();
            mFragments[FRAGMENT_C2C] = C2CFragment.newInstance();
            mFragments[FRAGMENT_ORDERS] = OrdersFragment.newInstance();
            mFragments[FRAGMENT_WALLET] = WalletFragment.newInstance();

            loadMultipleRootFragment(R.id.content, FRAGMENT_HOME,
                    mFragments[FRAGMENT_HOME],
                    mFragments[FRAGMENT_DETAILS],
                    mFragments[FRAGMENT_C2C],
                    mFragments[FRAGMENT_ORDERS],
                    mFragments[FRAGMENT_WALLET]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.findFragmentByTag自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FRAGMENT_HOME] = homeFragment;
            mFragments[FRAGMENT_DETAILS] = findChildFragment(DetailsFragment.class);
            mFragments[FRAGMENT_C2C] = findChildFragment(C2CFragment.class);
            mFragments[FRAGMENT_ORDERS] = findChildFragment(OrdersFragment.class);
            mFragments[FRAGMENT_WALLET] = findChildFragment(WalletFragment.class);
        }
    }

    @Override
    protected void initView() {
        initTablayout();
    }

    @Override
    protected void setListener() {

    }

    @Subscriber(tag = EventBusTags.TAG_HOME_SWTICH_PAGE)
    public void remoteSwtichPage(int position) {
        lastPosition = 0;
        showHideFragment(mFragments[position]);
        mTabLayout.setCurrentTab(position);
    }

    private void initTablayout() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout.setOnTabSelectListener(this);
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setCurrentTab(0);
    }


    @Override
    public void onTabSelect(int position) {
        swtichPage(position);
    }

    @Override
    public void onTabReselect(int position) {
        swtichPage(position);
    }


    private void swtichPage(int position) {
        if (position != FRAGMENT_HOME && position != FRAGMENT_DETAILS && !UserInfoHelper.getInstance().checkLogin()) {
            mTabLayout.setCurrentTab(lastPosition);
            return;
        }
        lastPosition = position;
        showHideFragment(mFragments[position]);
    }

    /**
     * 子fragment弹出软键盘时候,隐藏底部导航栏
     */
    public void showAndHideBottomBar(boolean ishide) {
        mTabLayout.setVisibility(ishide ? View.GONE : View.VISIBLE);
    }
}
