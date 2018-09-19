package com.oneway.ui.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.R;
import com.oneway.ui.adapter.recyclerview.RecyclerViewCreator;
import com.oneway.ui.adapter.recyclerview.XRecyclerViewAdapter;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.dialog.base.BaseDailog;

import java.util.ArrayList;

/**
 * 作者 oneway on 2018/8/6
 * 描述:
 * 参考链接:
 */
public class TipLabelBottomSelectDialog<T> extends BaseDailog implements RecyclerViewCreator {

    TextView left;
    TextView right;
    TextView center;
    RecyclerView mRecyclerView;
    private XRecyclerViewAdapter mAdapter;
    private int selectPosition;
    ArrayList<T> infos;
    private View itemTitle;
    private boolean isShowTitle = false;
    private String lfteTitle = "取消";
    private String centerTitle;
    private String rightTitle = "确定";

    @Override
    public int setLayoutId() {
        return R.layout.dialog_tip_bottom_select;
    }

    public TipLabelBottomSelectDialog(@NonNull Context context, ArrayList<T> infos, int roomPosition) {
        super(context);
        this.selectPosition = roomPosition;
        this.infos = infos;
    }

    public TipLabelBottomSelectDialog(@NonNull Context context, ArrayList<T> infos, int roomPosition, String lfteTitle, String centerTitle, String rightTitle) {
        super(context);
        this.selectPosition = roomPosition;
        this.infos = infos;
        this.isShowTitle = true;
        this.lfteTitle = lfteTitle;
        this.centerTitle = centerTitle;
        this.rightTitle = rightTitle;
    }

    @Override
    protected void initData() {
        mWindow.setGravity(Gravity.BOTTOM);
        mWindow.setWindowAnimations(R.style.BottomIn);
        itemTitle = findViewById(R.id.item_title);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        center = findViewById(R.id.center);
        setTitle();
        setMaxHight();
        mRecyclerView = findViewById(R.id.recyclerView);
        left.setOnClickListener(mPerfectClickListener);
        right.setOnClickListener(mPerfectClickListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new XRecyclerViewAdapter(this);
        mAdapter.setNewData(infos);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                selectPosition = position;
                mAdapter.notifyDataSetChanged();
                if (itemListener != null) {
                    itemListener.onItemClick(position);
                }
            }
        });
    }

    private void setTitle() {
        itemTitle.setVisibility(isShowTitle ? View.VISIBLE : View.GONE);
        if (isShowTitle) {
            left.setText(lfteTitle);
            center.setText(centerTitle);
            right.setText(rightTitle);
        }
    }

    private void setMaxHight() {
        if (infos.size() > 6) {
            int h = UiUtils.getScreenHeight() * 3 / 5 - UiUtils.dp2px(100);
            LinearLayout rootView = findViewById(R.id.rootView);
            ViewGroup.LayoutParams lp = rootView.getLayoutParams();
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height = h;
            rootView.setLayoutParams(lp);
        }
    }

    public TipLabelBottomSelectDialog<T> setData(DataListener l) {
        this.dataListener = l;
        return this;
    }

    public TipLabelBottomSelectDialog<T> setData(LabelListener l) {
        this.labelListener = l;
        return this;
    }

    @Override
    public int bindListViewLayout() {
        return R.layout.item_text;
    }

    @Override
    public void bindData(int position, BaseViewHolder holder, Object data) {
        if (dataListener != null) {
            String itemName = dataListener.setData(position);
            holder.setText(R.id.tv, EmptyUtils.isEmpty(itemName) ? "" : itemName);
            holder.setTextColor(R.id.tv, position == selectPosition ? UiUtils.getColor(R.color.common_btn_blue_bg_light) : UiUtils.getColor(R.color.text));
        }
        if (labelListener != null) {
            int itemLabel = labelListener.setLabel(position);
            holder.setImageResource(R.id.label, EmptyUtils.isEmpty(itemLabel) ? 0 : itemLabel);
        }

    }


    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.left) {
                if (l != null) {
                    l.titleClick(false);
                }
            } else if (id == R.id.right) {
                if (l != null) {
                    l.titleClick(true);
                }
            }
        }
    };


    TitleClickListener l;
    ItemClickListener itemListener;
    DataListener dataListener;
    LabelListener labelListener;


    public void setTitleClick(TitleClickListener l) {
        this.l = l;
    }

    public TipLabelBottomSelectDialog<T> setItemClick(ItemClickListener l) {
        this.itemListener = l;
        return this;
    }


    public interface TitleClickListener {
        void titleClick(boolean isRight);
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public interface DataListener {
        String setData(int position);
    }

    public interface LabelListener {
        int setLabel(int position);
    }
}
