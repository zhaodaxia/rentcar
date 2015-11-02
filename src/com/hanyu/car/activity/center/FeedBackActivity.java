package com.hanyu.car.activity.center;

import android.os.Bundle;

import com.hanyu.car.R;
import com.hanyu.car.base.MyBaseActivity;

public class FeedBackActivity extends MyBaseActivity {

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("意见反馈");
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.feedback;
	}

}
