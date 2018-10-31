package com.xnhb.et.ui.ac.bill;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Response;
import com.oneway.tool.loader.ImageLoaderManager;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.adapter.recyclerview.RecyclerViewCreator;
import com.oneway.ui.adapter.recyclerview.XRecyclerViewAdapter;
import com.oneway.ui.base.fragment.BaseFragment;
import com.oneway.ui.common.LinearItemDecoration;
import com.oneway.ui.widget.list.ListLayout;
import com.xnhb.et.R;
import com.xnhb.et.bean.HistoricalInfo;
import com.xnhb.et.bean.base.LimitPage;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.util.MoneyUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/20
 * 描述: 充提记录
 * 参考链接:
 */
public class HistoricalFrament extends BaseFragment implements ListLayout.TaskListener, BaseQuickAdapter.OnItemClickListener, RecyclerViewCreator<HistoricalInfo> {
    public final static int PageType = 0;
    private static String ARG_PAGE_TYPE = "arg_page_type";
    private static String ARG_TAG_PRICE = "unit_price";
    @BindView(R.id.listLayout)
    ListLayout mListLayout;
    private Object type;

    public static HistoricalFrament newInstance(int type) {
        HistoricalFrament frament = new HistoricalFrament();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE_TYPE, type);
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
        mListLayout.setBackgroundColor(UiUtils.getColor(R.color.black));
        mListLayout.setTaskListener(this);
        mListLayout.addItemDecoration(new LinearItemDecoration(10, R.color.black));
        mListLayout.setEmptyText("暂无记录...");
        mListLayout.setAdaper(new XRecyclerViewAdapter<HistoricalInfo>(this));
        mListLayout.addOnItemClickListener(this);
        mListLayout.showLoadingView();
        mListLayout.pullRefresh();
    }

    @Override
    public void task() {
        reqListInfo();
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String type = getType();
        if (!"7".equals(type) && !"8".equals(type) && !"10".equals(type)) {
            HistoricalInfo info = (HistoricalInfo) adapter.getData().get(position);
            HistoricalDetailsActivity.launch(getAc(), info, getTypeStr());
        }

    }

    @Override
    public int bindListViewLayout() {
        return R.layout.item_historical_list;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, HistoricalInfo data) {
        String type = getType();
        if ("7".equals(type) || "8".equals(type) || "10".equals(type)) {
            holder.setText(R.id.tv_coin_count, MoneyUtils.getPrettyNumber(data.getMoney()) + "");
            holder.getView(R.id.iv_right).setVisibility(View.GONE);
        } else {
            holder.getView(R.id.iv_right).setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_coin_count, data.getQuantity() + "");
        }


        holder.setText(R.id.tv_coin_name, data.getCurrencyName() + "")
//                .setText(R.id.tv_coin_count, data.getQuantity() + "")
                .setText(R.id.tv_status, EmptyUtils.isEmpty(data.getStatusStr()) ? "" : data.getStatusStr() + "")
                .setText(R.id.tv_time, data.getCreateTime() + "");
        ImageLoaderManager.getLoader().load(OkGoHelper.getImageUrl(data.getCurrencyName()), holder.getView(R.id.iv_icon));
    }

    /**
     * 获取列表信息
     */
    public void reqListInfo() {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        //type（记录类型，）
        map.put("type", getType());
        map.put("pageNum", mListLayout.getCurrentPageNumber() + "");//当前页
        map.put("numPerPage", "20");//显示条数
        OkGoHelper.postOkGo(Api.RECHARGE_AND_WITHDRAWALS_HISTORICAL_URL, this)
                .params(map)
                .execute(new DialogCallback<ResultInfo<LimitPage<HistoricalInfo>>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<LimitPage<HistoricalInfo>>> response) {
                        ResultInfo<LimitPage<HistoricalInfo>> body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            return;
                        }
                        LimitPage<HistoricalInfo> result = body.getResult();
                        if (EmptyUtils.isEmpty(result)) {
                            return;
                        }
                        mListLayout.setTotalPageNumber(result.getPages());
                        mListLayout.setData(result.getList());
                    }

                    @Override
                    public void onError(Response<ResultInfo<LimitPage<HistoricalInfo>>> response) {
                        super.onError(response);

                    }
                });
    }

    /**
     * 获取页面类型
     *
     * @return 1 充值 2提现 7买入手续费 8卖出手续费 10系统赠送
     */
    public String getType() {
        int pageType = getArguments().getInt(ARG_PAGE_TYPE);
        if (pageType == 0) {
            return "1";
        } else if (pageType == 1) {
            return "2";
        } else if (pageType == 2) {
            return "7";
        } else if (pageType == 3) {
            return "8";
        } else {
            return "10";
        }
    }

    public String getTypeStr() {
        int pageType = getArguments().getInt(ARG_PAGE_TYPE);
        if (pageType == 0) {
            return "充值";
        } else if (pageType == 1) {
            return "提现";
        } else if (pageType == 2) {
            return "买入手续费";
        } else if (pageType == 3) {
            return "卖出手续费";
        } else {
            return "系统赠送";
        }
    }
}
