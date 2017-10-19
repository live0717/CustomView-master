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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;

/**
 * Project CustomView-master
 * Created by Amos
 * Created on 2017-10-18
 * Desc
 */

public class RxJavaOperatorActivity extends AppCompatActivity {

    public static final String TAG = "RxJavaOperatorActivity";

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RxJavaOperatorActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_operator);
        findViewById(R.id.btnMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.fromArray(new String[]{"this", "is", "a", "test"})
                        .map(new Function<String, String>() {
                            @Override
                            public String apply(@NonNull String s) throws Exception {
                                return s.toUpperCase();
                            }
                        }).subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.newThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                Log.d(TAG, s);
                            }
                        });

            }
        });

        findViewById(R.id.btnFlatMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flatMapUse();
            }
        });
        findViewById(R.id.btnConcatMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatMapUse();
            }
        });
        findViewById(R.id.btnFlatMapIterable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flatMapIterableUse();
            }
        });
        findViewById(R.id.btnSwitchMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchMapUse();
            }
        });
        findViewById(R.id.btnScan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanUse();
            }
        });
        findViewById(R.id.btnGroupBy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupByUse();
            }
        });
        findViewById(R.id.btnBuffer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bufferUse();
            }
        });
        findViewById(R.id.btnWindow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowUse();
            }
        });
    }

    private void windowUse() {
        Observable<Observable<Integer>> window = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .window(3);
        Observable.concat(window)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
                    }
                });
    }

    private void bufferUse() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .buffer(5).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        Log.d(TAG, "" + integers.size());
                    }
                });
    }

    private void groupByUse() {
        Source s1 = new Source(1, "语文", 112);
        Source s2 = new Source(2, "数学", 92);
        Source s3 = new Source(3, "英语", 132);
        Source s4 = new Source(4, "语文", 98);
        Source s5 = new Source(5, "数学", 98);
        Source s6 = new Source(6, "英语", 99);
        Observable<GroupedObservable<String, Source>> groupedObservableObservable = Observable.just(s1, s2, s3, s4, s5, s6)
                .groupBy(new Function<Source, String>() {
                    @Override
                    public String apply(@NonNull Source source) throws Exception {
                        return source.name;
                    }
                });
        Observable.concat(groupedObservableObservable).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Source>() {
                    @Override
                    public void accept(Source source) throws Exception {
                        Log.d(TAG, source.name + " 成绩分别是：" + source.score);
                    }
                });
    }

    private void scanUse() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        return integer * integer2;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "---->" + integer);
                    }
                });
    }

    private void switchMapUse() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .switchMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(@NonNull Integer integer) throws Exception {
                        int delay = 0;
                        if (integer % 2 == 0) {
                            delay = 500;
                        }
                        return Observable.just(integer).delay(delay, TimeUnit.MILLISECONDS);
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

    private void flatMapIterableUse() {
        Observable.just(2, 3, 5)
                .flatMapIterable(new Function<Integer, Iterable<String>>() {
                    @Override
                    public Iterable<String> apply(@NonNull Integer integer) throws Exception {
                        return Arrays.asList(integer * 10 + "", integer * 100 + "");
                    }

                }).observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, s);
                    }
                });
    }

    private void concatMapUse() {
        Observable.just("this", "is", "a", "test")
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull String s) throws Exception {
                        int delay = 0;
                        if (Objects.equals(s, "a")) {
                            delay = 500;
                        }
                        return Observable.just(s.toUpperCase()).delay(delay, TimeUnit.MILLISECONDS);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "use flatMap " + s);
                    }
                });

        Observable.just("this", "is", "a", "test")
                .concatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull String s) throws Exception {
                        int delay = 0;
                        if (Objects.equals(s, "a")) {
                            delay = 500;
                        }
                        return Observable.just(s.toUpperCase()).delay(delay, TimeUnit.MILLISECONDS);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "use concatMap " + s);
                    }
                });
    }

    private void flatMapUse() {
        Source s1 = new Source(1, "语文", 112);
        Source s2 = new Source(2, "数学", 92);
        Source s3 = new Source(3, "英语", 132);
        List<Source> ss = new ArrayList<>();
        ss.add(s1);
        ss.add(s2);
        ss.add(s3);
        Source s4 = new Source(4, "语文", 98);
        Source s5 = new Source(5, "数学", 92);
        Source s6 = new Source(6, "英语", 99);
        List<Source> ss2 = new ArrayList<>();
        ss2.add(s4);
        ss2.add(s5);
        ss2.add(s6);
        Student st1 = new Student("Tom", 1, ss);
        Student st2 = new Student("Amos", 2, ss2);
        Student st3 = new Student("Devin", 3, ss);
        Flowable.just(st1, st2, st3).flatMap(new Function<Student, Publisher<Source>>() {
            @Override
            public Publisher<Source> apply(@NonNull Student student) throws Exception {
                return Flowable.fromIterable(student.mSources);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Source>() {
                    @Override
                    public void accept(Source source) throws Exception {
                        Log.d(TAG, source.name + " 的成绩是：" + source.score);
                    }
                });
    }
}
