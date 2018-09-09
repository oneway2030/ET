package com.oneway.ui.adapter.listview.helper;


public interface ListViewCreator<T> {

    int bindListViewLayout();

    void bindData(int position, HelperViewHolder holder, T data);
}
