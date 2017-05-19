package com.example.acer.todaynews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.acer.todaynews.R;
import com.example.acer.todaynews.fragment.MyCareFmFK;
import com.example.acer.todaynews.fragment.MyCareFmFS;
import com.example.acer.todaynews.fragment.MyCareFmGZ;

import java.util.ArrayList;
import java.util.List;

import thinkfreely.changemodelibrary.ChangeModeController;

/**
 * Created by acer on 2017/3/23.
 */
public class MyCareActivity extends FragmentActivity{

    private ImageView my_care_img_back;
    private TabLayout my_care_tab_layout;
    private ImageView my_care_img_care;
    private ViewPager my_care_view_pager;

    private List<String> titleList ;
    private List<Fragment> fmList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_care);

        initView();

        MyCareVpFmAdapter adapter = new MyCareVpFmAdapter(getSupportFragmentManager());
        my_care_view_pager.setAdapter(adapter);
        my_care_tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        my_care_tab_layout.setupWithViewPager(my_care_view_pager);
        my_care_tab_layout.setTabsFromPagerAdapter(adapter);

        my_care_img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCareActivity.this,MainActivity.class);
                intent.putExtra("wd",4);
                startActivity(intent);
                overridePendingTransition(R.anim.wd_yz_enter_anim,R.anim.wd_yz_exit_anim);
                finish();
            }
        });

        my_care_img_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCareActivity.this,AddPersonActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        my_care_img_back = (ImageView) findViewById(R.id.my_care_img_back);
        my_care_tab_layout = (TabLayout) findViewById(R.id.my_care_tab_layout);
        my_care_img_care = (ImageView) findViewById(R.id.my_care_img_care);
        my_care_view_pager = (ViewPager) findViewById(R.id.my_care_view_pager);

        titleList = new ArrayList<>();
        titleList.add("关注");titleList.add("粉丝");titleList.add("访客");
        fmList = new ArrayList<>();

        MyCareFmGZ fmGZ = new MyCareFmGZ();
        MyCareFmFS fmFS = new MyCareFmFS();
        MyCareFmFK fmFK = new MyCareFmFK();
        fmList.add(fmGZ);fmList.add(fmFS);fmList.add(fmFK);
    }

    class MyCareVpFmAdapter extends FragmentPagerAdapter{

        public MyCareVpFmAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fmList.get(position);
        }

        @Override
        public int getCount() {
            return fmList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
}
