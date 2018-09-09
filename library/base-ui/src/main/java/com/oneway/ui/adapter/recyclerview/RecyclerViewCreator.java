package com.oneway.ui.adapter.recyclerview;


import com.chad.library.adapter.base.BaseViewHolder;

public interface RecyclerViewCreator<T> {

    int bindListViewLayout();

    void bindData(int position, BaseViewHolder holder, T data);
}
