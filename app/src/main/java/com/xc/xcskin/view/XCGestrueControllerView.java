package com.xc.xcskin.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * 自定义手势控制器View 
 * @author caizhiming
 *
 */
public class XCGestrueControllerView extends View implements OnTouchListener,OnGestureListener{


	private static final int FLING_MIN_DISTANCE = 20;// 移动最小距离

	//定义绘制数据
    private Paint paint;
    private float startX;
    private float startY;
    private float stopX;
    private float stopY;
    private int   bgColor;//背景颜色
    private int   gestureColor;//手势线颜色
    private boolean drawing;//是否在画手势线中
    private Path path;
    
    
    private float minX;
    private float minY;
    private float maxX;
    private float maxY;
    private float beginX;
    private float beginY;
    private float endX;
    private float endY;
    
    private static final float TOUCH_DISTANCE = 1;//每次移动的最小距离
    
	public XCGestrueControllerView(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}
	public XCGestrueControllerView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}
	public XCGestrueControllerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		
		bgColor = Color.rgb(80,80,80);
		gestureColor = Color.rgb(42, 178, 255);
		
		this.setBackgroundColor(bgColor);
		
		paint = new Paint();
		path = new Path();
		drawing = false;
		this.setOnTouchListener(this);
		this.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(10);
		Rect rect = new Rect(0,0,getWidth(),getHeight());
		
		if(drawing){
			paint.setColor(gestureColor);
			paint.setStyle(Paint.Style.STROKE);
			canvas.drawPath(path, paint);
		}else{
			paint.setColor(bgColor);
			paint.setStyle(Paint.Style.FILL);
			canvas.drawRect(rect, paint);
		}
		
	}
	
	
	
	//构建手势探测器
    GestureDetector mygesture = new GestureDetector(this);
	
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return mygesture.onTouchEvent(event);
	}
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		drawing = true;
		beginX = startX = e.getX();
		beginY = startY = e.getY();
		minX = minY = startX;
		maxX = maxY = startY;
		
		path.moveTo(startX, startY);
		
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		//按下去不移动且不LongPress时候触发，即点击
		path.reset();
		drawing = false;
		setGesture(KeyEvent.KEYCODE_DPAD_CENTER);
		return false;
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		
		stopX = e2.getX();
		stopY = e2.getY();
		float dx = Math.abs(stopX-startX);
		float dy = Math.abs(stopY-stopY);
		if(dx >=TOUCH_DISTANCE || dy >= TOUCH_DISTANCE){
			
			if(stopX < startX){
				minX = stopX;
			}else{
				maxX = stopX;
			}
			if(stopY < startY){
				minY = stopY;
			}else{
				maxY =stopY;
			}
			
			path.lineTo(stopX, stopY);
			startX = stopX;
			startY = stopY;
			
			
			
			postInvalidate();
		}
		
		
		return false;
	}
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		
		// e1：第1个ACTION_DOWN MotionEvent
        // e2：最后一个ACTION_MOVE MotionEvent   
		
		endX = stopX = e2.getX();
		endY = stopY = e2.getY();
		

		Log.v("XCGestrueControllerView", "begin("+beginX+","+beginY+")"+"&"+"end("+endX+","+endY+")"+
		"&"+"Min("+minX+","+minY+")"+"&Max("+maxX+","+maxY+")");
		
		if(maxY > beginY && maxX > endY && beginX < endX){
			//先下滑后上滑手势
			setGesture(KeyEvent.KEYCODE_BACK);
		}else if(minY < beginY && minY < endY && beginX < endX){
			//先上滑后下滑手势
			setGesture(KeyEvent.KEYCODE_MENU);
			
		}else if(minX < beginX && minX < endX && beginY < endY){
			//先左滑后右滑手势
			setGesture(KeyEvent.KEYCODE_VOLUME_DOWN);
			
		}else if(maxX > beginX && maxX > endX && beginY < endY){
			//先右滑后左滑手势
			setGesture(KeyEvent.KEYCODE_VOLUME_UP);
			
		}else if (Math.abs(e1.getY() - e2.getY()) >  Math.abs(e2.getX()-e1.getX())) {
        	//上下方向
        	if(e1.getY()-e2.getY() >FLING_MIN_DISTANCE){
        		//由下到上滑动
        		setGesture(KeyEvent.KEYCODE_DPAD_UP);
        	}else if(e2.getY() - e1.getY() >FLING_MIN_DISTANCE){
        		//由上到下滑动
        		setGesture(KeyEvent.KEYCODE_DPAD_DOWN);
        	}
            
        }else
        {//左右方向
        	if(e1.getX() -e2.getX() <FLING_MIN_DISTANCE){
        		//由左到右滑动
        		setGesture(KeyEvent.KEYCODE_DPAD_RIGHT);
        	}else if(e2.getX()- e1.getX() < FLING_MIN_DISTANCE){
        		//由右到左滑动
        		setGesture(KeyEvent.KEYCODE_DPAD_LEFT);
        	}
        }
        path.reset();
        drawing = false;
        postInvalidate();
		return false;
	}
	private void setGesture(int type){
		if(mGestureCallBack !=null){
			mGestureCallBack.doGesture(type);
		}
	}
	/**
     * **************** 手势操作回调函数 *******************
     */
    private GestureCallBack mGestureCallBack = null;

    public interface GestureCallBack {
        void doGesture(int type);
    }

    public void setOnGestureCallBack(GestureCallBack gestureCallBack) {
        this.mGestureCallBack = gestureCallBack;
    }

}
