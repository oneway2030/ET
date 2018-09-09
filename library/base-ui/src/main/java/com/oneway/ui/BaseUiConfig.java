package com.oneway.ui;

import android.content.Context;

import com.oneway.tool.ToolConfig;


/**
 * Created by Administrator on 2017/12/28.
 */

public class BaseUiConfig {
    private static Context mContext;


    private static BaseUiConfig instance;

    private BaseUiConfig() {
    }


    public static BaseUiConfig getInstance() {
        if (instance == null) {
            synchronized (ToolConfig.class) {
                if (instance == null) {
                    instance = new BaseUiConfig();
                }
            }
        }
        return instance;
    }

    public BaseUiConfig init(Context context) {
        mContext = context.getApplicationContext();
        return this;
    }

    public static Context getContext() {
        return ToolConfig.getContext();
    }

}
