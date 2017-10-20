package com.amos.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.amos.customview.bookread.BookReaderActivity;
import com.amos.customview.df.DFActivity;
import com.amos.customview.reader.MainReaderActivity;

/**
 * Project CustomView-master
 * Created by Amos
 * Created on 2017-10-20
 * Desc
 */

public class UIDemoActivity extends AppCompatActivity {
    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, UIDemoActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        findViewById(R.id.btnArcProgress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UIDemoActivity.this, CustomArcActivity.class));
            }
        });
        findViewById(R.id.rvMultipleItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UIDemoActivity.this, MultipleActivity.class));
            }
        });
        findViewById(R.id.rvCollapsing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UIDemoActivity.this, CollapseActivity.class));
            }
        });
        findViewById(R.id.btnDF).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UIDemoActivity.this, DFActivity.class));
            }
        });
        findViewById(R.id.btnBTS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UIDemoActivity.this, BottomDialogActivity.class));
            }
        });
        findViewById(R.id.btnReader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainReaderActivity.startActivity(UIDemoActivity.this);
            }
        });
        findViewById(R.id.btnBookReader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookReaderActivity.startActivity(UIDemoActivity.this);
            }
        });
    }
}
