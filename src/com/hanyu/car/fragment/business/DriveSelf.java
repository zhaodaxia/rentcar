package com.hanyu.car.fragment.business;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.hanyu.car.R;
import com.hanyu.car.activity.business.DriveSelfDetailsActivity;
import com.hanyu.car.adapter.business.BusinessListViewAdapter;
import com.hanyu.car.base.BaseFragment;
import com.hanyu.car.bean.BusinessInfo;
import com.hanyu.car.global.MyApplication;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.pulltorefresh.PullToRefreshBase;
import com.hanyu.car.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.hanyu.car.pulltorefresh.PullToRefreshListView;
import com.hanyu.car.utils.LogUtils;
import com.hanyu.car.utils.MyTimeUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class DriveSelf extends BaseFragment {
	@ViewInject(R.id.ptr_item)
	private PullToRefreshListView ptr_item;
	private BusinessListViewAdapter myadapter;
	private List<BusinessInfo> infos;

	public void setListener() {
		ptr_item.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
		ptr_item.setPullLoadEnabled(false);
		ptr_item.setPullRefreshEnabled(true);
		ptr_item.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				ptr_item.onPullDownRefreshComplete();
				ptr_item.setLastUpdatedLabel(MyTimeUtils.getStringDate());
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				ptr_item.onPullUpRefreshComplete();
			}
		});
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public View initView(LayoutInflater inflater) {
		view = View.inflate(context, R.layout.ptr_listview, null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		getDriveData();

		setListener();
		ptr_item.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				intent = new Intent(context, DriveSelfDetailsActivity.class);
				intent.putExtra("driveself_details", infos.get(position).bus_name.toString());
				intent.putExtra("busid", infos.get(position).busid);
				startActivity(intent);
			}
		});
	}

	private void getDriveData() {
		RequestParams params = new RequestParams();
		params.addBodyParameter("bus_cid", "1");
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.BUSINESS_LIST, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String json = arg0.result;
				LogUtils.e(getClass(), "json:" + json);
				infos = JSON.parseArray(json, BusinessInfo.class);
				myadapter = new BusinessListViewAdapter(context, infos);
				ptr_item.getRefreshableView().setAdapter(myadapter);
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				getDriveData();
			}
		});
	}

}
