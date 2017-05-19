package com.example.acer.todaynews.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.acer.todaynews.R;

/**
 * Created by acer on 2017/3/17.
 */
public class MyPopWindow {

    /*public void setPopWindow(View v, Activity activity){
        // 获得View
        View view = View.inflate(activity,R.layout.wd_pop_window, null);

        DisplayMetrics metrics = new DisplayMetrics();

        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels = metrics.widthPixels;

        int heightPixels = (metrics.heightPixels*9)/10;

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                heightPixels);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);


        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.wd_pop_anim);
        // 在底部显示
        window.showAtLocation(v,0,0,heightPixels/10);
    }*/
}
