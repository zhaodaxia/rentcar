package com.hanyu.car.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hanyu.car.R;

public class LongCarListViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	public final static int[] iv1 = { R.drawable.cq_03, R.drawable.cq_06,
			R.drawable.cq_08 };
	public final static String[] tv1 = { "车辆列表", "融资方案", "我要咨询" };
	public final static String[] tv2 = { "查看列表", "查看方案", "预约咨询" };
	public final static String[] tv3 = {
			"目前我们提供20个汽车品牌和300多个车型让您随心所选，租车流程简单快捷、方便，可提供上门签订长期租车合同。",
			"如果您还在因为资金不够而租不到想要的车，您可以看看我们最新推出的融资方案，它可以帮您快速的实现租车梦想。",
			"如果您对我们的租车流程或融资方案有任何疑问的话，您可以在线预约咨询，我们会及时为您解决您所面临的困惑。" };

	// private LayoutParams paramsAL = new
	// LayoutParams(LayoutParams.MATCH_PARENT, GlobalParams.screenWidth);

	public LongCarListViewAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
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
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.long_rentcar, null);
			viewHolder = new ViewHolder();
			viewHolder.long_rentcar_rl1 = (RelativeLayout) convertView
					.findViewById(R.id.long_rentcar_rl1);
			viewHolder.long_rentcar_tv1 = (TextView) convertView
					.findViewById(R.id.long_rentcar_tv1);
			viewHolder.long_rentcar_tv2 = (TextView) convertView
					.findViewById(R.id.long_rentcar_tv2);
			viewHolder.long_rentcar_tv3 = (TextView) convertView
					.findViewById(R.id.long_rentcar_tv3);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// convertView.setLayoutParams(paramsAL);
		viewHolder.long_rentcar_rl1.setBackgroundResource(iv1[position]);
		viewHolder.long_rentcar_tv1.setText(tv1[position]);
		viewHolder.long_rentcar_tv2.setText(tv2[position]);
		viewHolder.long_rentcar_tv3.setText(tv3[position]);
		return convertView;
	}

	private class ViewHolder {
		RelativeLayout long_rentcar_rl1;
		TextView long_rentcar_tv1;
		TextView long_rentcar_tv2;
		TextView long_rentcar_tv3;

	}

}
