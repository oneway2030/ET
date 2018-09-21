package com.oneway.ui.widget.status;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * @Description: 各种状态视图显示布局
 * @date: 2017-03-08 15:51
 */
public class StatusLayout extends FrameLayout {

    //布局管理器
    private StatusLayoutManager mStatusLayoutManager;
    /**
     * 存放布局集合
     * key {@link StatusType}
     * value  视图view
     */
    private SparseArray<View> mLayoutSparseArray = new SparseArray();

    public StatusLayout(Context context) {
        super(context);
    }

    public StatusLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StatusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setStatusLayoutManager(StatusLayoutManager statusLayoutManager) {
        mStatusLayoutManager = statusLayoutManager;
        addAllLayoutToLayout();
    }

    /**
     * 添加所有视图到FrameLayout中
     * 并缓存视图到mLayoutSparseArray中
     */
    private void addAllLayoutToLayout() {
        if (mStatusLayoutManager.getContentView() != null) {
            mLayoutSparseArray.put(StatusType.CONTENT.getType(), mStatusLayoutManager.getContentView());
        }
        if (mStatusLayoutManager.getView(StatusType.LOADING) != null) {
            addView(mStatusLayoutManager.getView(StatusType.LOADING));
        }
        if (mStatusLayoutManager.getView(StatusType.EMPTY) != null) {
            addView(mStatusLayoutManager.getView(StatusType.EMPTY));
        }
        if (mStatusLayoutManager.getView(StatusType.NETWORK_ERROR) != null) {
            addView(mStatusLayoutManager.getView(StatusType.NETWORK_ERROR));
        }
        if (mStatusLayoutManager.getView(StatusType.OTHER_ERROR) != null) {
            addView(mStatusLayoutManager.getView(StatusType.OTHER_ERROR));
        }
        //默认开始显示loading
        showLoadingView();
        //        if (mStatusLayoutManager.getContentLayoutResId() != 0) {
//            addLayoutResId(mStatusLayoutManager.getContentLayoutResId(), StatusType.CONTENT.getType());
//        }
//        if (mStatusLayoutManager.getLoadingLayoutResId() != 0) {
//            addLayoutResId(mStatusLayoutManager.getLoadingLayoutResId(), StatusType.LOADING.getType());
//        }
//        if (mStatusLayoutManager.getLoadingLayoutResId() != null)
//            addView(mStatusLayoutManager.getEmptyView());
//        if (mStatusLayoutManager.getEmptyView() != null)
//            addView(mStatusLayoutManager.getEmptyView());
//        if (mStatusLayoutManager.getOtherErrorView() != null)
//            addView(mStatusLayoutManager.getOtherErrorView());
//        if (mStatusLayoutManager.getNetworkErrorView() != null)
//            addView(mStatusLayoutManager.getNetworkErrorView());
    }

    private void addLayoutResId(@LayoutRes int layoutResId, int id) {
        View resView = LayoutInflater.from(mStatusLayoutManager.getContext()).inflate(layoutResId, null);
        mLayoutSparseArray.put(id, resView);
        addView(resView);
    }

    /**
     * 显示Loaging视图
     */
    public void showLoadingView() {
        if (inflateLayout(StatusType.LOADING.getType())) {
            showHideViewById(StatusType.LOADING.getType(), "", 0);
        }
    }

    /**
     * 显示内容视图
     */
    public void showContentView() {
        if (mLayoutSparseArray.get(StatusType.CONTENT.getType()) != null)
            showHideViewById(StatusType.CONTENT.getType(), "", 0);
    }

    /**
     * 显示空视图
     */
    public void showEmptyView() {
        showEmptyView("", 0);
    }

    public void showEmptyView(String str) {
        showEmptyView(str, 0);
    }

    public void showEmptyView(String str, int imgId) {
        if (inflateLayout(StatusType.EMPTY.getType())) {
            showHideViewById(StatusType.EMPTY.getType(), str, imgId);
        }
    }

    /**
     * 显示网络异常
     */
    public void showNetworkErrorView() {
        showNetworkErrorView("", 0);
    }

    public void showNetworkErrorView(String str) {
        showNetworkErrorView(str, 0);
    }

    public void showNetworkErrorView(String str, int imgId) {
        if (inflateLayout(StatusType.NETWORK_ERROR.getType()))
            showHideViewById(StatusType.NETWORK_ERROR.getType(), str, imgId);
    }

    /**
     * 显示其他异常
     */
    public void showOtherErrorView() {
        showOtherErrorView("", 0);
    }

    public void showOtherErrorView(String str) {
        showOtherErrorView(str, 0);
    }

    public void showOtherErrorView(String str, int imgId) {
        if (inflateLayout(StatusType.OTHER_ERROR.getType()))
            showHideViewById(StatusType.OTHER_ERROR.getType(), str, imgId);
    }


