package com.xc.xcskin;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

import com.xc.xcskin.view.XCDropDownListView;

public class XCDropDownListViewDemo extends Activity {

	XCDropDownListView dropDownListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xc_dropdownlist_view_demo);

		dropDownListView = (XCDropDownListView)findViewById(R.id.drop_down_list_view);
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0;i< 6;i++){
			list.add("下拉列表项"+(i+1));
		}
		dropDownListView.setItemsData(list);

	}

}
