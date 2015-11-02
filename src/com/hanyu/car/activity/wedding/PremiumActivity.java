package com.hanyu.car.activity.wedding;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.hanyu.car.R;
import com.hanyu.car.adapter.wedding.ComboListViewAdapter;
import com.hanyu.car.adapter.wedding.PremiumListViewAdapter;
import com.hanyu.car.base.MyBaseActivity;
import com.hanyu.car.bean.PackageInfo;
import com.hanyu.car.bean.PremiumInfo;
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


public class PremiumActivity extends MyBaseActivity {
	@ViewInject(R.id.premium_ptr)
	private PullToRefreshListView premium_ptr;
	private PremiumListViewAdapter adapter;
	private RelativeLayout back;
	
	private List<PremiumInfo> infos;
	
	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("高端轿车");
//		adapter = new PremiumListViewAdapter(PremiumActivity.this);
//		premium_ptr.getRefreshableView().setAdapter(adapter);
		premium_ptr.setPullLoadEnabled(true);
		premium_ptr.setPullRefreshEnabled(true);
		premium_ptr.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				premium_ptr.onPullDownRefreshComplete();
				premium_ptr.setLastUpdatedLabel(MyTimeUtils.getStringDate());
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				premium_ptr.onPullUpRefreshComplete();
			}
		});
		
		premium_ptr.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				intent = new Intent(PremiumActivity.this, PremiumDetailsActivity.class);
				intent.putExtra("premium_details", infos.get(position).mcar_name);
				intent.putExtra("mcarid",infos.get(position).mcarid);
				startActivity(intent);
			}
		});
		getPremium();
	}

	private void getPremium() {
		// TODO Auto-generated method stub
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.PREMIUM_PACKAGE_LIST, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				getPremium();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String json = arg0.result;
				LogUtils.e(getClass(), json);
				infos = JSON.parseArray(json, PremiumInfo.class);
				adapter = new PremiumListViewAdapter(PremiumActivity.this, infos);
				premium_ptr.getRefreshableView().setAdapter(adapter);
			}
		});
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.wedding_premium;
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
