package com.hanyu.car.activity.center;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.hanyu.car.R;
import com.hanyu.car.base.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

public class OwnerActivity extends BaseActivity {
	@ViewInject(R.id.owner_lv)
	private ListView owner_lv;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.owner;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("车辆管理");
		setRightIv(R.drawable.gl_03, new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(OwnerActivity.this, AddMyCarActivity.class);
				startActivity(intent);
			}
		});

		MyAdapter adapter = new MyAdapter();
		owner_lv.setAdapter(adapter);
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub

	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			convertView = View.inflate(OwnerActivity.this, R.layout.owner_item,
					null);
			return convertView;
		}

	}

}
