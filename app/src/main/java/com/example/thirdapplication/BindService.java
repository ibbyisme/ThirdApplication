package com.example.thirdapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BindService extends Service {
    private int count;
    private boolean quit;
    private MyBinder binder=new MyBinder();
    public class MyBinder extends Binder {
        public int getCount(){
            return count;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    public void onCreate()
    {
        super.onCreate();
        new Thread(){
            public void run(){
                while(!quit)
                {
                    try{
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e){}
                    count++;
                }
            }
        }.start();
    }
    public boolean onUnBind(Intent intent){
        return true;
    }
    public void onDestroy(){
        super.onDestroy();
        this.quit=true;
    }
}
