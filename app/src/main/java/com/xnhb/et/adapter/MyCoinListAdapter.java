package com.xnhb.et.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Response;
import com.oneway.tool.loader.ImageLoaderManager;
import com.oneway.tool.utils.convert.StringUtil;
import com.oneway.tool.utils.log.LogUtil;
import com.xnhb.et.R;
import com.xnhb.et.bean.CoinInfo;
import com.xnhb.et.bean.WrapCoinInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.common.GlideImageLoader;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者 oneway on 2018/9/28
 * 描述: 我的界面 币列表
 * 参考链接:
 */
public class MyCoinListAdapter extends BaseQuickAdapter<CoinInfo, BaseViewHolder> {
    private boolean isHideMoney = false;

    public MyCoinListAdapter() {
        super(R.layout.item_coin_list, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoinInfo item) {
        //TODO 设置对应参数   currencyName    using  freeze
        //TODO 這里测试暂时写死 图片名字为ET
        String imageUrl = OkGoHelper.getNetWorkUrl(Api.GET_IMAGE_URL + "ET");
//        String imageUrl = OkGoHelper.getNetWorkUrl(Api.GET_IMAGE_URL + item.getCurrencyName());
        ImageLoaderManager.getLoader().load(imageUrl, helper.getView(R.id.icon));
        helper.setText(R.id.tv_coin_name, item.getCurrencyName())
                .setText(R.id.item_coin_list_text1, StringUtil.htmlFromat(R.string.item_coin_list_text1, isHideMoney ? "***" : item.getUsing()))
//                .setText(R.id.item_coin_list_text2, StringUtil.htmlFromat(R.string.item_coin_list_text2, "0.0"))
                .setText(R.id.item_coin_list_text3, StringUtil.htmlFromat(R.string.item_coin_list_text3, isHideMoney ? "***" : item.getFreeze()));
    }

    public void hideMoney() {
        isHideMoney = !isHideMoney;
        notifyDataSetChanged();
    }
}