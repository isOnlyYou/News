package com.example.acer.todaynews.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.acer.todaynews.R;

import java.util.Timer;
import java.util.TimerTask;

import thinkfreely.changemodelibrary.ChangeModeController;

public class WelcomeActivity extends Activity {

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        timer = new Timer();
        timer.schedule(new MyTask(),1000);
    }

    class MyTask extends TimerTask{

        @Override
        public void run() {
            Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
