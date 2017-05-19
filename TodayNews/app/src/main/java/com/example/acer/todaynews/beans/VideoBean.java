package com.example.acer.todaynews.beans;

import java.util.List;

/**
 * Created by acer on 2017/3/28.
 */
public class VideoBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 娱乐
         * channelType : 0
         * isFix : 1
         * isSubscible : 1
         * url : http://c.3g.163.com/nc/video/list/V9LG4CHOR/n/
         * url_footer : -10.html
         */

        private String title;
        private int channelType;
        private int isFix;
        private int isSubscible;
        private String url;
        private String url_footer;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getChannelType() {
            return channelType;
        }

        public void setChannelType(int channelType) {
            this.channelType = channelType;
        }

        public int getIsFix() {
            return isFix;
        }

        public void setIsFix(int isFix) {
            this.isFix = isFix;
        }

        public int getIsSubscible() {
            return isSubscible;
        }

        public void setIsSubscible(int isSubscible) {
            this.isSubscible = isSubscible;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl_footer() {
            return url_footer;
        }

        public void setUrl_footer(String url_footer) {
            this.url_footer = url_footer;
        }
    }
}
