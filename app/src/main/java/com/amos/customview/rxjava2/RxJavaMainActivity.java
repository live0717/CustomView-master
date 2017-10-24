package com.amos.customview.rxjava2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.amos.customview.R;

/**
 * Project CustomView-master
 * Created by Amos
 * Created on 2017-10-20
 * Desc
 */

public class RxJavaMainActivity extends AppCompatActivity {
    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RxJavaMainActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_main);
        findViewById(R.id.btnRxJava).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxJavaDemoActivity.startActivity(RxJavaMainActivity.this);
            }
        });
        findViewById(R.id.btnOperators).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConversionOperatorActivity.startActivity(RxJavaMainActivity.this);
            }
        });
        findViewById(R.id.btnFilter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterOperatorActivity.startActivity(RxJavaMainActivity.this);
            }
        });
        findViewById(R.id.btnCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateOperatorActivity.startActivity(RxJavaMainActivity.this);
            }
        });
        findViewById(R.id.btnOtherUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OtherOperatorActivity.startActivity(RxJavaMainActivity.this);
            }
        });
    }
}
