package com.hanyu.car.activity.travelcar;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.hanyu.car.R;
import com.hanyu.car.adapter.TravelListViewAdapter;
import com.hanyu.car.base.BaseActivity;
import com.hanyu.car.bean.TravelidBean;
import com.hanyu.car.global.ImageLoaderCfg;
import com.hanyu.car.global.MyApplication;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.pulltorefresh.PullToRefreshBase;
import com.hanyu.car.pulltorefresh.PullToRefreshListView;
import com.hanyu.car.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.hanyu.car.utils.LogUtils;
import com.hanyu.car.utils.MyTimeUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MoreTravelActivity extends BaseActivity {
	@ViewInject(R.id.travel_ptrlv)
	private PullToRefreshListView travel_ptrlv;
	@ViewInject(R.id.travel_right)
	private TextView travel_right;
	@ViewInject(R.id.travel_back)
	private RelativeLayout travel_back;
	private TravelListViewAdapter myadapter;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.travel_back:
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public int setLayout() {
		return R.layout.travelpager;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		initData();

	}

	private void initData() {
		setListener();
		travel_right.setText("自定义");
		travel_ptrlv.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
		travel_ptrlv.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				intent = new Intent(MoreTravelActivity.this, TravelDetailsActivity.class);
				intent.putExtra("id", myadapter.getItem(position).travelid);
				intent.putExtra("title", myadapter.getItem(position).travel_startcity+"至"+myadapter.getItem(position).travel_endcity);
				startActivity(intent);
			}
		});

		getTravelData();
	}

	@Override
	public void setListener() {
		travel_back.setOnClickListener(this);
		// 打开自定义界面
		travel_right.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MoreTravelActivity.this, MyRentCarActivity.class);
				MoreTravelActivity.this.startActivity(intent);
			}
		});
		travel_back.setOnClickListener(this);
		travel_ptrlv.setPullLoadEnabled(false);
		travel_ptrlv.setPullRefreshEnabled(true);
		travel_ptrlv.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				travel_ptrlv.setLastUpdatedLabel(MyTimeUtils.getStringDate());
				getTravelData();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				travel_ptrlv.onPullUpRefreshComplete();
			}
		});
	}

	private void getTravelData() {
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.TRAVEL_LIST, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				getTravelData();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				LogUtils.e(getClass(), arg0.result);
				List<TravelidBean> travelList = JSON.parseArray(arg0.result, TravelidBean.class);
				myadapter = new TravelListViewAdapter(MoreTravelActivity.this, travelList);
				travel_ptrlv.getRefreshableView().setAdapter(myadapter);
				travel_ptrlv.onPullDownRefreshComplete();

			}
		});
	}

}
