package com.xc.xcskin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.xc.xcskin.R;
import com.xc.xcskin.utils.Utils;
/**
 * 登陆界面拖动效果View
 * @author caizhiming
 *
 */
public class XCLoginView extends RelativeLayout{

    private Scroller mScroller = null;
    //屏幕宽度
    private int mScreenWith = 0;
    //屏幕高度
    private int mScreenHeight = 0;
    //登录界面View的宽度
    private int mViewWidth = 0;
    //登录界面View的高度
    private int mViewHeight = 0;
    
    //是否在滑动中
    private boolean mIsMoving = false;
    //显示登录界面与否
    private boolean mShow = false;
    //遮罩层显示隐藏监听器
    private OnMaskListener mListener = null;
    
    //滑动动画时间
    private int mDuration = 1000;
    
    private int mDownY = 0;
    private int mMoveY = 0;
    private int mScrollY = 0;
    private int mUpY = 0;
    
    public XCLoginView(Context context) {
        super(context);
        init(context);
    }
    public XCLoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public XCLoginView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    private void init(Context context) {
        // TODO Auto-generated method stub
        setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
        setFocusable(true);
        mScroller = new Scroller(context);
        mScreenWith = Utils.getWindowWidth(context);
        mScreenHeight = Utils.getWindowHeigh(context);
        final View loginView = LayoutInflater.from(context).inflate(R.layout.xc_login_view,null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        addView(loginView,params);
        loginView.post(new Runnable() {
            
            @Override
            public void run() {
                // TODO Auto-generated method stub
                mViewHeight = loginView.getHeight();
                XCLoginView.this.scrollTo(0, mScreenHeight);
            }
        });
        ImageView btn_close = (ImageView)loginView.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dismiss();
            }
        });
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = (int) event.getY();
                if(isShow()){
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveY = (int) event.getY();
                mScrollY = mMoveY - mDownY;
                if(mScrollY > 0 && mScrollY <= mViewHeight){
                    //向下滚动
                    if(mShow){
                        scrollTo(0, -Math.abs(mScrollY));
                    }
                }else{
                    //向上滑动
                    if(mScreenHeight - this.getTop() <= mViewHeight && (!mShow)){
                        scrollTo(0, mViewHeight - mScrollY);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mUpY = (int) event.getY();
                if(isShow()){
                    if( this.getScrollY() <= -(mViewHeight /2)){
                        startScroll(this.getScrollY(),-(mViewHeight - this.getScrollY()), mDuration);
                        mShow = false;
                    } else {
                        startScroll(this.getScrollY(), -this.getScrollY(), mDuration);
                        mShow = true;
                    }
                }
                changeMaskState();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
    /**
     * 关闭登录界面
     */
    public void dismiss() {
        // TODO Auto-generated method stub
        if(!isShow() && !mIsMoving)
            return;
        startScroll(0, -mViewHeight, mDuration);
        mListener.onDismiss();
        mShow = false;
    }
    /**
     * 打开登录界面
     */
    public void show(){
        if(isShow() && !mIsMoving)
            return;
        startScroll(-mViewHeight, mViewHeight, mDuration);
        mListener.onShow();
        mShow = true;
        
    }
    public boolean isShow(){
        return mShow;
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
    /**
     * 拖动移动
     * @param startY
     * @param dy
     * @param duration
     */
    public void startScroll(int startY, int dy,int duration){
        mIsMoving = true;
        mScroller.startScroll(0, startY, 0, dy,duration);
        invalidate();
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        return super.onInterceptTouchEvent(ev);
    }
    
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        super.onLayout(changed, l, t, r, b);
    }
    /**
     * 设置遮罩层监听器
     * @param listener
     */
    public void setOnMaskListener(OnMaskListener listener){
        mListener = listener;
    }
    /**
     * 遮罩层开关监听器
     * @author caizhiming
     *
     */
    public interface OnMaskListener{
        void onShow();
        void onDismiss();
    }
    public void changeMaskState(){
        if(mShow){
            mListener.onShow();
        }else{
            mListener.onDismiss();
        }
    }
    
}
