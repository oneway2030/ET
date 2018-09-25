package com.xnhb.et.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;

import com.oneway.ui.dialog.base.BaseDailog;
import com.oneway.ui.widget.dialog.TipLabelBottomSelectDialog;
import com.xnhb.et.R;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/25
 * 描述: 选择币的类型
 * 参考链接:
 */
public class SelectCoinDialog extends BaseDailog {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    public SelectCoinDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initData() {
        mWindow.setGravity(Gravity.BOTTOM);
        mWindow.setWindowAnimations(R.style.BottomAnim);
    }

    @Override
    public int setLayoutId() {
        return R.layout.common_recy_list;
    }




}
