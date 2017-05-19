package com.example.acer.todaynews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.todaynews.R;

import thinkfreely.changemodelibrary.ChangeModeController;

/**
 * Created by acer on 2017/3/21.
 */
public class WdPhoneZHMMActivity extends Activity{

    private ImageView phone_zhmm_back;
    private ImageView phone_zhmm_dismiss;
    private EditText phone_zhmm_et_phone_num;
    private TextView phone_zhmm_tx_find;
    private EditText phone_zhmm_et_pwd;
    private Button phone_zhmm_bt_enter;
    private TextView phone_zhmm_tx_yz;
    private ImageView phone_zhmm_img_qq;
    private ImageView phone_zhmm_img_wx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_zhmm);

        initView();

        phone_zhmm_tx_yz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WdPhoneZHMMActivity.this,WdPhoneYzNumActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.wd_yz_enter_anim,R.anim.wd_yz_exit_anim);
                finish();
            }
        });

        phone_zhmm_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WdPhoneZHMMActivity.this,WdPhoneYzNumActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.wd_yz_enter_anim,R.anim.wd_yz_exit_anim);
                finish();
            }
        });

        phone_zhmm_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WdPhoneZHMMActivity.this,MainActivity.class);
                intent.putExtra("wd",4);
                startActivity(intent);
                overridePendingTransition(R.anim.wd_pop_enter_anim,R.anim.wd_pop_exit_anim);
                finish();
            }
        });
    }

    private void initView() {
        phone_zhmm_back = (ImageView) findViewById(R.id.phone_zhmm_back);
        phone_zhmm_dismiss = (ImageView) findViewById(R.id.phone_zhmm_dismiss);
        phone_zhmm_et_phone_num = (EditText) findViewById(R.id.phone_zhmm_et_phone_num);
        phone_zhmm_tx_find = (TextView) findViewById(R.id.phone_zhmm_tx_find);
        phone_zhmm_et_pwd = (EditText) findViewById(R.id.phone_zhmm_et_pwd);
        phone_zhmm_bt_enter = (Button) findViewById(R.id.phone_zhmm_bt_enter);
        phone_zhmm_tx_yz = (TextView) findViewById(R.id.phone_zhmm_tx_yz);
        phone_zhmm_img_qq = (ImageView) findViewById(R.id.phone_zhmm_img_qq);
        phone_zhmm_img_wx = (ImageView) findViewById(R.id.phone_zhmm_img_wx);
    }
}
