package com.xc.xcskin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author caizhiming
 */
public class XCGridView extends GridView {

    public XCGridView(Context context) {
        super(context);
    }

    public XCGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    protected void onMeasure(int width, int height) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(width, expandSpec);
    }

}
