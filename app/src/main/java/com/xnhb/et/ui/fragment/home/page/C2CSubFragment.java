package com.xnhb.et.ui.fragment.home.page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.oneway.tool.utils.calculate.BigDecimalUtils;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.base.fragment.XFragment;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.widget.btn.StateButton;
import com.xnhb.et.R;

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
    private static String ARG_TAG_PRICE = "unit_price";
    private static String ARG_COIN_TYPE_ = "ENCY";  //默认ENCY
    private static double UNIT_PRICE = 0;  //单价
    private String title;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_c2c_sub;
    }

    public static C2CSubFragment newInstance(String title, double unitPrice) {
        C2CSubFragment frament = new C2CSubFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TAG, title);
        bundle.putDouble(ARG_TAG_PRICE, unitPrice);
        frament.setArguments(bundle);
        return frament;
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initUi();
        et2.addTextChangedListener(this);
        submit.setOnClickListener(mPerfectClickListener);
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.submit) {
                //提交
            }
        }
    };

    private void initUi() {
        title = getArguments().getString(ARG_TAG);
        UNIT_PRICE = getArguments().getDouble(ARG_TAG_PRICE);
        et1.setText(UNIT_PRICE + "");
        et3.setText("0.0000");
        if ("我要买".equals(title)) {
            tvTitle1.setText("买入价");
            tvTitle2.setText("买入量");
            submit.setText(getString(R.string.c2c_btn, "买入", ARG_COIN_TYPE_));
        } else if ("我要卖".equals(title)) {
            tvTitle1.setText("卖出价");
            tvTitle2.setText("卖出量");
            submit.setText(getString(R.string.c2c_btn, "卖出", ARG_COIN_TYPE_));
        }
    }

    /**
     *
     */
    public void updateCoinType(String type) {
        ARG_COIN_TYPE_ = type;
        initUi();
    }

    /**
     * TODO 买入个数限制 买入单价限制 总价限制 需要探讨
     */
    public double calculationCoin(double count) {
        return BigDecimalUtils.multiply(count, UNIT_PRICE, 4);
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
            et3.setText(v == 0 ? "0.0000" : v + "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


}
