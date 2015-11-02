package com.hanyu.car.adapter.center;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hanyu.car.R;

public class SeatNumberAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	public final static String[] tv1 = {"5座","7座","9座","11座"};
	
	
	public SeatNumberAdapter(Context context){
		inflater = LayoutInflater.from(context);		
	}
	@Override
	public int getCount() {
		return tv1.length;
	}

	@Override
	public Object getItem(int position) {
		return tv1[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null){			
			convertView = inflater.inflate(R.layout.text_item, null);
			viewHolder = new ViewHolder();
			viewHolder.text_item = (TextView) convertView.findViewById(R.id.text_item);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
			viewHolder.text_item.setText(tv1[position]);
			return convertView;
	}
	
	private class ViewHolder{
		TextView text_item;

	}

}
