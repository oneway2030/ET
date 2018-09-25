package com.xnhb.et.ui.fragment.home.page;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.adapter.recyclerview.RecyclerViewCreator;
import com.oneway.ui.adapter.recyclerview.XRecyclerViewAdapter;
import com.oneway.ui.base.fragment.XFragment;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.list.ListLayout;
import com.oneway.ui.widget.status.StatusType;
import com.xnhb.et.R;
import com.xnhb.et.bean.OrderInfo;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.ui.ac.user.LoginAndRegisterActivity;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/20
 * 描述: 详情的列表
 * 参考链接:
 */
public class DetailsSubListFrament extends XFragment implements ListLayout.TaskListener, BaseQuickAdapter.OnItemClickListener, RecyclerViewCreator<OrderInfo>, ListLayout.PageStatusListener {
    public final static String BUNDLE_ARGUMENTS = "PageType";
    public final static int PAGE_TYPE_ECNY = 0;//ECNY
    public final static int PAGE_TYPE_ETH = 1;//ETH
    public final static int PAGE_TYPE_BTC = 2;//BTC
    public final static int PAGE_TYPE_CUSTOM = 3;//自定义
    public int PageType = 0;
    @BindView(R.id.listLayout)
    ListLayout mListLayout;


    public static DetailsSubListFrament newInstance(int tag) {
        DetailsSubListFrament frament = new DetailsSubListFrament();
        Bundle bundle = new Bundle();
        bundle.putInt(DetailsSubListFrament.BUNDLE_ARGUMENTS, tag);
        frament.setArguments(bundle);
        return frament;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.common_list_layout;
    }

//    @Override
//    protected void initArguments(Bundle arguments) {
//
//    }

    @Override
    protected void initView() {
        super.initView();
        PageType = getArguments().getInt(BUNDLE_ARGUMENTS, -1);
        mListLayout.setBackgroundColor(UiUtils.getColor(R.color.black));
        mListLayout.setTaskListener(this);
        mListLayout.setEmptyText("暂无信息...");
        mListLayout.addOnItemClickListener(this);
        mListLayout.setPageStatusListener(this);
        mListLayout.setOtherErrorView(R.layout.unlogin_layout);
        mListLayout.setAdaper(new XRecyclerViewAdapter<OrderInfo>(this));
        mListLayout.showLoadingView();
        mListLayout.pullRefresh();
    }


    @Override
    public void onStart() {
        super.onStart();
        if (PageType == PAGE_TYPE_CUSTOM) {
            if (!UserInfoHelper.getInstance().isLogin()) {//如果没登陆则显示其他错误界面
                mListLayout.showOtherErrorView();
                return;
            }
        }
    }

    @Override
    public void task() {
        if (PageType == PAGE_TYPE_CUSTOM) {
            if (!UserInfoHelper.getInstance().isLogin()) {//如果没登陆则显示其他错误界面
                mListLayout.showOtherErrorView();
                return;
            }
        }
        ArrayList<OrderInfo> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new OrderInfo());
        }
        mListLayout.setData(list);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ToastManager.info("跳转详情");
    }

    @Override
    public int bindListViewLayout() {
        return R.layout.item_details_list;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, OrderInfo data) {

    }


    @Subscriber(tag = EventBusTags.TAG_LOGIN_SUCDESS)
    public void remoteSwtichPage(int position) {
        if (getUserVisibleHint()) {
            //刷新当前界面
            if (PageType == PAGE_TYPE_CUSTOM) {
                mListLayout.showLoadingView();
                mListLayout.pullRefresh();
            }
        }
    }

    @Override
    public void retry(int type) {
        if (type == StatusType.OTHER_ERROR.getType()) {
            //跳转到登陆界面
            LoginAndRegisterActivity.launch(getActivity());
        } else {
            mListLayout.showLoadingView();
            mListLayout.pullRefresh();
        }
    }
}
