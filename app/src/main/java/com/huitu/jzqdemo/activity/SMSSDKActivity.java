package com.huitu.jzqdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.huitu.jzqdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class SMSSDKActivity extends AppCompatActivity {


    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.btn_)
    Button btn_;
    @BindView(R.id.share_btn)
    Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smssdk);
        ButterKnife.bind(this);
    }


    // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
    public void sendCode(String country, String phone) {
        //用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler();
        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //  处理成功得到验证码的结果
                    // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                    Log.i("TAGTAG", "发送验证码成功");
                } else {
                    //  处理错误的结果
                    Log.i("TAGTAG", "发送验证码失败");
                }

            }
        });
        // 触发操作
        SMSSDK.getVerificationCode(country, phone);
    }

    // 提交验证码，其中的code表示验证码，如“1357”
    public void submitCode(String country, String phone, String code) {
        //用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler();
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //  处理验证成功的结果
                    Log.i("TAGTAG", "验证成功");
                } else {
                    //  处理错误的结果
                    Log.i("TAGTAG", "验证失败");
                }
                Log.i("TAGTAG", Thread.currentThread().getName());
            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode(country, phone, code);
    }

    protected void onDestroy() {
        super.onDestroy();
        //用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler();
    }

    @OnClick({R.id.btn, R.id.btn_, R.id.share_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                sendCode("86", "15046693594");
                break;
            case R.id.btn_:
                submitCode("86", "15046693594", et.getText().toString());
                break;
            case R.id.share_btn:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://github.com/leavesC");
                Intent intent = Intent.createChooser(shareIntent, "分享一段文本信息");
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
        }
    }

    ;


}
