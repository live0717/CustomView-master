package com.amos.customview.df;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.amos.customview.R;

/**
 * Created by Amos on 2017/8/23.
 */

public class DFActivity extends AppCompatActivity {

    MyDialogFragment mDialogFragment = new MyDialogFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_df);

        findViewById(R.id.btnDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogFragment.show(getSupportFragmentManager(), "dialog");
            }
        });
    }


}
