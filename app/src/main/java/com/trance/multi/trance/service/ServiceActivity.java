package com.trance.multi.trance.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.trance.multi.trance.MainActivity;
import com.trance.multi.trance.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener{

    private MyService.DownLoadBinder downLoadBinder;
    private static final String TAG = "ServiceActivity";
    Button start,stop,bind,unbind,startIntentService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downLoadBinder = (MyService.DownLoadBinder)service;
            downLoadBinder.startDownload();
            downLoadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        start = findViewById(R.id.start_service);
        stop = findViewById(R.id.stop_service);
        bind = findViewById(R.id.bind_service);
        unbind = findViewById(R.id.unbind_service);
        startIntentService = findViewById(R.id.start_intent_service);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent = new Intent(this,MyService.class);
                startService(startIntent);
                break;

            case R.id.stop_service:
                Intent stopIntent = new Intent(this,MyService.class);
                stopService(stopIntent);
                break;

            case R.id.bind_service:
                Intent bindIntent = new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;

            case R.id.unbind_service:
                unbindService(connection);
                break;

            case R.id.start_intent_service:
                Log.d(TAG, "Main thread id is "+Thread.currentThread().getId());
                Intent intentService = new Intent(this,MyIntentService.class);
                startService(intentService);
                break;

            default:
                break;
        }
    }
}
