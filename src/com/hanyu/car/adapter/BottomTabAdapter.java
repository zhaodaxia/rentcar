package com.hanyu.car.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.hanyu.car.R;

public class BottomTabAdapter extends BaseAdapter implements ListAdapter {
	
	private LayoutInflater inflater;
	public final static int[] iv1 = {R.drawable.zy_01,R.drawable.zy_03,R.drawable.zy_05,R.drawable.zy_07,R.drawable.zy_09,R.drawable.zy_11};	
	public final static String[] tv1 = {"租车主页","旅行租车","婚礼租车","商务租车","长期租车","个人中心"};
	
	public BottomTabAdapter(Context context){
		inflater = LayoutInflater.from(context);		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null){			
			convertView = inflater.inflate(R.layout.bottom_button, null);
			viewHolder = new ViewHolder();
			viewHolder.rb_homepage = (ImageView) convertView.findViewById(R.id.rb_homepage);
			viewHolder.tv_homepage = (TextView) convertView.findViewById(R.id.tv_homepage);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
//			convertView.setLayoutParams(paramsAL);
			viewHolder.rb_homepage.setBackgroundResource(iv1[position]);
			viewHolder.tv_homepage.setText(tv1[position]);
			return convertView;
	}
	
	private class ViewHolder{
		ImageView rb_homepage;
		TextView tv_homepage;
	}
}
