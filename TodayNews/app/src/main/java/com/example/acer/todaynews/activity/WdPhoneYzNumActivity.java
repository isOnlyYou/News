package com.example.acer.todaynews.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.todaynews.R;
import com.example.acer.todaynews.utils.MyHelper;

import cn.smssdk.SMSSDK;
import thinkfreely.changemodelibrary.ChangeModeController;

/**
 * Created by acer on 2017/3/21.
 */
public class WdPhoneYzNumActivity extends Activity{

    private ImageView phone_yz_dismiss;
    private EditText phone_yz_et_phone_num;
    private TextView phone_yz_tx_send;
    private EditText phone_yz_et_num;
    private Button phone_yz_bt_enter;
    private TextView phone_yz_tx_zhmm;
    private ImageView phone_yz_img_qq;
    private ImageView phone_yz_img_wx;
    private TextView phone_yz_tx_phone_wrong;
    private TextView phone_yz_tx_num_wrong;

    private String number;
    private MyHelper helper;
    private SQLiteDatabase db;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_yz);

        preferences = getSharedPreferences("config", 0);

        initView();

        zhmmTxListen();

        yzImgDismiss();

        phone_yz_tx_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSecurity();

            }
        });

        phone_yz_bt_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testSecurity();
            }
        });
    }

    //关闭页面的img的监听
    private void yzImgDismiss() {
        phone_yz_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WdPhoneYzNumActivity.this,MainActivity.class);
                intent.putExtra("wd",4);
                startActivity(intent);
                overridePendingTransition(R.anim.wd_pop_enter_anim,R.anim.wd_pop_exit_anim);
                finish();
            }
        });
    }

    //使用账号密码登录的TextView的点击监听
    private void zhmmTxListen() {
        phone_yz_tx_zhmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WdPhoneYzNumActivity.this,WdPhoneZHMMActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.wd_zhmm_enter_anim,R.anim.wd_zhmm_exit_anim);
            }
        });
    }

    private void initView() {
        phone_yz_dismiss = (ImageView) findViewById(R.id.phone_yz_dismiss);

        phone_yz_et_phone_num = (EditText) findViewById(R.id.phone_yz_et_phone_num);
        phone_yz_tx_send = (TextView) findViewById(R.id.phone_yz_tx_send);
        phone_yz_tx_phone_wrong = (TextView) findViewById(R.id.phone_yz_tx_phone_wrong);
        phone_yz_et_num = (EditText) findViewById(R.id.phone_yz_et_num);
        phone_yz_tx_num_wrong = (TextView) findViewById(R.id.phone_yz_tx_num_wrong);

        phone_yz_bt_enter = (Button) findViewById(R.id.phone_yz_bt_enter);
        phone_yz_tx_zhmm = (TextView) findViewById(R.id.phone_yz_tx_zhmm);
        phone_yz_img_qq = (ImageView) findViewById(R.id.phone_yz_img_qq);
        phone_yz_img_wx = (ImageView) findViewById(R.id.phone_yz_img_wx);

        helper = new MyHelper(WdPhoneYzNumActivity.this);
        db = helper.getWritableDatabase();
    }

    /**
     * 获取验证码
     *
     * @param
     */
    public void getSecurity() {
        number = phone_yz_et_phone_num.getText().toString().trim();
        //发送短信，传入国家号和电话---使用SMSSDK核心类之前一定要在MyApplication中初始化，
        // 否ze不能使用
        if (TextUtils.isEmpty(number) || number.length() != 11) {
            phone_yz_tx_phone_wrong.setVisibility(View.VISIBLE);
        } else {
            SMSSDK.getVerificationCode("+86", number);
            phone_yz_tx_phone_wrong.setVisibility(View.INVISIBLE);

            setNumTime();
        }
    }

    //设置倒计时发送的方法
    private void setNumTime() {
        phone_yz_tx_send.setEnabled(false);
        CountDownTimer timer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                phone_yz_tx_send.setText("重新发送（"+millisUntilFinished/1000+"s)");
            }

            @Override
            public void onFinish() {
                phone_yz_tx_send.setEnabled(true);
                phone_yz_tx_send.setText("发送验证码");
            }
        };
        timer.start();
    }

    /**
     * 向服务器提交验证码，在监听回调中判断是否通过验证
     * @param
     */
    public void testSecurity() {
        String security = phone_yz_et_num.getText().toString();
        if (!TextUtils.isEmpty(security)) {
            //提交短信验证码
            SMSSDK.submitVerificationCode("+86", number, security);//国家号，手机号码，验证码
            phone_yz_tx_num_wrong.setText("未注册手机验证后自动登录");
            Toast.makeText(this, "提交了注册信息:" + number, Toast.LENGTH_SHORT).show();

            //先将注册的手机号添加进数据库
            ContentValues values = new ContentValues();
            values.put("tele_num",number);
            db.insert("phone_num",null,values);

            //页面跳转
            Intent intent = new Intent(WdPhoneYzNumActivity.this,MainActivity.class);
            intent.putExtra("wd",4);

            //将登陆状态和手机号存起来，再次进入界面时判断登陆状态
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isEnter",true);
            editor.putString("phoneNum",number);
            editor.commit();

            startActivity(intent);
            overridePendingTransition(R.anim.wd_pop_enter_anim,R.anim.wd_pop_exit_anim);
            finish();

        } else {
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            phone_yz_tx_num_wrong.setText("验证码错误");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //要在activity销毁时反注册，否侧会造成内存泄漏问题
        SMSSDK.unregisterAllEventHandler();
    }
}
