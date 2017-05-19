package com.example.acer.todaynews.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bawei.library.bean.ChannelItem;
import com.example.acer.todaynews.R;
import com.example.acer.todaynews.activity.ChannelManagerActivity;
import com.example.acer.todaynews.activity.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2017/3/16.
 */
public class MainSYFragment extends Fragment {

    private LinearLayout main_fm_sy_find_layout;
    private ImageView main_fm_sy_img_jia;

    private TabLayout main_fm_sy_tab_layout;
    private ViewPager main_fm_sy_view_pager;

    private ArrayList<ChannelItem> firstList = new ArrayList<>();
    private ArrayList<Fragment> fmList = new ArrayList<>();
    private ArrayList<ChannelItem> tabList = new ArrayList<>();
    private MyVpFmAdapter adapter;

    private String jsonUrlA = "http://sight.urundata.com:5004/v1.0.0/Article/GetArticleList?PageIndex=" ;
    private String jsonUrlB = "&ID=";
    private String jsonUrlC = "&PageSize=12&Type=1";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fm_sy, null);
        main_fm_sy_find_layout = (LinearLayout) view.findViewById(R.id.main_fm_sy_find_layout);
        main_fm_sy_img_jia = (ImageView) view.findViewById(R.id.main_fm_sy_img_jia);
        main_fm_sy_tab_layout = (TabLayout) view.findViewById(R.id.main_fm_sy_tab_layout);
        main_fm_sy_view_pager = (ViewPager) view.findViewById(R.id.main_fm_sy_view_pager);

        addFirstList();

        return view;
    }

    private void firstEnter() {
        tabList.clear();
        tabList.addAll(firstList);
        addFmAndBundle(tabList);
        setVpAndTab();
    }

    //数据库为空时使用的数据
    private void addFirstList() {
        firstList.add(new ChannelItem(1, "热点", 1, 1));
        firstList.add(new ChannelItem(2, "社会", 2, 1));
        firstList.add(new ChannelItem(3, "科技", 3, 1));
        firstList.add(new ChannelItem(4, "国际", 4, 1));
        firstList.add(new ChannelItem(5, "军事", 5, 1));
        firstList.add(new ChannelItem(6, "财经", 6, 1));
        firstList.add(new ChannelItem(7, "时尚", 7, 1));
        firstList.add(new ChannelItem(8, "娱乐", 8, 1));
        firstList.add(new ChannelItem(9, "汽车", 9, 1));
        firstList.add(new ChannelItem(10, "体育", 10, 1));
        firstList.add(new ChannelItem(11, "游戏", 11, 1));
        firstList.add(new ChannelItem(12, "旅游", 12, 1));
        firstList.add(new ChannelItem(13, "历史", 13, 1));
        firstList.add(new ChannelItem(14, "探索", 14, 1));
        firstList.add(new ChannelItem(15, "美食", 15, 1));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        main_fm_sy_img_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ChannelManagerActivity.class);
                startActivityForResult(intent,500);
            }
        });

        //调用的Activity的查询数据库的方法，只要数据库为空，就调用死数据，不为空时就从数据库读取
        MainActivity activity = (MainActivity) getActivity();
        List<ChannelItem> channelItems = activity.selectDb();
        if (channelItems == null){
            firstEnter();
        }else {
            tabList.addAll(channelItems);
            addFmAndBundle(tabList);
            setVpAndTab();
        }
    }

    //设置ViewPager和TabLayout关联
    private void setVpAndTab() {
        if (main_fm_sy_view_pager.getAdapter() == null){
            adapter = new MyVpFmAdapter(getChildFragmentManager(), fmList);
            main_fm_sy_view_pager.setAdapter(adapter);
            main_fm_sy_tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
            main_fm_sy_tab_layout.setupWithViewPager(main_fm_sy_view_pager);
            main_fm_sy_tab_layout.setTabsFromPagerAdapter(adapter);
        }
    }

    //添加Fragment并传url
    private void addFmAndBundle(List<ChannelItem> itemList) {
        fmList.clear();
        for (int i = 0;i<itemList.size();i++){
            Bundle bundle = new Bundle();
            bundle.putString("jsonUrlA",jsonUrlA);
            bundle.putString("jsonUrlB",jsonUrlB+itemList.get(i).getId()+jsonUrlC);
            FragmentSY fmSy = new FragmentSY();
            fmSy.setArguments(bundle);
            fmList.add(fmSy);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == 500 && resultCode == 600) {
                String json = data.getStringExtra("json");
                Gson gson = new Gson();
                ArrayList<ChannelItem> list = gson.fromJson(json, new TypeToken<ArrayList<ChannelItem>>() {
                }.getType());
                tabList.clear();
                tabList.addAll(list);

                addFmAndBundle(tabList);

                main_fm_sy_tab_layout.removeAllTabs();
                main_fm_sy_view_pager.getAdapter().notifyDataSetChanged();
                addTabs();

                Log.e("=============", tabList.size() + "");
            }
        }
    }

    public void addTabs(){
        for (int i = 0; i < tabList.size(); i++) {
            main_fm_sy_tab_layout.addTab(main_fm_sy_tab_layout.newTab().setText(tabList.get(i).getName()));
        }
    }

    class MyVpFmAdapter extends FragmentPagerAdapter{
        private ArrayList<Fragment> fList;

        public MyVpFmAdapter(FragmentManager fm, ArrayList<Fragment> fList) {
            super(fm);
            this.fList = fList;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabList.get(position).getName();
        }

        @Override
        public Fragment getItem(int position) {
            return fList.get(position);
        }

        @Override
        public int getCount() {
            return fList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
