package com.xc.xcskin;

import android.app.Activity;
import android.os.Bundle;

import com.xc.xcskin.view.XCRoundImageViewByXfermode;

public class XCRoundAndOvalImageViewDemo2 extends Activity{

    private XCRoundImageViewByXfermode circleImageView;//圆形图片
    private XCRoundImageViewByXfermode roundRectImageView;//圆角矩形图片
    private XCRoundImageViewByXfermode ovalImageView;//椭圆图片
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xc_round_oval_imageview_demo2);
        initViews();
    }
    /**
     * 初始化Views
     */
    private void initViews(){
        circleImageView = (XCRoundImageViewByXfermode)findViewById(R.id.cicleImageView);
        roundRectImageView = (XCRoundImageViewByXfermode)findViewById(R.id.roundRectImageView);
        ovalImageView = (XCRoundImageViewByXfermode)findViewById(R.id.ovalImageView);

        roundRectImageView.setType(XCRoundImageViewByXfermode.TYPE_ROUND);
        roundRectImageView.setRoundBorderRadius(100);

        ovalImageView.setType(XCRoundImageViewByXfermode.TYPE_OVAL);
        ovalImageView.setRoundBorderRadius(30);
    }
}
