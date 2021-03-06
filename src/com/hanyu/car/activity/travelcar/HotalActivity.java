package com.hanyu.car.activity.travelcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hanyu.car.R;
import com.hanyu.car.adapter.travel.HotalListViewAdapter;
import com.hanyu.car.base.MyBaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

public class HotalActivity extends MyBaseActivity {
	protected static final int resultCode = 3;
	
	
	@ViewInject(R.id.select_item_litview)
	private ListView select_item_litview;
	
	private HotalListViewAdapter adapter;
	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("酒店");
		
		adapter = new HotalListViewAdapter(getApplicationContext());
		select_item_litview.setAdapter(adapter);
		select_item_litview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.putExtra("hotal", adapter.getItem(position).toString());
				setResult(resultCode, intent);
				finish();
			}
		});
	}

	@Override
	public int setLayout() {
		return R.layout.select_item;
	}

}
