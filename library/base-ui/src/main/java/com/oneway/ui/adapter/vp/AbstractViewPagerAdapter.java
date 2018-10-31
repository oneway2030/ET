package com.oneway.ui.adapter.vp;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.oneway.tool.utils.convert.EmptyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 oneway on 2018/10/31
 * 描述:
 * 参考链接:
 */
public abstract class AbstractViewPagerAdapter<T> extends PagerAdapter {
    protected List<T> mData;
    private SparseArray<View> mViews;
    protected String[] mTitles;

    public AbstractViewPagerAdapter(List<T> data) {
        this(data, null);
    }

    public AbstractViewPagerAdapter(List<T> data, String[] titles) {
        this.mTitles = titles;
        this.mData = data;
        if (EmptyUtils.isEmpty(mData)) {
            mData = new ArrayList<T>();
        }
        mViews = new SparseArray<View>(data.size());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        if (view == null) {
            view = newView(container, position);
            mViews.put(position, view);
        }
        container.addView(view);
        return view;
    }

    public abstract View newView(ViewGroup container, int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    public String getTitle(int position) {
        return EmptyUtils.isEmpty(mTitles) ? "" : mTitles[position];
    }
}