    /**
     * 重试加载
     */
    public void retryLoad(View view, int retryViewId, final StatusType type) {
//        int setRetryViewId = id;
        if (retryViewId == 0) {
            retryViewId = mStatusLayoutManager.getRetryViewId();
        }
        if (retryViewId == 0) {
            return;
        }
        View retryView = view.findViewById(retryViewId);
        if (retryView == null || mStatusLayoutManager.getRetryListener() == null)
            return;
        retryView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mStatusLayoutManager.getRetryListener().onRetry(type.getType());
            }
        });
    }

    /**
     * 显示或隐藏 视图
     */
    private void showHideViewById(int id, String str, int imgId) {
        for (int i = 0; i < mLayoutSparseArray.size(); i++) {
            int key = mLayoutSparseArray.keyAt(i);
            View valueView = mLayoutSparseArray.valueAt(i);
            if (key == id) {
                valueView.setVisibility(View.VISIBLE);
                setViewUi(id, valueView, str, imgId);
                if (mStatusLayoutManager.getStatusViewListener() != null) {
                    mStatusLayoutManager.getStatusViewListener().onShowView(valueView, key);
                }
            } else {
                if (valueView.getVisibility() != View.GONE) {
                    valueView.setVisibility(View.GONE);
                    if (mStatusLayoutManager.getStatusViewListener() != null)
                        mStatusLayoutManager.getStatusViewListener().onHideView(valueView, key);
                }
            }
        }
    }

    /**
     * 初始化视图, 并加入到mLayoutSparseArray 集合中来
     */
    private boolean inflateLayout(int id) {
        boolean isShow;
        if (mLayoutSparseArray.get(id) != null) return true;
        if (id == StatusType.LOADING.getType() && mStatusLayoutManager.getNetworkErrorView() != null) {
            View view = mStatusLayoutManager.getLoadingView().inflate();
            mLayoutSparseArray.put(id, view);
//            retryLoad(view, mStatusLayoutManager.getNetworkErrorRetryViewId(), StatusType.LOADING);
//            setErrorUi(view, mStatusLayoutManager.getErrorText(), mStatusLayoutManager.getErrorImg());
            isShow = true;
        } else if (id == StatusType.EMPTY.getType() && mStatusLayoutManager.getEmptyView() != null) {
            View view = mStatusLayoutManager.getEmptyView().inflate();
            if (mStatusLayoutManager.getEmptyViewClickEnable()) {
                retryLoad(view, mStatusLayoutManager.getEmptyRetryViewId(), StatusType.EMPTY);
            }
            mLayoutSparseArray.put(id, view);
            setEmptyUi(view, mStatusLayoutManager.getEmptyText(), mStatusLayoutManager.getEmptyImg());
            isShow = true;
        } else if (id == StatusType.NETWORK_ERROR.getType() && mStatusLayoutManager.getNetworkErrorView() != null) {
            View view = mStatusLayoutManager.getNetworkErrorView().inflate();
            retryLoad(view, mStatusLayoutManager.getNetworkErrorRetryViewId(), StatusType.NETWORK_ERROR);
            mLayoutSparseArray.put(id, view);
            setErrorUi(view, mStatusLayoutManager.getErrorText(), mStatusLayoutManager.getErrorImg());
            isShow = true;
        } else if (id == StatusType.OTHER_ERROR.getType() && mStatusLayoutManager.getOtherErrorView() != null) {
            View view = mStatusLayoutManager.getOtherErrorView().inflate();
            retryLoad(view, mStatusLayoutManager.getOtherErrorRetryViewId(), StatusType.OTHER_ERROR);
            mLayoutSparseArray.put(id, view);
            setOtherUi(view, mStatusLayoutManager.getOtherText(), mStatusLayoutManager.getOtherImg());
            isShow = true;
        } else {
            isShow = false;
        }
        return isShow;
    }

    public void setViewUi(int id, View view, String str, int imgId) {
        if (id == StatusType.EMPTY.getType()) {
            setEmptyUi(view, str, imgId);
        } else if (id == StatusType.NETWORK_ERROR.getType()) {
            setErrorUi(view, str, imgId);
        } else if (id == StatusType.OTHER_ERROR.getType()) {
            setOtherUi(view, str, imgId);
        }
    }

    private void setEmptyUi(View view, String str, int imgId) {
        setView(view, StatusConfig.tv_empty, StatusConfig.iv_empty, str, imgId);
    }

    private void setErrorUi(View view, String str, int imgId) {
        setView(view, StatusConfig.tv_error, StatusConfig.iv_error, str, imgId);
    }

    private void setOtherUi(View view, String str, int imgId) {
        setView(view, StatusConfig.tv_other, StatusConfig.iv_other, str, imgId);
    }

    private void setView(View view, int tvId, int ivId, String str, int imgId) {
        if (!TextUtils.isEmpty(str)) {
            TextView tv = view.findViewById(tvId);
            if (tv != null)
                tv.setText(str);
        }
        // 设置其他页面 图片
        if (imgId != 0) {
            ImageView iv = view.findViewById(ivId);
            if (iv != null)
                iv.setImageResource(imgId);
        }
    }

}
