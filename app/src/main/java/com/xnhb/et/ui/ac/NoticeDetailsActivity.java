package com.xnhb.et.ui.ac;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.xnhb.et.R;
import com.xnhb.et.bean.NoticeInfo;
import com.xnhb.et.bean.NoticeInfo2;

import butterknife.BindView;

/**
 * @author oneway
 * @describe 公告详情
 * @since 2018/9/11 0011
 */


public class NoticeDetailsActivity extends BaseTitleActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    private NoticeInfo2 mNoticeInfo;

    public static void launch(Context context, NoticeInfo2 info) {
        Intent intent = new Intent();
        intent.setClass(context, NoticeDetailsActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra("NoticeInfo", info);
        context.startActivity(intent);
    }
    @Override
    public int setLayoutId() {
        return R.layout.activity_notice_details;
    }

    @Override
    protected boolean getIntent(Intent intent) {
        mNoticeInfo = intent.getParcelableExtra("NoticeInfo");
        return EmptyUtils.isEmpty(mNoticeInfo);
    }

    @Override
    protected String getTitleText() {
        return "官网公告";
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        tvTitle.setText(mNoticeInfo.getTitle());
        tvTime.setText(mNoticeInfo.getCreateTime());
//        tvContent.setText(mNoticeInfo.getContent());
        tvContent.setText(Html.fromHtml(mNoticeInfo.getContent()));
    }

}
