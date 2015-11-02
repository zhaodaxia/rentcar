package com.hanyu.car.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.hanyu.car.R;
import com.hanyu.car.bean.TravelidBean;

/**
 * travel_listview的适配器
 * 
 * @author yang
 * 
 */
public class TravelListViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<TravelidBean> list;

	public TravelListViewAdapter(Context context, List<TravelidBean> list) {
		inflater = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public TravelidBean getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.travel_listview, null);
			viewHolder.travel_list_tv1 = (TextView) convertView.findViewById(R.id.travel_list_tv1);
			viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
			viewHolder.tv_hotel = (TextView) convertView.findViewById(R.id.tv_hotel);
			viewHolder.tv_brand = (TextView) convertView.findViewById(R.id.tv_brand);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		TravelidBean info = list.get(position);

		viewHolder.travel_list_tv1.setText(info.travel_startcity + " 至 " + info.travel_endcity);
		viewHolder.tv_time.setText(info.travel_time);
		viewHolder.tv_price.setText(info.travel_price);
		viewHolder.tv_hotel.setText(info.travel_hotel);
		viewHolder.tv_brand.setText(info.travel_brand + info.travel_model);
		return convertView;

	}

	class ViewHolder {
		TextView travel_list_tv1, tv_time, tv_price, tv_hotel, tv_brand;
	}

}
