package com.xnhb.et.ui.fragment.home.page;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Response;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.convert.StringUtil;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.adapter.recyclerview.RecyclerViewCreator;
import com.oneway.ui.adapter.recyclerview.XRecyclerViewAdapter;
import com.oneway.ui.base.fragment.BaseFragment;
import com.oneway.ui.common.LinearItemDecoration;
import com.oneway.ui.dialog.TipsDialog;
import com.oneway.ui.dialog.base.OnCloseListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.list.ListLayout;
import com.oneway.ui.widget.status.StatusType;
import com.xnhb.et.R;
import com.xnhb.et.bean.C2COrderDetailsInfo;
import com.xnhb.et.bean.C2COrderInfo;
import com.xnhb.et.bean.base.LimitPage;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.ui.ac.user.LoginAndRegisterActivity;
import com.xnhb.et.widget.dialog.C2COrderDetailsDialog;

import org.simple.eventbus.Subscriber;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/20
 * 描述: 订单里的列表
 * 参考链接:
 */
public class C2COrderSubListFrament extends BaseFragment implements ListLayout.TaskListener, BaseQuickAdapter.OnItemClickListener, RecyclerViewCreator<C2COrderInfo>, ListLayout.PageStatusListener {
    public final static String BUNDLE_ARGUMENTS_PAGE_TYPE = "BUNDLE_ARGUMENTS";
    public final static int PAGE_TYPE_EXECUTION_ = 0;//委托记录
    public final static int PAGE_TYPE_complete_ = 1;//成交记录
    public final static int PAGE_TYPE_Cancel_ = 2;//已取消
    private int pageType = 0;
    @BindView(R.id.listLayout)
    ListLayout mListLayout;

    public static C2COrderSubListFrament newInstance(int pageType) {
        C2COrderSubListFrament frament = new C2COrderSubListFrament();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_ARGUMENTS_PAGE_TYPE, pageType);
        frament.setArguments(bundle);
        return frament;
    }


    @Override
    protected int setLayoutId() {
        return R.layout.common_list_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        pageType = getArguments().getInt(BUNDLE_ARGUMENTS_PAGE_TYPE, 0);
        mListLayout.setBackgroundColor(UiUtils.getColor(R.color.black));
        mListLayout.setTaskListener(this);
        mListLayout.setPageStatusListener(this);
        mListLayout.setOtherErrorView(R.layout.unlogin_layout);
        mListLayout.addItemDecoration(new LinearItemDecoration(10, R.color.black));
        mListLayout.setEmptyText("暂无订单...");
        mListLayout.setAdaper(new XRecyclerViewAdapter<C2COrderInfo>(this));
        mListLayout.addOnItemClickListener(this);
        mListLayout.showLoadingView();
        mListLayout.pullRefresh();
    }

    @Override
    public void task() {
        if (UserInfoHelper.getInstance().isLogin()) {
            getC2COrderInfo();
        } else {
            mListLayout.showOtherErrorView();
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        C2COrderInfo info = (C2COrderInfo) adapter.getData().get(position);
        //TODO 弹出详情dialog
        //当满足status==1，type==0条件时，可触发点击事件查看订单详细信息）
        if ("1".equals(info.getStatus()) && "0".equals(info.getType()))
            getOrderDetails(info.getId());
    }

    public void getOrderDetails(String id) {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("id", id);
        OkGoHelper.postOkGo(Api.C2C_ORDER_DETAILS_URL, this)
                .params(map)
                .execute(new DialogCallback<ResultInfo<C2COrderDetailsInfo>>(getActivity()) {
                    @Override
                    public void onSuccess(Response<ResultInfo<C2COrderDetailsInfo>> response) {
                        ResultInfo<C2COrderDetailsInfo> body = response.body();
                        C2COrderDetailsInfo info = body.getResult();
                        C2COrderDetailsDialog mDialog = new C2COrderDetailsDialog(getActivity(), info);
                        mDialog.showDialog();
                    }
                });
    }

    @Override
    public int bindListViewLayout() {
        return R.layout.item_c2c_order_list;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, C2COrderInfo data) {
        holder.setTextColor(R.id.tv_transaction_type, "0".equals(data.getTradeModus()) ? UiUtils.getColor(R.color.logout_color) : UiUtils.getColor(R.color.green_dark));
        holder.setText(R.id.tv_transaction_type, data.getTypeName())
                .setText(R.id.tv_transaction_status, data.getStatusStr())
                .setText(R.id.tv_time, data.getCreateTime())
                .setText(R.id.tv_order_id, data.getOrderNumber())
                .setText(R.id.tv_count, (int) data.getQuantity() + "")
                .setText(R.id.tv_money, data.getMoney() + "");
    }

    /**
     * 横向列表数据
     */
    public void getC2COrderInfo() {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("pageNum", mListLayout.getCurrentPageNumber() + "");
        map.put("numPerPage", "20");
        map.put("type", pageType + "");
        OkGoHelper.postOkGo(Api.C2C_ORDER_INFO_URL, this)
                .params(map)
                .execute(new DialogCallback<ResultInfo<LimitPage<C2COrderInfo>>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<LimitPage<C2COrderInfo>>> response) {
                        ResultInfo<LimitPage<C2COrderInfo>> body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            return;
                        }
                        LimitPage<C2COrderInfo> result = body.getResult();
                        if (EmptyUtils.isEmpty(result)) {
                            return;
                        }
                        mListLayout.setTotalPageNumber(result.getPages());
                        mListLayout.setData(result.getList());
                    }

                    @Override
                    public void onError(Response<ResultInfo<LimitPage<C2COrderInfo>>> response) {
                        super.onError(response);
                        mListLayout.showErrorView();
                    }

                    @Override
                    protected void handleLoginExpires() {
                        mListLayout.showOtherErrorView();
                    }
                });
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


    /**
     * 登陆后 刷新
     */
    @Subscriber(tag = EventBusTags.TAG_LOGIN_SUCDESS)
    public void remoteSwtichPage(int position) {
        //刷新当前界面
        mListLayout.showLoadingView();
        mListLayout.pullRefresh();
    }


}
