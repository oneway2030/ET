package com.xnhb.et.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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

        helper.setText(R.id.tv_volum, StringUtil.htmlFromat(R.string.home_item_volum, "99.0009", "et"))
//        .setText(R.id.tv_volum,);
        ;
    }
}
