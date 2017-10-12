package com.amos.customview.reader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amos.customview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amos on 2017/9/11.
 */

public class MainReaderActivity extends AppCompatActivity {
    ScanView scanview;
    ScanViewAdapter adapter;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MainReaderActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        scanview = (ScanView) findViewById(R.id.scanview);
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 50; i++)
            items.add("第 " + (i + 1) + " 页");
        adapter = new ScanViewAdapter(this, items);
        scanview.setAdapter(adapter);
    }


}
