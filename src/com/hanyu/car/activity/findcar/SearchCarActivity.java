package com.hanyu.car.activity.findcar;

import android.os.Bundle;

import com.hanyu.car.R;
import com.hanyu.car.base.MyBaseActivity;

public class SearchCarActivity extends MyBaseActivity {

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("查询结果");
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.search_car;
	}

}
