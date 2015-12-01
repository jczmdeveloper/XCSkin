package com.xc.xcskin;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.xc.xcskin.view.XCGestrueControllerView;
import com.xc.xcskin.view.XCGestrueControllerView.GestureCallBack;
import com.xc.xcskin.view.XCProgressBar;

public class XCGestureControllerViewDemo extends Activity {

	XCGestrueControllerView gestrueControllerView ;

	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xc_gesture_controller_view_demo);
		gestrueControllerView  = (XCGestrueControllerView) findViewById(R.id.gestureControllerView);
		gestrueControllerView.setOnGestureCallBack(new GestureCallBack() {
			
			@Override
			public void doGesture(int type) {
				// TODO Auto-generated method stub
				if(type == KeyEvent.KEYCODE_BACK){
					Toast.makeText(XCGestureControllerViewDemo.this, "先下后上手势（后退/返回键）", Toast.LENGTH_SHORT).show();
				}else if(type == KeyEvent.KEYCODE_MENU){
					Toast.makeText(XCGestureControllerViewDemo.this, "先上后下手势（菜单键）", Toast.LENGTH_SHORT).show();
				}else if(type == KeyEvent.KEYCODE_VOLUME_DOWN){
					Toast.makeText(XCGestureControllerViewDemo.this, "先左后右手势（音量-键）", Toast.LENGTH_SHORT).show();
				}else if(type == KeyEvent.KEYCODE_VOLUME_UP){
					Toast.makeText(XCGestureControllerViewDemo.this, "先右后左手势（音量+键）", Toast.LENGTH_SHORT).show();
				}else if (type == KeyEvent.KEYCODE_DPAD_UP) {
                    Toast.makeText(XCGestureControllerViewDemo.this, "上滑手势", Toast.LENGTH_SHORT).show();
                } else if (type == KeyEvent.KEYCODE_DPAD_DOWN) {
                	Toast.makeText(XCGestureControllerViewDemo.this, "下滑手势", Toast.LENGTH_SHORT).show();
                } else if (type == KeyEvent.KEYCODE_DPAD_LEFT) {
                	Toast.makeText(XCGestureControllerViewDemo.this, "左滑手势", Toast.LENGTH_SHORT).show();
                } else if (type == KeyEvent.KEYCODE_DPAD_RIGHT) {
                	Toast.makeText(XCGestureControllerViewDemo.this, "右滑手势", Toast.LENGTH_SHORT).show();
                } else if (type == KeyEvent.KEYCODE_DPAD_CENTER) {
                	Toast.makeText(XCGestureControllerViewDemo.this, "点击手势", Toast.LENGTH_SHORT).show();
                }
			}
		});
		
	}
	

	

}
