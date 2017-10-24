package com.amos.customview.rxjava2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.amos.customview.R;

import org.reactivestreams.Publisher;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Project CustomView-master
 * Created by Amos
 * Created on 2017-10-23
 * Desc
 */

public class CreateOperatorActivity extends AppCompatActivity {
    public static final String TAG = "CreateOperatorActivity";

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, CreateOperatorActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_create);
        findViewById(R.id.btnCreateUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUse();
            }
        });
        findViewById(R.id.btnDeferUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deferUse();
            }
        });
        findViewById(R.id.btnFromUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromUse();
            }
        });
        findViewById(R.id.btnIntervalUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intervalUse();
            }
        });
        findViewById(R.id.btnJustUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flowable.just(1, 2, 3, "this is a test")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Serializable>() {
                            @Override
                            public void accept(Serializable serializable) throws Exception {

                            }
                        });

            }
        });
        findViewById(R.id.btnRangeUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flowable.range(0, 10)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.d(TAG, "" + integer);
                            }
                        });
            }
        });
    }

    private void intervalUse() {
        Disposable subscribe = Flowable.interval(10, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "" + aLong);
                        if (aLong >= 100) {

                        }
                    }
                });
        try {
            Thread.sleep(500);
            if (subscribe.isDisposed()) {
                subscribe.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void fromUse() {
        String[] arrays = {"this", "is", "a", "test"};
        Flowable.fromArray(arrays)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, s);
                    }
                });
    }

    private void deferUse() {
        Flowable.defer(new Callable<Publisher<String>>() {
            @Override
            public Publisher<String> call() throws Exception {
                return Flowable.just("this is a test");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, s);
                    }
                });
    }

    private void createUse() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {
                e.onNext("this is a test");
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
    }
}
