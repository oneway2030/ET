package com.xnhb.et.ui.fragment.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.oneway.ui.base.fragment.BaseFragment;
import com.oneway.ui.base.fragment.FragmentBaseAdapter;
import com.xnhb.et.R;
import com.xnhb.et.ui.fragment.home.page.OrderListFrament;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 作者 oneway on 2018/9/10
 * 描述:订单 列表
 * 参考链接:
 */
public class OrdersFragment extends BaseFragment {

    @BindView(R.id.title_layout)
    TextView mTitleLayout;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    Unbinder unbinder;
    @BindView(R.id.vp)
    ViewPager vp;
    String[] titles = {"委托记录", "成交记录"};

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
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
        OrderListFrament executionFrament = new OrderListFrament();
        //成交记录
        OrderListFrament completeFrament = new OrderListFrament();
//        //已取消
//        OrderListFrament cancelFrament = new OrderListFrament();
        fragments.add(executionFrament);
        fragments.add(completeFrament);
//        fragments.add(cancelFrament);
        return fragments;
    }

}
