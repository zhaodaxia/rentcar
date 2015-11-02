package com.hanyu.car.activity.center;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.hanyu.car.R;
import com.hanyu.car.base.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

public class WeddingOrderActivity extends BaseActivity {
	@ViewInject(R.id.wedding_order_lv)
	private ListView wedding_order_lv;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.wedding_order2;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("婚车预约");

		wedding_order_lv.setSelector(new ColorDrawable(Color.TRANSPARENT));
		MyAdapter adapter = new MyAdapter();
		wedding_order_lv.setAdapter(adapter);
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub

	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 1;
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
			convertView = View.inflate(getApplicationContext(),
					R.layout.wedding_order_item, null);
			return convertView;
		}

	}

}
