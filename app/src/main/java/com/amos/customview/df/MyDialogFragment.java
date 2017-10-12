package com.amos.customview.df;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amos.customview.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amos on 2017/8/23.
 */

public class MyDialogFragment extends DialogFragment {

    private RecyclerView mRvTag;
    private List<BookListTags.DataBean> mDataBeen;
    private BookListTagAdapter mAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String result = data.getString("result");
            Gson gson = new Gson();
            BookListTags bookListTags = gson.fromJson(result, BookListTags.class);
            mAdapter.addAll(bookListTags.data);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
        mDataBeen = new ArrayList<>();
        mAdapter = new BookListTagAdapter(getContext(), mDataBeen);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://api.zhuishushenqi.com/book-list/tagType");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(100000);
                    httpURLConnection.setReadTimeout(100000);
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuilder builder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            builder.append(line);
                        }
                        String result = builder.toString();
                        Message message = mHandler.obtainMessage();
                        Bundle bundle = new Bundle();
                        bundle.putString("result", result);
                        message.setData(bundle);
                        mHandler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tag, container);
        mRvTag = view.findViewById(R.id.rvTag);

        mRvTag.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvTag.setHasFixedSize(true);
        mRvTag.setAdapter(mAdapter);
        return view;
    }
}
