package com.hanyu.car.activity.center;

import android.os.Bundle;
import android.widget.TextView;

import com.hanyu.car.R;
import com.hanyu.car.base.MyBaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

public class AboutUSActivity extends MyBaseActivity {



	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("关于我们");
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.aboutus;
	}

}
