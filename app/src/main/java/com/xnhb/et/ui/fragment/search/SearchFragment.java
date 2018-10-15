package com.xnhb.et.ui.fragment.search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.oneway.tool.event.BusManager;
import com.oneway.tool.utils.common.KeyboardUtils;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.adapter.recyclerview.RecyclerViewCreator;
import com.oneway.ui.adapter.recyclerview.XRecyclerViewAdapter;
import com.oneway.ui.base.fragment.XFragment;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.helper.PageStateHelper;
import com.oneway.ui.toast.ToastManager;
import com.xnhb.et.R;
import com.xnhb.et.adapter.SearchSubAdapter;
import com.xnhb.et.bean.SearchInfo;
import com.xnhb.et.bean.WrapSearchResult;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.ui.ac.search.ISearchView;
import com.xnhb.et.ui.ac.search.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/25
 * 描述:搜索结果
 * 参考链接:
 */
public class SearchFragment extends XFragment<SearchPresenter> implements ISearchView, RecyclerViewCreator<WrapSearchResult<SearchInfo>>, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.title_bar)
    LinearLayout mTitleBar;
    @BindView(R.id.et_content)
    EditText etSearch;
    @BindView(R.id.tv_clear)
    ImageView tvClear;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private XRecyclerViewAdapter mAdapter;
    private PageStateHelper mPageStateHelper;

    public static SearchFragment newInstance() {
        SearchFragment frament = new SearchFragment();
        Bundle bundle = new Bundle();
        frament.setArguments(bundle);
        return frament;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public SearchPresenter newP() {
        return new SearchPresenter();
    }

    @Override
    protected boolean isBarEnabled() {
        return true;
    }

    @Override
    protected void initView() {
        ImmersionBar.setTitleBar(mActivity, mTitleBar);
        watchSearch();
        KeyboardUtils.showSoftInput(etSearch);
        tvClear.setOnClickListener(mPerfectClickListener);
        tvCancel.setOnClickListener(mPerfectClickListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new XRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mPageStateHelper = new PageStateHelper(getActivity(), mRecyclerView);
        mPageStateHelper.showContentView();
    }


    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.tv_clear) {//清除
                etSearch.setText("");
            } else if (id == R.id.tv_cancel) {//取消
                _mActivity.onBackPressed();
            }
        }
    };


    public void search(String keyWord) {
        String searchResult = etSearch.getText().toString().trim();
        if (EmptyUtils.isEmpty(searchResult)) {
            ToastManager.info("搜索结果不能为空");
            return;
        }
        getP().searchKey(keyWord);
    }


    private void watchSearch() {
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    KeyboardUtils.hideSoftInput(etSearch);
                    // 搜索，进行自己要的操作.1..//这里是我要做的操作！
                    search(etSearch.getText().toString().trim());
                    return true;
                }
                return false;
            }


        });
    }


    @Override
    public void searchResult(ArrayList<WrapSearchResult<SearchInfo>> datas) {
        if (EmptyUtils.isEmpty(datas)) {
            mAdapter.setNewData(null);
            mPageStateHelper.showEmptyView("没有结果", 0);
        } else {
            mPageStateHelper.showContentView();
            mAdapter.setNewData(datas);
        }

    }

    @Override
    public int bindListViewLayout() {
        return R.layout.item_search_layout;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, WrapSearchResult<SearchInfo> data) {
        holder.setText(R.id.tv_titile, data.getName());
        List<SearchInfo> trade = data.getTrade();
        RecyclerView mRecyclerView = holder.getView(R.id.sub_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SearchSubAdapter adapter = new SearchSubAdapter();
        adapter.setOnItemClickListener(SearchFragment.this);
        mRecyclerView.setAdapter(adapter);
        adapter.setNewData(trade);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        SearchInfo info = (SearchInfo) adapter.getData().get(position);
        //TODO 点击条目 应该 跳转都详情页面
        BusManager.getBus().post(EventBusTags.TAG_SEARCH_RESULT, info);
//        Bundle bundle = new Bundle();
//        bundle.putString(DetailsFragment.KEY_RESULT, searchResult);
//        setFragmentResult(RESULT_OK, bundle);
        _mActivity.onBackPressed();
    }
}
