package com.amos.customview.baserecyclerview.refreshandmore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.amos.customview.R;

/**
 * Project CustomView-master
 * Created by Amos
 * Created on 2017-10-09
 * Desc
 */

public class RefreshMoreActivity extends AppCompatActivity {

    RecyclerView mRvRefreshMore;



    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RefreshMoreActivity.class));
    }

    private void initData(){
        for (int i=0;i<10;i++){

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_more);
        mRvRefreshMore = (RecyclerView) findViewById(R.id.rvRefreshMore);
    }

}
