package com.example.acer.todaynews.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.todaynews.R;
import com.example.acer.todaynews.activity.EditMessageActivity;
import com.example.acer.todaynews.activity.HistoryActivity;
import com.example.acer.todaynews.activity.MainActivity;
import com.example.acer.todaynews.activity.MyCareActivity;
import com.example.acer.todaynews.activity.WdPhoneYzNumActivity;
import com.example.acer.todaynews.view.MineScrollView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import thinkfreely.changemodelibrary.ChangeModeController;


/**
 * Created by acer on 2017/3/16.
 */
public class MainWDFragment extends Fragment {

    private ImageView main_fm_wd_phone_img;
    private ImageView main_fm_wd_qq_img;
    private RadioGroup main_fm_wd_rg;
    private RelativeLayout main_fm_wd_xxtz;
    private RelativeLayout main_fm_wd_ttsc;
    private RelativeLayout main_fm_wd_jdtg;
    private RelativeLayout main_fm_wd_xtsz;
    private RelativeLayout main_fm_wd_yhfk;
    private ImageView main_fm_wd_wx_img;
    private View view;
    private TextView main_fm_wd_lot;
    private LinearLayout main_fm_wd_dengluqian;
    private LinearLayout main_fm_wd_dengluhou;
    private LinearLayout main_fm_wd_dengluhou_;
    private ImageView main_fm_wd_img_pic;
    private TextView main_fm_wd_tx_name;
    private TextView main_fm_wd_dt;
    private TextView main_fm_wd_gz;
    private TextView main_fm_wd_fs;
    private TextView main_fm_wd_fk;
    private SharedPreferences preferences;

    private boolean isNight = true;
    private UMShareAPI mShareAPI;
    private MineScrollView main_fm_wd_scroll_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_fm_wd, null);

        main_fm_wd_scroll_view = (MineScrollView) view.findViewById(R.id.main_fm_wd_scroll_view);

        main_fm_wd_phone_img = (ImageView) view.findViewById(R.id.main_fm_wd_phone_img);
        main_fm_wd_qq_img = (ImageView) view.findViewById(R.id.main_fm_wd_qq_img);
        main_fm_wd_wx_img = (ImageView) view.findViewById(R.id.main_fm_wd_wx_img);
        main_fm_wd_lot = (TextView) view.findViewById(R.id.main_fm_wd_lot);

        main_fm_wd_rg = (RadioGroup) view.findViewById(R.id.main_fm_wd_rg);
        main_fm_wd_xxtz = (RelativeLayout) view.findViewById(R.id.main_fm_wd_xxtz);
        main_fm_wd_ttsc = (RelativeLayout) view.findViewById(R.id.main_fm_wd_ttsc);
        main_fm_wd_jdtg = (RelativeLayout) view.findViewById(R.id.main_fm_wd_jdtg);
        main_fm_wd_xtsz = (RelativeLayout) view.findViewById(R.id.main_fm_wd_xtsz);
        main_fm_wd_yhfk = (RelativeLayout) view.findViewById(R.id.main_fm_wd_yhfk);

        main_fm_wd_dengluqian = (LinearLayout) view.findViewById(R.id.main_fm_wd_dengluqian);
        main_fm_wd_dengluhou = (LinearLayout) view.findViewById(R.id.main_fm_wd_dengluhou);
        main_fm_wd_dengluhou_ = (LinearLayout) view.findViewById(R.id.main_fm_wd_dengluhou_);

        main_fm_wd_img_pic = (ImageView) view.findViewById(R.id.main_fm_wd_img_pic);
        main_fm_wd_tx_name = (TextView) view.findViewById(R.id.main_fm_wd_tx_name);
        main_fm_wd_dt = (TextView) view.findViewById(R.id.main_fm_wd_dt);
        main_fm_wd_gz = (TextView) view.findViewById(R.id.main_fm_wd_gz);
        main_fm_wd_fs = (TextView) view.findViewById(R.id.main_fm_wd_fs);
        main_fm_wd_fk = (TextView) view.findViewById(R.id.main_fm_wd_fk);

        main_fm_wd_scroll_view.setOnScrollListener(new MineScrollView.OnScrollListener() {
            @Override
            public void onScroll(int Y) {

            }

            @Override
            public void offScroll() {

            }
        });

        return view;
    }

    //得到登陆界面存的值，判断登陆状态并得到登陆手机号
    private void getSpValues() {
        preferences = getActivity().getSharedPreferences("config", 0);
        boolean isEnter = preferences.getBoolean("isEnter", false);

        if (isEnter) {
            main_fm_wd_dengluhou.setVisibility(View.VISIBLE);
            main_fm_wd_dengluhou_.setVisibility(View.VISIBLE);
            main_fm_wd_dengluqian.setVisibility(View.GONE);
            main_fm_wd_lot.setVisibility(View.GONE);

            String phoneNum = preferences.getString("phoneNum", null);
            if (phoneNum != null) {
                main_fm_wd_tx_name.setText(phoneNum);
            }
        } else {
            main_fm_wd_dengluhou.setVisibility(View.GONE);
            main_fm_wd_dengluhou_.setVisibility(View.GONE);
            main_fm_wd_dengluqian.setVisibility(View.VISIBLE);
            main_fm_wd_lot.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getSpValues();

        phoneImgListen();

        lotTxListen();

        setRgListen();

        dengLuHouListen();

        main_fm_wd_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditMessageActivity.class);
                startActivity(intent);
                //getActivity().finish();
            }
        });

        main_fm_wd_gz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyCareActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.wd_zhmm_enter_anim, R.anim.wd_zhmm_exit_anim);
                getActivity().finish();
            }
        });

        main_fm_wd_fs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyCareActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.wd_zhmm_enter_anim, R.anim.wd_zhmm_exit_anim);
                getActivity().finish();
            }
        });

        main_fm_wd_fk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyCareActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.wd_zhmm_enter_anim, R.anim.wd_zhmm_exit_anim);
                getActivity().finish();
            }
        });
    }

    private void dengLuHouListen() {
        main_fm_wd_dengluhou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditMessageActivity.class);
                startActivity(intent);
                //getActivity().finish();
            }
        });
    }

    //设置RadioGroup的监听
    private void setRgListen() {
        main_fm_wd_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_fm_wd_rb_sc:
                        Intent intent = new Intent(getActivity(), HistoryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.main_fm_wd_rb_ls:
                        Intent intent1 = new Intent(getActivity(), HistoryActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.main_fm_wd_rb_yj:

                        MainActivity.theme = (MainActivity.theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
                        getActivity().recreate();

                        break;
                }
            }
        });

        main_fm_wd_qq_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShareAPI = UMShareAPI.get(getActivity());
                mShareAPI.doOauthVerify(getActivity(), SHARE_MEDIA.QQ, umAuthListener);
            }
        });
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getActivity().getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            if (action == ACTION_AUTHORIZE) {
                mShareAPI.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, umAuthListener);
            }else if (action == ACTION_GET_PROFILE){
                Log.e("===========",data.toString());
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getActivity().getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getActivity().getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };


    //跳转到手机号登录的页面
    private void phoneImgListen() {
        main_fm_wd_phone_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WdPhoneYzNumActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.wd_pop_enter_anim, R.anim.wd_pop_exit_anim);
                getActivity().finish();
            }
        });
    }

    //更多登录方式的监听
    private void lotTxListen() {
        main_fm_wd_lot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WdPhoneYzNumActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.wd_pop_enter_anim, R.anim.wd_pop_exit_anim);
                getActivity().finish();
            }
        });
    }
}