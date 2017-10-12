package com.amos.customview.bookread;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Amos on 2017/9/12.
 * 用于绘制小说阅读界面的所有东西
 */

public class PageFactory {
    private static PageFactory mPageFactory;

    private Context mContext;
    /*默认的背景颜色 */
    private int mDefaultBackColor = 0xffff9e85;
    /* 页面的宽*/
    private int mPageWidth;
    /* 页面的高*/
    private int mPageHeight;
    /* 默认字体大小*/
    private float mDefaultFontSize;
    /* 时间格式*/
    private SimpleDateFormat mDateFormat;
    /* 时间*/
    private String mDate;
    /* 阅读进度格式*/
    private DecimalFormat mDecimalFormat;


    /* 电池的边框宽度*/
    private float mBorderWidth;
    /*电池画笔*/
    private Paint mBatteryPaint;
    /*电池字体大小*/
    private float mBatteryFontSize;
    /* 电池的Intent*/
    private Intent batteryInfoIntent;
    /*电池电量百分比*/
    private float mBatteryPercentage;
    /*电池外边框*/
    private RectF rect1 = new RectF();
    /*电池内边框*/
    private RectF rect2 = new RectF();


    /* 上下与边缘的距离*/
    private float marginHeight;
    /* 左右与边缘的距离*/
    private float measureMarginWidth;
    /* 左右与边缘的距离*/
    private float marginWidth;
    /*状态栏距离底部高度*/
    private float statusMarginBottom;
    /*行间距*/
    private float mLineSpace;
    /*段间距*/
    private float mParagraphSpace;
    /*字高度*/
    private float mFontHeight;
    /*字体*/
    private Typeface mTypeface;


    /*文字画笔*/
    private Paint mPaint;
    /*加载画笔*/
    private Paint mWaitPaint;
    private OnReadStateChangeListener listener;

    public void setOnReadStateChangeListener(OnReadStateChangeListener listener) {
        this.listener = listener;
    }

    /**
     * 切换章节
     *
     * @param chapter
     */
    private void onChapterChanged(int chapter) {
        if (listener != null)
            listener.onChapterChanged(chapter);
    }

    /**
     * 改变阅读页面
     *
     * @param chapter
     * @param page
     */
    private void onPageChanged(int chapter, int page) {
        if (listener != null)
            listener.onPageChanged(chapter, page);
    }

    /**
     * 加载章节失败
     *
     * @param chapter
     */
    private void onLoadChapterFailure(int chapter) {
        if (listener != null)
            listener.onLoadChapterFailure(chapter);
    }

    //小说的状态
    public enum BookStatus {
        //没有上一页
        NO_PRE_PAGE,
        //没有下一页
        NO_NEXT_PAGE,
        //上一页加载失败
        PRE_CHAPTER_LOAD_FAILURE,
        //下一页加载失败
        NEXT_CHAPTER_LOAD_FAILURE,
        //加载数据成功
        LOAD_SUCCESS
    }

    /**
     * 小说阅读状态改变
     */
    public interface OnReadStateChangeListener {
        void onChapterChanged(int chapter);

        void onPageChanged(int chapter, int page);

        void onLoadChapterFailure(int chapter);

        void onCenterClick();

        void onFlip();
    }
}
