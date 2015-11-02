package com.hanyu.car.adapter.business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hanyu.car.R;

public class TypeListViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	public final static String[] tv1 = {"不限", "豪华轿车", "高端轿车", "其他车型"};

	public TypeListViewAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return tv1.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return tv1[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.text_item, null);
			viewHolder = new ViewHolder();
			viewHolder.text_item = (TextView) convertView
					.findViewById(R.id.text_item);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.text_item.setText(tv1[position]);
		return convertView;
	}

	private class ViewHolder {
		TextView text_item;

	}

}
