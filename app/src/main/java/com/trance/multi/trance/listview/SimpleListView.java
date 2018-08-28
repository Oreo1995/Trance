package com.trance.multi.trance.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.trance.multi.trance.R;

import java.util.ArrayList;

public class SimpleListView extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list_view);
        listView = findViewById(R.id.list_view);
        ArrayList<String> list = new ArrayList();
        list.add("Asia");
        list.add("Europe");
        list.add("Austrolia");
        list.add("China");
        list.add("Hello");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(SimpleListView.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }
}
