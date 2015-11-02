package com.hanyu.car.activity.wedding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hanyu.car.R;
import com.hanyu.car.adapter.wedding.CarColorAdapter;
import com.hanyu.car.base.MyBaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SelectCarColorActivity extends MyBaseActivity {
	protected static final int CARCOLOR = 0;

	@ViewInject(R.id.select_item_litview)
	private ListView select_item_litview;
	
	private Intent intent;
	
	private CarColorAdapter adapter;

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("车身颜色");
		adapter = new CarColorAdapter(getApplicationContext());
		select_item_litview.setAdapter(adapter);
		select_item_litview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				intent = new Intent();
				intent.putExtra("carcolor", adapter.getItem(position).toString());
				setResult(CARCOLOR, intent);
				finish();
			}
		});
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.select_item;
	}

}
