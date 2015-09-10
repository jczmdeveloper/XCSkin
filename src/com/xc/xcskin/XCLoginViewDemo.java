package com.xc.xcskin;


import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.xc.xcskin.view.XCLoginView;
import com.xc.xcskin.view.XCLoginView.OnMaskListener;

public class XCLoginViewDemo extends Activity {

    private XCLoginView mLoginView;
    private View mViewMask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xc_login_view_demo);
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        mLoginView = (XCLoginView)findViewById(R.id.login_view);
        mViewMask = findViewById(R.id.view_mask);
        ImageView btn_login = (ImageView)findViewById(R.id.btn_login);
        btn_login.setClickable(true);
        btn_login.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(!mLoginView.isShow())
                    mLoginView.show();
            }
        });
        mLoginView.setEnabled(true);
        mViewMask.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mLoginView.isShow()){
                    mLoginView.dismiss();
                }
            }
        });
        mLoginView.setOnMaskListener(new OnMaskListener() {
            
            @Override
            public void onShow() {
                // TODO Auto-generated method stub
                mViewMask.setVisibility(View.VISIBLE);
            }
            
            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                mViewMask.setVisibility(View.GONE);
            }
        });
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode ==  KeyEvent.KEYCODE_BACK){
            if(mLoginView.isShow()){
                mLoginView.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
