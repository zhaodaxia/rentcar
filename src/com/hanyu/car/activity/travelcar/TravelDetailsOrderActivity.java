package com.hanyu.car.activity.travelcar;

import android.os.Bundle;
import android.view.View;

import com.hanyu.car.R;
import com.hanyu.car.base.BaseActivity;

public class TravelDetailsOrderActivity extends BaseActivity {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.travel_details_order;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("立即预约");
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub

	}

}
