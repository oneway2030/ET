package com.oneway.ui.base.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class FragmentBaseAdapter<T> extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    ArrayList<T> mTitleList;
    String[] mTitleArray;

    public FragmentBaseAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentBaseAdapter(FragmentManager fm, List<Fragment> fragments, ArrayList<T> mTitleList) {
        super(fm);
        this.fragments = fragments;
        this.mTitleList = mTitleList;
    }

    public FragmentBaseAdapter(FragmentManager fm, List<Fragment> fragments, String[] mTitleArray) {
        super(fm);
        this.fragments = fragments;
        this.mTitleArray = mTitleArray;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public String getPageTitle(int position) {
        if (mTitleList != null) {
            T t = mTitleList.get(position);
            if (t instanceof String) {
                return (String) t;
            }
        } else if (mTitleArray != null) {
            return mTitleArray[position];
        }
        return "";
    }

    @Override
    public int getCount() {

        return fragments.size();
    }

}