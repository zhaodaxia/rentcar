package com.hanyu.car.activity.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hanyu.car.R;
import com.hanyu.car.adapter.business.TypeListViewAdapter;
import com.hanyu.car.base.MyBaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

public class TypeSelectActivity extends MyBaseActivity {
	protected static final int TYPE = 2;

	@ViewInject(R.id.select_item_litview)
	private ListView select_item_litview;
	
	private Intent intent;
	private TypeListViewAdapter adapter;

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("车型选择");
		adapter = new TypeListViewAdapter(getApplicationContext());
		select_item_litview.setAdapter(adapter);
		select_item_litview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				intent = new Intent();
				intent.putExtra("type", adapter.getItem(position).toString());
				setResult(TYPE, intent);
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
