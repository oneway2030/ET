package com.xnhb.et.ui.ac.detail.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Response;
import com.oneway.tool.event.BusManager;
import com.oneway.tool.utils.common.TimeUtils;
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
import com.xnhb.et.bean.OrderInfo;
import com.xnhb.et.bean.base.LimitPage;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.ui.ac.user.LoginAndRegisterActivity;
import com.xnhb.et.util.MoneyUtils;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/20
 * 描述: 订单里的列表
 * 参考链接:
 */
public class OrderSubListFrament2 extends BaseFragment implements ListLayout.TaskListener, BaseQuickAdapter.OnItemClickListener, RecyclerViewCreator<OrderInfo>, ListLayout.PageStatusListener {
    public final static String BUNDLE_ARGUMENTS_PAGE_TYPE = "BUNDLE_ARGUMENTS";
    public final static String BUNDLE_ARGUMENTS_DATA = "BUNDLE_ARGUMENTS_DATA";
    public final static int PAGE_TYPE_EXECUTION_ = 0;//委托记录
    public final static int PAGE_TYPE_complete_ = 1;//成交记录
    private int pageType = 0;
    private String currentName;
    private String tradeName;
    @BindView(R.id.listLayout)
    ListLayout mListLayout;
    private ArrayList<OrderInfo> recordList;
    private XRecyclerViewAdapter<OrderInfo> mAdapter;

    public static OrderSubListFrament2 newInstance(ArrayList<OrderInfo> recordList, int pageType, String currentName, String tradeName) {
        OrderSubListFrament2 frament = new OrderSubListFrament2();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(BUNDLE_ARGUMENTS_DATA, recordList);
        bundle.putInt(BUNDLE_ARGUMENTS_PAGE_TYPE, pageType);
        bundle.putString("currentName", currentName);
        bundle.putString("tradeName", tradeName);
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
        recordList = getArguments().getParcelableArrayList(BUNDLE_ARGUMENTS_DATA);
        pageType = getArguments().getInt(BUNDLE_ARGUMENTS_PAGE_TYPE, 0);
        currentName = getArguments().getString("currentName");
        tradeName = getArguments().getString("tradeName");
        mListLayout.setEnablePullRefresh(false);
        mListLayout.setBackgroundColor(UiUtils.getColor(R.color.black));
        mListLayout.setTaskListener(this);
        mListLayout.setPageStatusListener(this);
        mListLayout.setOtherErrorView(R.layout.unlogin_layout);
        mListLayout.addItemDecoration(new LinearItemDecoration(10, R.color.black));
        mListLayout.setEmptyText("暂无订单...");
        mAdapter = new XRecyclerViewAdapter<>(this);
        mListLayout.setAdaper(mAdapter);
        mListLayout.addOnItemClickListener(this);
        mListLayout.showLoadingView();
        mListLayout.pullRefresh();
    }

    @Override
    public void task() {
        mListLayout.setData(recordList);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (pageType == 0) {
            OrderInfo info = (OrderInfo) adapter.getData().get(position);
            showDialog(info.getId());
        }
    }

    @Override
    public int bindListViewLayout() {
        return R.layout.item_order_list;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, OrderInfo data) {
        String time = "";
        try {
            time = TimeUtils.millis2String(Long.valueOf(data.getCreateTime()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        holder.setTextColor(R.id.tv_transaction_type, "0".equals(data.getTradeType()) ? UiUtils.getColor(R.color.green_dark) : UiUtils.getColor(R.color.logout_color));
        holder.setText(R.id.tv_transaction_type, pageType == 0 ? data.getTradeTypeStr() : data.getTypeStr())
                .setText(R.id.tv_name, currentName + "/" + tradeName)
                .setText(R.id.tv_time, time)
                .setText(R.id.tv_price, StringUtil.htmlFromat(R.string.item_order_text, MoneyUtils.scaleMoney4((pageType == 0 ? data.getPrice() : data.getTradePrice()) + ""), tradeName))
                .setText(R.id.tv_count, StringUtil.htmlFromat(R.string.item_order_text, MoneyUtils.scaleMoney4((pageType == 0 ? data.getQuantity() : data.getTradeQuantity()) + ""), currentName))
                .setText(R.id.tv_transaction_count, StringUtil.htmlFromat(R.string.item_order_text, MoneyUtils.scaleMoney4(data.getTradeQuantity() + ""), pageType == 0 ? "" : currentName));
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
                        List<OrderInfo> data = mAdapter.getData();
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).getId().equals(id)) {
                                BusManager.getBus().post(EventBusTags.TAG_CANCEL_ENTRUST, "");
                                mAdapter.remove(i);
                                if (EmptyUtils.isEmpty(mAdapter.getData())) {
                                    mListLayout.showEmptyView();
                                }
                                return;
                            }
                        }
//                        mListLayout.showLoadingView();
//                        mListLayout.pullRefresh();
                    }

                    @Override
                    protected void handleLoginExpires() {
                        //TODO 什么也不干 不要删除
                    }
                });
    }
}
