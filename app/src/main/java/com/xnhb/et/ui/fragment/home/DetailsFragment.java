package com.xnhb.et.ui.fragment.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.oneway.ui.base.fragment.BaseFragment;
import com.oneway.ui.base.fragment.FragmentBaseAdapter;
import com.oneway.ui.widget.NoScrollViewPager;
import com.xnhb.et.R;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.ui.fragment.home.page.DetailsListFrament;
import com.xnhb.et.ui.fragment.home.page.OrderListFrament;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class DetailsFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.title_layout)
    LinearLayout mTitleLayout;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;
    String[] titles = {"ECNY", "ETH", "BTC", "自选"};

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initView() {
        FragmentBaseAdapter mFragmentAdapter = new FragmentBaseAdapter(getChildFragmentManager(), getFragments(), titles);
        vp.setAdapter(mFragmentAdapter);
        tablayout.setupWithViewPager(vp);
        tablayout.addOnTabSelectedListener(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mian_details;
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
        //ECNY
        DetailsListFrament ECNYFrament = new DetailsListFrament();
        //ETH
        DetailsListFrament ETHFrament = new DetailsListFrament();
        //BTC
        DetailsListFrament BTCFrament = new DetailsListFrament();
        //自选
        DetailsListFrament customFrament = new DetailsListFrament();
        fragments.add(ECNYFrament);
        fragments.add(ETHFrament);
        fragments.add(BTCFrament);
        fragments.add(customFrament);
        return fragments;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        setViewPosition(tab);
    }

    private void setViewPosition(TabLayout.Tab tab) {
        String tabTitle = tab.getText().toString().trim();
        if (titles[titles.length - 1].equals(tabTitle)) {//如果是自选,则判断是否登陆
            // 判断是否登陆
            if (!UserInfoHelper.getInstance().checkLogin()) {
                tablayout.setScrollPosition(0, 0f, true);
                return;
            }
        }
        vp.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        setViewPosition(tab);
    }



}
