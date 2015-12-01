package com.xc.xcskin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.xc.xcskin.R;

public class XCAutoNewlineLayout extends ViewGroup {
    private int line_height;
    public static final int DEFAULT_HORIZONTAL_SPACING = 5;
    public static final int DEFAULT_VERTICAL_SPACING = 5;

    private int horizontalSpacing;
    private int verticalSpacing;

    public XCAutoNewlineLayout(Context context) {
        super(context);
    }

    public XCAutoNewlineLayout(Context context, int horizontalSpacing, int verticalSpacing) {
        super(context);
        this.horizontalSpacing = horizontalSpacing;
        this.verticalSpacing = verticalSpacing;
    }

    public XCAutoNewlineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.XCAutoNewlineLayout);
        horizontalSpacing = styledAttributes.getDimensionPixelSize(R.styleable.XCAutoNewlineLayout_item_h_space, DEFAULT_HORIZONTAL_SPACING);
        verticalSpacing = styledAttributes.getDimensionPixelSize(R.styleable.XCAutoNewlineLayout_item_v_space, DEFAULT_VERTICAL_SPACING);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if ((MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.UNSPECIFIED))
            throw new AssertionError();

        final int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
        final int count = getChildCount();
        int line_height = 0;
        // int line_height = 22;

        int xpos = getPaddingLeft();
        int ypos = getPaddingTop();

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                child.measure(MeasureSpec.makeMeasureSpec(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT), MeasureSpec.makeMeasureSpec(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

                final int childw = child.getMeasuredWidth();
                line_height = Math.max(line_height, child.getMeasuredHeight() + verticalSpacing);

                if (xpos + childw > width) {
                    xpos = getPaddingLeft();
                    ypos += line_height;
                }

                xpos += childw + horizontalSpacing;
            }
        }
        this.line_height = line_height;

        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            height = ypos + line_height;

        } else if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            if (ypos + line_height < height) {
                height = ypos + line_height;
            }
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(1, 1); // 默认为1px间距
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p != null;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        final int width = r - l;
        int xpos = getPaddingLeft();
        int ypos = getPaddingTop();

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final int childw = child.getMeasuredWidth();
                final int childh = child.getMeasuredHeight();
                
                if (xpos + childw > width) {
                    xpos = getPaddingLeft();
                    ypos += line_height;
                }
                child.layout(xpos, ypos, xpos + childw, ypos + childh);
                xpos += childw + horizontalSpacing;
            }
        }
    }

}