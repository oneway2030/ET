package com.xnhb.et.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.fragment.FragmentBaseAdapter;
import com.oneway.ui.util.PageStateHelper;
import com.xnhb.et.R;
import com.xnhb.et.ui.fragment.home.page.DetailsListFrament;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者 oneway on 2018/9/21
 * 描述:
 * 参考链接:
 */
public class TestActivity extends BaseTitleActivity {
    @BindView(R.id.title_layout)
    LinearLayout mTitleLayout;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;
    String[] titles = {"ECNY", "ETH", "BTC", "自选"};

    @Override
    protected String getTitleText() {
        return null;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_mian_details;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        FragmentBaseAdapter mFragmentAdapter = new FragmentBaseAdapter(getSupportFragmentManager(), getFragments(), titles);
        vp.setAdapter(mFragmentAdapter);
        tablayout.setupWithViewPager(vp);
    }

    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        //ECNY
        Bundle bundle = new Bundle();
        DetailsListFrament ECNYFrament = new DetailsListFrament();
        bundle.putInt(DetailsListFrament.BUNDLE_ARGUMENTS, DetailsListFrament.PAGE_TYPE_ECNY);
        ECNYFrament.setArguments(bundle);
        //ETH
        DetailsListFrament ETHFrament = new DetailsListFrament();
        bundle = new Bundle();
        bundle.putInt(DetailsListFrament.BUNDLE_ARGUMENTS, DetailsListFrament.PAGE_TYPE_ETH);
        ETHFrament.setArguments(bundle);
        //BTC
        DetailsListFrament BTCFrament = new DetailsListFrament();
        bundle = new Bundle();
        bundle.putInt(DetailsListFrament.BUNDLE_ARGUMENTS, DetailsListFrament.PAGE_TYPE_BTC);
        BTCFrament.setArguments(bundle);
        //自选
        DetailsListFrament customFrament = new DetailsListFrament();
        bundle = new Bundle();
        bundle.putInt(DetailsListFrament.BUNDLE_ARGUMENTS, DetailsListFrament.PAGE_TYPE_CUSTOM);
        customFrament.setArguments(bundle);
        fragments.add(ECNYFrament);
        fragments.add(ETHFrament);
        fragments.add(BTCFrament);
        fragments.add(customFrament);
        return fragments;
    }
}
