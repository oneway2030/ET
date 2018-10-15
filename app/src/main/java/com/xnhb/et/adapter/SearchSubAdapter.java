package com.xnhb.et.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oneway.tool.loader.ImageLoaderManager;
import com.oneway.tool.utils.convert.StringUtil;
import com.xnhb.et.R;
import com.xnhb.et.bean.CoinInfo;
import com.xnhb.et.bean.SearchInfo;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.util.MoneyUtils;

/**
 * 作者 oneway on 2018/10/15
 * 描述:
 * 参考链接:
 */
public class SearchSubAdapter extends BaseQuickAdapter<SearchInfo, BaseViewHolder> {

    public SearchSubAdapter() {
        super(R.layout.item_search_sub_layout, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchInfo item) {
        helper.setText(R.id.content, item.getCurrencyName() + "/" + item.getTradeCurrencyName());
    }
}