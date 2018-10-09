package com.xnhb.et.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oneway.tool.utils.convert.StringUtil;
import com.oneway.tool.utils.ui.UiUtils;
import com.xnhb.et.R;
import com.xnhb.et.bean.CoinInfo;
import com.xnhb.et.bean.HomeHDataInfo;

/**
 * 作者 oneway on 2018/9/28
 * 描述: 我的界面 币列表
 * 参考链接:
 */
public class MyCoinListAdapter extends BaseQuickAdapter<CoinInfo, BaseViewHolder> {

    public MyCoinListAdapter() {
        super(R.layout.item_coin_list, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoinInfo item) {
        //TODO 设置对应参数   currencyName    using  freeze
        helper.setText(R.id.item_coin_list_text1, StringUtil.htmlFromat(R.string.item_coin_list_text1, "0.000000"))
                .setText(R.id.item_coin_list_text2, StringUtil.htmlFromat(R.string.item_coin_list_text2, "0.0"))
                .setText(R.id.item_coin_list_text3, StringUtil.htmlFromat(R.string.item_coin_list_text3, "0.000000"));
    }
}