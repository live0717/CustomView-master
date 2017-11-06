package com.amos.customview.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amos.customview.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author : Amos
 * @Date : 2017-11-06
 */

public class EventBusActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, EventBusActivity.class));
    }

    private TextView tvEventBus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);

        tvEventBus = (TextView) findViewById(R.id.tvEventBus);

        EventBus.getDefault().register(this);
        findViewById(R.id.btnStartNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestActivity.startActivity(EventBusActivity.this);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        String message = messageEvent.getMessage();
        if (tvEventBus != null) {
            tvEventBus.setText(message);
        }
        Toast.makeText(EventBusActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
