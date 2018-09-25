package com.xnhb.et.ui.fragment.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.oneway.ui.base.fragment.BaseFragment;
import com.oneway.ui.base.fragment.FragmentBaseAdapter;
import com.oneway.ui.base.fragment.XFragment;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.tv.AutoVerticalTextView;
import com.xnhb.et.R;
import com.xnhb.et.adapter.HomeHAdapter;
import com.xnhb.et.bean.HomeHDataInfo;
import com.xnhb.et.common.GlideImageLoader;
import com.xnhb.et.ui.ac.NoticeActivity;
import com.xnhb.et.ui.fragment.home.page.HomeSubFragment;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class HomeFragment extends XFragment {
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.autoVerticalTextview)
    AutoVerticalTextView mAutoVerticalTextview;
    @BindView(R.id.notice_layout)
    LinearLayout noticeLayout;
    @BindView(R.id.xTablayout)
    XTabLayout mTabLayout;
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.HRecyclerView)
    RecyclerView mHRecyclerView;
    String[] mTitles = {"涨幅榜", "成交榜"};
    private FragmentBaseAdapter mFragmentAdapter;
    private HomeHAdapter mAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mian_home;
    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
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
                .titleBar(mBanner)
                .statusBarColor(R.color.colorPrimary_statbar_bg)
                .init();
    }

    @Override
    protected void initView() {
        noticeLayout.setOnClickListener(mPerfectClickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mHRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new HomeHAdapter();
        mHRecyclerView.setAdapter(mAdapter);
        mFragmentAdapter = new FragmentBaseAdapter(getChildFragmentManager(), getFragmentPage(), mTitles);
        mViewPager.setOffscreenPageLimit(mTitles.length);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {
        setBanner();
        mAdapter.setNewData(getHData());
        mAutoVerticalTextview.setTextList(getTvData());//加入显示内容,集合类型
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.notice_layout) {
                NoticeActivity.launch(getActivity());
            }
        }
    };

    private void setBanner() {
        mBanner.setImages(getBannerData())
                .setImageLoader(new GlideImageLoader())
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setOnBannerListener(position -> ToastManager.info("点击了 =>" + position));
        mBanner.start();
    }


    @Override
    public void onResume() {
        super.onResume();
        mAutoVerticalTextview.startAutoScroll();
        mBanner.startAutoPlay();
    }

    //停止滚动
    @Override
    public void onPause() {
        super.onPause();
        mAutoVerticalTextview.stopAutoScroll();
        mBanner.stopAutoPlay();
    }

    public List<Fragment> getFragmentPage() {
        List<Fragment> fragments = new ArrayList<>();
        //涨幅
        HomeSubFragment mZhangFuFragment = new HomeSubFragment();
        //成交
        HomeSubFragment mChenJiaoFragment = new HomeSubFragment();
        fragments.add(mZhangFuFragment);
        fragments.add(mChenJiaoFragment);
        return fragments;
    }

    protected ArrayList<Integer> getBannerData() {
        ArrayList<Integer> pics = new ArrayList<>();
        pics.add(R.mipmap.banner_bg);
        pics.add(R.mipmap.banner_bg);
        pics.add(R.mipmap.banner_bg);
        pics.add(R.mipmap.test);
        return pics;
    }

    protected ArrayList<String> getTvData() {
        ArrayList<String> titles = new ArrayList<>();
        titles.add("一条公告");
        titles.add("二条公告");
        titles.add("三条公告");
        titles.add("四条公告");
        return titles;
    }

    public ArrayList<HomeHDataInfo> getHData() {
        ArrayList<HomeHDataInfo> infos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            infos.add(new HomeHDataInfo());
        }
        return infos;
    }
}
