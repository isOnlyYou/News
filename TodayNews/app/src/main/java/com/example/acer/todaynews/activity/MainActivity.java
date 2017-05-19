package com.example.acer.todaynews.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioGroup;

import com.bawei.library.bean.ChannelItem;
import com.bawei.library.db.DBUtil;
import com.example.acer.todaynews.R;
import com.example.acer.todaynews.fragment.MainGZFragment;
import com.example.acer.todaynews.fragment.MainSPFragment;
import com.example.acer.todaynews.fragment.MainSYFragment;
import com.example.acer.todaynews.fragment.MainWDFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import thinkfreely.changemodelibrary.ChangeModeController;

/**
 * Created by acer on 2017/3/15.
 */
public class MainActivity extends FragmentActivity {

    private RadioGroup main_rg;
    private FragmentManager manager;
    private MainSYFragment syFm;
    private MainSPFragment spFm;
    private MainGZFragment gzFm;
    private MainWDFragment wdFm;
    public static int theme = R.style.AppTheme;

    private List<ChannelItem> selectDbList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        // 判断是否有主题存储
        if(savedInstanceState != null){
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        addFragment();
        setRgListen();
        main_rg.check(R.id.main_rb_sy);

        Intent intent = getIntent();
        int wd = intent.getIntExtra("wd", 0);
        if (wd == 4) {
            setFragment(wdFm, spFm, gzFm, syFm);
            main_rg.check(R.id.main_rb_wd);
        }
    }

    public List<ChannelItem> selectDb() {
        Cursor cursor = DBUtil.getInstance(this).selectData(null, "selected=?", new String[]{"1"},
                null, null, null);
        if (cursor.getCount() == 0){
            return null;
        }else {
            while (cursor.moveToNext()){
                int id = Integer.parseInt(cursor.getString(1));
                String name = cursor.getString(2);
                int orderId = Integer.parseInt(cursor.getString(3));
                int selected = Integer.parseInt(cursor.getString(4));
                ChannelItem item = new ChannelItem(id,name,orderId,selected);
                selectDbList.add(item);
            }
            return selectDbList;
        }
    }

    private void initView() {
        main_rg = (RadioGroup) findViewById(R.id.main_rg);

        manager = getSupportFragmentManager();
    }

    private void addFragment() {
        FragmentTransaction transaction = manager.beginTransaction();
        syFm = new MainSYFragment();
        spFm = new MainSPFragment();
        gzFm = new MainGZFragment();
        wdFm = new MainWDFragment();
        transaction.add(R.id.main_frame_layout, syFm, "syFm");
        transaction.add(R.id.main_frame_layout, spFm, "spFm");
        transaction.add(R.id.main_frame_layout, gzFm, "gzFm");
        transaction.add(R.id.main_frame_layout, wdFm, "wdFm");
        transaction.show(syFm);
        transaction.hide(spFm);
        transaction.hide(gzFm);
        transaction.hide(wdFm);
        transaction.commit();
    }

    private void setRgListen() {
        main_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb_sy:
                        setFragment(syFm, spFm, gzFm, wdFm);
                        break;
                    case R.id.main_rb_sp:
                        setFragment(spFm, syFm, gzFm, wdFm);
                        break;
                    case R.id.main_rb_gz:
                        setFragment(gzFm, spFm, syFm, wdFm);
                        break;
                    case R.id.main_rb_wd:
                        setFragment(wdFm, spFm, gzFm, syFm);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void setFragment(Fragment f1, Fragment f2, Fragment f3, Fragment f4) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.show(f1);
        transaction.hide(f2);
        transaction.hide(f3);
        transaction.hide(f4);
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
       // super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
