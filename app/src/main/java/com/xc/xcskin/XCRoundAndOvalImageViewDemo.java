package com.xc.xcskin;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.xc.xcskin.view.XCRoundAndOvalImageView;

public class XCRoundAndOvalImageViewDemo extends Activity{

    private XCRoundAndOvalImageView circleImageView;//圆形图片
    private XCRoundAndOvalImageView roundRectImageView;//圆角矩形图片
    private XCRoundAndOvalImageView ovalImageView;//椭圆图片
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        //设置全屏  
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        setContentView(R.layout.xc_round_oval_imageview_demo);
        
        initViews();
    }
    /**
     * 初始化Views
     */
    private void initViews(){
        circleImageView = (XCRoundAndOvalImageView)findViewById(R.id.cicleImageView);
        roundRectImageView = (XCRoundAndOvalImageView)findViewById(R.id.roundRectImageView);
        ovalImageView = (XCRoundAndOvalImageView)findViewById(R.id.ovalImageView);
        
        roundRectImageView.setType(XCRoundAndOvalImageView.TYPE_ROUND);
        roundRectImageView.setRoundRadius(100);
        
        ovalImageView.setType(XCRoundAndOvalImageView.TYPE_OVAL);
        ovalImageView.setRoundRadius(50);
        
    }
}
