package com.amos.customview.mdialog;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Amos
 * Created on 2017年09月19.
 * Desc :
 */

public class CustomService extends Service {

    CustomBinder mBinder=new CustomBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

   public class CustomBinder extends Binder {
        public void startDownload() {
            Log.d("TAG", "startDownload() executed");
            // 执行具体的下载任务
        }

        public int getProgress() {
            Log.d("TAG", "getProgress() executed");
            return 0;
        }
    }
}
