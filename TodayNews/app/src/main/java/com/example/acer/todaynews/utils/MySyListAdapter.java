package com.example.acer.todaynews.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.todaynews.R;
import com.example.acer.todaynews.beans.SyNewsBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by acer on 2017/3/27.
 */
public class MySyListAdapter extends BaseAdapter {

    private List<SyNewsBean.DataBean> list;
    private Context context;

    private static final int TYPE_0 = 0;
    private static final int TYPE_1 = 1;
    private static final int TYPE_2 = 2;
    private static final int TYPE_3 = 3;

    public MySyListAdapter(List<SyNewsBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getImageType() == 0){
            return TYPE_0;
        }else if(list.get(position).getImageType() == 2){
            return TYPE_3;
        }else {
            if (list.get(position).getThumbnailImage().contains("|")){
                return TYPE_2;
            }else {
                return TYPE_1;
            }
        }
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
        ViewHolder0 holder0 = null;
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        ViewHolder3 holder3 = null;

        int type = getItemViewType(position);

        if (convertView == null){
            if (type == TYPE_0){
                holder0 = new ViewHolder0();

                convertView = View.inflate(context, R.layout.sy_item_no_pic,null);

                holder0.sy_item_no_pic_tx_title = (TextView) convertView.findViewById(R.id.sy_item_no_pic_tx_title);
                holder0.sy_item_no_pic_tx_word = (TextView) convertView.findViewById(R.id.sy_item_no_pic_tx_word);

                convertView.setTag(holder0);
            } else if (type == TYPE_1){
                holder1 = new ViewHolder1();

                convertView = View.inflate(context,R.layout.sy_item_one_pic,null);

                holder1.sy_item_one_pic_img = (ImageView) convertView.findViewById(R.id.sy_item_one_pic_img);
                holder1.sy_item_one_pic_tx_title = (TextView) convertView.findViewById(R.id.sy_item_one_pic_tx_title);
                holder1.sy_item_one_pic_tx_word = (TextView) convertView.findViewById(R.id.sy_item_one_pic_tx_word);

                convertView.setTag(holder1);
            }else if (type == TYPE_2){
                holder2 = new ViewHolder2();

                convertView = View.inflate(context,R.layout.sy_item_two_pic,null);

                holder2.sy_item_two_pic_img_01 = (ImageView) convertView.findViewById(R.id.sy_item_two_pic_img_01);
                holder2.sy_item_two_pic_img_02 = (ImageView) convertView.findViewById(R.id.sy_item_two_pic_img_02);
                holder2.sy_item_two_pic_tx = (TextView) convertView.findViewById(R.id.sy_item_two_pic_tx);

                convertView.setTag(holder2);
            }else if (type == TYPE_3){
                holder3 = new ViewHolder3();

                convertView = View.inflate(context,R.layout.sy_item_three_pic,null);

                holder3.sy_item_three_pic_tx01 = (TextView) convertView.findViewById(R.id.sy_item_three_pic_tx01);
                holder3.sy_item_three_pic_tx02 = (TextView) convertView.findViewById(R.id.sy_item_three_pic_tx02);
                holder3.sy_item_three_pic_img01 = (ImageView) convertView.findViewById(R.id.sy_item_three_pic_img01);
                holder3.sy_item_three_pic_img02 = (ImageView) convertView.findViewById(R.id.sy_item_three_pic_img02);
                holder3.sy_item_three_pic_img03 = (ImageView) convertView.findViewById(R.id.sy_item_three_pic_img03);

                convertView.setTag(holder3);
            }
        }else {
            switch (type){
                case TYPE_0:
                    holder0 = (ViewHolder0) convertView.getTag();
                    break;
                case TYPE_1:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
                case TYPE_2:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
                case TYPE_3:
                    holder3 = (ViewHolder3) convertView.getTag();
                    break;
            }
        }

        switch (type){
            case TYPE_0:
                holder0.sy_item_no_pic_tx_title.setText(list.get(position).getTitle());
                holder0.sy_item_no_pic_tx_word.setText(list.get(position).getTitleKeyWord());
                break;
            case TYPE_1:
                holder1.sy_item_one_pic_tx_title.setText(list.get(position).getTitle());
                holder1.sy_item_one_pic_tx_word.setText(list.get(position).getTitleKeyWord());
                ImageLoader.getInstance().displayImage(list.get(position).getThumbnailImage(),
                        holder1.sy_item_one_pic_img,ImageLoaderUtil.getOptions());
                break;
            case TYPE_2:
                holder2.sy_item_two_pic_tx.setText(list.get(position).getTitle()+"\r\n"+list.get(position).getTitleKeyWord());
                String[] split = list.get(position).getThumbnailImage().split("\\|");
                ImageLoader.getInstance().displayImage(split[0],holder2.sy_item_two_pic_img_01,
                        ImageLoaderUtil.getOptions());
                ImageLoader.getInstance().displayImage(split[1],holder2.sy_item_two_pic_img_02,
                        ImageLoaderUtil.getOptions());
                break;
            case TYPE_3:
                holder3.sy_item_three_pic_tx01.setText(list.get(position).getTitle());
                holder3.sy_item_three_pic_tx02.setText(list.get(position).getTitleKeyWord());
                String[] split1 = list.get(position).getThumbnailImage().split("\\|");
                ImageLoader.getInstance().displayImage(split1[0],holder3.sy_item_three_pic_img01,
                        ImageLoaderUtil.getOptions());
                ImageLoader.getInstance().displayImage(split1[1],holder3.sy_item_three_pic_img02,
                        ImageLoaderUtil.getOptions());
                ImageLoader.getInstance().displayImage(split1[2],holder3.sy_item_three_pic_img03,
                        ImageLoaderUtil.getOptions());
                break;
        }

        return convertView;
    }

    class ViewHolder0{
        TextView sy_item_no_pic_tx_title;
        TextView sy_item_no_pic_tx_word;
    }
    class ViewHolder1{
        TextView sy_item_one_pic_tx_title;
        TextView sy_item_one_pic_tx_word;
        ImageView sy_item_one_pic_img;
    }
    class ViewHolder2{
        ImageView sy_item_two_pic_img_01;
        ImageView sy_item_two_pic_img_02;
        TextView sy_item_two_pic_tx;
    }
    class ViewHolder3{
        TextView sy_item_three_pic_tx01;
        TextView sy_item_three_pic_tx02;
        ImageView sy_item_three_pic_img01;
        ImageView sy_item_three_pic_img02;
        ImageView sy_item_three_pic_img03;
    }
}
