package com.hanyu.car.activity.longterm;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.hanyu.car.R;
import com.hanyu.car.base.MyBaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

public class CheckPlanActivity extends MyBaseActivity {
	@ViewInject(R.id.check_plan_back)
	private RelativeLayout check_plan_back;
	@ViewInject(R.id.check_plan_right)
	private RelativeLayout check_plan_right;


	@Override
	public void init(Bundle savedInstanceState) {
		Back();
	}

	private void Back() {
		check_plan_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.check_paln;
	}

}
