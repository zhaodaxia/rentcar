package com.hanyu.car.activity.center;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import com.hanyu.car.GlobalParams;
import com.hanyu.car.R;
import com.hanyu.car.base.BaseActivity;
import com.hanyu.car.global.MyApplication;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.utils.LogUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MyOrderActivity extends BaseActivity {
	@ViewInject(R.id.travel_order)
	private RelativeLayout travel_order;
	@ViewInject(R.id.wedding_order)
	private RelativeLayout wedding_order;
	@ViewInject(R.id.business_order)
	private RelativeLayout business_order;
	@ViewInject(R.id.longterm_order)
	private RelativeLayout longterm_order;
	@ViewInject(R.id.tv_travel)
	private TextView tv_travel;
	@ViewInject(R.id.tv_mcar)
	private TextView tv_mcar;
	@ViewInject(R.id.tv_busapp)
	private TextView tv_busapp;
	@ViewInject(R.id.tv_long)
	private TextView tv_long;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.travel_order:
			intent = new Intent(MyOrderActivity.this, TravelOrderActivity.class);
			startActivity(intent);
			break;
		case R.id.wedding_order:
			intent = new Intent(MyOrderActivity.this, WeddingOrderActivity.class);
			startActivity(intent);
			break;
		case R.id.business_order:
			intent = new Intent(MyOrderActivity.this, BusinessOrderActivity.class);
			startActivity(intent);
			break;
		case R.id.longterm_order:
			intent = new Intent(MyOrderActivity.this, LongTermOrderActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	public int setLayout() {
		return R.layout.my_order;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("我的预约");
		
		getOrderData();
	}

	private void getOrderData() {
		loadingDialog.show();
		RequestParams params = new RequestParams();
		params.addBodyParameter("memberid", GlobalParams.memberId);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.USER_ORDER, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				getOrderData();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				loadingDialog.dismiss();
				LogUtils.e(getClass(), arg0.result);
				try {
					JSONObject jsonObject = new JSONObject(arg0.result);
					String travel = jsonObject.getString("travel");
					String mcar = jsonObject.getString("mcar");
					String busapp = jsonObject.getString("busapp");
					String longCar = jsonObject.getString("long");
					tv_travel.setText("共"+travel+"条预约信息");
					tv_mcar.setText("共"+mcar+"条预约信息");
					tv_busapp.setText("共"+busapp+"条预约信息");
					tv_long.setText("共"+longCar+"条预约信息");
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	@Override
	public void setListener() {
		travel_order.setOnClickListener(this);
		wedding_order.setOnClickListener(this);
		business_order.setOnClickListener(this);
		longterm_order.setOnClickListener(this);
	}

}
