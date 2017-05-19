package com.example.acer.todaynews.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by acer on 2017/3/17.
 */
public class MineScrollView extends ScrollView {
    public MineScrollView(Context context) {
        super(context);
    }

    public MineScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MineScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //用于记录下拉位置
    private float y = 0f;
    //zoomView原本的宽高,zoomView即需要放大的View，默认为第一个子View
    private int zoomViewWidth = 0;
    private int zoomViewHeight = 0;
    //是否正在放大
    private boolean mScaling = false;

    //放大的view，默认为第一个子view
    private View zoomView;
    public void setZoomView(View zoomView) {
        this.zoomView = zoomView;
    }
    //滑动放大系数，系数越大，滑动时放大程度越大
    private float mScaleRatio = 0.5f;
    public void setmScaleRatio(float mScaleRatio) {
        this.mScaleRatio = mScaleRatio;
    }
    //最大的放大倍数
    private float mScaleTimes = 2f;
    public void setmScaleTimes(int mScaleTimes) {
        this.mScaleTimes = mScaleTimes;
    }
    //回弹时间系数，系数越小，回弹越快
    private float mReplyRatio = 0.3f;
    public void setmReplyRatio(float mReplyRatio) {
        this.mReplyRatio = mReplyRatio;
    }

    //当View中所有的子控件 均被映射成xml后触发，即子View全部加载完调用
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //不可过度滚动，否则上移后下拉会出现部分空白的情况
        setOverScrollMode(OVER_SCROLL_NEVER);
        //获得默认第一个view，getChildAt（）为ViewGroup的方法
        if (getChildAt(0) != null && getChildAt(0) instanceof ViewGroup &&
                zoomView == null) {
            ViewGroup vg = (ViewGroup) getChildAt(0);
            if (vg.getChildCount() > 0) {
                zoomView = vg.getChildAt(0);
            }
        }
    }

    //getMeasuredWidth（）获得全部的宽度，包括隐藏的
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (zoomViewWidth <= 0 || zoomViewHeight <=0) {
            zoomViewWidth = zoomView.getMeasuredWidth();
            zoomViewHeight = zoomView.getMeasuredHeight();
        }
        if (zoomView == null || zoomViewWidth <= 0 || zoomViewHeight <= 0) {
            return super.onTouchEvent(ev);
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //如果正在放大
                if (!mScaling) {
                    //手机屏幕显示区域左上角y坐标减去MultiViewGroup视图左上角y坐标=0
                    // (因为子视图的顶部跟手机屏幕重合一直不变)
                    if (getScrollY() == 0) {
                        //getX()是表示Widget相对于自身左上角的x坐标
                        //getX()和getY()获得的永远是view的触摸位置坐标
                        y = ev.getY();//滑动到顶部时，记录位置，即视图顶部的Y坐标
                    } else {
                        break;
                    }
                }
                int distance = (int) ((ev.getY() - y)*mScaleRatio);
                //滑动回调
                if (onScrollListener!=null){
                    onScrollListener.onScroll(distance);
                }
                if (distance < 0) break;//若往下滑动
                mScaling = true;
                setZoom(distance);
                return true;
            case MotionEvent.ACTION_UP:
                mScaling = false;
                replyView();
                //滑动回调
                if (onScrollListener!=null){
                    onScrollListener.offScroll();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 放大的view
     * @param s
     */
    private void setZoom(float s) {
        float scaleTimes = (float) ((zoomViewWidth+s)/(zoomViewWidth*1.0));
        //如超过最大放大倍数，直接返回
        if (scaleTimes > mScaleTimes) return;

        ViewGroup.LayoutParams layoutParams = zoomView.getLayoutParams();
        layoutParams.width = (int) (zoomViewWidth + s);
        layoutParams.height = (int)(zoomViewHeight*((zoomViewWidth+s)/zoomViewWidth));
        //设置控件水平居中
        ((MarginLayoutParams) layoutParams).setMargins(-(layoutParams.width - zoomViewWidth) / 2, 0, 0, 0);
        zoomView.setLayoutParams(layoutParams);
    }

    /**
     * 回弹
     */
    private void replyView() {
        final float distance = zoomView.getMeasuredWidth() - zoomViewWidth;
        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(distance, 0.0F).setDuration((long) (distance * mReplyRatio));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setZoom((Float) animation.getAnimatedValue());
            }
        });
        anim.start();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    private OnScrollListener onScrollListener;
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    /**
     * 滑动监听
     */
    public  interface OnScrollListener{
        void onScroll(int Y);
        void offScroll();

    }

    /**
     * 滑动与点击事件的冲突处理
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                return false;
            case MotionEvent.ACTION_MOVE:
                return true;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
