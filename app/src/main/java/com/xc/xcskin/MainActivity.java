package com.xc.xcskin;

import com.xc.xcskin.view.XCCurtainView;
import com.xc.xcskin.view.XCLoginView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	

	Button btn_round_progress_bar_demo;
	Button btn_progress_bar_demo;
	Button btn_slide_show_demo;
    Button btn_horizon_scroll_view_demo;
    Button btn_aoto_newline_layout_demo;
    Button btn_drop_down_list_view_demo;
	Button btn_gesture_controller_view_demo;
	Button btn_scroll_title_View_demo;
	Button btn_round_imageview_demo;
	Button btn_round_rect_imageview_demo;
	Button btn_arc_progressbar_demo;
	Button btn_round_oval_imageview_demo;
	Button btn_slidemenu_view_demo;
	Button btn_sinking_view_demo;
	Button btn_round_oval_imageview_demo2;
	Button btn_guaguaka_view_demo;
	Button btn_arcmenu_view_demo;
	Button btn_flowlayout_demo;
	Button btn_curtain_view_demo;
	Button btn_login_view_demo;
	Button btn_round_pieview_demo;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initViews();
		setAction();
	}

	private void initViews() {
		btn_round_progress_bar_demo = (Button) findViewById(R.id.btn_round_progress_bar_demo);
		btn_progress_bar_demo = (Button)findViewById(R.id.btn_progress_bar_demo);
		btn_slide_show_demo = (Button) findViewById(R.id.btn_slide_show_demo);
		btn_horizon_scroll_view_demo = (Button)findViewById(R.id.btn_horizon_scroll_view_demo);
		btn_aoto_newline_layout_demo = (Button)findViewById(R.id.btn_aoto_newline_layout_demo);
		btn_drop_down_list_view_demo =(Button)findViewById(R.id.btn_drop_down_list_view_demo);
		btn_gesture_controller_view_demo = (Button)findViewById(R.id.btn_gesture_controller_view_demo);
		btn_scroll_title_View_demo = (Button)findViewById(R.id.btn_scroll_title_View_demo);
		btn_round_imageview_demo = (Button)findViewById(R.id.btn_round_imageview_demo);
		btn_round_rect_imageview_demo = (Button)findViewById(R.id.btn_round_rect_imageview_demo);
		btn_arc_progressbar_demo = (Button)findViewById(R.id.btn_arc_progressbar_demo);
		btn_round_oval_imageview_demo = (Button)findViewById(R.id.btn_round_oval_imageview_demo);
		btn_slidemenu_view_demo = (Button)findViewById(R.id.btn_slidemenu_view_demo);
		btn_sinking_view_demo = (Button)findViewById(R.id.btn_sinking_view_demo);
		btn_round_oval_imageview_demo2 = (Button)findViewById(R.id.btn_round_oval_imageview_demo2);
		btn_guaguaka_view_demo = (Button)findViewById(R.id.btn_guaguaka_view_demo);
		btn_arcmenu_view_demo = (Button)findViewById(R.id.btn_arcmenu_view_demo);
		btn_flowlayout_demo = (Button)findViewById(R.id.btn_flowlayout_demo);
		btn_curtain_view_demo = (Button)findViewById(R.id.btn_curtain_view_demo);
		btn_login_view_demo = (Button)findViewById(R.id.btn_login_view_demo);
		btn_round_pieview_demo = (Button)findViewById(R.id.btn_round_pieview_demo);
	}

	private void setAction() {
		btn_round_progress_bar_demo.setOnClickListener(btnListener);
		btn_progress_bar_demo.setOnClickListener(btnListener);
		btn_slide_show_demo.setOnClickListener(btnListener);
		btn_horizon_scroll_view_demo.setOnClickListener(btnListener);
		btn_aoto_newline_layout_demo.setOnClickListener(btnListener);
		btn_drop_down_list_view_demo.setOnClickListener(btnListener);
		btn_gesture_controller_view_demo.setOnClickListener(btnListener);
		btn_scroll_title_View_demo.setOnClickListener(btnListener);
		btn_round_imageview_demo.setOnClickListener(btnListener);
		btn_round_rect_imageview_demo.setOnClickListener(btnListener);
		btn_arc_progressbar_demo.setOnClickListener(btnListener);
		btn_round_oval_imageview_demo.setOnClickListener(btnListener);
		btn_slidemenu_view_demo.setOnClickListener(btnListener);
		btn_sinking_view_demo.setOnClickListener(btnListener);
		btn_round_oval_imageview_demo2.setOnClickListener(btnListener);
		btn_guaguaka_view_demo.setOnClickListener(btnListener);
		btn_arcmenu_view_demo.setOnClickListener(btnListener);
		btn_flowlayout_demo.setOnClickListener(btnListener);
		btn_curtain_view_demo.setOnClickListener(btnListener);
		btn_login_view_demo.setOnClickListener(btnListener);
		btn_round_pieview_demo.setOnClickListener(btnListener);
	}

	public OnClickListener btnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			if (v.equals(btn_round_progress_bar_demo)) {
				intent = new Intent(MainActivity.this,
						XCRoundProgressBarDemo.class);
				
			}else if(v.equals(btn_progress_bar_demo)){
				intent = new Intent(MainActivity.this,XCProgressBarDemo.class);
			}
			else if(v.equals(btn_slide_show_demo)){
				intent = new Intent(MainActivity.this,
						XCSlideShowViewDemo.class);
			}
			else if(v.equals(btn_horizon_scroll_view_demo)){
				intent = new Intent(MainActivity.this,XCHorizontalScrollViewDemo.class);
			}else if(v.equals(btn_aoto_newline_layout_demo)){
				intent = new Intent(MainActivity.this,XCAutoNewlineLayoutDemo.class);
			}else if(v.equals(btn_drop_down_list_view_demo)){
				intent = new Intent(MainActivity.this,XCDropDownListViewDemo.class);
			}else if(v.equals(btn_gesture_controller_view_demo)){
				intent = new Intent(MainActivity.this,XCGestureControllerViewDemo.class);
			}else if(v.equals(btn_scroll_title_View_demo)){
				intent = new Intent(MainActivity.this,XCScrollTitleViewDemo.class);
			}else if(v.equals(btn_round_imageview_demo)){
				intent = new Intent(MainActivity.this,XCRoundImageViewDemo.class);
			}else if(v.equals(btn_round_rect_imageview_demo)){
				intent = new Intent(MainActivity.this,XCRoundRectImageViewDemo.class);
			}else if(v.equals(btn_arc_progressbar_demo)){
				intent = new Intent(MainActivity.this,XCArcProgressBarDemo.class);
			}else if(v.equals(btn_round_oval_imageview_demo)){
			    intent = new Intent(MainActivity.this,XCRoundAndOvalImageViewDemo.class);
			}else if(v.equals(btn_slidemenu_view_demo)){
			    intent = new Intent(MainActivity.this,XCSlideMenuViewDemo.class);
			}else if(v.equals(btn_sinking_view_demo)){
			    intent = new Intent(MainActivity.this,XCSinkingViewDemo.class);
			}else if(v.equals(btn_round_oval_imageview_demo2)){
			    intent = new Intent(MainActivity.this,XCRoundAndOvalImageViewDemo2.class);
			}else if(v.equals(btn_guaguaka_view_demo)){
			    intent = new Intent(MainActivity.this,XCGuaguakaViewDemo.class);
			}else if(v.equals(btn_arcmenu_view_demo)){
			    intent = new Intent(MainActivity.this,XCArcMenuViewDemo.class);
			}else if(v.equals(btn_flowlayout_demo)){
			    intent = new Intent(MainActivity.this,XCFlowlayoutDemo.class);
			}else if(v.equals(btn_curtain_view_demo)){
			    intent = new Intent(MainActivity.this,XCCurtainViewDemo.class);
			}else if(v.equals(btn_login_view_demo)){
			    intent = new Intent(MainActivity.this,XCLoginViewDemo.class);
			}else if(v.equals(btn_round_pieview_demo)){
			    intent = new Intent(MainActivity.this,XCRoundPieViewDemo.class);
			}
			
			startActivity(intent);

		}
	};
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler(){
		public void handleMessage(Message msg){
			 switch (msg.what) {
			 
			 }
		}
	};
	boolean willExit = false;
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		Runnable resetFlag = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				willExit = false;
			}
		};
		
		if(!willExit){
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			willExit = true;
			handler.postDelayed(resetFlag, 2*1000);
		}else{
			handler.removeCallbacks(resetFlag);
			finish();
		}
	}
	
	

}
