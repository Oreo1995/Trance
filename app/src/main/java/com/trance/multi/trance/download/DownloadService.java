package com.trance.multi.trance.download;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.trance.multi.trance.MainActivity;
import com.trance.multi.trance.R;

import java.io.File;

public class DownloadService extends Service {

    private DownloadTask downloadTask;
    private String downloadUrl;
    final int NOTIFICATION_ID = 1;
    private static final String TAG = "DownloadService";

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1,getNotification("Downloading...",progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Dwonload Success",-1));
            Toast.makeText(DownloadService.this,"Download Success",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Failed",-1));
            Toast.makeText(DownloadService.this,"Download Failed",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this,"Download Paused",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            Log.d(TAG, "onCanceled: DS");
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this,"Download Canceled",Toast.LENGTH_SHORT).show();
        }
    };

    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class  DownloadBinder extends Binder{
        public void startDownload(String url){
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                startForeground(NOTIFICATION_ID,getNotification("Downloading...",0));
                Toast.makeText(DownloadService.this,"Download...",Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload(){
            if (downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload() {
            Log.d(TAG, "cancelDownload: ");
            if (downloadTask != null) {
                Log.d(TAG, "cancelDownload: dt != null");
                downloadTask.cancelDownload();
            } else{
                Log.d(TAG, "cancelDownload: dt == null");
                if(downloadUrl != null){
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory+fileName);
                    if(file.exists()){
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this,"Download Canceled",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title,int progress){
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
                    .setContentTitle(title);
            if(progress >= 0){
                //当progress大于或等于0时才需显示下载进度
                builder.setContentText(progress + "%");
                builder.setProgress(100,progress,false);
            }
            return builder.build();
        }else {
            builder = new NotificationCompat.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher_round)
//                    .setChannelId(String.valueOf(NOTIFICATION_ID))
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                    .setContentIntent(pi)
                    .setContentTitle(title);
            if (progress >= 0) {
                //当progress大于或等于0时才需显示下载进度
                builder.setContentText(progress + "%");
                builder.setProgress(100, progress, false);
            }
            return builder.build();
        }
//        return null;
    }

}
