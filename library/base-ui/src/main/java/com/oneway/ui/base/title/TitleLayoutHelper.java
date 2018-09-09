package com.oneway.ui.base.title;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oneway.tool.utils.ui.UiUtils;
import com.oneway.ui.base.in.TitleContainer;
import com.oneway.ui.common.PerfectClickListener;

/**
 * 作者 oneway on 2018/8/27
 * 描述:
 * 参考链接:
 */
public class TitleLayoutHelper {

    public interface RightViewClickListener {
        void onRightViewClickListener(View view, RightViewType Type);
    }

    /**
     * 添加 一个textview到右边
     */
    public TextView setRightTextViewLayout(Context context, String str, int color, float size, final RightViewClickListener listener) {
        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(UiUtils.getColor(color));
        textView.setTextSize(size);
        textView.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if (listener != null) {
                    listener.onRightViewClickListener(v, RightViewType.TEXT);
                }
            }
        });
        return textView;
    }

    /**
     * 添加 image 最多三个
     */
    public View setRightDefLayout(Context context, int def_right_pading, int def_child_pading, LinearLayout.LayoutParams lp, final RightViewClickListener listener, int... res) {
        LinearLayout rootView = new LinearLayout(context);
        rootView.setPadding(0, 0, UiUtils.dp2px(def_right_pading), 0);
        for (int i = 0; i < (res.length > 3 ? 3 : res.length); i++) {
            ImageView iv = new ImageView(context);
            iv.setPadding(UiUtils.dp2px(def_child_pading), 0, UiUtils.dp2px(def_child_pading), 0);
            iv.setImageResource(res[i]);
            rootView.addView(iv, 0, lp);
            final int finalI = i;
            iv.setOnClickListener(new PerfectClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    if (listener != null) {
                        if (finalI == 0) {
                            listener.onRightViewClickListener(v, RightViewType.RIGHT);
                        } else if (finalI == 1) {
                            listener.onRightViewClickListener(v, RightViewType.CENTER);
                        } else if (finalI == 2) {
                            listener.onRightViewClickListener(v, RightViewType.LEFT);
                        }
                    }
                }
            });
        }
        return rootView;
    }


    /**
     * 在右边设置一个text文本
     *
     * @param content  文本内容
     * @param color    文本颜色
     * @param size     文本字体大小
     * @param listener 文本点击监听
     */
    public TitleContainer setOneTextLayout(TitleContainer container, Context context, String content, int color, float size, RightViewClickListener listener) {
        TextView textView = setRightTextViewLayout(context, content, color, size, listener);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setPadding(UiUtils.dp2px(15), 0, UiUtils.dp2px(15), 0);
        container.setRightView(textView, lp);
        return container;
    }

    /**
     * 在右边设置 imageView,  最多三个
     *
     * @param listener 三个imageView监听  分别从
     * @param res      image图片资源,有多少个就 添加多少个, maxsize=3
     */
    public TitleContainer setImageLayout(TitleContainer container, Context context, RightViewClickListener listener, int... res) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        View view = setRightDefLayout(context, 10, 5, lp, listener, res);
        container.setRightView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return container;
    }
}
