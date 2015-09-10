package com.xc.xcskin.view;

import com.xc.xcskin.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * 自定义仿UC设置页滑动标签View
 * @author caizhiming
 *
 */

@SuppressLint("NewApi")
public class XCScrollTitleView extends LinearLayout {
	
	//数据定义
	private static final String TAG = "ScrollTitleView";
	private int SIZE_NORMAL = 15;// 标题字体大小
	private int SIZE_SELECT = 16;// 标题选中时字体大小
	private int COLOR_NORMAL = Color.GRAY;//标题颜色
	private int COLOR_SELECT = Color.rgb(0, 162, 232);//标题选中时颜色
	

	private Context context;
	private LinearLayout titleLinear;// 标题所在行
	private TextView[] tvTitles;// 标题（个数取决于ViewPager的页数）
	private ImageView cursor;// 游标

	private ViewPager mViewPager;
	private int offset;// 游标初始偏移
	private int one;// ViewPager滑动一页后，游标要移动的距离
	private int pageCount = -1;// ViewPager页数

	private int screenW;// 屏幕宽度
	private int bmpW;// 游标本身宽度
	private int bmpRealW;// 游标自适应屏幕后的宽度
	private float bmpScaleW;// bitmap宽度缩放比例，用于matrix，值为bmpRealW/bmpW

	private OnScrollTitlePageChangeListener pageChangeListener;

	
	/**
	 * 设置ViewPager与组件关联
	 * 
	 * @param viewPager
	 */
	public void setViewPager(ViewPager viewPager) {
		mViewPager = viewPager;
		initWidget();
	}

	/**
	 * 更改标题，数组长度不能超过ViewPager页数
	 * 
	 * @param titles
	 */
	public void changeTitle(String[] titles) {
		if (titles == null || titles.length > pageCount) {
			Log.e(TAG, "changeTitle()参数传递错误");
			return;
		}
		for (int i = 0; i < titles.length; i++) {
			if (tvTitles == null || tvTitles.length != pageCount) {
				Log.e(TAG, "TextView组件错误");
				return;
			}
			tvTitles[i].setText(titles[i]);
		}
	}

	/**
	 * ViewPager组件的滑动事件不能再设置，可以使用该内部接口代替
	 */
	public interface OnScrollTitlePageChangeListener {
		public void onPageSelected(int position);

		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels);

