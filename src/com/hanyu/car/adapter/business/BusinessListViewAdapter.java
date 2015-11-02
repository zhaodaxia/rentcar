package com.hanyu.car.adapter.business;

import java.util.List;

import com.hanyu.car.R;
import com.hanyu.car.adapter.business.BusinessListViewAdapter.ViewHolder;
import com.hanyu.car.bean.BusinessInfo;
import com.hanyu.car.global.ImageLoaderCfg;
import com.hanyu.car.http.HttpUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BusinessListViewAdapter extends BaseAdapter {
	/**
	 * 商务租车ListView适配器
	 */

	private LayoutInflater inflater;
	private List<BusinessInfo> infos;

	public BusinessListViewAdapter(Context context, List<BusinessInfo> infos) {
		inflater = LayoutInflater.from(context);
		this.infos = infos;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return infos == null ? 0 : infos.size();
	}

	@Override
	public BusinessInfo getItem(int position) {
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.business_listview, null);
		}
		ViewHolder holder = ViewHolder.getHolder(convertView);
		BusinessInfo info = infos.get(position);
		holder.tv_name.setText(info.bus_name);
		holder.tv_price.setText(info.bus_price);
		holder.tv_car_number.setText(info.bus_plate);
		ImageLoader.getInstance().displayImage(HttpUrl.IMAGE_URL + info.bus_pic, holder.iv_header,
				ImageLoaderCfg.options);
		return convertView;
	}

	static class ViewHolder {
		TextView tv_name, tv_brand, tv_price, tv_car_number;
		ImageView iv_header;

		private ViewHolder(View convertView) {
			tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			tv_brand = (TextView) convertView.findViewById(R.id.tv_brand);
			tv_price = (TextView) convertView.findViewById(R.id.tv_price);
			tv_car_number = (TextView) convertView.findViewById(R.id.tv_car_number);
			iv_header = (ImageView) convertView.findViewById(R.id.iv_header);
		}

		public static ViewHolder getHolder(View convertView) {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			if (holder == null) {
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			return holder;
		}
	}

}
