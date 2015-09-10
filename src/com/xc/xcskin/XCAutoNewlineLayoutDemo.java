package com.xc.xcskin;

import com.xc.xcskin.view.XCAutoNewlineLayout;
import com.xc.xcskin.view.XCHorizontalScrollView;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

public class XCAutoNewlineLayoutDemo extends Activity {

	Button button;
	XCAutoNewlineLayout autoNewlineLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xc_auto_newline_layout_demo);
		button = (Button) findViewById(R.id.btn);

		autoNewlineLayout = (XCAutoNewlineLayout) findViewById(R.id.auto_newline_layout);

		for (int i = 0; i < 35; i++) {
			TextView view = new TextView(this);
			view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			view.setBackgroundColor(Color.argb(200, 5, 125, 200));
			view.setTextColor(Color.WHITE);
			view.setPadding(10, 10, 10, 10);
			if(i%2 == 0){
				view.setText("我是偶数："+i);
			}else if(i%3 == 0){
				view.setText("我是3倍数："+i);
			}else {
				view.setText("我是数："+i);
			}
			
			view.setTextSize(15);
			view.setGravity(Gravity.CENTER);
			autoNewlineLayout.addView(view);
		}

	}

}
