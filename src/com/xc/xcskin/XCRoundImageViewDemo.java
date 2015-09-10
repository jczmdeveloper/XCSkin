package com.xc.xcskin;

import android.app.Activity;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.xc.xcskin.view.XCGestrueControllerView;
import com.xc.xcskin.view.XCGestrueControllerView.GestureCallBack;
import com.xc.xcskin.view.XCProgressBar;
import com.xc.xcskin.view.XCRoundImageView;

public class XCRoundImageViewDemo extends Activity {



	XCRoundImageView roundImageView;
	XCRoundImageView grayRoundImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xc_round_imageview_demo);
		
		roundImageView = (XCRoundImageView) findViewById(R.id.roundImageView);
		roundImageView.setVisibility(View.VISIBLE);
		roundImageView.setImageResource(R.drawable.roundimageview);
		
		grayRoundImageView =  (XCRoundImageView)findViewById(R.id.imageView);
		grayRoundImageView.setImageResource(R.drawable.roundimageview);
		grayRoundImageView.setGray(true);
		
	}
	

	

}
