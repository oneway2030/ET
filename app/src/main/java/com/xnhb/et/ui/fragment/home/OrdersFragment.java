package com.xnhb.et.ui.fragment.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.oneway.ui.base.fragment.FragmentBaseAdapter;
import com.oneway.ui.base.fragment.XFragment;
import com.xnhb.et.R;
import com.xnhb.et.ui.fragment.home.page.OrderSubListFrament;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 作者 oneway on 2018/9/10
 * 描述:订单 列表
 * 参考链接:
 */
public class OrdersFragment extends XFragment {

    @BindView(R.id.title_layout)
    TextView mTitleLayout;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    Unbinder unbinder;
    @BindView(R.id.vp)
    ViewPager vp;
    String[] titles = {"委托记录", "成交记录"};

    @Override
    protected boolean isBarEnabled() {
        return true;
    }

    public static OrdersFragment newInstance() {
        Bundle args = new Bundle();
        OrdersFragment fragment = new OrdersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        FragmentBaseAdapter mFragmentAdapter = new FragmentBaseAdapter(getChildFragmentManager(), getFragments(), titles);
        vp.setAdapter(mFragmentAdapter);
        tablayout.setupWithViewPager(vp);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mian_orders;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar
                .titleBar(mTitleLayout)
                .init();
    }


    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        //委托记录
        OrderSubListFrament executionFrament = new OrderSubListFrament();
        //成交记录
        OrderSubListFrament completeFrament = new OrderSubListFrament();
//        //已取消
//        OrderSubListFrament cancelFrament = new OrderSubListFrament();
        fragments.add(executionFrament);
        fragments.add(completeFrament);
//        fragments.add(cancelFrament);
        return fragments;
    }

}
