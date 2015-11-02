package com.hanyu.car.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hanyu.car.R;
import com.hanyu.car.activity.longterm.CarListActivity;
import com.hanyu.car.activity.longterm.CheckPlanActivity;
import com.hanyu.car.activity.longterm.ReservationActivity;
import com.hanyu.car.adapter.LongCarListViewAdapter;
import com.hanyu.car.base.BaseFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 长期租车界面
 * 
 * @author yang
 * 
 */
public class LongCarFragment extends BaseFragment {
	@ViewInject(R.id.longpager_listview)
	private ListView longpager_listview;
	@ViewInject(R.id.long_rentcar_tv2)
	private TextView long_rentcar_tv2;
	@ViewInject(R.id.long_back)
	private RelativeLayout long_back;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.long_back:
			getActivity().finish();
			break;

		default:
			break;
		}
	}

	@Override
	public View initView(LayoutInflater inflater) {
		view = View.inflate(context, R.layout.longpager, null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		LoadView();
	}

	@Override
	public void setListener() {
		long_back.setOnClickListener(this);
	}

	private void LoadView() {
		LongCarListViewAdapter adapter = new LongCarListViewAdapter(context);
		longpager_listview.setAdapter(adapter);
		longpager_listview.setSelector(new ColorDrawable(Color.TRANSPARENT));
		longpager_listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					switch (position) {
					case 0:
						intent = new Intent(context,CarListActivity.class);
						startActivity(intent);
						break;
					case 1:
						intent = new Intent(context,CheckPlanActivity.class);
						startActivity(intent);
						break;
					case 2:
						intent = new Intent(context,ReservationActivity.class);
						startActivity(intent);
						break;

					default:
						break;
					}
					
			}
		});
	}

}
