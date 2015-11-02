package com.hanyu.car.adapter.wedding;

import java.util.List;

import com.hanyu.car.R;
import com.hanyu.car.bean.LuxuryInfos;
import com.hanyu.car.bean.PremiumInfo;
import com.hanyu.car.global.ImageLoaderCfg;
import com.hanyu.car.http.HttpUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PremiumListViewAdapter extends BaseAdapter {
	/**
	 * 高端轿车ListView适配器
	 */
	private Context context;
	private List<PremiumInfo> infos;

	public PremiumListViewAdapter(Context context, List<PremiumInfo> infos) {
		this.context = context;
		this.infos = infos;
	}
	

	public PremiumListViewAdapter(Context context) {
		this.context = context;
	}

	public int getCount() {
		return infos == null ? 0 : infos.size();
	}

	@Override
	public PremiumInfo getItem(int position) {
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.premium_listview, null);
			viewholder = new ViewHolder();
			viewholder.permium_tv1 = (TextView) convertView
					.findViewById(R.id.tv_name);
			viewholder.permium_tv2 = (TextView) convertView
					.findViewById(R.id.tv_price);
			viewholder.permium_tv3 = (TextView) convertView
					.findViewById(R.id.tv_loc);
			viewholder.permium_iv1 = (ImageView) convertView
					.findViewById(R.id.iv_header);
			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}
		
		PremiumInfo info = infos.get(position);
		viewholder.permium_tv1.setText(info.mcar_name);
		viewholder.permium_tv2.setText(info.mcar_price);
		viewholder.permium_tv3.setText(info.mcar_addr);
		if(info.mcar_pic!=null){
		    ImageLoader.getInstance().displayImage(HttpUrl.IMAGE_URL + info.mcar_pic, viewholder.permium_iv1, ImageLoaderCfg.options);
		}
		return convertView;
	}

	class ViewHolder {
		TextView permium_tv1;
		TextView permium_tv2;
		TextView permium_tv3;
		ImageView permium_iv1;
	}

}
