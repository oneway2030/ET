package com.oneway.ui.bean;

import android.app.Activity;

/**
 * 作者 oneway on 2018/7/11
 * 描述: 测试页面实体
 * 参考链接:
 */
public class TestPageInfo {
    private int title;
    private int pic;
    private Class<? extends Activity> demoClass;

    public TestPageInfo() {
    }

    public TestPageInfo(int title, Class<? extends Activity> demoClass) {
        this.title = title;
        this.demoClass = demoClass;
    }

    public TestPageInfo(int title, int desc, Class<? extends Activity> demoClass) {
        this.title = title;
        this.pic = desc;
        this.demoClass = demoClass;
    }

    @Override
    public String toString() {
        return "TestPageInfo{" +
                "title=" + title +
                ", pic=" + pic +
                ", demoClass=" + demoClass +
                '}';
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public Class<? extends Activity> getDemoClass() {
        return demoClass;
    }

    public void setDemoClass(Class<? extends Activity> demoClass) {
        this.demoClass = demoClass;
    }
}
