package com.trance.multi.trance;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.trance.multi.trance.ViewPager.FragmentPagerAdapterActivity;
import com.trance.multi.trance.customview.CustomView1;
import com.trance.multi.trance.download.DownloadActivity;
import com.trance.multi.trance.listview.ListViewCollect;
import com.trance.multi.trance.recyclerview.ProgressDialogActivity;
import com.trance.multi.trance.recyclerview.RecyclerviewActivity;
import com.trance.multi.trance.service.ServiceActivity;
import com.trance.multi.trance.test.HandlerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.download)
    Button download;
    @BindView(R.id.handler)
    Button handler;
    @BindView(R.id.myservice)
    Button myservice;
    @BindView(R.id.view_pager)
    Button view_pager;
    @BindView(R.id.view_pager2)
    Button view_page2;
    @BindView(R.id.listcollect)
    Button listcoll;
    @BindView(R.id.custom_view)
    Button custom_view;
    @BindView(R.id.progress_dialog)
    Button progress_dialog;
    @BindView(R.id.recyclerview_refresh)
    Button recycler_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /**
         * 状态栏透明，界面背景颜色延伸至状态栏
         */
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }


        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        String a = "xibala的";
        String b = "hello";
        int c = 5;
        boolean d = false;


        download.setOnClickListener(v->{
            Intent intent = new Intent(this, DownloadActivity.class);
            startActivity(intent);
        });

        handler.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,HandlerActivity.class);
            startActivity(intent);
        });

        myservice.setOnClickListener(v->{
            Intent intent = new Intent(this, ServiceActivity.class);
            startActivity(intent);
        });



//        view_pager.setOnClickListener(v->{
//            Intent intent = new Intent(this,ViewPagerActivity.class);
//            startActivity(intent);
//        });

        view_page2.setOnClickListener(v->{
            Intent intent = new Intent(this, FragmentPagerAdapterActivity.class);
            startActivity(intent);
        });

        listcoll.setOnClickListener(v->{
            Intent intent = new Intent(this, ListViewCollect.class);
            startActivity(intent);
        });

        custom_view.setOnClickListener(v->{
            Intent intent = new Intent(this, CustomView1.class);
            startActivity(intent);
        });

        progress_dialog.setOnClickListener(v->{
            Intent intent = new Intent(this, ProgressDialogActivity.class);
            startActivity(intent);
        });

        recycler_refresh.setOnClickListener(v->{
            Intent intent = new Intent(this, RecyclerviewActivity.class);
            startActivity(intent);
        });
    }

    public void setCls(Class<?> cls) {
        cls = MainActivity.class;
    }

    public void setData(){
        String a = "naive";
    }

    @Override
    public void onClick(View v) {

    }
}
