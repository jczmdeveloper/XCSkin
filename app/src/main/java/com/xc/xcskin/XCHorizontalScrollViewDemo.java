package com.xc.xcskin;

import com.xc.xcskin.view.XCHorizontalScrollView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

public class XCHorizontalScrollViewDemo extends Activity {


	Button button;
	XCHorizontalScrollView horizontalScrollView;
	LinearLayout llViewList;
	int[] imagesID  = new int[5];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xc_horiziontal_scroll_view_demo);
		button = (Button)findViewById(R.id.btn);
		
		horizontalScrollView = (XCHorizontalScrollView)findViewById(R.id.horizontalScrollView);
		llViewList = (LinearLayout)findViewById(R.id.llViewList);
		imagesID[0] = R.drawable.pic1;
		imagesID[1] = R.drawable.pic2;
		imagesID[2] = R.drawable.pic3;
		imagesID[3] = R.drawable.pic4;
		imagesID[4] = R.drawable.pic5;
		
		
		LayoutParams lParams = new LinearLayout.LayoutParams(
				800, 400);
		lParams.setMargins(15, 5, 15, 5);
		for (int i = 0; i < imagesID.length; i++) {
			
			ImageView view = new ImageView(this);
			view.setLayoutParams(lParams);
			view.setScaleType(ScaleType.CENTER_CROP);
			view.setImageResource(imagesID[i]);
			llViewList.addView(view);
		}
		
	}
	

}
