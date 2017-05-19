package com.bawei.library.tools;

import android.content.Context;

/**
 * 1. 类的用途
 * 2. @author forever
 * 3. @date 2017/3/20 23:15
 */

public class DataTools {
    /**
     * dipתΪ px
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     *  px תΪ dip
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}

