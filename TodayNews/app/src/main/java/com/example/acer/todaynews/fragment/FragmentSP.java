package com.example.acer.todaynews.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.acer.todaynews.R;
import com.example.acer.todaynews.beans.VideoMsg;
import com.example.acer.todaynews.utils.MySpListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liaoinstan.springview.container.MeituanFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import cz.msebera.android.httpclient.Header;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by acer on 2017/3/27.
 */
public class FragmentSP extends Fragment {

    private SpringView fm_sp_spring_view;
    private ListView fm_sp_list_view;

    private int item = 0;
    private String jsonUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_sp, null);

        fm_sp_spring_view = (SpringView) view.findViewById(R.id.fm_sp_spring_view);
        fm_sp_list_view = (ListView) view.findViewById(R.id.fm_sp_list_view);

        fm_sp_spring_view.setType(SpringView.Type.FOLLOW);
        fm_sp_spring_view.setHeader(new MeituanHeader(getActivity()));
        fm_sp_spring_view.setFooter(new MeituanFooter(getActivity()));

        Bundle arguments = getArguments();
        String url = arguments.getString("url");
        String url_footer = arguments.getString("url_footer");
        jsonUrl = url+ item + url_footer;

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getActivity(), jsonUrl, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    JSONObject object = new JSONObject(responseString);
                    Iterator<String> keys = object.keys();
                    while(keys.hasNext()){
                        String next = keys.next();
                        String json = object.getString(next);
                        Gson gson = new Gson();
                        ArrayList<VideoMsg.V9LG4CHORBean> list = gson.fromJson(json, new TypeToken<ArrayList<VideoMsg.V9LG4CHORBean>>(){}.getType());
                        Log.e("+++++++++",list.size()+"");
                        fm_sp_list_view.setAdapter(new MySpListAdapter(list,getActivity()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setSVFrashLoad() {
        fm_sp_spring_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "刷新数据啦~~~~", Toast.LENGTH_SHORT).show();
                        fm_sp_spring_view.onFinishFreshAndLoad();
                    }
                },2000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "加载更多内容", Toast.LENGTH_SHORT).show();
                        fm_sp_spring_view.onFinishFreshAndLoad();
                    }
                },2000);
            }
        });
    }
}
