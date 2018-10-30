package com.xnhb.et.ui.fragment.home.page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.oneway.tool.utils.calculate.BigDecimalUtils;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.tool.utils.log.LogUtil;
import com.oneway.ui.base.fragment.XFragment;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.btn.StateButton;
import com.xnhb.et.R;
import com.xnhb.et.bean.C2CCoinInfo;
import com.xnhb.et.bean.C2CListInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.net.ApiService;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.util.MoneyUtils;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/25
 * 描述:
 * 参考链接:
 */
public class C2CSubFragment extends XFragment implements TextWatcher {
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.tv_title3)
    TextView tvTitle3;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.tv_title4)
    TextView tvTitle4;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.et3)
    TextView et3;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.et1)
    TextView et1;
    @BindView(R.id.tv_right1)
    TextView tvRight1;
    @BindView(R.id.tv_right2)
    TextView tvRight2;
    @BindView(R.id.tv_right3)
    TextView tvRight3;
    @BindView(R.id.submit)
    StateButton submit;
    private static String ARG_TAG = "title";
    private String title;
    private C2CListInfo mCoinInfo;
    //TODO 交易方式暂时写死
    private String tradeModus = "1";

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_c2c_sub;
    }

    public static C2CSubFragment newInstance(String title) {
        C2CSubFragment frament = new C2CSubFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TAG, title);
        frament.setArguments(bundle);
        return frament;
    }

    private boolean isInit = false;

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        isInit = true;
        title = getArguments().getString(ARG_TAG);
        et1.setText("0");
        et3.setText("0.0000");
        if ("我要买".equals(title)) {
            tvTitle1.setText("买入价");
            tvTitle2.setText("买入量");
            submit.setText(getString(R.string.c2c_btn, "买入", ""));
        } else if ("我要卖".equals(title)) {
            tvTitle1.setText("卖出价");
            tvTitle2.setText("卖出量");
            submit.setText(getString(R.string.c2c_btn, "卖出", ""));
        }
        et2.addTextChangedListener(this);
        submit.setOnClickListener(mPerfectClickListener);
    }

    @Override
    protected void initView() {
        super.initView();

    }


    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.submit) {
                //提交
                submit();
            }
        }

    };


    /**
     * TODO 买入个数限制 买入单价限制 总价限制 需要探讨
     */
    public double calculationCoin(double count) {
        try {
            return BigDecimalUtils.multiply(count, getUnitPrice(), 4);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public Double getUnitPrice() {
        String unitPrice = et1.getText().toString().trim();
        if ("0E-8".equals(unitPrice)) {
            return 0.0;
        }
        try {
            return Double.valueOf(unitPrice);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //TODO 监听et输入数量,计算总价
        String count = s.toString().trim();
        if (EmptyUtils.isEmpty(count)) {
            et3.setText("0.0000");
            return;
        }
        try {
            double v = calculationCoin(Double.valueOf(count));
            et3.setText(v == 0 ? "0.0000" : BigDecimalUtils.format(v) + "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新页面ui
     *
     * @param c2cListInfo
     * @param c2CCoinInfo
     */
    public void updataUi(C2CListInfo c2cListInfo, C2CCoinInfo c2CCoinInfo) {
        if (!isInit) {
            onLazyInitView(null);
        }
        if ("我要买".equals(title)) {
            et1.setText(MoneyUtils.check0(c2CCoinInfo.getBuyPrice()));
            submit.setText(getString(R.string.c2c_btn, "买入", c2cListInfo.getCurrencyName()));
        } else {
            et1.setText(MoneyUtils.check0(c2CCoinInfo.getSellPrice()));
            submit.setText(getString(R.string.c2c_btn, "卖出", c2cListInfo.getCurrencyName()));
        }
        this.mCoinInfo = c2cListInfo;
    }


    /**
     * 交易
     */
    private void submit() {
        if (mCoinInfo != null) {
            String totalCount = et2.getText().toString().trim();
            String unitPrice = et1.getText().toString().trim();
            if (EmptyUtils.isEmpty(unitPrice) || "0".equals(unitPrice) || "0.0".equals(unitPrice)) {
                return;
            }
            //TODO 这里 web并未做校验
            // 校验数量
//            if (checkCount(stringToDouble(totalCount))) {
//                return;
//            }
            ApiService.buyAndSell(this, "我要买".equals(title), totalCount, unitPrice, tradeModus, mCoinInfo.getCurrencyId(), new DialogCallback<ResultInfo<Void>>() {
                @Override
                public void onSuccess(Response<ResultInfo<Void>> response) {
                    //交易回掉
                    LogUtil.i("交易成功");
                }
            });
        }
    }

    /**
     * 校验数量是否超出或低于限制
     */
    private boolean checkCount(Double aDoubleCount) {
        Double maxCount = stringToDouble("我要买".equals(title) ? mCoinInfo.getBuyMax() : mCoinInfo.getSellMin());
        Double minCount = stringToDouble("我要买".equals(title) ? mCoinInfo.getBuyMin() : mCoinInfo.getSellMin());
        if (aDoubleCount < minCount) {
            ToastManager.info(getString(R.string.hint_min, mCoinInfo.getBuyMin()));
            return true;
        }
        if (aDoubleCount > maxCount) {
            ToastManager.info(getString(R.string.hint_max, mCoinInfo.getBuyMax()));
            return true;
        }
        return false;
    }

    /**
     * 将 金额 String 转double
     *
     * @param price
     * @return
     */
    public double stringToDouble(String price) {
        try {
            return Double.valueOf(price);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
