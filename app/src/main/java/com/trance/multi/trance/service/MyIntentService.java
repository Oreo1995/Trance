package com.trance.multi.trance.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";
    public MyIntentService(){
        super("MyIntentService");
    }
    
    @Override
    protected void onHandleIntent(Intent intent) {
        //print current thread id
        Log.d(TAG, "Thread id is "+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: executed");
    }
}
