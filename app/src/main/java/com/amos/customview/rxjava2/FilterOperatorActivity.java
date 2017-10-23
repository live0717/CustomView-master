package com.amos.customview.rxjava2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.amos.customview.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.exceptions.Exceptions;
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
        findViewById(R.id.btnOfTypeUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ofTypeUse();
            }
        });
        findViewById(R.id.btnTakeUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeUse();
            }
        });
        findViewById(R.id.btnTakeLastUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeLastUse();
            }
        });
        findViewById(R.id.btnTakeUntilUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeUntilUse();
            }
        });
        findViewById(R.id.btnTakeWhileUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeWhileUse();
            }
        });
        findViewById(R.id.btnSkipUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipUse();
            }
        });
        findViewById(R.id.btnSkipLastUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipLastUse();
            }
        });
        findViewById(R.id.btnDebounceUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                debounceUse();
            }
        });
        findViewById(R.id.btnElementAt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elementAtUse();
            }
        });
        findViewById(R.id.btnSampleUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sampleUse();
            }
        });
    }

    private void sampleUse() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    throw Exceptions.propagate(e1);
                }
                e.onNext(2);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    throw Exceptions.propagate(e1);
                }
                e.onNext(3);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    throw Exceptions.propagate(e1);
                }
                e.onNext(4);
                e.onNext(5);
                e.onComplete();
            }
        }).sample(999, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
                    }
                });
    }

    private void elementAtUse() {
        Flowable.just(1, 2, 3, 4, 5, 6)
                .elementAt(4)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
                    }
                });
    }

    private void debounceUse() {
        Flowable.just(1, 2, 3)
                .debounce(100, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "ElementAt" + integer);
                    }
                });
    }


    private void skipLastUse() {
        Flowable.just(1, 2, 3, 4, 5, 6)
                .skipLast(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
                    }
                });
    }

    private void skipUse() {
        Flowable.just(1, 2, 3, 4, 5, 6)
                .skip(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
                    }
                });

    }

    private void takeWhileUse() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .takeWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer != 5;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "------>" + integer);
                    }
                });
    }

    private void takeUntilUse() {
//        Observable<Long> observableA = Observable.interval(300, TimeUnit.MILLISECONDS);
//        Observable<Long> observableB = Observable.interval(800, TimeUnit.MILLISECONDS);
//
//        observableA.takeUntil(observableB)
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        Log.d(TAG, "------>" + aLong);
//                    }
//                });
        Observable.just(1, 2, 3, 4, 5, 6)
                .takeUntil(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer >= 4;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "------>" + integer);
                    }
                });
    }

    private void takeLastUse() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .takeLast(4)
                .take(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
                    }
                });
    }

    private void takeUse() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .take(4)
                .take(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
                    }
                });
    }

    private void ofTypeUse() {
        Observable.just(1, 2, 3, 4, 5, "a", "b", "c").ofType(Integer.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
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
