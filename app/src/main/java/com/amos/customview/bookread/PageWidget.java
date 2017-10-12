package com.amos.customview.bookread;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Amos on 2017/9/12.
 * 小说页面
 */

public class PageWidget extends View {

    // 屏幕宽
    private int mScreenWidth = 0;
    // 屏幕高
    private int mScreenHeight = 0;
    private Context mContext;
    //背景颜色
    private int mBgColor = 0xFFCEC29C;

    public PageWidget(Context context) {
        this(context, null);
    }

    public PageWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPage();
    }

    private void initPage() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metric);
        mScreenWidth = metric.widthPixels;
        mScreenHeight = metric.heightPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景颜色
        canvas.drawColor(mBgColor);
    }

    private int downX = 0;
    private int downY = 0;

    private int moveX = 0;
    private int moveY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                downY = (int) event.getY();
                moveX = 0;
                moveY = 0;
                //判断是否点击中部
                if (downX >= mScreenWidth / 3 && downX <= mScreenWidth * 2 / 3
                        && downY >= mScreenHeight / 3 && downY <= mScreenHeight * 2 / 3) {

                } else {
                    if (downX < mScreenWidth / 2) {
                        //往左翻页,上一页

                    } else if (downX >= mScreenWidth / 2) {
                        //往右翻页,下一页

                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return true;
    }




}
