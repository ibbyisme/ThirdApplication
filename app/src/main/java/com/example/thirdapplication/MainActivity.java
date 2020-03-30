package com.example.thirdapplication;
//AppCompatActivity
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button button_do,button_not,button_get;
    BindService.MyBinder binder;
    private ServiceConnection conn=new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service){
            binder=(BindService.MyBinder) service;
        }
        public void onServiceDisconnected(ComponentName name)
        {
            System.out.println("Severce Disconnection");
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_do=(Button)findViewById(R.id.button1);
        button_not=(Button)findViewById(R.id.button2);
        button_get=(Button)findViewById(R.id.button3);
        final Intent intent=new Intent(this,BindService.class);
        button_do.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                bindService(intent,conn, Service.BIND_AUTO_CREATE);
            }
        });
        button_not.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                unbindService(conn);
            }
        });
        button_get.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this,"Service的值为"+binder.getCount(),Toast.LENGTH_LONG).show();
            }
        });

    }

}
