package com.amos.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Amos on 2017/8/14.
 * Desc：
 */

public class MultipleActivity extends AppCompatActivity {
    private static final String TAG = "MultipleActivity";
    RecyclerView mRecyclerView;
    TopCategoryBean mCategoryBean = new TopCategoryBean();
    MultipleAdapter mMultipleAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvMultipleItem);

        mMultipleAdapter = new MultipleAdapter(this, mCategoryBean);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://api.zhuishushenqi.com/cats/lv2/statistics");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(60000);
                    urlConnection.setConnectTimeout(10000);
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = urlConnection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                        Gson gson = new Gson();
                        final TopCategoryBean categoryBean = gson.fromJson(sb.toString(), TopCategoryBean.class);
                        Log.i(TAG,"-------->"+categoryBean);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mMultipleAdapter.setCategoryBean(categoryBean);
                                mRecyclerView.setAdapter(mMultipleAdapter);
                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
