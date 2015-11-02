package com.hanyu.car.activity.findcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hanyu.car.R;
import com.hanyu.car.adapter.fastfindcar.UseListViewAdapter;
import com.hanyu.car.base.MyBaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

public class UseActivity extends MyBaseActivity {
	

	protected static final int resultCode = 0;
	@ViewInject(R.id.select_item_litview)
	private ListView select_item_litview;
	
	private UseListViewAdapter adapter;

	@Override
	public void init(Bundle savedInstanceState) {
		adapter = new UseListViewAdapter(getApplicationContext());
		select_item_litview.setAdapter(adapter);
		setBack();
		setTopTitle("选择用途");
		
		select_item_litview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.putExtra("use", adapter.getItem(position).toString());
				setResult(resultCode, intent);
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
