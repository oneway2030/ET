package com.xnhb.et.ui.fragment.home;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.adapter.recyclerview.RecyclerViewCreator;
import com.oneway.ui.adapter.recyclerview.XRecyclerViewAdapter;
import com.oneway.ui.base.fragment.BaseLazyFragment;
import com.oneway.ui.common.UniversalItemDecoration;
import com.oneway.ui.widget.list.ListLayout;
import com.xnhb.et.R;
import com.xnhb.et.bean.TransactionInfo;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author oneway
 * @describe
 * @since 2018/9/11 0011
 */


public class HomeSubFragment extends BaseLazyFragment implements ListLayout.TaskListener, BaseQuickAdapter.OnItemClickListener, RecyclerViewCreator<TransactionInfo> {
    @BindView(R.id.ListLayout)
    ListLayout mListLayout;

    @Override
    protected boolean isLazyLoad() {
        return false;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home_sub;
    }

    @Override
    protected void initLazyData() {
        mListLayout.setTaskListener(this);
        mListLayout.addItemDecoration(new MyUniversalItemDecoration());
        mListLayout.setEmptyText("暂无信息...");
        mListLayout.setAdaper(new XRecyclerViewAdapter<TransactionInfo>(this));
        mListLayout.addOnItemClickListener(this);
        mListLayout.showLoadingView();
        mListLayout.pullRefresh();
    }

    @Override
    public void task() {
        ArrayList<TransactionInfo> infos = new ArrayList<>();
        infos.add(new TransactionInfo("市场", "最新价", "24h涨跌幅", "24h涨跌幅", true));
        infos.add(new TransactionInfo("BTC", "300", "1.014", "+0.32", true));
        infos.add(new TransactionInfo("BTQ", "1.01", "0.61", "-0.33", false));
        infos.add(new TransactionInfo("NEO", "0.31", "3.20", "+0.34", true));
        infos.add(new TransactionInfo("BOS", "3.01", "10.2", "-0.35", false));
        infos.add(new TransactionInfo("QXS", "11.11", "3.06", "+0.36", true));
        infos.add(new TransactionInfo("BAS", "414.41", "0.9842", "-0.37", false));
        infos.add(new TransactionInfo("BXX", "14.141", "0.0006", "+0.38", true));
        infos.add(new TransactionInfo("CBX", "0.0099", "3.0015", "-0.39", false));
        mListLayout.setData(infos);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //条目点击

    }

    @Override
    public int bindListViewLayout() {
        return R.layout.item_home_transaction;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, TransactionInfo data) {
        if (position == 0) {
            holder.getView(R.id.center).setVisibility(View.VISIBLE);
            holder.getView(R.id.center_layout).setVisibility(View.GONE);

            holder.setVisible(R.id.center, true);
            holder.setText(R.id.left, data.getName())
                    .setText(R.id.center, data.getUnitPrice())
                    .setText(R.id.right, data.getIncrease());
            holder.setTextColor(R.id.left, UiUtils.getColor(R.color.white))
                    .setTextColor(R.id.center, UiUtils.getColor(R.color.white))
                    .setTextColor(R.id.right, UiUtils.getColor(R.color.white));
        } else {
            holder.getView(R.id.center).setVisibility(View.GONE);
            holder.getView(R.id.center_layout).setVisibility(View.VISIBLE);
            holder.setText(R.id.left, position + " " + data.getName())
                    .setText(R.id.right, data.getIncrease() + "%")
                    .setText(R.id.tv_unit_price, data.getUnitPrice())
                    .setText(R.id.tv_rmb_unit_price, data.getRmbUnitPrice());
            TextView tv = holder.getView(R.id.center);
            holder.setTextColor(R.id.left, UiUtils.getColor(R.color.white));
            if (data.isAdd()) {
//                tv.setText(StringUtil.htmlFromat(R.string.mian_pirce_red, data.getUnitPrice(), data.getRmbUnitPrice()));
                holder.setTextColor(R.id.right, UiUtils.getColor(R.color.price_red))
                        .setTextColor(R.id.tv_unit_price, UiUtils.getColor(R.color.price_red));
            } else {
//                tv.setText(StringUtil.htmlFromat(R.string.mian_pirce_green, data.getUnitPrice(), data.getRmbUnitPrice()));
                holder.setTextColor(R.id.right, UiUtils.getColor(R.color.price_green))
                        .setTextColor(R.id.tv_unit_price, UiUtils.getColor(R.color.price_green));
            }

        }
    }

    /**
     * 分割线
     */
    public class MyUniversalItemDecoration extends UniversalItemDecoration {
        @Override
        public Decoration getItemOffsets(int position, int childCount) {
            ColorDecoration decoration = new ColorDecoration();
            decoration.decorationColor = UiUtils.getColor(R.color.divider_home);
            decoration.bottom = 1;
            return decoration;
        }
    }
}
