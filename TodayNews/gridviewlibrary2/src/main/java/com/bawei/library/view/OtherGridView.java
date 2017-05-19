package com.bawei.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 1. 类的用途
 * 2. @author forever
 * 3. @date 2017/3/20 23:16
 */

public class OtherGridView extends GridView {

    public OtherGridView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}