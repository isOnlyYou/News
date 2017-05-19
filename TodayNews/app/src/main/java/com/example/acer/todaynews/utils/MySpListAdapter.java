package com.example.acer.todaynews.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.todaynews.R;
import com.example.acer.todaynews.activity.MainActivity;
import com.example.acer.todaynews.beans.VideoMsg;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by acer on 2017/3/28.
 */
public class MySpListAdapter extends BaseAdapter {

    private List<VideoMsg.V9LG4CHORBean> list;
    private Context context;

    public MySpListAdapter(List<VideoMsg.V9LG4CHORBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.sp_item_video,null);

            holder.sp_item_tx_title = (TextView) convertView.findViewById(R.id.sp_item_tx_title);
            holder.sp_item_tx_count = (TextView) convertView.findViewById(R.id.sp_item_tx_count);
            holder.sp_item_video_player = (JCVideoPlayerStandard) convertView.findViewById(R.id.sp_item_video_player);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.sp_item_tx_title.setText(list.get(position).getTitle());
        holder.sp_item_tx_count.setText(list.get(position).getPlayCount()+" 次播放点击");

        Log.e("============",list.get(position).getMp4_url());

        boolean setUp = holder.sp_item_video_player.setUp(
                list.get(position).getMp4_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        if (setUp) {
            Glide.with(context).load(list.get(position).getCover())
                    .into(holder.sp_item_video_player.thumbImageView);
        }

        return convertView;
    }

    class ViewHolder{
        TextView sp_item_tx_title;
        JCVideoPlayerStandard sp_item_video_player;
        TextView sp_item_tx_count;
    }
}
