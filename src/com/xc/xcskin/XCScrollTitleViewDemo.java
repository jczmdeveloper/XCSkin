package com.xc.xcskin;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.xc.xcskin.view.XCScrollTitleView;
import com.xc.xcskin.view.XCScrollTitleView.OnScrollTitlePageChangeListener;

public class XCScrollTitleViewDemo extends Activity {

	
	private String[] tabList={"常用","设置","工具","更多"};
	private XCScrollTitleView scrollTitleView;
	private ViewPager viewPager;
	private List<View> viewList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xc_scroll_title_view_demo);
		
		getViews();
		init();
		setListener();
		
	}
	private void getViews(){
		scrollTitleView = (XCScrollTitleView) findViewById(R.id.scrollTitleView);
		viewPager = (ViewPager)findViewById(R.id.myViewPager);
		viewList = new ArrayList<View>();
		
		LayoutInflater inflater = LayoutInflater.from(this);
		View view1 = inflater.inflate(R.layout.xc_scroll_title_view_tab1, null);
		View view2 = inflater.inflate(R.layout.xc_scroll_title_view_tab2, null);
		View view3 = inflater.inflate(R.layout.xc_scroll_title_view_tab3, null);
		View view4 = inflater.inflate(R.layout.xc_scroll_title_view_tab4, null);
		
		viewList.add(view1);
		viewList.add(view2);
		viewList.add(view3);
		viewList.add(view4);
	}
	private void init(){
		//初始化ViewPager
		viewPager.setAdapter(pagerAdapter);
	
		//初始化ScrollTitle
		scrollTitleView.setViewPager(viewPager);
		//可在代码中动态改变标签显示内容
		scrollTitleView.changeTitle(tabList);
	}
	private void setListener(){
		//不要设置ViewPager滑动监听，否则组件无法正常滑动，如果需要可以用以下方式代替
		scrollTitleView.setOnScrollTitlePageChangeListener(new OnScrollTitlePageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	PagerAdapter pagerAdapter = new PagerAdapter() {
		
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			//return super.getPageTitle(position);
			return tabList[position];
		}


		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

		 

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return viewList.size();
		}
		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).removeView(viewList.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).addView(viewList.get(arg1));
		
			return viewList.get(arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	

}
