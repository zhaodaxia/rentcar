package com.hanyu.car.adapter.center;

import com.hanyu.car.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TravelOrderAdapter extends BaseAdapter {
	private LayoutInflater layoutinflater;

	public TravelOrderAdapter(Context context) {
		layoutinflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
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
		ViewHolder viewhodel;
		if(convertView == null){
			convertView = layoutinflater.inflate(R.layout.travel_order_item, null);
			viewhodel = new ViewHolder();
			
			
			
			convertView.setTag(viewhodel);
		}else{
			convertView = (View) convertView.getTag();
		}
		return convertView;
	}
	
	class ViewHolder{
		
	}

}
