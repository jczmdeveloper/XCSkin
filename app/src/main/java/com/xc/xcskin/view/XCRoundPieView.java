
package com.xc.xcskin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/** 仿微信动态圆饼图View
 * @author caizhiming
 */
public class XCRoundPieView extends View {

    private Paint paint;// 画笔对象的引用

    private static final int STROKE = 0;// 空心

    private static final int FILL = 1;// 实心

    private int mWeixinColor;

    private int mOtherColor;

    private int mRetainColor;

    private int mWeixinSweepAngle = 45;// 弧度

    private int mOtherSweepAngle = 120;// 弧度

    private int mRetainSweepAngle = 195;

    private int mCurAngle = 0;

    private int centerX;// 获取中心点X坐标

    private int centerY;// 获取中心点Y坐标

    private int radius;// 圆环的半径

    private RectF oval;

    private boolean mDrawing = false;

    public XCRoundPieView(Context context) {
        this(context, null);
    }

    public XCRoundPieView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XCRoundPieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);// 设置空心
        paint.setStrokeWidth(20);// 设置圆环宽度
        paint.setAntiAlias(true);// 消除锯齿
        mWeixinColor = Color.rgb(23, 182, 17);
        mOtherColor = Color.rgb(135, 206, 242);
        mRetainColor = Color.rgb(233, 233, 233);
    }
    
    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                start();
            }
        };
    };

    public void setColors(int weixinColor, int otherColor, int retainColor) {
        mWeixinColor = weixinColor;
        mOtherColor = otherColor;
        mRetainColor = retainColor;
    }

    public void setAngleData(int weixin, int other, int retain) {
        int total = weixin + other + retain;
        mWeixinSweepAngle = 360 * weixin / total;// 弧度
        mOtherSweepAngle = 360 * other / total;// 弧度
        mRetainSweepAngle = 360 * retain / total;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        if (centerX == 0 || centerY == 0) {
            centerX = getWidth() / 2;
            centerY = getHeight() / 2;
            radius = centerX;
            // 用于定义的圆弧的形状和大小的界限
            oval = new RectF(centerX - radius, centerX - radius, centerX + radius, centerX + radius);
            mHandler.sendEmptyMessageDelayed(0, 500);
        } else {
            if (mCurAngle <= mWeixinSweepAngle) {
                paint.setColor(mWeixinColor);
                canvas.drawArc(oval, -90, mCurAngle, true, paint); // 根据进度画圆弧
            } else if (mCurAngle > mWeixinSweepAngle && mCurAngle <= mWeixinSweepAngle + mOtherSweepAngle) {
                paint.setColor(mWeixinColor);
                canvas.drawArc(oval, -90, mWeixinSweepAngle, true, paint); // 根据进度画圆弧
                paint.setColor(mOtherColor);
                int to1 = -90 + mWeixinSweepAngle;
                canvas.drawArc(oval, to1, mCurAngle - to1 - 90, true, paint);
            } else {
                paint.setColor(mWeixinColor);
                canvas.drawArc(oval, -90, mWeixinSweepAngle, true, paint); // 根据进度画圆弧
                paint.setColor(mOtherColor);
                int to1 = -90 + mWeixinSweepAngle;
                canvas.drawArc(oval, to1, mOtherSweepAngle, true, paint);
                int to2 = to1 + mOtherSweepAngle;
                paint.setColor(mRetainColor);
                canvas.drawArc(oval, to2, mCurAngle - to2 - 90, true, paint);
            }
        }
    }

    public Paint getPaint() {
        return paint;

    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void restart() {
        if (mDrawing)
            return;
        mCurAngle = 0;
        start();
    }

    private void start() {
        mDrawing = true;
        setProgress();
        new Thread() {
            public void run() {
                try {
                    while (mCurAngle < 360) {
                        mCurAngle += 1;
                        setProgress();
                        Thread.sleep(3);
                    }
                    mDrawing = false;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            };
        }.start();

    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，
     * 需要同步 刷新界面调用postInvalidate()能在非UI线程刷新
     * @author caizhiming
     */
    public synchronized void setProgress() {
        postInvalidate();
    }

}
