package com.trance.multi.trance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.trance.multi.trance.ViewPager.FragmentPagerAdapterActivity;
import com.trance.multi.trance.customview.CustomView1;
import com.trance.multi.trance.download.DownloadActivity;
import com.trance.multi.trance.listview.ListViewCollect;
import com.trance.multi.trance.recyclerview.ProgressDialogActivity;
import com.trance.multi.trance.recyclerview.RecyclerviewActivity;
import com.trance.multi.trance.service.ServiceActivity;
import com.trance.multi.trance.test.HandlerActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String a = "xibala的";
        String b = "hello";
        Button download = findViewById(R.id.download);
        Button handler = findViewById(R.id.handler);
        Button myservice = findViewById(R.id.myservice);
        Button view_pager = findViewById(R.id.view_pager);
        int c = 5;
        boolean d = false;
        Button view_page2 = findViewById(R.id.view_pager2);
        Button listcoll = findViewById(R.id.listcollect);
        Button custom_view = findViewById(R.id.custom_view);
        Button progress_dialog = findViewById(R.id.progress_dialog);
        Button recycler_refresh = findViewById(R.id.recyclerview_refresh);

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
}
