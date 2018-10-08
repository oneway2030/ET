package com.xnhb.et.ui.fragment.home.page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Response;
import com.oneway.tool.event.BusManager;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.adapter.recyclerview.RecyclerViewCreator;
import com.oneway.ui.adapter.recyclerview.XRecyclerViewAdapter;
import com.oneway.ui.base.fragment.XFragment;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.btn.StateButton;
import com.oneway.ui.widget.list.ListLayout;
import com.oneway.ui.widget.status.StatusType;
import com.xnhb.et.R;
import com.xnhb.et.bean.QuotationInfo;
import com.xnhb.et.bean.QuotationListInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.ui.ac.user.LoginAndRegisterActivity;
import com.xnhb.et.ui.fragment.home.DetailsFragment;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/20
 * 描述: 详情的列表
 * 参考链接:
 */
public class DetailsSubListFrament extends XFragment implements ListLayout.TaskListener, BaseQuickAdapter.OnItemClickListener, RecyclerViewCreator<QuotationListInfo>, ListLayout.PageStatusListener, BaseQuickAdapter.OnItemChildClickListener {
    public final static String BUNDLE_ARGUMENTS = "PageType";
    public final static String CUSTOM_STR = "自选";
    public int TAG_CUSTOM_SELECT_TYPE = 0;//ECNY

    public final static int PAGE_TYPE_ECNY = 0;//ECNY
    public final static int PAGE_TYPE_ETH = 1;//ETH
    public final static int PAGE_TYPE_BTC = 2;//BTC
    public final static int PAGE_TYPE_CUSTOM = 3;//自定义
    //    public int PageType = 0;
    @BindView(R.id.listLayout)
    ListLayout mListLayout;
    private QuotationInfo mQuotationInfo;
    private XRecyclerViewAdapter<QuotationListInfo> mAdapter;


