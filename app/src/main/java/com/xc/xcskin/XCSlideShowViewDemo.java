package com.xc.xcskin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class XCSlideShowViewDemo extends Activity {



/**
 * ViewPager实现的轮播图广告自定义视图，如京东首页的广告轮播图效果；
 * 既支持自动轮播页面也支持手势滑动切换页面
 * @author caizhiming
 *
 */
	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xc_slideshow_view_demo);
		button = (Button)findViewById(R.id.btn);
		
	}
	

}
