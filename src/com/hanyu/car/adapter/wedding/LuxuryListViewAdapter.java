package com.hanyu.car.adapter.wedding;

import java.util.List;

import com.hanyu.car.R;
import com.hanyu.car.adapter.wedding.ComboListViewAdapter.ViewHolder;
import com.hanyu.car.bean.LuxuryInfos;
import com.hanyu.car.bean.PackageInfo;
import com.hanyu.car.global.ImageLoaderCfg;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.view.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LuxuryListViewAdapter extends BaseAdapter {
	/**
	 * 豪华轿车ListView适配器
	 */
	
	private Context context;
	private List<LuxuryInfos> infos;

	public LuxuryListViewAdapter(Context context, List<LuxuryInfos> infos) {
		this.context = context;
		this.infos = infos;
	}

	public int getCount() {
		return infos == null ? 0 : infos.size();
	}

	@Override
	public LuxuryInfos getItem(int position) {
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.luxury_listview, null);
		}
		ViewHolder holder = ViewHolder.getHolder(convertView);
		LuxuryInfos info = infos.get(position);
		holder.tv_name.setText(info.mcar_name);
		holder.tv_price.setText(info.mcar_price);
		holder.tv_loc.setText(info.mcar_addr);
		if(info.mcar_pic!=null){
		    ImageLoader.getInstance().displayImage(HttpUrl.IMAGE_URL + info.mcar_pic, holder.iv_header, ImageLoaderCfg.options);
		}
		
		return convertView;
	}

	static class ViewHolder {
		CircleImageView iv_header;
		TextView tv_name,  tv_price, tv_level, tv_loc;//tv_brand,
		
		private ViewHolder(View convertView) {
			tv_loc = (TextView) convertView.findViewById(R.id.tv_loc);
			tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			//tv_brand = (TextView) convertView.findViewById(R.id.tv_brand);
			tv_price = (TextView) convertView.findViewById(R.id.tv_price);
			//tv_level = (TextView) convertView.findViewById(R.id.tv_level);
			iv_header =  (CircleImageView) convertView.findViewById(R.id.iv_header);
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
