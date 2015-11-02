package com.hanyu.car.adapter.travel;

import java.util.List;

import com.hanyu.car.R;
import com.hanyu.car.bean.TravelSubjectInfo;
import com.hanyu.car.global.ImageLoaderCfg;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.utils.LogUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TravelSubjectAdapter extends BaseAdapter {

	private List<TravelSubjectInfo> list;
	private Context context;

	public static DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.laosilaisi).showImageForEmptyUri(R.drawable.laosilaisi)
			.showImageOnFail(R.drawable.laosilaisi).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
			.displayer(new SimpleBitmapDisplayer()).build();

	public TravelSubjectAdapter(Context context, List<TravelSubjectInfo> travelList) {
		this.list = travelList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public TravelSubjectInfo getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.travel_list_item, null);
		}
		ViewHolder holder = ViewHolder.getHolder(convertView);
		TravelSubjectInfo info = list.get(position);
		holder.tv_title.setText(info.travel_startcity + "至" + info.travel_endcity + info.travel_time + "之旅");

		String picsStr = info.travel_pics.substring(0, info.travel_pics.length() - 1);
		try {
			String[] pics = picsStr.split("\\u002B");
			ImageLoader.getInstance().displayImage(HttpUrl.IMAGE_URL + pics[0], holder.iv_pic, ImageLoaderCfg.options);
			for (int i = 0; i < pics.length; i++) {
				LogUtils.e(getClass(), "split:" + pics[i]);
			}
		} catch (Exception e) {
			LogUtils.e(getClass(), picsStr);
			ImageLoader.getInstance().displayImage(HttpUrl.IMAGE_URL + picsStr, holder.iv_pic, options);
		}

		return convertView;
	}

	static class ViewHolder {
		ImageView iv_pic;
		TextView tv_title;

		private ViewHolder(View convertView) {
			iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
			tv_title = (TextView) convertView.findViewById(R.id.tv_title);
		}

		public static ViewHolder getHolder(View convertView) {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			if (holder == null) {
				holder = new ViewHolder(convertView);
			}
			return holder;
		}
	}

}
