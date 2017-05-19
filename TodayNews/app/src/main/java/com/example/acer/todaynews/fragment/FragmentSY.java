package com.example.acer.todaynews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.acer.todaynews.R;
import com.example.acer.todaynews.activity.DetailActivity;
import com.example.acer.todaynews.beans.SyNewsBean;
import com.example.acer.todaynews.utils.MySyListAdapter;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.MeituanFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by acer on 2017/3/25.
 */
public class FragmentSY extends Fragment {

    private SpringView fm_sy_spring_view;
    private ListView fm_sy_list_view;

    private int pageIndex = 1;
    private String jsonUrl;
    private String jsonUrlA;
    private String jsonUrlB;

    private List<SyNewsBean.DataBean> totalList = new ArrayList<>();
    private MySyListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_sy, null);
        fm_sy_spring_view = (SpringView) view.findViewById(R.id.fm_sy_spring_view);
        fm_sy_list_view = (ListView) view.findViewById(R.id.fm_sy_list_view);

        fm_sy_spring_view.setType(SpringView.Type.FOLLOW);
        fm_sy_spring_view.setHeader(new MeituanHeader(getActivity()));
        fm_sy_spring_view.setFooter(new MeituanFooter(getActivity()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置下拉刷新和上拉加载
        setSVFrashLoad();

        Bundle arguments = getArguments();
        jsonUrlA = arguments.getString("jsonUrlA");
        jsonUrlB = arguments.getString("jsonUrlB");

        jsonUrl = jsonUrlA +pageIndex+ jsonUrlB;

        getHttpData(jsonUrl);

        listItemClick();
    }

    private void getHttpData(String jsonUrl) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getActivity(), jsonUrl, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new Gson();
                SyNewsBean syNewsBean = gson.fromJson(responseString, SyNewsBean.class);
                List<SyNewsBean.DataBean> data = syNewsBean.getData();
                totalList.addAll(data);
                if (totalList != null && !totalList.isEmpty()){

                    if (adapter == null){
                        adapter = new MySyListAdapter(totalList,getActivity());
                        fm_sy_list_view.setAdapter(adapter);
                    }else {
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter = null;
    }

    private void setSVFrashLoad() {
        fm_sy_spring_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        totalList.clear();
                        getHttpData(jsonUrl);
                        Toast.makeText(getActivity(), "刷新数据啦~~~~", Toast.LENGTH_SHORT).show();
                        fm_sy_spring_view.onFinishFreshAndLoad();
                    }
                },2000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageIndex++;
                        jsonUrl = jsonUrlA +pageIndex+ jsonUrlB;
                        getHttpData(jsonUrl);
                        Toast.makeText(getActivity(), "加载更多内容", Toast.LENGTH_SHORT).show();
                        fm_sy_spring_view.onFinishFreshAndLoad();
                    }
                },2000);
            }
        });
    }

    private void listItemClick(){
        fm_sy_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int categoryID = totalList.get(position).getCategoryID();
                int articleID = totalList.get(position).getId();
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                intent.putExtra("categoryID",categoryID);
                intent.putExtra("articleID",articleID);
                startActivity(intent);
            }
        });
    }
}
