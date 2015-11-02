package com.hanyu.car.activity.travelcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hanyu.car.R;
import com.hanyu.car.adapter.travel.DestinationListViewAdapter;
import com.hanyu.car.base.MyBaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

public class DestinationActivity extends MyBaseActivity {
	protected static final int resultCode = 2;
	
	@ViewInject(R.id.select_item_litview)
	private ListView select_item_litview;
	
	private DestinationListViewAdapter adapter;
	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("目的地");
		
		adapter = new DestinationListViewAdapter(getApplicationContext());
		select_item_litview.setAdapter(adapter);
		select_item_litview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.putExtra("destination", adapter.getItem(position).toString());
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
