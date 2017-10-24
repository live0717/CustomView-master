package com.amos.customview.rxjava2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.amos.customview.R;

import java.util.Map;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Project CustomView-master
 * Created by Amos
 * Created on 2017-10-24
 * Desc
 */

public class OtherOperatorActivity extends AppCompatActivity {

    public static final String TAG = "OtherOperatorActivity";

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, OtherOperatorActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_other);

        findViewById(R.id.btnZipUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zipUse();
            }
        });
        findViewById(R.id.btnCombineLatestUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combineLastestUse();
            }
        });
        findViewById(R.id.btnMergeUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mergeUse();
            }
        });
        findViewById(R.id.btnOnErrorReturnUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorReturnUse();
            }
        });
        findViewById(R.id.btnOnErrorResumeNextUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorResumeNextUse();
            }
        });
        findViewById(R.id.btnRetryUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retryUse();
            }
        });
        findViewById(R.id.btnRetryWhenUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retryWhenUse();
            }
        });
        findViewById(R.id.btnToMapUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMapUse();
            }
        });
        findViewById(R.id.btnDoOnEachUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOnEachUse();
            }
        });
        findViewById(R.id.btnDoOnNextUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOnNextUse();
            }
        });
        findViewById(R.id.btnDoOnSubscribeUse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOnSubscribeUse();
            }
        });
    }

    private void doOnSubscribeUse() {
        Observable.just(1, 2, 3, 4)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
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

    private void doOnNextUse() {
        Observable.just(1, 2, 3, 4)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        integer = integer + 10;
                        Log.d(TAG, "--->" + integer);
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

    private void doOnEachUse() {
        Observable.just(1, 2, 3, 4)
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        Log.d(TAG, "---->" + integerNotification);
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

    private void toMapUse() {
        Observable.just(3, 6, 8, 5, 6)
                .toMap(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return "key:" + integer;
                    }
                }, new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return "value:" + integer;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Map<String, String>>() {
                    @Override
                    public void accept(Map<String, String> stringStringMap) throws Exception {
                        Log.d(TAG, stringStringMap.toString());
                    }
                });

    }

    private void retryWhenUse() {
        Observable.just(1, 2, "3", 4)
                .cast(Integer.class)
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(@NonNull Observable<Throwable> throwableObservable) throws Exception {
                        return Observable.just(100);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, throwable.getMessage());
                    }
                });

    }

    private void retryUse() {
        Observable.just(1, 2, "3", 4)
                .cast(Integer.class)
                .retry(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, throwable.getMessage());
                    }
                });
    }

    private void onErrorResumeNextUse() {
        Observable.just(1, 2, "3", 4)
                .cast(Integer.class)
                .onErrorResumeNext(new ObservableSource<Integer>() {
                    @Override
                    public void subscribe(@NonNull Observer<? super Integer> observer) {
                        observer.onNext(100);
                        observer.onComplete();
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

    private void onErrorReturnUse() {
        Observable.just(1, "2", 3, 4)
                .cast(Integer.class)
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(@NonNull Throwable throwable) throws Exception {
                        return 100;
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

    private void mergeUse() {
        Observable<String> observable1 = Observable.just("1", "2", "3", "4");
        Observable<String> observable2 = Observable.just("a", "b", "c");
        Observable.merge(observable1, observable2).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, s);
                    }
                });

    }

    private void combineLastestUse() {
        PublishSubject<String> p1 = PublishSubject.create();
        p1.onNext("admin");
        PublishSubject<String> p2 = PublishSubject.create();
        p2.onNext("admin");
        Observable.combineLatest(p1, p2, new BiFunction<String, String, String>() {
            @Override
            public String apply(@NonNull String s, @NonNull String s2) throws Exception {
                if (s.equals("admin") && s2.equals("admin")) {
                    return "success";
                } else {
                    return "failed";
                }

            }
        }).subscribeOn(Schedulers.io())
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

    private void zipUse() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1000);
                e.onComplete();
            }
        });
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("this is a test");
                e.onComplete();
            }
        });
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(@NonNull Integer integer, @NonNull String s) throws Exception {
                return s + integer;
            }
        }).subscribeOn(Schedulers.io())
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
