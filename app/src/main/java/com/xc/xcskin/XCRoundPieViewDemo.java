package com.xc.xcskin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.xc.xcskin.view.XCRoundPieView;
/**
 * 仿微信清除存储空间的动态圆饼图View
 * @author caizhiming
 *
 */
public class XCRoundPieViewDemo extends Activity {

    XCRoundPieView xcRoundBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xc_round_pieview_demo);
        xcRoundBar = (XCRoundPieView) findViewById(R.id.xcRoundBar);
        findViewById(R.id.test).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                xcRoundBar.restart();
            }
        });
        
    }

	

}
