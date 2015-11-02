package com.hanyu.car.adapter.wedding;

import java.util.List;

import com.hanyu.car.R;
import com.hanyu.car.bean.PackageInfo;
import com.hanyu.car.global.ImageLoaderCfg;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.view.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ComboListViewAdapter extends BaseAdapter {
	/**
	 * 婚车套餐ListView适配器
	 */
	private Context context;
	private List<PackageInfo> infos;

	public ComboListViewAdapter(Context context, List<PackageInfo> infos) {
		this.context = context;
		this.infos = infos;
	}

	public int getCount() {
		return infos == null ? 0 : infos.size();
	}

	@Override
	public PackageInfo getItem(int position) {
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.combo_listview, null);
		}
		ViewHolder holder = ViewHolder.getHolder(convertView);
		PackageInfo info = infos.get(position);
		holder.combo_tv1.setText(info.mpackpage_name);
		holder.tv_price.setText(info.mpackpage_price);
		holder.tv_desc.setText(info.mpackpage_include);
		ImageLoader.getInstance().displayImage(HttpUrl.IMAGE_URL + info.mpackpage_pic, holder.iv_header, ImageLoaderCfg.options);
		return convertView;
	}

	static class ViewHolder {
		CircleImageView iv_header;
		TextView combo_tv1;
		TextView tv_price, tv_desc;
		private ViewHolder(View convertView){
			iv_header = (CircleImageView) convertView.findViewById(R.id.iv_header);
			combo_tv1 = (TextView) convertView.findViewById(R.id.combo_tv1);
			tv_price = (TextView) convertView.findViewById(R.id.tv_price);
			tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
		}
		public static ViewHolder getHolder(View convertView){
			ViewHolder holder = (ViewHolder) convertView.getTag();
			if (holder == null) {
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			return holder;
		}
		
	}

}
