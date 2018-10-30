package com.xnhb.et.ui.ac.money;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.oneway.qrlib.helper.QRCodeHelper;
import com.oneway.tool.utils.common.ClipboardUtils;
import com.oneway.tool.utils.convert.EmptyUtils;
import com.oneway.ui.base.ac.BaseTitleActivity;
import com.oneway.ui.base.in.TitleContainer;
import com.oneway.ui.base.title.RightViewType;
import com.oneway.ui.base.title.TitleLayoutHelper;
import com.oneway.ui.common.PerfectClickListener;
import com.oneway.ui.toast.ToastManager;
import com.oneway.ui.widget.btn.StateButton;
import com.xnhb.et.R;
import com.xnhb.et.bean.CoinInfo;
import com.xnhb.et.bean.base.ResultInfo;
import com.xnhb.et.helper.UserInfoHelper;
import com.xnhb.et.net.Api;
import com.xnhb.et.net.okgo.DialogCallback;
import com.xnhb.et.net.okgo.OkGoHelper;
import com.xnhb.et.ui.ac.bill.HistoricalActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者 oneway on 2018/9/28
 * 描述: 充值
 * 参考链接:
 */
public class RechargeActivity extends BaseTitleActivity {
    private static String ARG_COIN_INFO = "coinInfo";
    private static String ARG_COIN_NAME = "coin_name";
    private static String ARG_COIN_ID = "coin_id";
    @BindView(R.id.iv_qr)
    ImageView ivQr;
    @BindView(R.id.tv_sub_title)
    TextView tvSubTitle;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.btn_copy)
    StateButton btnCopy;
    //    private CoinInfo mCoinInfo;
    private String name;
    private String id;


    private Bitmap mBitmap;


    public static void launch(Context context, String name, String id) {
        Intent intent = new Intent();
        intent.setClass(context, RechargeActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
//        intent.putExtra(ARG_COIN_INFO, info);
        intent.putExtra(ARG_COIN_NAME, name);
        intent.putExtra(ARG_COIN_ID, id);
        context.startActivity(intent);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected boolean getIntent(Intent intent) {
//        mCoinInfo = getIntent().getParcelableExtra(ARG_COIN_INFO);
        name = getIntent().getStringExtra(ARG_COIN_NAME);
        id = getIntent().getStringExtra(ARG_COIN_ID);
        return EmptyUtils.isEmpty(name) || EmptyUtils.isEmpty(id);
    }

    @Override
    protected String getTitleText() {
        return "充值" + name;
    }

    @Override
    protected void customTitle(TitleContainer toolbar) {
        TitleLayoutHelper titleLayoutHelper = new TitleLayoutHelper();
        titleLayoutHelper.setOneTextLayout(toolbar, this, "充提历史", R.color.color_text_press, 12, new TitleLayoutHelper.RightViewClickListener() {
            @Override
            public void onRightViewClickListener(View view, RightViewType Type) {
                HistoricalActivity.launch(RechargeActivity.this);
            }
        });
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        tvSubTitle.setText(name + " 充值地址");
        btnCopy.setOnClickListener(mPerfectClickListener);
        reqRechargeInfo();
    }


    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_copy) {
                ToastManager.success("已复制地址到剪切板");
                ClipboardUtils.copyText(tvAddress.getText().toString());
            }
        }
    };

    public void reqRechargeInfo() {
        Map map = new HashMap();
        map.put("token", UserInfoHelper.getInstance().getToken());
        map.put("currencyId", id);
        OkGoHelper.postOkGo(Api.RECHARGE_URL, this)
                .params(map)
                .execute(new DialogCallback<ResultInfo<String>>(this, true, false) {
                    @Override
                    public void onSuccess(Response<ResultInfo<String>> response) {
                        ResultInfo<String> body = response.body();
                        String address = body.getResult();
                        tvAddress.setText("" + address);
                        //生成二维码
                        makeQR(address);
                    }
                });
    }

    private void makeQR(String address) {
        if (EmptyUtils.isEmpty(address)) {
            return;
        }
        mBitmap = QRCodeHelper.creatQRCode(address);
        ivQr.setImageBitmap(mBitmap);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBitmap != null)
            mBitmap.recycle();
    }
}
