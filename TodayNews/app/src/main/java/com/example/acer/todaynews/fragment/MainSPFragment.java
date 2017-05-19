package com.example.acer.todaynews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.acer.todaynews.R;
import com.example.acer.todaynews.beans.VideoBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by acer on 2017/3/16.
 */
public class MainSPFragment extends Fragment {

    private TabLayout main_fm_sp_tab_layout;
    private ImageView main_fm_sp_img_find;
    private ViewPager main_fm_sp_view_pager;
    private List<VideoBean.DataBean> data;

    private List<Fragment> fmList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fm_sp, null);
        main_fm_sp_tab_layout = (TabLayout) view.findViewById(R.id.main_fm_sp_tab_layout);
        main_fm_sp_img_find = (ImageView) view.findViewById(R.id.main_fm_sp_img_find);
        main_fm_sp_view_pager = (ViewPager) view.findViewById(R.id.main_fm_sp_view_pager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String titleJson = "{\n" +
                "data:\n" +
                "[\n" +
                "  {\n" +
                "    \"title\": \"娱乐\",\n" +
                "    \"channelType\": 0,\n" +
                "    \"isFix\": 1,\n" +
                "    \"isSubscible\": 1,\n" +
                "    \"url\": \"http://c.3g.163.com/nc/video/list/V9LG4CHOR/n/\",\n" +
                "    \"url_footer\": \"-10.html\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"体育\",\n" +
                "    \"channelType\": 0,\n" +
                "    \"isFix\": 1,\n" +
                "    \"isSubscible\": 1,\n" +
                "    \"url\": \"http://c.3g.163.com/nc/video/list/VBF8F2E94/n/\",\n" +
                "    \"url_footer\": \"-10.html\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"搞笑\",\n" +
                "    \"channelType\": 0,\n" +
                "    \"isFix\": 0,\n" +
                "    \"isSubscible\": 1,\n" +
                "    \"url\": \"http://c.3g.163.com/nc/video/list/VAP4BFE3U/n/\",\n" +
                "    \"url_footer\": \"-10.html\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"美女\",\n" +
                "    \"channelType\": 0,\n" +
                "    \"isFix\": 0,\n" +
                "    \"isSubscible\": 1,\n" +
                "    \"url\": \"http://c.3g.163.com/nc/video/list/VAP4BG6DL/n/\",\n" +
                "    \"url_footer\": \"-10.html\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"新闻现场\",\n" +
                "    \"channelType\": 0,\n" +
                "    \"isFix\": 0,\n" +
                "    \"isSubscible\": 1,\n" +
                "    \"url\": \"http://c.3g.163.com/nc/video/list/VAV3H6JSN/n/\",\n" +
                "    \"url_footer\": \"-10.html\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"BoBo\",\n" +
                "    \"channelType\": 0,\n" +
                "    \"isFix\": 0,\n" +
                "    \"isSubscible\": 1,\n" +
                "    \"url\": \"http://c.3g.163.com/nc/video/list/VBK3JKUIF/n/\",\n" +
                "    \"url_footer\": \"-10.html\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"二次元\",\n" +
                "    \"channelType\": 0,\n" +
                "    \"isFix\": 0,\n" +
                "    \"isSubscible\": 1,\n" +
                "    \"url\": \"http://c.3g.163.com/nc/video/list/VBF8F1PSA/n/\",\n" +
                "    \"url_footer\": \"-10.html\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"黑科技\",\n" +
                "    \"channelType\": 0,\n" +
                "    \"isFix\": 0,\n" +
                "    \"isSubscible\": 1,\n" +
                "    \"url\": \"http://c.3g.163.com/nc/video/list/VBF8F2PKF/n/\",\n" +
                "    \"url_footer\": \"-10.html\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"军武\",\n" +
                "    \"channelType\": 0,\n" +
                "    \"isFix\": 0,\n" +
                "    \"isSubscible\": 1,\n" +
                "    \"url\": \"http://c.3g.163.com/nc/video/list/VBF8F3301/n/\",\n" +
                "    \"url_footer\": \"-10.html\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"涨知识\",\n" +
                "    \"channelType\": 0,\n" +
                "    \"isFix\": 0,\n" +
                "    \"isSubscible\": 1,\n" +
                "    \"url\": \"http://c.3g.163.com/nc/video/list/VBF8F3SGL/n/\",\n" +
                "    \"url_footer\": \"-10.html\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"八卦\",\n" +
                "    \"channelType\": 0,\n" +
                "    \"isFix\": 0,\n" +
                "    \"isSubscible\": 1,\n" +
                "    \"url\": \"http://c.3g.163.com/nc/video/list/VBF8EUDUS/n/\",\n" +
                "    \"url_footer\": \"-10.html\"\n" +
                "  }\n" +
                "]\n" +
                "}\n";
        Gson gson = new Gson();
        VideoBean videoBean = gson.fromJson(titleJson, VideoBean.class);
        data = videoBean.getData();
        for (int i = 0; i < data.size(); i++) {
            FragmentSP fmSP = new FragmentSP();
            fmList.add(fmSP);

            Bundle bundle = new Bundle();
            bundle.putString("url",data.get(i).getUrl());
            bundle.putString("url_footer",data.get(i).getUrl_footer());
            fmSP.setArguments(bundle);
        }

        SPVpAdapter adapter = new SPVpAdapter(getChildFragmentManager());
        main_fm_sp_view_pager.setAdapter(adapter);
        main_fm_sp_tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        main_fm_sp_tab_layout.setTabsFromPagerAdapter(adapter);
        main_fm_sp_tab_layout.setupWithViewPager(main_fm_sp_view_pager);
    }

    class SPVpAdapter extends FragmentPagerAdapter{

        public SPVpAdapter(FragmentManager fm) {
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
            return data.get(position).getTitle();
        }
    }
}
