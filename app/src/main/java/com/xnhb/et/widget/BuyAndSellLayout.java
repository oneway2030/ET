package com.xnhb.et.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.oneway.tool.utils.common.KeyboardUtils;
import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.widget.btn.StateButton;
import com.xnhb.et.R;

/**
 * 作者 oneway on 2018/10/23
 * 描述:
 * 参考链接:
 */
public class BuyAndSellLayout extends FrameLayout implements View.OnClickListener {
    private Context mContext;
    private boolean upUi;
    private StateButton mbtn;
    private LinearLayout llCount;
    private EditText etPrice;

    public BuyAndSellLayout(@NonNull Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public BuyAndSellLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public BuyAndSellLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        View rooView = UiUtils.newInstance(mContext, R.layout.custom_layout_buy_and_sell);
        addView(rooView);
        mbtn = findViewById(R.id.btn);
        llCount = findViewById(R.id.ll_price);
        etPrice = findViewById(R.id.et_price);
        llCount.setOnClickListener(this);
//        mbtn = findViewById(R.id.btn);
    }


    public void setupUi(boolean isBuy) {
        mbtn.setNormalBackgroundColor(isBuy ? UiUtils.getColor(R.color.green_dark) : UiUtils.getColor(R.color.red_btn));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ll_price) {
            KeyboardUtils.showSoftInput(etPrice);
        }
    }
}
