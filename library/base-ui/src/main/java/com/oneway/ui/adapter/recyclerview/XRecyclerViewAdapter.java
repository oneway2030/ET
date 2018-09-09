package com.oneway.ui.adapter.recyclerview;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by wanwei on 2018/5/21.
 */

public class XRecyclerViewAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private final RecyclerViewCreator<T> mViewCreator;

    public XRecyclerViewAdapter(RecyclerViewCreator<T> viewCreator) {
        super(viewCreator.bindListViewLayout(), null);
        this.mViewCreator = viewCreator;
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        mViewCreator.bindData(helper.getAdapterPosition(), helper, item);
    }
}
