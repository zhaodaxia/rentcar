package com.hanyu.car.activity.wedding;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.hanyu.car.R;
import com.hanyu.car.adapter.wedding.LuxuryListViewAdapter;
import com.hanyu.car.base.MyBaseActivity;
import com.hanyu.car.bean.LuxuryContentInfos;
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

public class LuxuryActivity extends MyBaseActivity {
	@ViewInject(R.id.luxury_ptr)
	private PullToRefreshListView luxury_ptr;

	private LuxuryListViewAdapter adapter;
	
	private RelativeLayout back;
	
	private List<LuxuryInfos> infos;
	
	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("豪华轿车");
		//adapter = new LuxuryListViewAdapter(LuxuryActivity.this,infos);
		//luxury_ptr.getRefreshableView().setAdapter(adapter);
		luxury_ptr.setPullLoadEnabled(true);
		luxury_ptr.setPullRefreshEnabled(true);
		luxury_ptr.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				luxury_ptr.onPullDownRefreshComplete();
				luxury_ptr.setLastUpdatedLabel(MyTimeUtils.getStringDate());
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				luxury_ptr.onPullUpRefreshComplete();
			}
		});
		luxury_ptr.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				intent = new Intent(LuxuryActivity.this, LuxuryDetialsActivity.class);
				intent.putExtra("luxury_details",infos.get(position).mcar_name);
				intent.putExtra("mcarid",infos.get(position).mcarid);
				startActivity(intent);
			}
		});
		//获取豪车列表信息
		getLuxucyList();
		
	}

	
	
	private void getLuxucyList() {
		RequestParams params = new RequestParams();
		params.addBodyParameter("keyword", "");
		//MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.LUXURY_PACKAGE_LIST, params, new RequestCallBack<String>() {
			MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.LUXURY_PACKAGE_LIST, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				getLuxucyList();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String json = arg0.result;
				LogUtils.e(getClass(), json);
				infos = JSON.parseArray(json, LuxuryInfos.class);
				adapter = new LuxuryListViewAdapter(LuxuryActivity.this,infos);
				luxury_ptr.getRefreshableView().setAdapter(adapter);
			}
		});
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.wedding_luxury;
	}
	
	/**
	 * 返回键
	 */
	public void setBack() {
		back = (RelativeLayout) findViewById(R.id.rl_back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}
}
