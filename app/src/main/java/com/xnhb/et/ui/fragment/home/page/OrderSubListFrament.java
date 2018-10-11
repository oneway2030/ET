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
import com.xnhb.et.bean.HomeHDataInfo;
import com.xnhb.et.bean.OrderInfo;
import com.xnhb.et.bean.base.LimitPage;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.ui.ac.user.LoginAndRegisterActivity;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/20
 * 描述: 订单里的列表
 * 参考链接:
 */
public class OrderSubListFrament extends BaseFragment implements ListLayout.TaskListener, BaseQuickAdapter.OnItemClickListener, RecyclerViewCreator<OrderInfo>, ListLayout.PageStatusListener {
    public final static String BUNDLE_ARGUMENTS_PAGE_TYPE = "BUNDLE_ARGUMENTS";
    public final static int PAGE_TYPE_EXECUTION_ = 0;//委托记录
    public final static int PAGE_TYPE_complete_ =1;//成交记录
    public final static int PAGE_TYPE_Cancel_ = 2;//已取消
    private int pageType = 0;
    @BindView(R.id.listLayout)
    ListLayout mListLayout;

    public static OrderSubListFrament newInstance(int pageType) {
        OrderSubListFrament frament = new OrderSubListFrament();
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
        mListLayout.setAdaper(new XRecyclerViewAdapter<OrderInfo>(this));
        mListLayout.addOnItemClickListener(this);
        mListLayout.showLoadingView();
        mListLayout.pullRefresh();
    }

    @Override
    public void task() {
        if (UserInfoHelper.getInstance().isLogin()) {
            getOrderInfo();
        } else {
            mListLayout.showOtherErrorView();
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        OrderInfo info = (OrderInfo) adapter.getData().get(position);
        showDialog(info.getId());
    }

    @Override
    public int bindListViewLayout() {
        return R.layout.item_order_list;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, OrderInfo data) {
        holder.setTextColor(R.id.tv_transaction_type, "0".equals(data.getTradeType()) ? UiUtils.getColor(R.color.logout_color) : UiUtils.getColor(R.color.green_dark));
        holder.setText(R.id.tv_transaction_type, data.getTradeTypeStr())
                .setText(R.id.tv_name, data.getTradeCurrencyName() + "/" + data.getCurrencyName())
                .setText(R.id.tv_time, data.getCreateTime())
                .setText(R.id.tv_price, StringUtil.htmlFromat(R.string.item_order_text1, data.getPrice(), data.getCurrencyName()))
                .setText(R.id.tv_count, StringUtil.htmlFromat(R.string.item_order_text2, data.getQuantity(), data.getTradeCurrencyName()))
                .setText(R.id.tv_transaction_count, data.getTradeQuantity() + "");
    }

    /**
     * 横向列表数据
     */
    public void getOrderInfo() {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("pageNum", mListLayout.getCurrentPageNumber() + "");
        map.put("numPerPage", "20");
        map.put("type", pageType + "");
        OkGoHelper.postOkGo(Api.ORDER_INFO_URL, this)
                .params(map)
                .execute(new DialogCallback<ResultInfo<LimitPage<OrderInfo>>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<LimitPage<OrderInfo>>> response) {
                        ResultInfo<LimitPage<OrderInfo>> body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            return;
                        }
                        LimitPage<OrderInfo> result = body.getResult();
                        if (EmptyUtils.isEmpty(result)) {
                            return;
                        }
                        mListLayout.setTotalPageNumber(result.getPages());
                        mListLayout.setData(result.getList());
                    }

                    @Override
                    public void onError(Response<ResultInfo<LimitPage<OrderInfo>>> response) {
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


    public void showDialog(String id) {
        new TipsDialog(getActivity(), "是否取消交易?", new OnCloseListener() {
            @Override
            public void onDailogClose(Dialog dialog, boolean confirm) {
                if (confirm) {
                    cancelOrder(id);
                }
            }
        }).showDialog();
    }

    public void cancelOrder(String id) {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("id", id);
        OkGoHelper.postOkGo(Api.ORDER_CANCEL_URL, this)
                .params(map)
                .execute(new DialogCallback<ResultInfo<Void>>(getActivity()) {
                    @Override
                    public void onSuccess(Response<ResultInfo<Void>> response) {
                        ToastManager.info("取消挂单成功");
                        mListLayout.showLoadingView();
                        mListLayout.pullRefresh();
                    }

                    @Override
                    protected void handleLoginExpires() {
                        //TODO 什么也不干 不要删除
                    }
                });
    }
}
