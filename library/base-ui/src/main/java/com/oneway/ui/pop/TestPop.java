package com.oneway.ui.pop;

import android.app.Activity;
import android.view.View;

import com.oneway.ui.pop.base.BasicPopup;

/**
 * @author oneway
 * @describe
 * @since 2018/6/12 0012
 */


public class TestPop extends BasicPopup {

    public TestPop(Activity activity) {
        super(activity);
    }

    @Override
    protected View makeContentView() {
        return null;
    }
}
