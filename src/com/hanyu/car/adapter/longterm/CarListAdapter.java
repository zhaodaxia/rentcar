package com.hanyu.car.adapter.longterm;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanyu.car.R;
import com.hanyu.car.bean.LongTermCarListInfo;
import com.hanyu.car.bean.LuxuryInfos;
import com.hanyu.car.global.ImageLoaderCfg;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.utils.ToastUtils;
import com.hanyu.car.view.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CarListAdapter extends BaseAdapter {
	/**
	 * 车辆列表ListView适配器
	 */
	//private LayoutInflater inflater;
	
	private Context context;
	private List<LongTermCarListInfo> infos;
	
	public CarListAdapter(Context context, List<LongTermCarListInfo> infos) {
		this.context = context;
		this.infos = infos;
	}

	public int getCount() {
		return infos == null ? 0 : infos.size();
	}

	@Override
	public LongTermCarListInfo getItem(int position) {
		return infos.get(position);
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
			convertView = View.inflate(context,R.layout.business_listview, null);
			viewHolder.longtermCarList_tv_name =  (TextView) convertView.findViewById(R.id.tv_name);
			viewHolder.longtermCarList_tv_brand = (TextView) convertView.findViewById(R.id.tv_brand);
			viewHolder.longtermCarList_tv_price = (TextView) convertView.findViewById(R.id.tv_price);
			viewHolder.longtermCarList_car_number = (TextView) convertView.findViewById(R.id.tv_car_number);
			viewHolder.longtermCarList_iv_header = (CircleImageView) convertView.findViewById(R.id.iv_header);
			viewHolder.longtermCarList_iv_tuijian = (ImageView) convertView.findViewById(R.id.tv_tuijian);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		LongTermCarListInfo info = infos.get(position);
		viewHolder.longtermCarList_tv_name.setText(info.long_name);
		viewHolder.longtermCarList_tv_price.setText(info.long_price);
		viewHolder.longtermCarList_car_number.setText(info.long_plate);
		
		ImageLoader.getInstance().displayImage(HttpUrl.IMAGE_URL + info.long_pic, viewHolder.longtermCarList_iv_header, ImageLoaderCfg.options);
	
		//车辆等级的判断
		if(info.long_status.equals("1")){
			viewHolder.longtermCarList_tv_brand.setText("豪华轿车");
		}else if(info.long_status.equals("2")){
			viewHolder.longtermCarList_tv_brand.setText("高档轿车");
		}else{
			viewHolder.longtermCarList_tv_brand.setText("其他");
		}
		
		//是否推荐判断
		if(info.long_tuijian.equals("1")){
			viewHolder.longtermCarList_iv_tuijian.setVisibility(View.VISIBLE);
		}else{
			viewHolder.longtermCarList_iv_tuijian.setVisibility(View.GONE);
		}
		return convertView;
	}

	class ViewHolder {
		TextView longtermCarList_tv_name;
		TextView longtermCarList_tv_brand;
		TextView longtermCarList_tv_price;
		TextView longtermCarList_car_number;
		ImageView longtermCarList_iv_tuijian;
		CircleImageView longtermCarList_iv_header;
	}

}
