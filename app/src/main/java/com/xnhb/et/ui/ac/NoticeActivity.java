package com.xnhb.et.ui.ac;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Response;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.adapter.recyclerview.RecyclerViewCreator;
import com.oneway.ui.adapter.recyclerview.XRecyclerViewAdapter;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.common.UniversalItemDecoration;
import com.oneway.ui.widget.list.ListLayout;
import com.xnhb.et.R;
import com.xnhb.et.bean.NoticeInfo2;
import com.xnhb.et.bean.base.LimitPage;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/11
 * 描述:公告列表页面
 * 参考链接:
 */
public class NoticeActivity extends BaseTitleActivity implements ListLayout.TaskListener, RecyclerViewCreator<NoticeInfo2>, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.listLayout)
    ListLayout mListLayout;
    public static final String INTENT_ARG = "intent_arg";
    private ArrayList<NoticeInfo2> infos;

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
    protected boolean getIntent(Intent intent) {
        infos = intent.getParcelableArrayListExtra(INTENT_ARG);
        return false;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mListLayout.setTaskListener(this);
        mListLayout.addItemDecoration(new MyUniversalItemDecoration());
        mListLayout.setEmptyText("暂无公告...");
        mListLayout.setAdaper(new XRecyclerViewAdapter<NoticeInfo2>(this));
        mListLayout.addOnItemClickListener(this);
        mListLayout.showLoadingView();
        mListLayout.pullRefresh();
    }

    @Override
    public void task() {
        Map map = new HashMap();
        map.put("title", "");
        map.put("pageNum", mListLayout.getCurrentPageNumber() + "");
        map.put("numPerPage", "20");
        OkGoHelper.postOkGo(Api.NOTICE_LIST_INFO, this)
                .params(map)
                .execute(new DialogCallback<ResultInfo<LimitPage<NoticeInfo2>>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<LimitPage<NoticeInfo2>>> response) {
                        ResultInfo<LimitPage<NoticeInfo2>> body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            return;
                        }
                        LimitPage<NoticeInfo2> result = body.getResult();
                        if (EmptyUtils.isEmpty(result)) {
                            return;
                        }
                        ArrayList<NoticeInfo2> list = result.getList();
                        mListLayout.setData(list);
                        mListLayout.setTotalPageNumber(result.getPages());
                    }

                    @Override
                    public void onError(Response<ResultInfo<LimitPage<NoticeInfo2>>> response) {
                        super.onError(response);
                        mListLayout.showErrorView();
                    }
                });

    }

    @Override
    public int bindListViewLayout() {
        return R.layout.text_two_layout;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, NoticeInfo2 data) {
        holder.setText(R.id.tv1, data.getTitle())
                .setText(R.id.tv2, data.getCreateTime());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        try {
            NoticeInfo2 info = (NoticeInfo2) adapter.getData().get(position);
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
