package com.amos.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CustomArcActivity extends AppCompatActivity {


    private ArcProgressBar mArcProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_arc);

        mArcProgress= (ArcProgressBar) findViewById(R.id.arc);
        mArcProgress.setMaxProgress(500);
        mArcProgress.setProgress(300);
        mArcProgress.setFirstText("45");
        mArcProgress.setSecondText("轻度污染");
        mArcProgress.setSecondTextSize(40f);
    }
}
