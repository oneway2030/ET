package com.oneway.ui.base.ac;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import java.util.Iterator;
import java.util.Stack;


/**
 * Created by Leo on 2016/7/15.
 * activity 管理类
 */
public class ActivityManager {

    static Stack<FragmentActivity> stack;
    static ActivityManager instace;

    private ActivityManager() {
        stack = new Stack<>();
    }

    public static ActivityManager getInstance() {
        if (instace == null) {
            instace = new ActivityManager();
        }
        return instace;
    }

    /**
     * 添加Activity堆栈中
     *
     * @param activity
     */
    public void addActivity(AppCompatActivity activity) {
        stack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后压入的）
     *
     * @return
     */
    public FragmentActivity getCurrentActivity() {
        FragmentActivity activity = stack.lastElement();
        return activity;
    }

    /**
     * 移除指定activity并finish
     *
     * @param activity
     */
    public void removeActivity(FragmentActivity activity) {
        stack.remove(activity);
        if (activity != null && !activity.isFinishing())
            activity.finish();
    }

    /**
     * 移除指定类名的activity
     *
     * @param clazz
     */
    public void removeActivity(Class<?> clazz) {
        for (int i = 0; i < stack.size(); i++) {
            FragmentActivity activity = stack.get(i);
            if (activity.getClass().equals(clazz)) {
                removeActivity(activity);
                return;
            }
        }
    }

    /**
     * 结束当前Activity
     */
    public void finishActivity() {
        FragmentActivity activity = stack.lastElement();
        removeActivity(activity);
    }

    /**
     * 结束所有Activity
     */
    public void finishAll() {
        finishAll(null);
    }

    /**
     * 除了指定的ac, 其他的都关闭
     */
    public void finishAll(Class clazz) {
        Iterator<FragmentActivity> iterator = stack.iterator();
        while (iterator.hasNext()) {
            FragmentActivity activity = iterator.next();
            if (clazz == null || !activity.getClass().equals(clazz)) {
                if (activity != null && !activity.isFinishing()) {
                    activity.finish();
                }
                iterator.remove();
            }
        }
    }


    /**
     * 获取指定的Activity
     */
    public FragmentActivity getActivity(Class<?> cls) {
        if (stack != null)
            for (FragmentActivity activity : stack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        return null;
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAll();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }

}
