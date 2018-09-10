package com.oneway.ui;

import android.content.Context;
import android.support.annotation.ColorRes;

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

    public int def_title_image = -1;
    public int def_title_bg_color = -1;
    public int def_title_text_color = -1;

    public BaseUiConfig setTitleDefBackImag(int image) {
        this.def_title_image = image;
        return this;
    }

    public int getTitleDefBackImag() {
        return def_title_image;
    }

    public BaseUiConfig setTitleDefBgColor(@ColorRes int defColor) {
        this.def_title_bg_color = defColor;
        return this;
    }

    public int getTitleDefBgColor() {
        return def_title_bg_color;
    }

    public BaseUiConfig setTitleDefTextColor(@ColorRes int defColor) {
        this.def_title_text_color = defColor;
        return this;
    }

    public int getTitleDefTextColor() {
        return def_title_text_color;
    }
}
