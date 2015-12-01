package com.xc.xcskin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.xc.xcskin.R;
/**
 * 自定义刮刮卡效果View
 * @author caizhiming
 *
 */
public class XCGuaguakaView extends View{

    /**
     * 定义使用到的数据
     */
    private Paint mOutterPaint;
    private Path  mPath;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private int mLastX;
    private int mLastY;
    private Bitmap mOutterBitmap;
    private Paint mOutBmpPaint;
    
    
    private String mText;
    private Paint mTextPaint;
    private int mTextSize;
    /**
     * 记录刮奖信息文本的宽高
     */
    private Rect  mTextBound;

    
    public final int TEXT_SIZE = 60;
    public final int SWIPE_PAINT_WIDTH = 30;
    
    private OnCompleteListener mOnCompleteListener;
    
    public void setOnCompleteListener(OnCompleteListener mOnCompleteListener) {
        this.mOnCompleteListener = mOnCompleteListener;
    }

    //判断遮盖层区域是否达到消除的比例
    private volatile boolean mCompleted = false;
    
    /**
     * 刮刮卡刮完之后的回调接口
     */
    public interface OnCompleteListener{
        void complete();
    }
    public XCGuaguakaView(Context context) {
        // TODO Auto-generated constructor stub
        this(context,null);
    }
    public XCGuaguakaView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        // TODO Auto-generated constructor stub
    }
    public XCGuaguakaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        initView();
    }
    /**
     * 进行初始化操作
     */
    private void initView(){
        mOutterPaint = new Paint();
        mOutBmpPaint = new Paint();
        mPath = new Path();
        mOutterBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ggk_bg);
        mText = "恭喜您，中了500万啦！";
        mTextBound = new Rect();
        mTextPaint = new Paint();
        mTextSize = TEXT_SIZE;
    }
    
    /**
     * 设置刮扫画笔的属性
     */
    private void setOutterPaint(){
        mOutterPaint.setColor(Color.parseColor("#c3c3c3"));
        mOutterPaint.setAntiAlias(true);
        mOutterPaint.setDither(true);
        mOutterPaint.setStrokeJoin(Join.ROUND);//设置圆角
        mOutterPaint.setStrokeCap(Cap.ROUND);
        mOutterPaint.setStyle(Style.STROKE);
        mOutterPaint.setStrokeWidth(SWIPE_PAINT_WIDTH);
    }
    /**
     * 设置绘制刮刮卡圆角背景的画笔属性
     */
    private void setOutBmpPaint(){
        mOutBmpPaint.setColor(Color.parseColor("#c3c3c3"));
        mOutBmpPaint.setAntiAlias(true);
        mOutBmpPaint.setDither(true);
        mOutBmpPaint.setStrokeJoin(Join.ROUND);//设置圆角
        mOutBmpPaint.setStrokeCap(Cap.ROUND);
        mOutBmpPaint.setStyle(Style.FILL);
        mOutBmpPaint.setStrokeWidth(20);
    }
    /**
     * 设置绘制刮奖文案信息的画笔属性
     */
    private void setTextPaint(){
        mTextPaint.setColor(Color.DKGRAY);
        mTextPaint.setStyle(Style.FILL);
        mTextPaint.setTextSize(mTextSize);
        //获取画笔绘制文本的宽高
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //初始化bitmap
        mBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        //初始化canvas
        mCanvas = new Canvas(mBitmap);
        
        //设置画笔的一些属性
        setOutterPaint();
        setOutBmpPaint();
        setTextPaint();
        //绘制一层刮刮卡圆角背景图层
        mCanvas.drawRoundRect(new RectF(0,0,width,height), 30, 30, mOutBmpPaint);
        mCanvas.drawBitmap(mOutterBitmap, null, new RectF(0,0,width,height),null);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        //绘制文字
        canvas.drawText(mText, getWidth()/2-mTextBound.width()/2, getHeight()/2+mTextBound.height()/2, mTextPaint);
        //刮扫完成回调
        if(mCompleted){
            if(null != mOnCompleteListener){
                mOnCompleteListener.complete();
            }
        }
        //判断是否完成，如果完成了就不绘制遮盖层
        if(!mCompleted){
            drawPath();
            canvas.drawBitmap(mBitmap,0,0,null);
        }
    }
    /**
     * 设置Xfermode模式为DST_OUT，并绘制扫的路径
     */
    private void drawPath() {
        // TODO Auto-generated method stub
        
        mOutterPaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
        
        mCanvas.drawPath(mPath, mOutterPaint);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);
                if(dx >3 || dy > 3){
                    mPath.lineTo(x, y);
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                new Thread(mRunnable).start();
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }
    /**
     * 起一个线程来计算已经扫的面积及占总区域的比例
     * 根据区域来判断是否完成
     */
    private Runnable mRunnable = new Runnable(){
        @Override
        public void run() {
            int w = getWidth();
            int h = getHeight();
            
            float wipeArea = 0;
            float totalArea = w * h ;
            
            Bitmap bitmap = mBitmap; 
            
            int[] mPixels = new int[w * h];
            //获取bitmap的所有像素信息
            bitmap.getPixels(mPixels, 0, w, 0, 0, w, h);
            for(int i= 0; i< w;i++)
                for(int j= 0; j< h;j++){
                    int index = i + j * w;
                    if(mPixels[index] == 0){
                        wipeArea ++;
                    }
                }
            //计算已扫区域所占的比例
            if(wipeArea >0 && totalArea > 0){
                int percent = (int) (wipeArea * 100 / totalArea);
                Log.v("czm", "percent="+percent);
                
                if(percent > 70){
                    //清除图层区域
                    mCompleted = true;
                    postInvalidate();
                    
                }
            }
        };
    };
}
