package com.xnhb.et.ui.ac.detail;

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
import com.xnhb.et.bean.OrderInfo;
import com.xnhb.et.bean.TradeUserInfo;
import com.xnhb.et.ui.ac.detail.fragment.OrderSubListFrament2;
import com.xnhb.et.ui.fragment.home.page.OrderSubListFrament;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
    ArrayList<OrderInfo> entrustRecord;
    ArrayList<OrderInfo> tradeRecord;
    private String title;
    private String currentName;
    private String tradeName;

    @Override
    protected String getTitleText() {
        return title;
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_coin_bill;
    }

    @Override
    protected boolean getIntent(Intent intent) {
        title = intent.getStringExtra("title");
        currentName = intent.getStringExtra("currentName");
        tradeName = intent.getStringExtra("tradeName");
        tradeRecord = intent.getParcelableArrayListExtra("tradeRecord");
        entrustRecord = intent.getParcelableArrayListExtra("entrustRecord");
        return false;
    }

    public static void launch(Context context, String title, String currentName, String tradeName, ArrayList<OrderInfo> entrustRecord, ArrayList<OrderInfo> tradeRecord) {
        Intent intent = new Intent();
        intent.setClass(context, CoinBillActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra("title", title);
        intent.putExtra("currentName", currentName);
        intent.putExtra("tradeName", tradeName);
        intent.putParcelableArrayListExtra("tradeRecord", tradeRecord);
        intent.putParcelableArrayListExtra("entrustRecord", entrustRecord);
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
        OrderSubListFrament2 entrustRecordFrament = OrderSubListFrament2.newInstance(entrustRecord, 0,currentName,tradeName);
        OrderSubListFrament2 tradeRecordFrament = OrderSubListFrament2.newInstance(tradeRecord, 1,currentName,tradeName);
        fragments.add(entrustRecordFrament);
        fragments.add(tradeRecordFrament);
        return fragments;
    }


}
