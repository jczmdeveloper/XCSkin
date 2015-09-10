package com.xc.xcskin;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;

import com.xc.xcskin.view.XCFlowLayout;

public class XCFlowlayoutDemo extends Activity {

    private String mNames[] = {
            "welcome","android","TextView",
            "apple","jamy","kobe bryant",
            "jordan","layout","viewgroup",
            "margin","padding","text",
            "name","type","search","logcat"
    };
    private XCFlowLayout mFlowLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xc_flowlayout_demo);
        
        initChildViews();
        
    }
    private void initChildViews() {
        // TODO Auto-generated method stub
        mFlowLayout = (XCFlowLayout) findViewById(R.id.flowlayout);
        MarginLayoutParams lp = new MarginLayoutParams(
                LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for(int i = 0; i < mNames.length; i ++){
            TextView view = new TextView(this);
            view.setText(mNames[i]);
            view.setTextColor(Color.WHITE);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.xcflowlayout_textview_bg));
            mFlowLayout.addView(view,lp);
        }
    }

}
