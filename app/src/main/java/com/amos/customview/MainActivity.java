package com.amos.customview;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.amos.customview.baserecyclerview.RVActivity;
import com.amos.customview.mdialog.CustomService;
import com.amos.customview.rxjava2.RxJavaMainActivity;

public class MainActivity extends AppCompatActivity {

    private CustomService.CustomBinder mBinder;

    boolean isBind = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinder = (CustomService.CustomBinder) iBinder;
            mBinder.startDownload();
            mBinder.getProgress();
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBind = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnCustomUI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIDemoActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btnStartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bindIntent = new Intent(MainActivity.this, CustomService.class);
                bindService(bindIntent, mConnection, BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.btnStopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBind) {
                    unbindService(mConnection);
                }
            }
        });
        findViewById(R.id.btnRv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RVActivity.startActivity(MainActivity.this);
            }
        });
        findViewById(R.id.btnRxJavaMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxJavaMainActivity.startActivity(MainActivity.this);
            }
        });
    }


}
