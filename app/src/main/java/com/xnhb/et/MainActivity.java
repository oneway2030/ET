package com.xnhb.et;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.base.ac.BaseStatusBarActivity;
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

/**
 * 作者 oneway on 2018/9/10
 * 描述: 首页
 * 参考链接:
 */
public class MainActivity extends BaseStatusBarActivity implements OnTabSelectListener {
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_DETAILS = 1;
    public static final int FRAGMENT_C2C = 2;
    public static final int FRAGMENT_ORDERS = 3;
    public static final int FRAGMENT_WALLET = 5;
    @BindView(R.id.tabLayout)
    CommonTabLayout mTabLayout;
    private HomeFragment mHomeFragment;
    private DetailsFragment mDetailsFragment;
    private C2CFragment mC2CFragment;
    private OrdersFragment mOrdersFragment;
    private WalletFragment mWalletFragment;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {UiUtils.getString(R.string.title_home), UiUtils.getString(R.string.title_details), UiUtils.getString(R.string.title_c2c), UiUtils.getString(R.string.title_order), UiUtils.getString(R.string.title_wallet)};
    private int[] mIconUnselectIds = {
            R.mipmap.navigation_home_unselected, R.mipmap.navigation_details_unselected,
            R.mipmap.navigation_c2c_unselected, R.mipmap.navigation_order_unselected, R.mipmap.navigation_wallet_unselected};
    private int[] mIconSelectIds = {
            R.mipmap.navigation_home_selected, R.mipmap.navigation_details_selected,
            R.mipmap.navigation_c2c_selected, R.mipmap.navigation_order_selected, R.mipmap.navigation_wallet_selected};
    private int defaultPosition = FRAGMENT_HOME;

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected int bindView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mHomeFragment = new HomeFragment();
        mDetailsFragment = new DetailsFragment();
        mC2CFragment = new C2CFragment();
        mOrdersFragment = new OrdersFragment();
        mWalletFragment = new WalletFragment();
        mFragments.add(mHomeFragment);
        mFragments.add(mDetailsFragment);
        mFragments.add(mC2CFragment);
        mFragments.add(mOrdersFragment);
        mFragments.add(mWalletFragment);
        initTablayout();
    }

    private void initTablayout() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout.setOnTabSelectListener(this);
        mTabLayout.setTabData(mTabEntities, this, R.id.fl_change, mFragments);
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
            mTabLayout.setCurrentTab(defaultPosition);
            return;
        }
        defaultPosition = position;
    }

    @Subscriber(tag = EventBusTags.TAG_HOME_SWTICH_PAGE)
    public void remoteSwtichPage(int position) {
        showFragment(position);
        //tab库里 会崩溃     //commit要换成commitAllowingStateLoss,不然在后台切换的时候会报错
        mTabLayout.setCurrentTab(position);
    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        defaultPosition = index;
        switch (index) {
            case FRAGMENT_HOME:
                /**
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 */
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    ft.add(R.id.content, mHomeFragment, HomeFragment.class.getName());
                } else {
                    ft.show(mHomeFragment);
                }
                break;
            case FRAGMENT_DETAILS:
                if (mDetailsFragment == null) {
                    mDetailsFragment = new DetailsFragment();
                    ft.add(R.id.content, mDetailsFragment, DetailsFragment.class.getName());
                } else {
                    ft.show(mDetailsFragment);
                }
                break;

            case FRAGMENT_C2C:
                if (mC2CFragment == null) {
                    mC2CFragment = new C2CFragment();
                    ft.add(R.id.content, mC2CFragment, C2CFragment.class.getName());
                } else {
                    ft.show(mC2CFragment);
                }
                break;

            case FRAGMENT_ORDERS:
                if (mOrdersFragment == null) {
                    mOrdersFragment = new OrdersFragment();
                    ft.add(R.id.content, mOrdersFragment, OrdersFragment.class.getName());
                } else {
                    ft.show(mOrdersFragment);
                }
                break;
            case FRAGMENT_WALLET:
                if (mWalletFragment == null) {
                    mWalletFragment = new WalletFragment();
                    ft.add(R.id.content, mWalletFragment, WalletFragment.class.getName());
                } else {
                    ft.show(mWalletFragment);
                }
                break;
        }
        ft.commit();
    }
    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (mHomeFragment != null) {
            ft.hide(mHomeFragment);
        }
        if (mDetailsFragment != null) {
            ft.hide(mDetailsFragment);
        }

        if (mC2CFragment != null) {
            ft.hide(mC2CFragment);
        }
        if (mOrdersFragment != null) {
            ft.hide(mOrdersFragment);
        }
        if (mWalletFragment != null) {
            ft.hide(mWalletFragment);
        }
    }


    @Override
    protected void setStatusBar() {

    }


}
