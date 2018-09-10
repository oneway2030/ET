package com.xnhb.et;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.oneway.ui.base.ac.BaseStatusBarActivity;
import com.oneway.ui.base.ac.XBaseActivity;
import com.oneway.ui.widget.BottomNavigationViewEx;
import com.xnhb.et.constant.GlobalConfiguration;
import com.xnhb.et.ui.fragment.C2CFragment;
import com.xnhb.et.ui.fragment.DetailsFragment;
import com.xnhb.et.ui.fragment.HomeFragment;
import com.xnhb.et.ui.fragment.OrdersFragment;
import com.xnhb.et.ui.fragment.WalletFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * 作者 oneway on 2018/9/10
 * 描述: 首页
 * 参考链接:
 */
public class MainActivity extends BaseStatusBarActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_C2C = 1;
    private static final int FRAGMENT_DETAILS = 2;
    private static final int FRAGMENT_ORDERS = 3;
    private static final int FRAGMENT_WALLET = 5;
    @BindView(R.id.navigation)
    BottomNavigationViewEx mNavigation;
    private HomeFragment mHomeFragment;
    private DetailsFragment mDetailsFragment;
    private C2CFragment mC2CFragment;
    private OrdersFragment mOrdersFragment;
    private WalletFragment mWalletFragment;

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
        mNavigation.enableItemShiftingMode(false);
        mNavigation.enableShiftingMode(false);
        mNavigation.setOnNavigationItemSelectedListener(this);
        if (savedInstanceState != null) {
            mHomeFragment = (HomeFragment) getSupportFragmentManager()
                    .findFragmentByTag(HomeFragment.class.getName());
            // 会员模块
            mDetailsFragment = (DetailsFragment) getSupportFragmentManager()
                    .findFragmentByTag(DetailsFragment.class.getName());
            mC2CFragment = (C2CFragment) getSupportFragmentManager()
                    .findFragmentByTag(C2CFragment.class.getName());
            mOrdersFragment = (OrdersFragment) getSupportFragmentManager()
                    .findFragmentByTag(OrdersFragment.class.getName());
            mWalletFragment = (WalletFragment) getSupportFragmentManager()
                    .findFragmentByTag(WalletFragment.class.getName());
            // 屏幕恢复时取出位置
            int position = savedInstanceState.getInt(GlobalConfiguration.MAIN_SHOW_POSITION);
            // showFragment(position);
            mNavigation.setCurrentItem(position);
        } else {
            // showFragment(FRAGMENT_HOME);
            handleShowPosition(getIntent());
        }
    }

    private void handleShowPosition(Intent intent) {
        if (intent != null) {
            defaultPosition =
                    intent.getIntExtra(GlobalConfiguration.MAIN_SHOW_POSITION, FRAGMENT_HOME);
            mNavigation.setCurrentItem(defaultPosition);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                showFragment(FRAGMENT_HOME);
                return true;
            case R.id.navigation_details:
                showFragment(FRAGMENT_DETAILS);
                return true;
            case R.id.navigation_c2c:
                showFragment(FRAGMENT_C2C);
                return true;
            case R.id.navigation_order:
                showFragment(FRAGMENT_ORDERS);
                return true;
            case R.id.navigation_wallet:
                showFragment(FRAGMENT_WALLET);
                return true;
        }
        return false;
    }

    private int defaultPosition = FRAGMENT_HOME;

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
