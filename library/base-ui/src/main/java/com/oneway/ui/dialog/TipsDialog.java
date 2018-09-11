package com.oneway.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oneway.ui.R;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.dialog.base.OnCloseListener;


/**
 * Created by ww on 2016/11/10.
 */

public class TipsDialog extends Dialog {
    private Context mContext;
    //默认点击外部消失
    boolean CanceledOnTouchOutside = true;
    //默认可以取消
    boolean cancelable = true;
    //默认是双按钮  此处是提示消息
    boolean isSingle = false;
    private int positiveColor = R.color.confirm_bt;
    private int negativeColor = R.color.cancel_bt;
    private String positiveBtnName = "确定";
    private String negativeBtnName = "取消";
    private String singleConfirmBtnName = "确定";
    private OnCloseListener l;
    private SpannableStringBuilder strBuilder = null;
    private String titleStr = "";
    private CharSequence msgStr = "";
    private TextView tvTwoMsgTop;
    private TextView tvSingleMsg;
    private Button cancle;
    private Button confirm;
    private LinearLayout.LayoutParams lp;
    private Button singleConfirm;
    private RelativeLayout doubleBtnLayout;
    private TextView tvTwoMsgBottom;
    private ImageView ivCloseRight;
    private boolean isShowClose = false;

    public TipsDialog(Context context) {
        super(context, R.style.Custom_Progress);
        this.mContext = context;
    }

    public TipsDialog(Context context, String msg) {
        this(context);
        this.cancelable = cancelable;
        this.msgStr = msg;
    }

    public TipsDialog(Context context, String topStr, String bottomStr) {
        this(context);
        this.titleStr = topStr;
        this.msgStr = bottomStr;
    }

    public TipsDialog(Context context, String msg, boolean cancelable, OnCloseListener listener) {
        this(context);
        this.cancelable = cancelable;
        this.msgStr = msg;
        this.l = listener;
    }


    public TipsDialog(Context context, CharSequence msg, OnCloseListener listener) {
        this(context);
        this.msgStr = msg;
        this.l = listener;
    }

