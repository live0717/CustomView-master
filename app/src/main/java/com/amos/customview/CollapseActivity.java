package com.amos.customview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

/**
 * Created by Amos on 2017/8/21.
 */

public class CollapseActivity extends AppCompatActivity {

    RecyclerView rvCol;
    private CollapseAdapter mAdapter;
    LinearLayout llHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse);
        rvCol = (RecyclerView) findViewById(R.id.rvCol);
        llHome = (LinearLayout) findViewById(R.id.llHome);

        mAdapter = new CollapseAdapter(this);
        rvCol.setHasFixedSize(true);
        rvCol.setLayoutManager(new LinearLayoutManager(this));
        rvCol.setAdapter(mAdapter);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg);
        Bitmap blur = BlurBitmap.blur(this, bitmap);
        llHome.setBackground(new BitmapDrawable(blur));

    }


}
