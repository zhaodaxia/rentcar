package com.hanyu.car.activity.wedding;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.hanyu.car.R;
import com.hanyu.car.adapter.wedding.ComboListViewAdapter;
import com.hanyu.car.base.MyBaseActivity;
import com.hanyu.car.bean.PackageInfo;
import com.hanyu.car.global.MyApplication;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.pulltorefresh.PullToRefreshBase;
import com.hanyu.car.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.hanyu.car.pulltorefresh.PullToRefreshListView;
import com.hanyu.car.utils.LogUtils;
import com.hanyu.car.utils.MyTimeUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ComboActivity extends MyBaseActivity {
	@ViewInject(R.id.combo_back)
	private RelativeLayout combo_back;

	@ViewInject(R.id.combo_ptr)
	private PullToRefreshListView combo_ptr;

	private ComboListViewAdapter adapter;

	private List<PackageInfo> infos;
	
	@Override
	public void init(Bundle savedInstanceState) {
		Back();
		combo_ptr.setPullLoadEnabled(false);
		combo_ptr.setPullRefreshEnabled(false);
		combo_ptr.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				combo_ptr.onPullDownRefreshComplete();
				combo_ptr.setLastUpdatedLabel(MyTimeUtils.getStringDate());
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				combo_ptr.onPullUpRefreshComplete();
			}
		});

		combo_ptr.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				intent = new Intent(ComboActivity.this, ComboDetailsActivity.class);
				intent.putExtra("combo_title", infos.get(position).mpackpage_name);
				intent.putExtra("mcarid",infos.get(position).mpackpageid);
				startActivity(intent);
			}

		});

		getComboList();
	}

	private void getComboList() {
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.PACKAGE_LIST, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				getComboList();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String json = arg0.result;
				LogUtils.e(getClass(), json);
				infos = JSON.parseArray(json, PackageInfo.class);
				adapter = new ComboListViewAdapter(ComboActivity.this, infos);
				combo_ptr.getRefreshableView().setAdapter(adapter);
			}
		});
	}

	private void Back() {
		combo_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public int setLayout() {
		return R.layout.wedding_combo;
	}

}