    public static DetailsSubListFrament newInstance(QuotationInfo tag) {
        DetailsSubListFrament frament = new DetailsSubListFrament();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_ARGUMENTS, tag);
        frament.setArguments(bundle);
        return frament;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.common_list_layout;
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mQuotationInfo = (QuotationInfo) getArguments().getSerializable(BUNDLE_ARGUMENTS);
        mListLayout.setBackgroundColor(UiUtils.getColor(R.color.black));
        mListLayout.setTaskListener(this);
        mListLayout.setEmptyText("暂无信息...");
        mListLayout.addOnItemClickListener(this);
        mListLayout.addOnItemChildClickListener(this);
        mListLayout.setPageStatusListener(this);
        mListLayout.setOtherErrorView(R.layout.unlogin_layout);
        mAdapter = new XRecyclerViewAdapter<>(this);
        mListLayout.setAdaper(mAdapter);
        mListLayout.showLoadingView();
        if (mQuotationInfo != null && CUSTOM_STR.equals(mQuotationInfo.getName()) && DetailsFragment.customListInfo != null) {
            mListLayout.setData(DetailsFragment.customListInfo);
        } else {
            mListLayout.pullRefresh();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mQuotationInfo != null && CUSTOM_STR.equals(mQuotationInfo.getName())) {
            if (!UserInfoHelper.getInstance().isLogin()) {//如果没登陆则显示其他错误界面  去登陆界面
                mListLayout.showOtherErrorView();
                return;
            }
        }
    }

    @Override
    public void task() {
        if (CUSTOM_STR.equals(mQuotationInfo.getName())) {
            if (!UserInfoHelper.getInstance().isLogin()) {//如果没登陆则显示其他错误界面
                mListLayout.showOtherErrorView();
                return;
            }
        }
        if (CUSTOM_STR.equals(mQuotationInfo.getName())) {//自选
            reqCustomSelectNetData();
        } else { //非自选
            reqNetData();
        }
    }


    @Override
    public int bindListViewLayout() {
        return R.layout.item_details_list;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, QuotationListInfo data) {
        if (CUSTOM_STR.equals(mQuotationInfo.getName())) {
            holder.getView(R.id.iv_custom_select).setVisibility(View.VISIBLE);
            holder.setImageResource(R.id.iv_custom_select, R.mipmap.stars_selected);
            holder.addOnClickListener(R.id.iv_custom_select);
        } else {
            holder.getView(R.id.iv_custom_select).setVisibility(DetailsFragment.customListInfo == null ? View.GONE : View.VISIBLE);
            if (DetailsFragment.customListInfo != null) {
                holder.setImageResource(R.id.iv_custom_select, data.isCollection() ? R.mipmap.stars_selected : R.mipmap.stars_unselect);
                holder.addOnClickListener(R.id.iv_custom_select);
            }
        }
        holder.setText(R.id.tv_coin_name1, data.getTradeCurrencyName())
                .setText(R.id.tv_coin_name2, data.getCurrencyName())
                .setText(R.id.tv_total, "24h量  " + data.getTradeNums())
                .setText(R.id.tv_price1, data.getCurrentPrice())
                .setText(R.id.tv_price2, "≈" + data.getShowEncyMoeny() + "  ENCY ");
        StateButton btnRiseAndFallRange = holder.getView(R.id.btn_rise_and_fall_range);
        btnRiseAndFallRange.setText(data.getRise() + "%");
        if (EmptyUtils.isEmpty(data.getCon())) {
            //升
            btnRiseAndFallRange.setNormalBackgroundColor(UiUtils.getColor(R.color.green_dark));
            btnRiseAndFallRange.setPressedBackgroundColor(UiUtils.getColor(R.color.green_dark));
        } else {
            //降
            btnRiseAndFallRange.setNormalBackgroundColor(UiUtils.getColor(R.color.red_btn));
            btnRiseAndFallRange.setPressedBackgroundColor(UiUtils.getColor(R.color.red_btn));
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


    public void reqNetData() {
        if (EmptyUtils.isEmpty(mQuotationInfo.getId())) {
            mListLayout.showErrorView();
            return;
        }
        Map map = new HashMap();
        map.put("areaId", mQuotationInfo.getId());
        OkGoHelper.getOkGo(Api.QUOTATION_LIST_DATA, getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<ArrayList<QuotationListInfo>>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<ArrayList<QuotationListInfo>>> response) {
                        ResultInfo<ArrayList<QuotationListInfo>> body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            return;
                        }
                        ArrayList<QuotationListInfo> result = body.getResult();
                        formatData(result);
                        mListLayout.setData(result);
                    }
                });
    }

    private void reqCustomSelectNetData() {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        OkGoHelper.getOkGo(Api.CUSTOM_SELECT_LIST, getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<ArrayList<QuotationListInfo>>>(getActivity()) {
                    @Override
                    public void onSuccess(Response<ResultInfo<ArrayList<QuotationListInfo>>> response) {
                        ResultInfo<ArrayList<QuotationListInfo>> body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            return;
                        }
                        DetailsFragment.customListInfo = body.getResult();
                        mListLayout.setData(DetailsFragment.customListInfo);
                        BusManager.getBus().post(EventBusTags.TAG_CUSTOM_SELECT, CUSTOM_STR);
                    }
                });
    }

    /**
     * 登陆后 刷新
     */
    @Subscriber(tag = EventBusTags.TAG_LOGIN_SUCDESS)
    public void remoteSwtichPage(int position) {
        if (getUserVisibleHint()) {
            //刷新当前界面
            if (CUSTOM_STR.equals(mQuotationInfo.getName())) {
                mListLayout.showLoadingView();
                mListLayout.pullRefresh();
            } else {
                mListLayout.notifyDataSetChanged();
            }
        }
    }

    /**
     * 自选刷新
     */
    @Subscriber(tag = EventBusTags.TAG_CUSTOM_SELECT)
    public void remoteSwtichPage(String updateName) {
        if (!updateName.equals(mQuotationInfo.getName())) {//刷新 非updateName 页面
            if (CUSTOM_STR.equals(mQuotationInfo.getName())) { //如果需要更新的是 自选界面
                mListLayout.setData(DetailsFragment.customListInfo);
            } else {//刷新非自选界面
                List<QuotationListInfo> data = mAdapter.getData();
                formatData(data);
                mListLayout.notifyDataSetChanged();
            }
        }
    }

    /**
     * 给其他界面数据 设置 自选
     *
     * @param data
     */
    public void formatData(List<QuotationListInfo> data) {
        if (EmptyUtils.isEmpty(DetailsFragment.customListInfo)) {
            for (QuotationListInfo datum : data) {
                datum.setCollection(false);
            }
            return;
        }
        for (QuotationListInfo info : data) {
            for (QuotationListInfo customInfo : DetailsFragment.customListInfo) {
                if (info.getTradeId().equals(customInfo.getTradeId())) {
                    info.setCollection(true);
                    break;
                } else {
                    info.setCollection(false);
                }
//                if (info.getTradeId().equals(customInfo.getTradeId())) {
//                    info.setCollection(true);
//                    break;
//                }
            }
        }

    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //TODO 跳转详情
        ToastManager.info("跳转详情");
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
///front/collect/collect收藏接口（参数tradeId），/front/collect/cancel取消收藏接口（参数tradeId）{参数就是列表每条数据里面对应的tradeId}
        //进来 如果登录了 就请求 收藏列表 如果没登录则不请求
        if (UserInfoHelper.getInstance().isLogin()) {//
            Object o = adapter.getData().get(position);
            if (o instanceof QuotationListInfo) {
                QuotationListInfo info = (QuotationListInfo) o;
                reqCollection(info);
            }
        }
    }


    public void reqCollection(QuotationListInfo updateInfo) {
        String url;
        if (CUSTOM_STR.equals(mQuotationInfo.getName())) {
            url = Api.CANCEL_ADD_CUSTOM_SELECT;
        } else {
            url = updateInfo.isCollection() ? Api.CANCEL_ADD_CUSTOM_SELECT : Api.ADD_CUSTOM_SELECT;
        }
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("tradeId", updateInfo.getTradeId());
        OkGoHelper.getOkGo(url, getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<Void>>(getActivity()) {
                    @Override
                    public void onSuccess(Response<ResultInfo<Void>> response) {
                        if (CUSTOM_STR.equals(mQuotationInfo.getName())) {//如果是自选界面
                            //如果是自选界面则 删除该条自选
                            if (EmptyUtils.isNotEmpty(DetailsFragment.customListInfo)) {
                                DetailsFragment.customListInfo.remove(updateInfo);
                                mListLayout.setData(DetailsFragment.customListInfo);
                            }
                        } else {
                            //如果不是自选界面则 改變當前點擊條目信息 並刷新
                            updateCuotomSelectInfo(updateInfo);
                            updateInfo.setCollection(!updateInfo.isCollection());
                            mListLayout.notifyDataSetChanged();
                        }
                        //通知其他界面刷新
                        BusManager.getBus().post(EventBusTags.TAG_CUSTOM_SELECT, mQuotationInfo.getName());
//                        if (EmptyUtils.isEmpty(DetailsFragment.customListInfo) && !data.isCollection()) {
//                            DetailsFragment.customListInfo = new ArrayList<>();
//                            DetailsFragment.customListInfo.add(data);
//                            BusManager.getBus().post(EventBusTags.TAG_CUSTOM_SELECT, CUSTOM_STR);
//                            return;
//                        }

                    }
                });
    }

    public void updateCuotomSelectInfo(QuotationListInfo updateInfo) {
        if (updateInfo.isCollection()) {//取消自选
            if (EmptyUtils.isNotEmpty(DetailsFragment.customListInfo)) {
                for (QuotationListInfo info : DetailsFragment.customListInfo) {
                    if (info.getTradeId().equals(updateInfo.getTradeId())) {
                        DetailsFragment.customListInfo.remove(info);
                        return;
                    }
                }
            }
        } else {
            if (EmptyUtils.isEmpty(DetailsFragment.customListInfo)) {
                DetailsFragment.customListInfo = new ArrayList<>();
            }
            DetailsFragment.customListInfo.add(updateInfo);
        }
    }


}
