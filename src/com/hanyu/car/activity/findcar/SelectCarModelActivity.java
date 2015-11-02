package com.hanyu.car.activity.findcar;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.hanyu.car.R;
import com.hanyu.car.adapter.fastfindcar.SelectCarModelListViewAdapter;
import com.hanyu.car.base.MyBaseActivity;
import com.hanyu.car.bean.CarBrand;
import com.hanyu.car.global.MyApplication;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.utils.LogUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SelectCarModelActivity extends MyBaseActivity {
	protected static final int MODEL = 0;

	@ViewInject(R.id.select_item_litview)
	private ListView select_item_litview;

	private SharedPreferences sp;

	private SelectCarModelListViewAdapter adapter;
	
	//车的品牌ID
	private String Head_carbrandid;
	
	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("车型选择");

		select_item_litview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				sp = getSharedPreferences("carmodel", 0);
				Editor edit = sp.edit();
				intent = new Intent();
				String model = adapter.getItem(position).carbrand_name;
				intent.putExtra("carmodel", model);
				edit.putString("model", model);
				edit.commit();
				setResult(MODEL, intent);
				finish();
			}
		});
	}

	@Override
	public void initData() {
		super.initData();
		Head_carbrandid = getIntent().getStringExtra("Head_carbrandid");
		getCarData();

	}

	private void getCarData() {
		loadingDialog.show();
		
		RequestParams params = new RequestParams();
		params.addBodyParameter("pid", Head_carbrandid);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.CAR_BRAND, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				getCarData();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				loadingDialog.dismiss();
				
				LogUtils.e(getClass(), arg0.result);
				List<CarBrand> carBrands = JSON.parseArray(arg0.result, CarBrand.class);
				adapter = new SelectCarModelListViewAdapter(SelectCarModelActivity.this, carBrands);
				select_item_litview.setAdapter(adapter);
			}
		});
	}

	@Override
	public void initListener() {
		super.initListener();

	}

	@Override
	public int setLayout() {
		return R.layout.select_item;
	}

}
