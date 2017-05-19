package com.bawei.library.dao;

import android.content.ContentValues;

import com.bawei.library.bean.ChannelItem;

import java.util.List;
import java.util.Map;

/**
 * 1. 类的用途
 * 2. @author forever
 * 3. @date 2017/3/20 23:13
 */
public interface ChannelDaoInface {
    public boolean addCache(ChannelItem item);

    public boolean deleteCache(String whereClause, String[] whereArgs);

    public boolean updateCache(ContentValues values, String whereClause,
                               String[] whereArgs);

    public Map<String, String> viewCache(String selection,
                                         String[] selectionArgs);

    public List<Map<String, String>> listCache(String selection,
                                               String[] selectionArgs);

    public void clearFeedTable();
}

