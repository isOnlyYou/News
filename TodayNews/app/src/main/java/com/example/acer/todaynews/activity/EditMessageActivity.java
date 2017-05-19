package com.example.acer.todaynews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.acer.todaynews.R;

import thinkfreely.changemodelibrary.ChangeModeController;

/**
 * Created by acer on 2017/3/22.
 */
public class EditMessageActivity extends Activity{

    private Button edit_msg_bt_edit;
    private ImageView edit_msg_img_back;
    private ImageView edit_msg_img_pic;
    private TextView edit_msg_tx_name;
    private TextView edit_msg_tx_describe;
    private ListView edit_msg_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_msg);

        initView();

        edit_msg_img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditMessageActivity.this,MainActivity.class);
                intent.putExtra("wd",4);
                startActivity(intent);
                overridePendingTransition(R.anim.wd_yz_enter_anim,R.anim.wd_yz_exit_anim);
                finish();
            }
        });
    }

    private void initView() {
        edit_msg_bt_edit = (Button) findViewById(R.id.edit_msg_bt_edit);
        edit_msg_img_back = (ImageView) findViewById(R.id.edit_msg_img_back);
        edit_msg_img_pic = (ImageView) findViewById(R.id.edit_msg_img_pic);
        edit_msg_tx_name = (TextView) findViewById(R.id.edit_msg_tx_name);
        edit_msg_tx_describe = (TextView) findViewById(R.id.edit_msg_tx_describe);
        edit_msg_list_view = (ListView) findViewById(R.id.edit_msg_list_view);
    }
}
