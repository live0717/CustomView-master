package com.amos.customview.rxjava2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Project CustomView-master
 * Created by Amos
 * Created on 2017-10-23
 * Desc
 */

public class CreateOperatorActivity extends AppCompatActivity {
    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, CreateOperatorActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
