package com.oneway.ui.adapter.listview;

import com.oneway.ui.adapter.listview.helper.HelperAdapter;
import com.oneway.ui.adapter.listview.helper.HelperViewHolder;
import com.oneway.ui.adapter.listview.helper.ListViewCreator;

/**
 * Created by wanwei on 2018/5/21.
 */

public class XListViewAdapter<T> extends HelperAdapter<T> {

    private final ListViewCreator<T> mViewCreator;

    public XListViewAdapter(ListViewCreator<T> viewCreator) {
        super(null, viewCreator.bindListViewLayout());
        this.mViewCreator = viewCreator;
    }

    @Override
    public void HelpConvert(HelperViewHolder viewHolder, int position, T t) {
        mViewCreator.bindData(position, viewHolder, t);
    }


}
