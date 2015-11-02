package com.hanyu.car.activity.center;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hanyu.car.R;
import com.hanyu.car.adapter.center.SeatNumberAdapter;
import com.hanyu.car.base.MyBaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SelectSeatActivity extends MyBaseActivity {
	protected static final int SEATNUMBER = 0;


	@ViewInject(R.id.select_item_litview)
	private ListView select_item_litview;
	
	private SeatNumberAdapter adapter;

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("座位数");
		adapter = new SeatNumberAdapter(SelectSeatActivity.this);
		select_item_litview.setAdapter(adapter);
		select_item_litview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				intent = new Intent();
				String seatnumber = adapter.getItem(position).toString();
				intent.putExtra("seatnumber", seatnumber);
				setResult(SEATNUMBER, intent);
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
