package com.xnhb.et.ui.ac.detal;

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
import com.xnhb.et.ui.ac.c2c.C2CBillActivity;
import com.xnhb.et.ui.fragment.home.page.OrderSubListFrament;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 oneway on 2018/10/20
 * 描述:
 * 参考链接:
 */
public class CoinBillActivity extends BaseTitleActivity {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;
    String[] titles = {"委托记录", "成交记录"};

    @Override
    protected String getTitleText() {
        return "XX/ECNY";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_coin_bill;
    }

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CoinBillActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        FragmentBaseAdapter mFragmentAdapter = new FragmentBaseAdapter(getSupportFragmentManager(), getFragments(), titles);
        vp.setAdapter(mFragmentAdapter);
        tablayout.setupWithViewPager(vp);
    }

    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            OrderSubListFrament frament = OrderSubListFrament.newInstance(i);
            fragments.add(frament);
        }
        return fragments;
    }


}
