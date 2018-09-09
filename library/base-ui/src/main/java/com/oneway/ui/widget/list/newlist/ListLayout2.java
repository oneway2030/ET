package com.oneway.ui.widget.list.newlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 作者 oneway on 2018/7/13
 * 描述:
 * 参考链接:
 */
public class ListLayout2 extends RelativeLayout {
    private Context mContext;
    private RxListView mRxListView;

    public ListLayout2(@NonNull Context context) {
        this(context, null);
    }

    public ListLayout2(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListLayout2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
//        ListLayoutHelper mHelper = new ListLayoutHelper();
//        mRxListView = mHelper.getNativeLayout();
        mRxListView = new NativeViewLayout();
//        mRxListView.getLayout(mContext, this);
//        mRxListView.setLayoutView();
    }


}
