package com.hanyu.car.activity.center;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hanyu.car.R;
import com.hanyu.car.adapter.center.TravelOrderAdapter;
import com.hanyu.car.base.BaseActivity;
import com.hanyu.car.utils.SelectionDialog;
import com.lidroid.xutils.view.annotation.ViewInject;

public class TravelOrderActivity extends BaseActivity {
	@ViewInject(R.id.travel_order_back)
	private RelativeLayout travel_order_back;
	@ViewInject(R.id.travel_order_tv)
	private TextView travel_order_tv;
	@ViewInject(R.id.travel_order_lv)
	private ListView travel_order_lv;


	private TravelOrderAdapter adapter;

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.travel_order_back:
			finish();
			break;
		case R.id.travel_order_tv:
			// TODO 弹出自定义对话框
			SelectionDialog dialog = new SelectionDialog(TravelOrderActivity.this);
			dialog.builder().show();
			//TODO 得到diaolog中的选项值筛选ListView中的Item
			break;

		default:
			break;
		}
	}



	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.travel_order;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		adapter = new TravelOrderAdapter(TravelOrderActivity.this);
		travel_order_lv.setSelector(new ColorDrawable(Color.TRANSPARENT));
		travel_order_lv.setAdapter(adapter);
	}

	@Override
	public void setListener() {
		travel_order_back.setOnClickListener(this);
		travel_order_tv.setOnClickListener(this);
	}

	
	


}
