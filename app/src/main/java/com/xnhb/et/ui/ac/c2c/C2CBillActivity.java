package com.xnhb.et.ui.ac.c2c;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.fragment.FragmentBaseAdapter;
import com.xnhb.et.R;
import com.xnhb.et.bean.NoticeInfo;
import com.xnhb.et.ui.ac.NoticeDetailsActivity;
import com.xnhb.et.ui.fragment.home.page.OrderSubListFrament;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/26
 * 描述:
 * 参考链接:
 */
public class C2CBillActivity extends BaseTitleActivity {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;
    String[] titles = {"执行中", "已成交", "已取消"};

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, C2CBillActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected String getTitleText() {
        return "C2C账单";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_c2c_bill;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        FragmentBaseAdapter mFragmentAdapter = new FragmentBaseAdapter(getSupportFragmentManager(), getFragments(), titles);
        vp.setAdapter(mFragmentAdapter);
        tablayout.setupWithViewPager(vp);
    }

    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        //执行中
        OrderSubListFrament executionFrament = new OrderSubListFrament();
        //已成交
        OrderSubListFrament completeFrament = new OrderSubListFrament();
        //已取消
        OrderSubListFrament cancelFrament = new OrderSubListFrament();
        fragments.add(executionFrament);
        fragments.add(completeFrament);
        fragments.add(cancelFrament);
        return fragments;
    }
}
