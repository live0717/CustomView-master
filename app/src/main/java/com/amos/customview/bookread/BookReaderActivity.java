package com.amos.customview.bookread;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amos.customview.R;

public class BookReaderActivity extends AppCompatActivity {
    private PageWidget mPageWidget;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, BookReaderActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_reader);
        mPageWidget = (PageWidget) findViewById(R.id.pwRead);

    }
}
