package com.trance.multi.trance.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.trance.multi.trance.R;

public class ListViewCollect extends AppCompatActivity {
    Button bt1,bt2,bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_collect);
        initView();
        initListener();
    }

    public void initView(){
        bt1 = findViewById(R.id.simple);
        bt2 = findViewById(R.id.custom);
        bt3 = findViewById(R.id.convert);
    }

    public void initListener(){
        bt1.setOnClickListener(v->{
            Intent intent = new Intent(this,SimpleListView.class);
            startActivity(intent);
        });

        bt2.setOnClickListener(v->{
            Intent intent = new Intent(this, CustomListView.class);
            startActivity(intent);
        });

        bt3.setOnClickListener(v->{
            Intent intent = new Intent(this, ConvertList.class);
            startActivity(intent);
        });
    }


}