		public void onPageScrollStateChanged(int state);
	}

	/**
	 * 设置ViewPager滑动事件监听
	 * 
	 * @param pageChangeListener
	 */
	public void setOnScrollTitlePageChangeListener(
			OnScrollTitlePageChangeListener pageChangeListener) {
		this.pageChangeListener = pageChangeListener;
	}

	/**
	 * Constructors
	 * @param context
	 */
	public XCScrollTitleView(Context context) {
		super(context);
		init(context);
	}

	public XCScrollTitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public XCScrollTitleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	/**
	 * 初始化数据
	 * @param context
	 */
	private void init(Context context) {
		this.context = context;
		// 初始化自已
		LayoutParams lproot = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		this.setOrientation(LinearLayout.VERTICAL);
		this.setLayoutParams(lproot);

		// 初始化标题行布局
		titleLinear = new LinearLayout(context);
		LayoutParams lpTlinear = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		titleLinear.setOrientation(LinearLayout.HORIZONTAL);
		titleLinear.setLayoutParams(lpTlinear);
		titleLinear.setPadding(0, 15, 0, 15);

		// 初始化屏幕参数
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		screenW = dm.widthPixels;// 获取分辨率宽度
	}

	/**
	 * 动态加入所有组件
	 */
	private void initWidget() {
		// 非空判断
		if (mViewPager != null) {
			PagerAdapter pagerAdapter = mViewPager.getAdapter();
			if (pagerAdapter != null) {
				pageCount = pagerAdapter.getCount();
				if (pageCount > 0) {
					// 动态加载TextView
					tvTitles = new TextView[pageCount];
					for (int i = 0; i < pageCount; i++) {
						tvTitles[i] = new TextView(context);
						LayoutParams lpText = new LayoutParams(
								LayoutParams.MATCH_PARENT,
								LayoutParams.WRAP_CONTENT, 1);
						tvTitles[i].setLayoutParams(lpText);
						tvTitles[i].setGravity(Gravity.CENTER);
						tvTitles[i].setText(pagerAdapter.getPageTitle(i)
								.toString());

						TextPaint tp = tvTitles[i].getPaint();
						if (i == 0) {
							tp.setFakeBoldText(true);
							tvTitles[i].setTextSize(SIZE_SELECT);
							tvTitles[i].setTextColor(COLOR_SELECT);
						} else {
							tp.setFakeBoldText(false);
							tvTitles[i].setTextSize(SIZE_NORMAL);
							tvTitles[i].setTextColor(COLOR_NORMAL);
						}

						// 设置点击事件
						final int fi = i;
						tvTitles[i].setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								mViewPager.setCurrentItem(fi);
							}
						});

						titleLinear.addView(tvTitles[i]);
					}
				}

				// 整体加入组件
				this.removeAllViews();
				this.addView(titleLinear);

				initCursor();
				initImageView();
				this.addView(cursor);

				setViewPagerListener();
			}
		}
	}

	/**
	 * 初始化ImageView
	 */
	private void initCursor() {
		cursor = new ImageView(context);
		LayoutParams lpCursor = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		cursor.setLayoutParams(lpCursor);
		cursor.setImageResource(R.drawable.navibar);
		cursor.setScaleType(ScaleType.MATRIX);
		cursor.setBackgroundColor(Color.parseColor("#CDCDCD"));
	}

	/**
	 * 初始化Bitmap及相关尺寸
	 */
	private void initImageView() {
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.navibar)
				.getWidth();// 获取图片宽度
		bmpRealW = bmpW > (screenW / pageCount) ? screenW / pageCount : bmpW;
		bmpRealW = bmpRealW*4/5;
		offset = (screenW / pageCount - bmpRealW) / 2;// 计算偏移量
		bmpScaleW = ((float) bmpRealW) / bmpW;

		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		matrix.postScale(bmpScaleW, 1.0f);

		cursor.setImageMatrix(matrix);// 设置动画初始位置
		one = offset * 2 + bmpRealW;// 页卡1 -> 页卡2 偏移量
	}

	/**
	 * 设置ViewPager监听事件
	 */
	private void setViewPagerListener() {
		if (mViewPager == null) {
			Log.e(TAG, "ViewPager为空");
			return;
		}

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				for (int i = 0; i < pageCount; i++) {
					TextPaint tp = tvTitles[i].getPaint();
					if (i == position) {
						tp.setFakeBoldText(true);
						tvTitles[i].setTextSize(SIZE_SELECT);
						tvTitles[i].setTextColor(COLOR_SELECT);
					} else {
						tp.setFakeBoldText(false);
						tvTitles[i].setTextSize(SIZE_NORMAL);
						tvTitles[i].setTextColor(COLOR_NORMAL);
					}
				}

				if (pageChangeListener != null) {
					pageChangeListener.onPageSelected(position);
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// 导航条图片随手指移动
				Matrix matrix = new Matrix();

				if (bmpScaleW != 1.0f)
					matrix.postScale(bmpScaleW, 1.0f);
				// position是当前的位置，从右向左滑动时positionOffset为负数
				matrix.postTranslate(offset + one * position + one
						* positionOffset, 0);

				cursor.setImageMatrix(matrix);

				if (pageChangeListener != null) {
					pageChangeListener.onPageScrolled(position, positionOffset,
							positionOffsetPixels);
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// if (state == ViewPager.SCROLL_STATE_DRAGGING) {}

				if (pageChangeListener != null) {
					pageChangeListener.onPageScrollStateChanged(state);
				}
			}
		});
	}

}
