package com.example.acer.todaynews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.todaynews.R;

import thinkfreely.changemodelibrary.ChangeModeController;

/**
 * Created by acer on 2017/3/23.
 */
public class AddPersonActivity extends Activity{

    private ImageView add_person_img_back;
    private TextView add_person_tx_say;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        initView();

        add_person_img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPersonActivity.this,MyCareActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.wd_yz_enter_anim,R.anim.wd_yz_exit_anim);
                finish();
            }
        });
    }

    private void initView() {
        add_person_img_back = (ImageView) findViewById(R.id.add_person_img_back);
        add_person_tx_say = (TextView) findViewById(R.id.add_person_tx_say);
    }
}
