package com.amos.customview.rxjava2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.amos.customview.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Project CustomView-master
 * Created by Amos
 * Created on 2017-10-12
 * Desc
 *
 * @author Amos
 */

public class RxJavaDemoActivity extends AppCompatActivity {
    public static final String TAG = "RxJavaDemoActivity";


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RxJavaDemoActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        findViewById(R.id.btnSimpleUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                simpleUse();
                simpleUseSecond();
            }
        });
        findViewById(R.id.btnFlowable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowableUse();
            }
        });
        findViewById(R.id.btnSingle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleUse();
            }
        });
        findViewById(R.id.btnCompletable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Completable.create(new CompletableOnSubscribe() {
                    @Override
                    public void subscribe(@NonNull CompletableEmitter e) throws Exception {
                        Log.d(TAG,"subscribe");
                        e.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.newThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                Log.d(TAG,"onSubscribe");
                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG,"onComplete");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.d(TAG,e.getMessage());
                            }
                        });
            }
        });
        findViewById(R.id.btnMaybe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Maybe.create(new MaybeOnSubscribe<Boolean>() {
                    @Override
                    public void subscribe(@NonNull MaybeEmitter<Boolean> e) throws Exception {
                        Log.e(TAG,"send data");
                        e.onSuccess(true);
                        e.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.newThread())
                        .subscribe(new MaybeObserver<Boolean>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                Log.e(TAG,"onSubscribe");
                            }

                            @Override
                            public void onSuccess(@NonNull Boolean b) {
                                Log.e(TAG,b+"onSuccess");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.e(TAG,e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                Log.e(TAG,"onComplete");
                            }
                        });
            }
        });
    }

    /**
     * 简单使用
     */
    private void simpleUse() {
        //创建事件产生者
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });
        //创建事件接受者
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "subscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG, integer + "");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        };
        //订阅事件
        observable.subscribe(observer);
    }

    private void simpleUseSecond() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "subscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG, integer + "");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        });
    }

    private void flowableUse() {
        //创建事件产生者
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {
                e.onNext("this");
                e.onNext(" is ");
                e.onNext(" a ");
                e.onNext(" test!");
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR);
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, s);
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, t.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        };
        flowable.subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe(subscriber);
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {
                e.onNext("this");
                e.onNext(" is ");
                e.onNext(" a ");
                e.onNext(" test!");
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "complete");
                    }
                });
    }

    private void singleUse() {
        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<String> e) throws Exception {
                e.onSuccess("single use test");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onSuccess(@NonNull String s) {
                        Log.d(TAG, s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, e.getMessage());
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
