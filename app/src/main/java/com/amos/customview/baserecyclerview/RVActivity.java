package com.amos.customview.baserecyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.amos.customview.R;
import com.amos.customview.baserecyclerview.refreshandmore.RefreshMoreActivity;

/**
 * Project CustomView-master
 * Created by Amos
 * Created on 2017-09-29
 * Desc
 */

public class RVActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RVActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        findViewById(R.id.btnMoreRefresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RefreshMoreActivity.startActivity(RVActivity.this);
            }
        });
    }
}
