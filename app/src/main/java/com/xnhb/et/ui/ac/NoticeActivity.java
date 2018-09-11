package com.xnhb.et.ui.ac;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.adapter.recyclerview.RecyclerViewCreator;
import com.oneway.ui.adapter.recyclerview.XRecyclerViewAdapter;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.common.UniversalItemDecoration;
import com.oneway.ui.widget.list.ListLayout;
import com.xnhb.et.R;
import com.xnhb.et.bean.NoticeInfo;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/11
 * 描述:公告列表页面
 * 参考链接:
 */
public class NoticeActivity extends BaseTitleActivity implements ListLayout.TaskListener, RecyclerViewCreator<NoticeInfo>, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.listLayout)
    ListLayout mListLayout;

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, NoticeActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }


    @Override
    protected String getTitleText() {
        return "官网公告";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_notice;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mListLayout.setTaskListener(this);
        mListLayout.addItemDecoration(new MyUniversalItemDecoration());
        mListLayout.setEmptyText("暂无公告...");
        mListLayout.setAdaper(new XRecyclerViewAdapter<NoticeInfo>(this));
        mListLayout.addOnItemClickListener(this);
        mListLayout.showLoadingView();
        mListLayout.pullRefresh();
    }

    @Override
    public void task() {
        ArrayList<NoticeInfo> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(new NoticeInfo("标题", "2018-9-10", "我是内容"));
        }
        mListLayout.setData(list);
    }

    @Override
    public int bindListViewLayout() {
        return R.layout.text_two_layout;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, NoticeInfo data) {
        holder.setText(R.id.tv1, data.getTitle())
                .setText(R.id.tv2, data.getTime());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        try {
            NoticeInfo info = (NoticeInfo) adapter.getData().get(position);
            NoticeDetailsActivity.launch(this, info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 分割线
     */
    public class MyUniversalItemDecoration extends UniversalItemDecoration {
        @Override
        public Decoration getItemOffsets(int position, int childCount) {
            if (position == 0) {
                ColorDecoration decoration = new ColorDecoration();
                decoration.top = UiUtils.dp2px(5);
                return decoration;
            } else {
                ColorDecoration decoration = new ColorDecoration();
                decoration.top = UiUtils.dp2px(1);
                return decoration;
            }
        }
    }
}
