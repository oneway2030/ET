package com.xnhb.et.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.convert.StringUtil;
import com.oneway.tool.utils.ui.UiUtils;
import com.xnhb.et.R;
import com.xnhb.et.bean.HomeHDataInfo;

import java.util.List;

/**
 * 作者 oneway on 2018/9/12
 * 描述:
 * 参考链接:
 */
public class HomeHAdapter extends BaseQuickAdapter<HomeHDataInfo, BaseViewHolder> {

    public HomeHAdapter() {
        super(R.layout.item_home_horizontal_layout, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeHDataInfo item) {
        helper.setText(R.id.tv_title, item.getTradeCurrencyName() + "/" + item.getCurrencyName());
        //TODO 这里得判断 涨跌 然后设置颜色
        if (EmptyUtils.isEmpty(item.getCon())) {
            helper.setText(R.id.tv_percent, item.getRise() + "↑");
            helper.setTextColor(R.id.tv_percent, UiUtils.getColor(R.color.price_red))
                    .setTextColor(R.id.tv_currentPrice, UiUtils.getColor(R.color.price_red));
        } else {
            helper.setText(R.id.tv_percent, item.getRise() + "↓");
            helper.setTextColor(R.id.tv_percent, UiUtils.getColor(R.color.price_green))
                    .setTextColor(R.id.tv_currentPrice, UiUtils.getColor(R.color.price_green));
        }
        helper.setText(R.id.tv_currentPrice, item.getCurrentPrice() + "")
                .setText(R.id.tv_price1, item.getCurrencyName() + "/")
                .setText(R.id.tv_price2, "￥1");
        helper.setText(R.id.tv_volum, StringUtil.htmlFromat(R.string.home_item_volum, item.getTradeNums(), item.getTradeCurrencyName()));
    }
}
