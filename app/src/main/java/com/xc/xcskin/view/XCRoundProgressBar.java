package com.xc.xcskin.view;

import com.xc.xcskin.R;
import com.xc.xcskin.R.styleable;

import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * 带进度百分比显示的进度条，线程安全的View，可直接在线程中更新进度
 * @author caizhiming
 *
 */
public class XCRoundProgressBar extends View{

	
	
	private Paint paint;//画笔对象的引用
	private int roundColor;//圆环的颜色
	private int roundProgressColor;//圆环进度的颜色
	private int innerRoundColor;//圆环内部圆颜色
	private float roundWidth;//圆环的宽度
	private int textColor;//中间进度百分比字符串的颜色
	private float textSize ;//中间进度百分比字符串的字体
	private int max;//最大进度
	private int progress;//当前进度
	private boolean isDisplayText;//是否显示中间百分比进度字符串
	private int style;//进度条的风格：空心圆环或者实心圆环
	private static final int STROKE = 0;//空心
	private static final int FILL = 1;//实心
	
	public XCRoundProgressBar(Context context){
		this(context, null);
	}
	public XCRoundProgressBar(Context context,AttributeSet attrs){
		this(context,attrs,0);
	}
	public XCRoundProgressBar(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		
		paint  =  new Paint();
		//从attrs.xml中获取自定义属性和默认值
		TypedArray typedArray  = context.obtainStyledAttributes(attrs, R.styleable.XCRoundProgressBar);
		roundColor = typedArray.getColor(R.styleable.XCRoundProgressBar_roundColor, Color.GREEN);
		roundProgressColor = typedArray.getColor(R.styleable.XCRoundProgressBar_roundProgressColor, Color.RED);
		innerRoundColor = typedArray.getColor(R.styleable.XCRoundProgressBar_innerRoundColor, Color.GRAY);
		roundWidth = typedArray.getDimension(R.styleable.XCRoundProgressBar_roundWidth, 5);
		textColor  =typedArray.getColor(R.styleable.XCRoundProgressBar_textColor, Color.RED);
		textSize = typedArray.getDimension(R.styleable.XCRoundProgressBar_textSize, 15);
		max = typedArray.getInteger(R.styleable.XCRoundProgressBar_max, 100);
		style = typedArray.getInt(R.styleable.XCRoundProgressBar_style, STROKE);
		isDisplayText  =typedArray.getBoolean(R.styleable.XCRoundProgressBar_textIsDisplayable, true);
		typedArray.recycle();
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		//画最外层大圆环
		int centerX = getWidth()/2;//获取中心点X坐标
		int certerY = getHeight()/2;//获取中心点Y坐标
		int radius =(int)(centerX - roundWidth/2);//圆环的半径
		paint.setColor(roundColor);
		paint.setStyle(Paint.Style.STROKE);//设置空心
		paint.setStrokeWidth(roundWidth);//设置圆环宽度
		paint.setAntiAlias(true);//消除锯齿
		canvas.drawCircle(centerX,certerY, radius, paint);//绘制圆环
		
		//绘制圆环内部圆
		paint.setColor(innerRoundColor);
		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);
		canvas.drawCircle(centerX, certerY, radius-roundWidth/2, paint);
		
		
		
		//画进度
		paint.setStrokeWidth(roundWidth);//设置圆环宽度
		paint.setColor(roundProgressColor);//设置进度颜色
		RectF oval = new RectF(centerX - radius, centerX - radius, centerX 
                + radius, centerX + radius);  //用于定义的圆弧的形状和大小的界限 
		switch (style) {
		case STROKE: {
			paint.setStyle(Paint.Style.STROKE);
			canvas.drawArc(oval, 0, 360 * progress / max, false, paint); // 根据进度画圆弧
			break;
		}
		case FILL: {
			paint.setStyle(Paint.Style.FILL);
			if (progress != 0)
				canvas.drawArc(oval, 0, 360 * progress / max, true, paint); // 根据进度画圆弧
			break;
		}
		}
		//画中间进度百分比字符串
		paint.setStrokeWidth(0);
		paint.setColor(textColor);
		paint.setTextSize(textSize);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		int percent = (int)(((float)progress / (float)max) * 100);//计算百分比 
		float textWidth = paint.measureText(percent +"%");//测量字体宽度，需要居中显示
		
		if(isDisplayText && style == STROKE && percent != 0){
			canvas.drawText(percent+"%", centerX-textWidth/2, centerX + textSize/2, paint);
		}
		
		
	}
	public Paint getPaint() {
		return paint;
	}
	public void setPaint(Paint paint) {
		this.paint = paint;
	}
	public int getRoundColor() {
		return roundColor;
	}
	public void setRoundColor(int roundColor) {
		this.roundColor = roundColor;
	}
	public int getRoundProgressColor() {
		return roundProgressColor;
	}
	public void setRoundProgressColor(int roundProgressColor) {
		this.roundProgressColor = roundProgressColor;
	}
	public float getRoundWidth() {
		return roundWidth;
	}
	public void setRoundWidth(float roundWidth) {
		this.roundWidth = roundWidth;
	}
	public int getTextColor() {
		return textColor;
	}
	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}
	public float getTextSize() {
		return textSize;
	}
	public void setTextSize(float textSize) {
		this.textSize = textSize;
	}
	public synchronized int getMax() {
		return max;
	}
	public synchronized void setMax(int max) {
		if(max < 0){
			throw new IllegalArgumentException("max must more than 0");
		}
		this.max = max;
	}
	public synchronized int getProgress() {
		return progress;
	}
	/**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postInvalidate()能在非UI线程刷新
     * @author caizhiming
     */ 
	public synchronized void setProgress(int progress) {
		if(progress < 0){
			throw new IllegalArgumentException("progress must more than 0");
		}
		if(progress > max){
			this.progress = progress;
		}
		if(progress <= max){
			this.progress = progress;
			postInvalidate();
		}
	}
	public boolean isDisplayText() {
		return isDisplayText;
	}
	public void setDisplayText(boolean isDisplayText) {
		this.isDisplayText = isDisplayText;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}

	
}
