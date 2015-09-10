package com.xc.xcskin.view;

import com.xc.xcskin.R;
import com.xc.xcskin.R.styleable;

import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * 带进度百分比显示的进度条，线程安全的View，可直接在线程中更新进度
 * @author caizhiming
 *
 */
public class XCProgressBar extends View{

	
	
	private Paint paint;//画笔对象的引用
	private int color;//背景颜色
	private int progressColor;//进度的颜色
	private float roundWidth;//圆环的宽度
	private int textColor;//中间进度百分比字符串的颜色
	private float textSize ;//中间进度百分比字符串的字体
	private int max;//最大进度
	private int progress;//当前进度
	private boolean isDisplayText;//是否显示中间百分比进度字符串
	
	public XCProgressBar(Context context){
		this(context, null);
	}
	public XCProgressBar(Context context,AttributeSet attrs){
		this(context,attrs,0);
	}
	public XCProgressBar(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		
		paint  =  new Paint();
		color = getResources().getColor(R.color.progressbar_progress_color);
		progressColor = getResources().getColor(R.color.progressbar_color);
		textColor  =getResources().getColor(R.color.white);
		textSize = getResources().getDimension(R.dimen.progressbar_text);
		max = 100;
		isDisplayText = true;
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		//绘制进度条背景
		int centerX = getWidth()/2;
		int centerY  =getHeight()/2;
		int width = getWidth();
		int height = getHeight();
		Rect r = new Rect(0, 0, width, height);
		paint.setColor(color);
		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);
		canvas.drawRect(r, paint);
		
		//画进度
		paint.setColor(progressColor);//设置进度颜色
		width = width * progress/max;
		
		r = new Rect(0, 0, width, height);
		canvas.drawRect(r, paint);
		
		//画中间进度百分比字符串
		paint.setStrokeWidth(0);
		paint.setColor(textColor);
		paint.setTextSize(textSize);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		int percent = (int)(((float)progress / (float)max) * 100);//计算百分比 
		float textWidth = paint.measureText(percent +"%");//测量字体宽度，需要居中显示
		Rect bounds  =new Rect();
		paint.getTextBounds(percent +"%", 0, (percent+"%").length(), bounds);
		if(isDisplayText && percent != 0){
			canvas.drawText(percent+"%", centerX-textWidth/2, centerY +bounds.height()/2, paint);
		}
		
		
	}
	public Paint getPaint() {
		return paint;
	}
	public void setPaint(Paint paint) {
		this.paint = paint;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getRoundProgressColor() {
		return progressColor;
	}
	public void setRoundProgressColor(int progressColor) {
		this.progressColor = progressColor;
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

	
}
