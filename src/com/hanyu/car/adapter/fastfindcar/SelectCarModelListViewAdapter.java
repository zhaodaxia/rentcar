package com.hanyu.car.adapter.fastfindcar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.hanyu.car.R;
import com.hanyu.car.bean.CarBrand;

public class SelectCarModelListViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<CarBrand> brands;

	public SelectCarModelListViewAdapter(Context context, List<CarBrand> brands) {
		this.brands = brands;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return brands == null ? 0 : brands.size();
	}

	@Override
	public CarBrand getItem(int position) {
		return brands.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.text_item, null);
			viewHolder = new ViewHolder();
			viewHolder.text_item = (TextView) convertView.findViewById(R.id.text_item);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.text_item.setText(brands.get(position).carbrand_name);
		return convertView;
	}

	private class ViewHolder {
		TextView text_item;

	}

}
