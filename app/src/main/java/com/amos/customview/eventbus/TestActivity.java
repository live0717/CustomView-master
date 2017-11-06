package com.amos.customview.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.amos.customview.R;

import org.greenrobot.eventbus.EventBus;

/**
 * @author : Amos
 * @Date : 2017-11-06
 */

public class TestActivity extends AppCompatActivity {
    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, TestActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.btnUpdateAndBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("更新第一个activity的信息"));
                finish();
            }
        });
    }
}
