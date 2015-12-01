package com.xc.xcskin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xc.xcskin.view.XCArcMenuView;
import com.xc.xcskin.view.XCArcMenuView.OnMenuItemClickListener;
import com.xc.xcskin.view.XCGuaguakaView;
import com.xc.xcskin.view.XCGuaguakaView.OnCompleteListener;

/**
 * 使用并测试自定义卫星式菜单View
 * @author caizhiming
 *
 */
public class XCArcMenuViewDemo extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xc_arcmenu_view_demo);
        XCArcMenuView view = (XCArcMenuView) findViewById(R.id.arcmenu);
        view.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            
            @Override
            public void onClick(View view, int pos) {
                // TODO Auto-generated method stub
                String tag = (String) view.getTag();
                Toast.makeText(XCArcMenuViewDemo.this, tag, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
