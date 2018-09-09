package com.oneway.ui.dialog;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oneway.ui.R;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.dialog.base.BaseDialogFragment;
import com.oneway.ui.dialog.base.OnCloseListener2;

/**
 * 1. 标题 title  字号默认18sp字体
 * 2.
 */
public class HintDialog extends BaseDialogFragment {
    private RelativeLayout doubleBtnLayout;
    private TextView tvTopTwoMsg;   //两条信息 顶部消息
    private TextView tvBottomTwoMsg;//两条信息 底部消息
    private Button leftBtn;   //取消按钮
    private Button rightBtn;  //确认按钮
    private LinearLayout.LayoutParams lp;
    private TextView tvSingleMsg;   //一条消息
    private Button singleConfirmBtn;
    private ImageView ivCloseRight;
    //默认是双按钮  此处是提示消息
    boolean isSingle = false;
    private int rightBtnColor = R.color.confirm_bt;
    private int leftBtnColor = R.color.cancel_bt;
    private String rightBtnName = "确定";
    private String leftBtnName = "取消";
    private String singleConfirmBtnName = "确定";
    private OnCloseListener2 l;
    private SpannableStringBuilder strBuilder = null;
    private String titleStr = "";
    private CharSequence msgStr = "";


    private boolean isShowRightClose = false;

    @Override
    protected int getLayout() {
        return R.layout.dialog_common;
    }

    @Override
    protected boolean onKeyBack() {
        return false;
    }

    @Override
    protected void initWindow(Window window) {
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.ScaleAnim);
        window.setLayout((int) (mWidth * 0.75f), WindowManager.LayoutParams.WRAP_CONTENT);
    }


    @Override
    protected void init(Bundle savedInstanceState, View contentView) {
        ivCloseRight = contentView.findViewById(R.id.iv_close_right);
        tvTopTwoMsg = contentView.findViewById(R.id.tv_two_msg_top);
        tvBottomTwoMsg = contentView.findViewById(R.id.tv_two_msg_bottom);
        tvSingleMsg = contentView.findViewById(R.id.tv_single_msg);

        leftBtn = contentView.findViewById(R.id.cencle);
        rightBtn = contentView.findViewById(R.id.confirm);
        singleConfirmBtn = contentView.findViewById(R.id.single_confirm);
        doubleBtnLayout = contentView.findViewById(R.id.double_btn_layout);

        //设置双按钮
        rightBtn.setText(rightBtnName);
        leftBtn.setText(leftBtnName);
        singleConfirmBtn.setText(singleConfirmBtnName);
        rightBtn.setTextColor(getContext().getResources().getColor(rightBtnColor));
        leftBtn.setTextColor(getContext().getResources().getColor(leftBtnColor));
        //设置单按钮
        singleConfirmBtn.setBackgroundColor(getContext().getResources().getColor(rightBtnColor));
        rightBtn.setVisibility(isSingle ? View.GONE : View.VISIBLE);
        leftBtn.setVisibility(isSingle ? View.GONE : View.VISIBLE);
        singleConfirmBtn.setVisibility(isSingle ? View.VISIBLE : View.GONE);
        if (TextUtils.isEmpty(titleStr)) {//单文本
            tvSingleMsg.setText(msgStr);
        } else {//两条信息
            tvTopTwoMsg.setText(titleStr);
            tvBottomTwoMsg.setText(msgStr);
        }
        ivCloseRight.setVisibility(isShowRightClose ? View.VISIBLE : View.GONE);
        tvSingleMsg.setVisibility(TextUtils.isEmpty(titleStr) ? View.VISIBLE : View.GONE);
        tvTopTwoMsg.setVisibility(TextUtils.isEmpty(titleStr) ? View.GONE : View.VISIBLE);
        tvBottomTwoMsg.setVisibility(TextUtils.isEmpty(titleStr) ? View.GONE : View.VISIBLE);
        if (TextUtils.isEmpty(titleStr)) {
            tvSingleMsg.setText(msgStr);
        } else {
            tvTopTwoMsg.setText(titleStr);
            tvBottomTwoMsg.setText(msgStr);
        }
        singleConfirmBtn.setVisibility(isSingle ? View.VISIBLE : View.GONE);
        doubleBtnLayout.setVisibility(isSingle ? View.GONE : View.VISIBLE);
        if (!isSingle) {
            //双按钮
            leftBtn.setOnClickListener(mPerfectClickListener);
            rightBtn.setOnClickListener(mPerfectClickListener);
        } else {
            //单按钮
            singleConfirmBtn.setOnClickListener(mPerfectClickListener);
        }
    }


    /**
     * 设置确认键文字
     */
    public HintDialog setRightBtnText(String name) {
        this.rightBtnName = name;
        return this;
    }

    /**
     * 设置取消键文字
     */
    public HintDialog setLeftBtnText(String name) {
        this.leftBtnName = name;
        return this;
    }

    /**
     * 设置一个按钮的文字
     */
    public HintDialog setSingleBtnText(String name) {
        this.singleConfirmBtnName = name;
        return this;
    }

    /**
     * 设置单按钮 默认双按钮
     */
    public HintDialog isSingleBtn(boolean isHint) {
        this.isSingle = isHint;
        return this;
    }

    /**
     *
     */
    public HintDialog showRightClose() {
        this.isShowRightClose = isShowRightClose;
        return this;
    }


    /**
     * 设置确认键颜色
     */
    public HintDialog setRightBtnColor(@ColorInt int color) {
        this.rightBtnColor = color;
        return this;
    }

    /**
     * 设置取消键颜色
     */
    public HintDialog setLeftBtnColor(int color) {
        this.leftBtnColor = color;
        return this;
    }

    /**
     * 设置msg文字
     */
    public HintDialog setMsgSpannableStringBuilder(SpannableStringBuilder strBuilder) {
        this.strBuilder = strBuilder;
        return this;
    }


    public HintDialog setMsgMargins(int left, int top, int right, int bottom) {
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(left, top, right, bottom);
        return this;
    }

    /**
     * 设置标题
     */
    public HintDialog setTvTopTwoMsg(String tvTopTwoMsg) {
        this.titleStr = tvTopTwoMsg;
        return this;
    }
    /**
     * 设置面下的提示文字
     */
    public HintDialog setTipMsg(String str) {
        this.msgStr = str;
        return this;
    }


    /**
     * 设置btn监听
     */
    public HintDialog setOnCloseListener(OnCloseListener2 listener) {
        this.l = listener;
        return this;
    }


    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            dismiss();
            int i = v.getId();
            if (i == R.id.cencle) {
                if (l != null) {
                    l.onDailogClose(HintDialog.this, false);
                }

            } else if (i == R.id.confirm) {
                if (l != null) {
                    l.onDailogClose(HintDialog.this, true);
                }

            } else if (i == R.id.single_confirm) {
                if (l != null) {
                    l.onDailogClose(HintDialog.this, true);
                }
            }
        }
    };

}
