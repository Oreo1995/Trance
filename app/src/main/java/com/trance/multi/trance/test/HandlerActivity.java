package com.trance.multi.trance.test;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.trance.multi.trance.R;

public class HandlerActivity extends AppCompatActivity {
    final int UPDATE_TEXT = 1;
    static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        Button change = findViewById(R.id.change);
        TextView tv = findViewById(R.id.tv);

        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case UPDATE_TEXT:
                        tv.setText("Hello + "+i);
                        break;
                    default:
                        break;
                }
            }
        };

        change.setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = UPDATE_TEXT;
                    handler.sendMessage(message);
                    i++;
                }
            }).start();
        });
    }
}
