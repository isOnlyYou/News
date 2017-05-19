package com.example.acer.todaynews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.acer.todaynews.R;
import com.example.acer.todaynews.beans.DetailBean;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by acer on 2017/3/27.
 */
public class DetailActivity extends Activity{

    private WebView detail_web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detail_web_view = (WebView) findViewById(R.id.detail_web_view);

        detail_web_view.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // webview自己加载URL，然后通知系统不需要HandleURL
                view.loadUrl(url);
                return true;
            }
        });

        detail_web_view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && detail_web_view.canGoBack()) {  //表示按返回键

                        detail_web_view.goBack();   //后退

                        //webview.goForward();//前进
                        return true;    //已处理
                    }
                }

                return false;
            }
        });
        detail_web_view.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        int categoryID = intent.getIntExtra("categoryID", 0);
        int articleID = intent.getIntExtra("articleID", 0);

        String url = "http://sight.urundata.com:5004/v1.0.0/Article/GetArticleDetail?CategoryID=" +
                categoryID+"&UserID=864394010080028&ArticleID=" +
                articleID+"&ArticleType=0";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new Gson();
                DetailBean detailBean = gson.fromJson(responseString, DetailBean.class);
                DetailBean.DataBeanX data = detailBean.getData();
                String url1 = data.getUrl();
                detail_web_view.loadUrl(url1);
            }
        });
    }
}
