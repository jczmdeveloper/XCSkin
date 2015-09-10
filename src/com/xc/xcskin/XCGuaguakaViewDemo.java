package com.xc.xcskin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.xc.xcskin.view.XCGuaguakaView;
import com.xc.xcskin.view.XCGuaguakaView.OnCompleteListener;

/**
 * 使用并测试自定义刮刮卡效果View
 * @author caizhiming
 *
 */
public class XCGuaguakaViewDemo extends Activity{

    private XCGuaguakaView xcGuaguakaView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xc_guaguaka_view_demo);

        xcGuaguakaView = (XCGuaguakaView)findViewById(R.id.ggk);
        xcGuaguakaView.setOnCompleteListener(new OnCompleteListener() {
            
            @Override
            public void complete() {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "您已经刮的差不多啦", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
