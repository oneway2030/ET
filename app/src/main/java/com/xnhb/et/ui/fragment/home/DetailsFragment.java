package com.xnhb.et.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.model.Response;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.base.fragment.FragmentBaseAdapter;
import com.oneway.ui.base.fragment.XFragment;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.helper.PageStateHelper;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.status.OnRetryListener;
import com.xnhb.et.MainFragment;
import com.xnhb.et.R;
import com.xnhb.et.bean.QuotationInfo;
import com.xnhb.et.bean.QuotationListInfo;
import com.xnhb.et.bean.SearchInfo;
import com.xnhb.et.bean.WrapNoticeInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.CustomIllegalStateException;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.ui.fragment.home.page.DetailsSubListFrament;
import com.xnhb.et.ui.fragment.search.SearchFragment;

import org.simple.eventbus.Subscriber;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/10
 * 描述:
 * 参考链接:
 */
public class DetailsFragment extends XFragment implements TabLayout.OnTabSelectedListener, OnRetryListener {

    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.title_layout)
    LinearLayout mTitleLayout;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.root)
    LinearLayout rootView;
    //    String[] titles = {"ECNY", "ETH", "BTC", "自选"};
    public static final String KEY_RESULT = "key_result";
    private static final int REQ_SEARCH_FRAGMENT = 100;
    public static ArrayList<QuotationListInfo> customListInfo;
    private PageStateHelper mPageStateHelper;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mian_details;
    }

    public static DetailsFragment newInstance() {
        Bundle args = new Bundle();
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean isBarEnabled() {
        return true;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initStatePage();
        getNotice();
    }


    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.iv_search) {
                ((MainFragment) getParentFragment()).start(SearchFragment.newInstance(), REQ_SEARCH_FRAGMENT);
            }
        }
    };


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar
                .titleBar(mTitleLayout)
                .init();
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        setViewPosition(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        setViewPosition(tab);
    }

    private void setViewPosition(TabLayout.Tab tab) {
        String tabTitle = tab.getText().toString().trim();
        if ("自选".equals(tabTitle)) {//如果是自选,则判断是否登陆
            // 判断是否登陆
            if (!UserInfoHelper.getInstance().checkLogin()) {
                tablayout.setScrollPosition(0, 0f, true);
                return;
            }
        }
        vp.setCurrentItem(tab.getPosition());
    }

    /**
     * 搜索结果回调
     *
     * @param searchResultStr
     */
    @Subscriber(tag = EventBusTags.TAG_SEARCH_RESULT)
    public void searchResult(SearchInfo searchResultStr) {
        ToastManager.info("" + searchResultStr);
    }


    public void setData(ArrayList<QuotationInfo> areaList) {
        mPageStateHelper.showContentView();
        ArrayList<String> titles = getTitles(areaList);
        FragmentBaseAdapter mFragmentAdapter = new FragmentBaseAdapter(getChildFragmentManager(), getFragments(areaList), titles);
        vp.setOffscreenPageLimit(4);
        vp.setAdapter(mFragmentAdapter);
        tablayout.setupWithViewPager(vp);
        ivSearch.setOnClickListener(mPerfectClickListener);
    }

    public List<Fragment> getFragments(ArrayList<QuotationInfo> areaList) {
        List<Fragment> fragments = new ArrayList<>();
        for (QuotationInfo info : areaList) {
            DetailsSubListFrament frament = DetailsSubListFrament.newInstance(info);
            fragments.add(frament);
        }
        return fragments;
    }

    public ArrayList<String> getTitles(ArrayList<QuotationInfo> areaList) {
        ArrayList<String> titles = new ArrayList();
        for (QuotationInfo quotationInfo : areaList) {
            titles.add(quotationInfo.getName());
        }
        return titles;
    }

    /**
     * 获取tab 列表
     */
    public void getNotice() {
        mPageStateHelper.showLoadingView();
        Map map = new HashMap();
        OkGoHelper.postOkGo(Api.NOTICE_INFO, this)
                .params(map)
                .execute(new DialogCallback<ResultInfo<WrapNoticeInfo>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<WrapNoticeInfo>> response) {
                        ResultInfo<WrapNoticeInfo> body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            return;
                        }
                        WrapNoticeInfo result = body.getResult();
                        if (EmptyUtils.isEmpty(result)) {
                            return;
                        }
                        ArrayList<QuotationInfo> areaList = result.getAreaList();
                        areaList.add(new QuotationInfo("自选"));
                        if (UserInfoHelper.getInstance().isLogin()) { //已登录
                            reqCustomSelectNetData(areaList);
                        } else { //未登录
                            setData(areaList);
                        }
                    }

                    @Override
                    public void onError(Response<ResultInfo<WrapNoticeInfo>> response) {
                        handleError(response, null);
                    }
                });
    }


    /**
     * 获取自选信息
     *
     * @param areaList
     */
    private void reqCustomSelectNetData(ArrayList<QuotationInfo> areaList) {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        OkGoHelper.postOkGo(Api.CUSTOM_SELECT_LIST, getAc())
                .params(map)
                .execute(new DialogCallback<ResultInfo<ArrayList<QuotationListInfo>>>() {
                    @Override
                    public void onSuccess(Response<ResultInfo<ArrayList<QuotationListInfo>>> response) {
                        ResultInfo<ArrayList<QuotationListInfo>> body = response.body();
                        if (EmptyUtils.isEmpty(body)) {
                            return;
                        }
                        customListInfo = body.getResult();
                        setData(areaList);
                    }

                    @Override
                    public void onError(Response<ResultInfo<ArrayList<QuotationListInfo>>> response) {
                        handleError(response, areaList);
                    }
                });
    }

    private void handleError(com.lzy.okgo.model.Response response, ArrayList<QuotationInfo> areaList) {
        Throwable exception = response.getException();
        if (response != null) exception.printStackTrace();
        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            ToastManager.error("网络链接失败,请链接网络!");
        } else if (exception instanceof SocketTimeoutException) {
            ToastManager.error("网络链接超时");
        } else if (exception instanceof HttpException) {
            ToastManager.error("404!找不到服务器!");
        } else if (exception instanceof CustomIllegalStateException) {
            CustomIllegalStateException customException = (CustomIllegalStateException) exception;
            //0成功 -1错误  -2 登录失效 -3未认,证 -4冻结
            ToastManager.warning(customException.getMessage());
            int errorCode = customException.getErrorCode();
            if (errorCode == -2) {//token过期
                if (areaList != null) {
                    //清空登录状态
                    UserInfoHelper.getInstance().cleanUpUserInfo();
                    //初始化 子fragment
                    setData(areaList);
                    return;
                }
            }
        } else if (exception instanceof JsonSyntaxException) {
            ToastManager.error("解析错误!");
        } else {
            ToastManager.error("未知错误,请联系管理员!");
        }
        //TODO 显示错误界面
        mPageStateHelper.showErrorView();
    }

    /**
     * 初始化状态页面
     */
    public void initStatePage() {
        mPageStateHelper = new PageStateHelper(getActivity(), rootView, this);
    }

    @Override
    public void onRetry(int type) {
        getNotice();
    }
}