    public TipsDialog(Context context, SpannableStringBuilder strBuilder, OnCloseListener listener) {
        this(context);
        this.strBuilder = strBuilder;
        this.l = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);
        //点击外部关闭
        setCanceledOnTouchOutside(CanceledOnTouchOutside);
        // 按返回键是否取消
        setCancelable(cancelable);
        // 设置居中
        getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        // 设置背景层透明度
        lp.dimAmount = 0.2f;
        getWindow().setAttributes(lp);
        initView();
    }

    private void initView() {
        ivCloseRight = findViewById(R.id.iv_close_right);
        tvTwoMsgTop = findViewById(R.id.tv_two_msg_top);
        tvTwoMsgBottom = findViewById(R.id.tv_two_msg_bottom);
        tvSingleMsg = findViewById(R.id.tv_single_msg);

        cancle = findViewById(R.id.cancle);
        confirm = findViewById(R.id.confirm);
        singleConfirm = findViewById(R.id.single_confirm);
        doubleBtnLayout = findViewById(R.id.double_btn_layout);

        //设置双按钮
        confirm.setText(positiveBtnName);
        cancle.setText(negativeBtnName);
        singleConfirm.setText(singleConfirmBtnName);
        confirm.setTextColor(getContext().getResources().getColor(positiveColor));
        cancle.setTextColor(getContext().getResources().getColor(negativeColor));
        //设置单按钮
        singleConfirm.setBackgroundColor(getContext().getResources().getColor(positiveColor));
        confirm.setVisibility(isSingle ? View.GONE : View.VISIBLE);
        cancle.setVisibility(isSingle ? View.GONE : View.VISIBLE);
        singleConfirm.setVisibility(isSingle ? View.VISIBLE : View.GONE);
        if (TextUtils.isEmpty(titleStr)) {//单文本
            tvSingleMsg.setText(msgStr);
        } else {//两条信息
            tvTwoMsgTop.setText(titleStr);
            tvTwoMsgBottom.setText(msgStr);
        }
        ivCloseRight.setVisibility(isShowClose ? View.VISIBLE : View.GONE);
        tvSingleMsg.setVisibility(TextUtils.isEmpty(titleStr) ? View.VISIBLE : View.GONE);
        tvTwoMsgTop.setVisibility(TextUtils.isEmpty(titleStr) ? View.GONE : View.VISIBLE);
        tvTwoMsgBottom.setVisibility(TextUtils.isEmpty(titleStr) ? View.GONE : View.VISIBLE);
        if (TextUtils.isEmpty(titleStr)) {
            tvSingleMsg.setText(msgStr);
        } else {
            tvTwoMsgTop.setText(titleStr);
            tvTwoMsgBottom.setText(msgStr);
        }
        singleConfirm.setVisibility(isSingle ? View.VISIBLE : View.GONE);
        doubleBtnLayout.setVisibility(isSingle ? View.GONE : View.VISIBLE);
        if (!isSingle) {
            //双按钮
            cancle.setOnClickListener(mPerfectClickListener);
            confirm.setOnClickListener(mPerfectClickListener);
        } else {
            //单按钮
            singleConfirm.setOnClickListener(mPerfectClickListener);
        }
    }


    /**
     * 设置确认键文字
     */
    public TipsDialog setPositiveButton(String name) {
        this.positiveBtnName = name;
        return this;
    }

    /**
     */
    public TipsDialog setSingleButton(String name) {
        this.singleConfirmBtnName = name;
        return this;
    }

    /**
     * 设置单按钮 默认不是
     */
    public TipsDialog isSingle(boolean isHint) {
        this.isSingle = isHint;
        return this;
    }

    /**
     * 设置单按钮 默认不是
     */
    public TipsDialog showClose() {
        this.isShowClose = isShowClose;
        return this;
    }

    /**
     * 设置取消键文字
     */
    public TipsDialog setNegativeButton(String name) {
        this.negativeBtnName = name;
        return this;
    }

    /**
     * 设置确认键颜色
     */
    public TipsDialog setPositiveButtonColor(@ColorInt int color) {
        this.positiveColor = color;
        return this;
    }

    /**
     * 设置取消键颜色
     */
    public TipsDialog setNegativeButtonColor(int color) {
        this.negativeColor = color;
        return this;
    }

    /**
     * 设置msg文字
     */
    public TipsDialog setMsgSpannableStringBuilder(SpannableStringBuilder strBuilder) {
        this.strBuilder = strBuilder;
        return this;
    }


    public TipsDialog setMsgMargins(int left, int top, int right, int bottom) {
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(left, top, right, bottom);
        return this;
    }

    /**
     * 设置msg文字
     */
    public TipsDialog setMsgText(String str) {
        this.msgStr = str;
        return this;
    }

    /**
     * 设置标题
     */
    public TipsDialog setTvTwoMsgTop(String tvTwoMsgTop) {
        this.titleStr = tvTwoMsgTop;
        return this;
    }

    /**
     * 设置btn监听
     */
    public TipsDialog setOnCloseListener(OnCloseListener listener) {
        this.l = listener;
        return this;
    }

    /**
     * 显示dialog
     */
    public TipsDialog showDialog() {
        show();
        return this;
    }


    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            dismiss();
            int i = v.getId();
            if (i == R.id.cancle) {
                if (l != null) {
                    l.onDailogClose(TipsDialog.this, false);
                }

            } else if (i == R.id.confirm) {
                if (l != null) {
                    l.onDailogClose(TipsDialog.this, true);
                }

            } else if (i == R.id.single_confirm) {
                if (l != null) {
                    l.onDailogClose(TipsDialog.this, true);
                }
            }
        }
    };


}
