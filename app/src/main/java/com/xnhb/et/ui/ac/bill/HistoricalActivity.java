package com.xnhb.et.ui.ac.bill;

import android.os.Bundle;

import com.oneway.ui.base.ac.BaseTitleActivity;
import com.xnhb.et.R;

/**
 * 作者 oneway on 2018/9/26
 * 描述: 历史记录
 * 参考链接:
 */
public class HistoricalActivity extends BaseTitleActivity {
    @Override
    protected String getTitleText() {
        return "历史记录";
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_c2c_bill;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
