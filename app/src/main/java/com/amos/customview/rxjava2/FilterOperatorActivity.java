package com.amos.customview.rxjava2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.amos.customview.R;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Project CustomView-master
 * Created by Amos
 * Created on 2017-10-20
 * Desc
 */

public class FilterOperatorActivity extends AppCompatActivity {
    public static final String TAG = "FilterOperatorActivity";

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, FilterOperatorActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_demo);
        findViewById(R.id.btnFilterUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterUse();
            }
        });
    }

    private void filterUse() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer < 5;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
                    }
                });
    }
}
