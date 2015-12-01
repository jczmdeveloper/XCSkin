package com.xc.xcskin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
/**
 * 使用Xfermode渲染方案实现圆角矩形、椭圆ImageView
 * @author caizhiming
 *
 */
public class XCRoundImageViewByXfermode extends ImageView{

    //数据定义
    private Paint mPaint;  
    private Xfermode mXfermode = new PorterDuffXfermode(Mode.DST_IN);  
    private Bitmap mMaskBitmap; //用来做
    private int mRoundBorderRadius;//圆角大小
    private int mType;//类型：圆形、圆角或椭圆
    
    private WeakReference<Bitmap> mBufferBitmap;//使用缓存技术，避免每次都执行onDraw
    public static final int TYPE_CIRCLE = 1;//圆形
    public static final int TYPE_ROUND = 2;//圆角矩形
    public static final int TYPE_OVAL = 3;//椭圆形
    private static final int DEFAULT_ROUND_BORDER_RADIUS = 10;//默认圆角大小 
    
    //构造方法
    public XCRoundImageViewByXfermode(Context context){
        this(context, null);
    }
    public XCRoundImageViewByXfermode(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//设置消除锯齿
        mType = TYPE_CIRCLE;
        mRoundBorderRadius = DEFAULT_ROUND_BORDER_RADIUS;
    }

    /**
     * 测量view的大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //如果类型是圆形，则强制设置view的宽高一致，取宽高的较小值 
        if(mType == TYPE_CIRCLE){
            int width = Math.min(getMeasuredWidth(),getMeasuredHeight());
            setMeasuredDimension(width, width);
        }
    }
    /**
     * 绘制view的内容
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        //从缓存中取出bitmap
        Bitmap bmp = (mBufferBitmap == null ? null:mBufferBitmap.get());
        if(bmp == null || bmp.isRecycled()){
            //如果没有缓存存在的情况
            //获取drawable
            Drawable drawable = getDrawable();
            //获取drawable的宽高
            int dwidth = drawable.getIntrinsicWidth();
            int dheight = drawable.getIntrinsicHeight();
            Log.v("czm","dwidth="+dwidth+",width="+getWidth());
            if(null != drawable){
                bmp = Bitmap.createBitmap(getWidth(), getHeight(),  
                        Config.ARGB_8888); 
                float scale = 1.0f;
                //创建画布
                Canvas drawCanvas = new Canvas(bmp);
                //按照bitmap的宽高，以及view的宽高，计算缩放比例；因为设置的src宽高
                //比例可能和imageview的宽高比例不同，这里我们不希望图片失真；  
                
                if(mType == TYPE_CIRCLE)  
                {//如果是圆形  
                    scale = getWidth() * 1.0F / Math.min(dwidth, dheight);  
                }else if (mType == TYPE_ROUND || mType == TYPE_OVAL)  
                {//如果是圆角矩形或椭圆 
                    // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；
                    //缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；  
                    scale = Math.max(getWidth() * 1.0f / dwidth, getHeight()  
                            * 1.0f / dheight);  
                }
                Log.v("czm","scale="+scale);
                //根据缩放比例，设置bounds，即相当于做缩放图片  
                drawable.setBounds(0, 0, (int)(scale * dwidth), (int)(scale * dheight));
                drawable.draw(drawCanvas);
                //获取bitmap，即圆形、圆角或椭圆的bitmap
                if(mMaskBitmap == null || mMaskBitmap.isRecycled()){
                    mMaskBitmap = getDrawBitmap();
                }
                //为paint设置Xfermode 渲染模式
                mPaint.reset();
                mPaint.setFilterBitmap(false);
                mPaint.setXfermode(mXfermode);
                //绘制不同形状
                drawCanvas.drawBitmap(mMaskBitmap, 0, 0,mPaint);
                mPaint.setXfermode(null);
                
                //将准备好的bitmap绘制出来  
                canvas.drawBitmap(bmp, 0, 0, null);  
                //bitmap缓存起来，避免每次调用onDraw，分配内存  
                mBufferBitmap = new WeakReference<Bitmap>(bmp);
                drawable.setCallback(null);
            }
            bmp.recycle();
        }else{
            //如果缓存还存在的情况
            mPaint.setXfermode(null);  
            canvas.drawBitmap(bmp, 0.0f, 0.0f, mPaint);
            bmp.recycle();
            return; 
        }

    }
    /**
     * 绘制不同的图形Bitmap
     */
    private Bitmap getDrawBitmap(){
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(),  
                Bitmap.Config.ARGB_8888);  
        Canvas canvas = new Canvas(bitmap);  
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);  
        paint.setColor(Color.BLACK);  
  
        if(mType == TYPE_CIRCLE)
        {//绘制圆形 
            canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2,  
                    paint); 
        }else if(mType == TYPE_ROUND)  
        {//绘制圆角矩形
            canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()),  
                    mRoundBorderRadius, mRoundBorderRadius, paint);  
        }else if(mType == TYPE_OVAL ){
         //绘制椭圆
            canvas.drawOval(new RectF(0, 0, getWidth(), getHeight()), mPaint);
        }
  
        return bitmap;  
    }
    /**
     * 因为使用了缓存技术，所以需要在invalidate中做些回收释放资源的处理
     */
    @Override
    public void invalidate() {
        // TODO Auto-generated method stub
        mBufferBitmap = null;
        if(mMaskBitmap != null){
            mMaskBitmap.recycle();
            mMaskBitmap = null;
        }
        super.invalidate();
    }
    
    public int getRoundBorderRadius() {
        return mRoundBorderRadius;
    }
    public void setRoundBorderRadius(int mRoundBorderRadius) {
        if(this.mRoundBorderRadius != mRoundBorderRadius){
            this.mRoundBorderRadius = mRoundBorderRadius;
            invalidate();
        }
    }
    public int getType() {
        return this.mType;
    }
    public void setType(int mType) {
        if(this.mType != mType){
            this.mType = mType;
            invalidate();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mMaskBitmap.recycle();
        mMaskBitmap = null;
    }
}
