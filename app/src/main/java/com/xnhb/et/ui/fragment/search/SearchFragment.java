package com.xnhb.et.ui.fragment.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.oneway.tool.event.BusManager;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.base.fragment.XFragment;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.xnhb.et.R;
import com.xnhb.et.event.EventBusTags;
import com.xnhb.et.ui.fragment.home.DetailsFragment;

import org.simple.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者 oneway on 2018/9/25
 * 描述:
 * 参考链接:
 */
public class SearchFragment extends XFragment {

    @BindView(R.id.title_bar)
    LinearLayout mTitleBar;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_clear)
    ImageView tvClear;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    public static SearchFragment newInstance() {
        SearchFragment frament = new SearchFragment();
        Bundle bundle = new Bundle();
        frament.setArguments(bundle);
        return frament;
    }

    @Override
    protected boolean isBarEnabled() {
        return true;
    }

    @Override
    protected void initView() {
        ImmersionBar.setTitleBar(mActivity, mTitleBar);
        tvClear.setOnClickListener(mPerfectClickListener);
        tvCancel.setOnClickListener(mPerfectClickListener);
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.tv_clear) {//清除
                etContent.setText("");
            } else if (id == R.id.tv_cancel) {//取消
//                _mActivity.onBackPressed();
                search();
            }
        }
    };


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_search;
    }

    public void search() {
        String searchResult = etContent.getText().toString().trim();
        if (EmptyUtils.isEmpty(searchResult)) {
            ToastManager.info("搜索结果不能为空");
            return;
        }
        BusManager.getBus().post(EventBusTags.TAG_SEARCH_RESULT, searchResult);
//        Bundle bundle = new Bundle();
//        bundle.putString(DetailsFragment.KEY_RESULT, searchResult);
//        setFragmentResult(RESULT_OK, bundle);
        _mActivity.onBackPressed();
    }


}
