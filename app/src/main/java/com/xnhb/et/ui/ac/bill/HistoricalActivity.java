package com.xnhb.et.ui.ac.bill;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.fragment.FragmentBaseAdapter;
import com.xnhb.et.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/26
 * 描述: 充提 历史记录
 * 参考链接:
 */
public class HistoricalActivity extends BaseTitleActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    String[] titles = {"充值", "提现", "买入手续费", "卖出手续费", "系统赠送"};

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, HistoricalActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected String getTitleText() {
        return "历史记录";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_c2c_bill;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        tvHint.setVisibility(View.GONE);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        FragmentBaseAdapter mFragmentAdapter = new FragmentBaseAdapter(getSupportFragmentManager(), getFragments(), titles);
        vp.setAdapter(mFragmentAdapter);
        tablayout.setupWithViewPager(vp);
    }

    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HistoricalFrament frament = HistoricalFrament.newInstance(i);
            fragments.add(frament);
        }
        return fragments;
    }
}
