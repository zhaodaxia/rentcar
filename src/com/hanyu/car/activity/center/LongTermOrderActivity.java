package com.hanyu.car.activity.center;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hanyu.car.R;
import com.hanyu.car.activity.center.BusinessOrderActivity.MyAdapter;
import com.hanyu.car.base.BaseActivity;
import com.hanyu.car.utils.LogUtils;
import com.hanyu.car.utils.SelectionDialog;
import com.hanyu.car.utils.SelectionDialog.SelectionListener;
import com.lidroid.xutils.view.annotation.ViewInject;

public class LongTermOrderActivity extends BaseActivity {
	protected static final String tag = "LongTermOrderActivity";
	@ViewInject(R.id.longterm_order_back)
	private RelativeLayout longterm_order_back;
	@ViewInject(R.id.longterm_order_tv)
	private TextView longterm_order_tv;
	@ViewInject(R.id.cacle_order)
	private TextView cacle_order;//取消预约
	@ViewInject(R.id.state_tv)
	private TextView state_tv;//预约状态
	@ViewInject(R.id.longterm_order_lv)
	private ListView longterm_order_lv;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.longterm_order_back:
			finish();
			break;
		case R.id.longterm_order_tv:
			// TODO 弹出自定义对话框
			SelectionDialog dialog = new SelectionDialog(LongTermOrderActivity.this);
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
		return R.layout.long_term_order;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		longterm_order_lv.setSelector(new ColorDrawable(Color.TRANSPARENT));
		MyAdapter adapter = new MyAdapter();
		longterm_order_lv.setAdapter(adapter);
	}

	@Override
	public void setListener() {
		longterm_order_back.setOnClickListener(this);
		longterm_order_tv.setOnClickListener(this);
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
			convertView = View.inflate(getApplicationContext(),
					R.layout.business_order_item, null);
			return convertView;
		}

	}

}
