
package com.xc.xcskin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.xc.xcskin.R;

/**
 * 下拉窗帘自定义View
 * 
 * @author caizhiming
 */
public class XCCurtainView extends RelativeLayout implements OnTouchListener {

    private Context mContext = null;

    private Scroller mScroller = null;

    private ImageView mCurtainImageView;

    private ImageView mRopeImageView;

    // 窗帘广告高度
    private int mCurtainHeight = 0;

    // 是否在移动中
    private boolean mIsMoving = false;

    // 开始按下时候Y的坐标
    private int mDownY = 0;

    // 拖动时候Y的坐标
    private int mMovingY = 0;

    // 拖动Y方向上的距离
    private int mScrollY = 0;

    // 松开时候Y的坐标
    private int mUpY = 0;

    // 是否处于打开状态
    private boolean mIsOpen = false;

    /** 上升动画时间 */
    private int mUpDuration = 1000;

    /** 下落动画时间 */
    private int mDownDuration = 500;

    public XCCurtainView(Context context) {
        super(context);
        init(context);
    }

    public XCCurtainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public XCCurtainView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * 初始化View
     */
    private void init(Context context) {
        mContext = context;
        // Interpolator 设置为有反弹效果的 （Bounce：反弹）
        Interpolator interpolator = new BounceInterpolator();
        mScroller = new Scroller(context, interpolator);
        View view = LayoutInflater.from(context).inflate(R.layout.xc_curtain_view, null);
        mCurtainImageView = (ImageView) view.findViewById(R.id.img_curtain_ads);
        mRopeImageView = (ImageView) view.findViewById(R.id.img_curtain_rope);
        addView(view);
        
        mCurtainImageView.post(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                mCurtainHeight = mCurtainImageView.getHeight();
                XCCurtainView.this.scrollTo(0, mCurtainHeight);
            }
        });
        mRopeImageView.setOnTouchListener(this);
    }

    @Override
    public void computeScroll() {
        // TODO Auto-generated method stub

        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 更新界面
            postInvalidate();
            mIsMoving = true;
        } else {
            mIsMoving = false;
        }
        super.computeScroll();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        if (!mIsMoving) {
            // 屏幕顶部与该View的距离
            int offViewToTopY = 0;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDownY = (int) event.getRawY();
                    offViewToTopY = (int) (mDownY - event.getY());
                    return true;
                case MotionEvent.ACTION_MOVE:
                    mMovingY = (int) event.getRawY();
                    mScrollY = mMovingY - mDownY;
                    if (mScrollY < 0) {
                        // 向上滑动
                        if (mIsOpen) {
                            if (Math.abs(mScrollY) <= (mCurtainImageView.getBottom() - offViewToTopY)) {
                                scrollTo(0, -mScrollY);
                            }
                        }
                    } else {
                        // 向下滑动
                        if (!mIsOpen) {
                            if (mScrollY <= mCurtainHeight) {
                                scrollTo(0, mCurtainHeight- mScrollY);
                            }
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    mUpY = (int) event.getRawY();
                    if (Math.abs(mUpY - mDownY) <= 10) {
                        onRopeClick();
                        break;
                    }
                    if (mDownY > mUpY) {
                        // 向上滑动
                        if (mIsOpen) {
                            if (Math.abs(mScrollY) >= mCurtainHeight / 2) {
                                // 向上滑动超过半个屏幕高的时候 开启向上消失动画
                                startMoveAnim(this.getScrollY(), (mCurtainHeight - this.getScrollY()), mUpDuration);
                                mIsOpen = false;
                            } else {
                                startMoveAnim(this.getScrollY(), -this.getScrollY(), mUpDuration);
                                mIsOpen = true;
                            }
                        }
                    } else {
                        // 向下滑动
                        if (mScrollY > mCurtainHeight / 2) {
                            // 向上滑动超过半个屏幕高的时候 开启向上消失动画
                            startMoveAnim(this.getScrollY(), -this.getScrollY(), mUpDuration);
                            mIsOpen = true;
                        } else {
                            startMoveAnim(this.getScrollY(), (mCurtainHeight - this.getScrollY()), mUpDuration);
                            mIsOpen = false;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    /**
     * 拖动动画
     * 
     * @param startY
     * @param dy 垂直距离, 滚动的y距离
     * @param duration 时间
     */
    public void startMoveAnim(int startY, int dy, int duration) {
        mIsMoving = true;
        mScroller.startScroll(0, startY, 0, dy, duration);
        invalidate();// 通知UI线程的更新
    }

    /**
     * 点击绳索开关，会展开关闭 在onToch中使用这个中的方法来当点击事件，避免了点击时候响应onTouch的衔接不完美的影响
     */
    private void onRopeClick() {
        // TODO Auto-generated method stub
        if (mIsOpen) {
            startMoveAnim(0, mCurtainHeight, mUpDuration);
        } else {
            startMoveAnim(mCurtainHeight, -mCurtainHeight, mDownDuration);
        }
        mIsOpen = !mIsOpen;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        super.onLayout(changed, l, t, r, b);
    }

}
