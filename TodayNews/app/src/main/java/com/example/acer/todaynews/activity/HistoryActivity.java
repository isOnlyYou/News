package com.example.acer.todaynews.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.todaynews.R;
import com.example.acer.todaynews.fragment.HistoryFmTSLS;
import com.example.acer.todaynews.fragment.HistoryFmWDSC;
import com.example.acer.todaynews.fragment.HistoryFmYDLS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2017/3/21.
 */
public class HistoryActivity extends FragmentActivity {

    private ImageView history_img_back;
    private ImageView history_img_find;
    private TextView history_tx_edit;
    private TabLayout history_tab_layout;
    private ViewPager history_view_pager;

    private List<Fragment> fmList;
    private List<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initView();

        HistoryFmWDSC fmWDSC = new HistoryFmWDSC();
        HistoryFmYDLS fmYDLS = new HistoryFmYDLS();
        HistoryFmTSLS fmTSLS = new HistoryFmTSLS();
        fmList.add(fmWDSC);
        fmList.add(fmYDLS);
        fmList.add(fmTSLS);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        history_view_pager.setAdapter(adapter);

        history_tab_layout.setupWithViewPager(history_view_pager);
        history_tab_layout.setTabsFromPagerAdapter(adapter);
    }

    private void initView() {
        history_img_back = (ImageView) findViewById(R.id.history_img_back);
        history_img_find = (ImageView) findViewById(R.id.history_img_find);
        history_tx_edit = (TextView) findViewById(R.id.history_tx_edit);
        history_tab_layout = (TabLayout) findViewById(R.id.history_tab_layout);
        history_view_pager = (ViewPager) findViewById(R.id.history_view_pager);

        fmList = new ArrayList<>();
        titleList = new ArrayList<>();

        titleList.add("我的收藏");
        titleList.add("阅读历史");
        titleList.add("收藏历史");
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
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
