package com.hanyu.car.activity.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hanyu.car.R;
import com.hanyu.car.adapter.business.PriceListViewAdapter;
import com.hanyu.car.base.MyBaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

public class PriceSelectActivity extends MyBaseActivity {
	protected static final int PRICE = 1;
	@ViewInject(R.id.select_item_litview)
	private ListView select_item_litview;
	private Intent intent;
	private PriceListViewAdapter adapter;

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("价格选择");
		adapter = new PriceListViewAdapter(getApplicationContext());
		select_item_litview.setAdapter(adapter);
		select_item_litview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				intent = new Intent();
				intent.putExtra("price", adapter.getItem(position).toString());
				setResult(PRICE, intent);
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
