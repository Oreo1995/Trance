package com.trance.multi.trance.download;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.trance.multi.trance.R;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "DownloadActivity";
    int NOTIFICATION_ID = 2;
    Button start,pause,cancel,notify;
    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        start = findViewById(R.id.start_download);
        pause = findViewById(R.id.pause_download);
        cancel = findViewById(R.id.cancel_download);
        notify = findViewById(R.id.notify);

        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        cancel.setOnClickListener(this);
        notify.setOnClickListener(this);

        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);
        if(ContextCompat.checkSelfPermission(this, Manifest.
                permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    @Override
    public void onClick(View v) {

        if(downloadBinder == null){
            return;
        }

        switch (v.getId()) {
            case R.id.start_download:
//                String url = "https://raw.githubsercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
                String url = "https://1.na.dl.wireshark.org/win64/Wireshark-win64-2.6.1.exe";
                downloadBinder.startDownload(url);
                break;

            case R.id.pause_download:
                downloadBinder.pauseDownload();
                break;

            case R.id.cancel_download:
                downloadBinder.cancelDownload();
                Log.d(TAG, "onCancelClick: ");
                break;

            case R.id.notify:
                getNotificationManager().notify(NOTIFICATION_ID,getNotification("你好","今天过得怎么样"));
                break;

            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"拒绝权限将无法使用应用程序",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, String content){
        Intent intent = new Intent(this, DownloadActivity.class);

        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);

        NotificationCompat.Builder builder;
        if(Build.VERSION.SDK_INT >= 26){
            NotificationChannel nc = new NotificationChannel(String.valueOf(NOTIFICATION_ID),
                    DownloadService.class.getName(),NotificationManager.IMPORTANCE_DEFAULT);
            getNotificationManager().createNotificationChannel(nc);
            builder = new NotificationCompat.Builder(this,String.valueOf(NOTIFICATION_ID));
            builder.setSmallIcon(R.mipmap.ic_launcher_round)
//                    .setChannelId(String.valueOf(NOTIFICATION_ID))
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round))
                    .setContentIntent(pi)
                    .setContentTitle(title)
                    .setContentText(content);
            return builder.build();
        }
        return null;
    }
}
