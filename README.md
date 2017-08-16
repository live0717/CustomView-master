　　挺久没写文章了，近段时间被拉过去写JS项目了，在做一个项目的时候，遇到一个新的需求就是空气质量，实现空气污染指数的时候，需要到一个圆弧的进度，在网上没找到合适，干脆就自己写了一个，顺便复习一下自定义View，下面是具体的实现。

先看一下效果

![](http://i.imgur.com/CJQK727.png)

这里的话我只做一个进度条，使用也很简单。圆弧外的文本是一个textview，不是这个控件里面的，说明一下。

下面先看一下整体的代码：


    import android.animation.ValueAnimator;
    import android.content.Context;
    import android.content.res.TypedArray;
    import android.graphics.Canvas;
    import android.graphics.Color;
    import android.graphics.Paint;
    import android.graphics.Rect;
    import android.graphics.RectF;
    import android.support.annotation.Nullable;
    import android.util.AttributeSet;
    import android.view.View;
    
    public class ArcProgressBar extends View {

    private static final String TAG = "ArcProgressBar";
    /**
     * 圆弧的宽度
     */
    private int mStrokeWidth = dp2px(8);
    /**
     * 圆弧开始的角度
     */
    private float mStartAngle = 135;
    /**
     * 起点角度和终点角度对应的夹角大小
     */
    private float mAngleSize = 270;
    /**
     * 圆弧背景颜色
     */
    private int mArcBgColor = Color.YELLOW;
    /**
     * 最大的进度，用于计算进度与夹角的比例
     */
    private float mMaxProgress = 500;
    /**
     * 当前进度对应的起点角度到当前进度角度夹角的大小
     */
    private float mCurrentAngleSize = 0;
    /**
     * 当前进度
     */
    private float mCurrentProgress = 0;
    /**
     * 动画的执行时长
     */
    private long mDuration = 3000;
    /**
     * 进度圆弧的颜色
     */
    private int mProgressColor = Color.RED;
    /**
     * 第一行文本
     */
    private String mFirstText = "42";
    /**
     * 第一行文本的颜色
     */
    private int mFirstTextColor = Color.RED;
    /**
     * 第一行文本的字体大小
     */
    private float mFirstTextSize = 56f;
    /**
     * 第二行文本
     */
    private String mSecondText = "优";
    /**
     * 第二行文本的颜色
     */
    private int mSecondTextColor = Color.RED;
    /**
     * 第二行文本的字体大小
     */
    private float mSecondTextSize = 56f;


    public ArcProgressBar(Context context) {
        super(context, null);
    }

    public ArcProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);

    }

    public ArcProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
    }

    /**
     * 设置初始化的参数
     *
     * @param context
     * @param attrs
     */
    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ArcProgressBar);
        mMaxProgress = array.getFloat(R.styleable.ArcProgressBar_arc_max_progress, 500f);
        mArcBgColor = array.getColor(R.styleable.ArcProgressBar_arc_bg_color, Color.YELLOW);
        mStrokeWidth = dp2px(array.getDimension(R.styleable.ArcProgressBar_arc_stroke_width, 12f));
        mCurrentProgress = array.getFloat(R.styleable.ArcProgressBar_arc_progress, 300f);
        mProgressColor = array.getColor(R.styleable.ArcProgressBar_arc_progress_color, Color.RED);
        mFirstText = array.getString(R.styleable.ArcProgressBar_arc_first_text);
        mFirstTextSize = dp2px(array.getDimension(R.styleable.ArcProgressBar_arc_first_text_size, 20f));
        mFirstTextColor = array.getColor(R.styleable.ArcProgressBar_arc_first_text_color, Color.RED);
        mSecondText = array.getString(R.styleable.ArcProgressBar_arc_second_text);
        mSecondTextSize = dp2px(array.getDimension(R.styleable.ArcProgressBar_arc_second_text_size, 20f));
        mSecondTextColor = array.getColor(R.styleable.ArcProgressBar_arc_second_text_color, Color.RED);
        mAngleSize = array.getFloat(R.styleable.ArcProgressBar_arc_angle_size, 270f);
        mStartAngle = array.getFloat(R.styleable.ArcProgressBar_arc_start_angle, 135f);
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;
        RectF rectF = new RectF();
        rectF.left = mStrokeWidth;
        rectF.top = mStrokeWidth;
        rectF.right = centerX * 2 - mStrokeWidth;
        rectF.bottom = centerX * 2 - mStrokeWidth;
        //画最外层的圆弧
        drawArcBg(canvas, rectF);
        //画进度
        drawArcProgress(canvas, rectF);
        //绘制第一级文本
        drawFirstText(canvas, centerX);
        //绘制第二级文本
        drawSecondText(canvas, centerX);

    }


    /**
     * 画最开始的圆弧
     *
     * @param canvas
     * @param rectF
     */
    private void drawArcBg(Canvas canvas, RectF rectF) {
        Paint mPaint = new Paint();
        //画笔的填充样式，Paint.Style.FILL 填充内部;Paint.Style.FILL_AND_STROKE 填充内部和描边;Paint.Style.STROKE 描边
        mPaint.setStyle(Paint.Style.STROKE);
        //圆弧的宽度
        mPaint.setStrokeWidth(mStrokeWidth);
        //抗锯齿
        mPaint.setAntiAlias(true);
        //画笔的颜色
        mPaint.setColor(mArcBgColor);
        //画笔的样式 Paint.Cap.Round 圆形,Cap.SQUARE 方形
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //开始画圆弧
        canvas.drawArc(rectF, mStartAngle, mAngleSize, false, mPaint);
    }

    /**
     * 画进度的圆弧
     *
     * @param canvas
     * @param rectF
     */
    private void drawArcProgress(Canvas canvas, RectF rectF) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mStrokeWidth);
        paint.setColor(mProgressColor);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(rectF, mStartAngle, mCurrentAngleSize, false, paint);
    }


    /**
     * 绘制第一级文字
     *
     * @param canvas  画笔
     * @param centerX 位置
     */
    private void drawFirstText(Canvas canvas, float centerX) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mFirstTextColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(mFirstTextSize);
        Rect firstTextBounds = new Rect();
        paint.getTextBounds(mFirstText, 0, mFirstText.length(), firstTextBounds);
        canvas.drawText(mFirstText, centerX, firstTextBounds.height() / 2 + getHeight() * 2 / 5, paint);
    }

    /**
     * 绘制第二级文本
     *
     * @param canvas  画笔
     * @param centerX 文本
     */
    private void drawSecondText(Canvas canvas, float centerX) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mSecondTextColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(mSecondTextSize);
        Rect bounds = new Rect();
        paint.getTextBounds(mSecondText, 0, mSecondText.length(), bounds);
        canvas.drawText(mSecondText, centerX, getHeight() / 2 + bounds.height() / 2 +
                getFontHeight(mSecondText, mSecondTextSize), paint);
    }

    /**
     * 设置最大的进度
     *
     * @param progress
     */
    public void setMaxProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("Progress value can not be less than 0 ");
        }
        mMaxProgress = progress;
    }

    /**
     * 设置当前进度
     *
     * @param progress
     */
    public void setProgress(float progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("Progress value can not be less than 0");
        }
        if (progress > mMaxProgress) {
            progress = mMaxProgress;
        }
        mCurrentProgress = progress;
        float size = mCurrentProgress / mMaxProgress;
        mCurrentAngleSize = (int) (mAngleSize * size);
        setAnimator(0, mCurrentAngleSize);
    }

    /**
     * 设置进度圆弧的颜色
     *
     * @param color
     */
    public void setProgressColor(int color) {
        if (color == 0) {
            throw new IllegalArgumentException("Color can no be 0");
        }
        mProgressColor = color;
    }

    /**
     * 设置圆弧的颜色
     *
     * @param color
     */
    public void setArcBgColor(int color) {
        if (color == 0) {
            throw new IllegalArgumentException("Color can no be 0");
        }
        mArcBgColor = color;
    }

    /**
     * 设置圆弧的宽度
     *
     * @param strokeWidth
     */
    public void setStrokeWidth(int strokeWidth) {
        if (strokeWidth < 0) {
            throw new IllegalArgumentException("strokeWidth value can not be less than 0");
        }
        mStrokeWidth = dp2px(strokeWidth);
    }

    /**
     * 设置动画的执行时长
     *
     * @param duration
     */
    public void setAnimatorDuration(long duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("Duration value can not be less than 0");
        }
        mDuration = duration;
    }

    /**
     * 设置第一行文本
     *
     * @param text
     */
    public void setFirstText(String text) {
        mFirstText = text;
    }

    /**
     * 设置第一行文本的颜色
     *
     * @param color
     */
    public void setFirstTextColor(int color) {
        if (color <= 0) {
            throw new IllegalArgumentException("Color value can not be less than 0");
        }
        mFirstTextColor = color;
    }

    /**
     * 设置第一行文本的大小
     *
     * @param textSize
     */
    public void setFirstTextSize(float textSize) {
        if (textSize <= 0) {
            throw new IllegalArgumentException("textSize can not be less than 0");
        }
        mFirstTextSize = textSize;
    }

    /**
     * 设置第二行文本
     *
     * @param text
     */
    public void setSecondText(String text) {
        mSecondText = text;
    }

    /**
     * 设置第二行文本的颜色
     *
     * @param color
     */
    public void setSecondTextColor(int color) {
        if (color == 0) {
            throw new IllegalArgumentException("Color value can not be less than 0");
        }
        mSecondTextColor = color;
    }

    /**
     * 设置第二行文本的大小
     *
     * @param textSize
     */
    public void setSecondTextSize(float textSize) {
        if (textSize <= 0) {
            throw new IllegalArgumentException("textSize can not be less than 0");
        }
        mSecondTextSize = textSize;
    }

    /**
     * 设置圆弧开始的角度
     *
     * @param startAngle
     */
    public void setStartAngle(int startAngle) {
        mStartAngle = startAngle;
    }

    /**
     * 设置圆弧的起始角度到终点角度的大小
     *
     * @param angleSize
     */
    public void setAngleSize(int angleSize) {
        mAngleSize = angleSize;
    }

    /**
     * dp转成px
     *
     * @param dp
     * @return
     */
    private int dp2px(float dp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f * (dp >= 0 ? 1 : -1));
    }

    /**
     * 设置动画
     *
     * @param start  开始位置
     * @param target 结束位置
     */
    private void setAnimator(float start, float target) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(start, target);
        valueAnimator.setDuration(mDuration);
        valueAnimator.setTarget(mCurrentAngleSize);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentAngleSize = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * 测量字体的高度
     *
     * @param textStr
     * @param fontSize
     * @return
     */
    private float getFontHeight(String textStr, float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Rect bounds = new Rect();
        paint.getTextBounds(textStr, 0, textStr.length(), bounds);
        return bounds.height();
    }

	}
