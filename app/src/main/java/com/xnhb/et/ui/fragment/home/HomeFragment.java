package com.xnhb.et.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.oneway.ui.base.fragment.BaseFragment;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.tv.AutoVerticalTextView;
import com.xnhb.et.R;
import com.xnhb.et.common.GlideImageLoader;
import com.xnhb.et.ui.ac.NoticeActivity;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.autoVerticalTextview)
    AutoVerticalTextView mAutoVerticalTextview;
    @BindView(R.id.notice_layout)
    LinearLayout noticeLayout;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mian_home;
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
    protected void initData() {
        noticeLayout.setOnClickListener(mPerfectClickListener);
        setBanner();
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
}
