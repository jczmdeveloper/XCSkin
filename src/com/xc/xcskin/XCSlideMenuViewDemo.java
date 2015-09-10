package com.xc.xcskin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.xc.xcskin.view.XCSlideMenuView;

public class XCSlideMenuViewDemo extends Activity{

    private XCSlideMenuView xcSlideMenu;
    private TextView btnSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.xc_slide_menu_view_demo);
        xcSlideMenu = (XCSlideMenuView) findViewById(R.id.slideMenu);
        btnSwitch = (TextView)findViewById(R.id.btnSwitch);
        btnSwitch.setClickable(true);
        btnSwitch.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                xcSlideMenu.switchMenu();
            }
        });
        
    }
}