然后是具体使用控件里面的代码：

	<com.amos.customview.ArcProgressBar
        android:id="@+id/arc"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_gravity="center"/>

这里使用非常简单，跟其它控件的使用没什么区别。

里面还定义了很多直接在xml文件里面就可以直接设置的属性

	<declare-styleable name="ArcProgressBar">
        <attr name="arc_max_progress" format="float"/>
        <attr name="arc_bg_color" format="color"/>
        <attr name="arc_stroke_width" format="dimension"/>
        <attr name="arc_progress" format="float"/>
        <attr name="arc_progress_color" format="color"/>
        <attr name="arc_first_text" format="string"/>
        <attr name="arc_first_text_color" format="color"/>
        <attr name="arc_first_text_size" format="dimension"/>
        <attr name="arc_second_text" format="string"/>
        <attr name="arc_second_text_color" format="color"/>
        <attr name="arc_second_text_size" format="dimension"/>
        <attr name="arc_angle_size" format="float"/>
        <attr name="arc_start_angle" format="float"/>
    </declare-styleable>

这个自定义View非常简单，代码里面注释也写的很清楚了，就不做过多的介绍了，有兴趣的可以去做更多的扩展，比如，直接做成圆形或者扇形，还有这里必须要两行文本，如果项目需要，也可以直接去删除掉文本，不过需要计算一下文本的绘制位置。

有兴趣的可以去GitHub上面给个Start，地址是：[传送门](https://github.com/Devin1102/CustomView-master)
