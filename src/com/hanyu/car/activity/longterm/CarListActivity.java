package com.hanyu.car.activity.longterm;

import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.hanyu.car.R;
import com.hanyu.car.activity.wedding.LuxuryActivity;
import com.hanyu.car.adapter.longterm.CarListAdapter;
import com.hanyu.car.adapter.wedding.LuxuryListViewAdapter;
import com.hanyu.car.base.MyBaseActivity;
import com.hanyu.car.bean.LongTermCarListInfo;
import com.hanyu.car.bean.LuxuryInfos;
import com.hanyu.car.global.MyApplication;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.pulltorefresh.PullToRefreshBase;
import com.hanyu.car.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.hanyu.car.pulltorefresh.PullToRefreshListView;
import com.hanyu.car.utils.LogUtils;
import com.hanyu.car.utils.MyTimeUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;


public class CarListActivity extends MyBaseActivity {
	@ViewInject(R.id.carlist_back)
	private RelativeLayout carlist_back;
	@ViewInject(R.id.carlist_ptr)
	private PullToRefreshListView carlist_ptr;
	
	private List<LongTermCarListInfo> infos;
	
	private CarListAdapter adapter;
	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Back();
		CarListPTR();
	}

	private void CarListPTR() {
		//adapter = new CarListAdapter(CarListActivity.this);
		carlist_ptr.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
		//carlist_ptr.getRefreshableView().setAdapter(adapter);
		carlist_ptr.setPullLoadEnabled(false);
		carlist_ptr.setPullRefreshEnabled(true);
		carlist_ptr.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				carlist_ptr.onPullDownRefreshComplete();
				carlist_ptr.setLastUpdatedLabel(MyTimeUtils.getStringDate());
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				carlist_ptr.onPullUpRefreshComplete();
			}
		});
		
		carlist_ptr.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				intent = new Intent(CarListActivity.this, CarListDetailsActivity.class);
				intent.putExtra("carlist_details", infos.get(position).long_name);
				intent.putExtra("longid",infos.get(position).longid);
				startActivity(intent);
			}
		});
		
		//获取豪车列表信息
		getCarList();
	}

	private void getCarList() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("keyword", "");
		//MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.LUXURY_PACKAGE_LIST, params, new RequestCallBack<String>() {
			MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.LONGTERM_CARLIST, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				getCarList();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String json = arg0.result;
				LogUtils.e(getClass(), json);
				infos = JSON.parseArray(json, LongTermCarListInfo.class);
				adapter = new CarListAdapter(CarListActivity.this,infos);
				carlist_ptr.getRefreshableView().setAdapter(adapter);
			}
		});
	}

	private void Back() {
		carlist_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.carlist;
	}

}
