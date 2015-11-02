package com.hanyu.car.activity.wedding;

import java.util.Collections;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.hanyu.car.GlobalParams;
import com.hanyu.car.R;
import com.hanyu.car.activity.findcar.SelectCarBrandActivity;
import com.hanyu.car.activity.findcar.SelectCarModelActivity;
import com.hanyu.car.adapter.fastfindcar.SelectCarBrandListViewAdapter;
import com.hanyu.car.base.MyBaseActivity;
import com.hanyu.car.bean.CarBrand;
import com.hanyu.car.global.MyApplication;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.utils.LogUtils;
import com.hanyu.car.utils.ShowAgeDialog;
import com.hanyu.car.utils.ShowAgeDialog.OnsaveDayListener;
import com.hanyu.car.utils.ToastUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class OrderActivity extends MyBaseActivity {
	protected static final int HEADBRAND = 0;

	protected static final int FLOOWBRAND = 1;

	protected static final int HEADMODEL = 2;

	protected static final int FOLLOWMODEL = 3;

	protected static final int HEADCOLOR = 4;

	protected static final int FOLLOWCOLOR = 5;

	//用车日期
	@ViewInject(R.id.usecar_data)
	private RelativeLayout usecar_data;
	
	//用车日期文本
	@ViewInject(R.id.usecar_data_tv)
	private TextView usecar_data_tv;
	
	//头车品牌
	@ViewInject(R.id.head_car_brand)
	private RelativeLayout head_car_brand;
	
	//头车品牌文本
	@ViewInject(R.id.head_car_brand_tv)
	private TextView head_car_brand_tv;
	
	//头车车型
	@ViewInject(R.id.head_car_model)
	private RelativeLayout head_car_model;
	
	//头车车型文本
	@ViewInject(R.id.head_car_model_tv)
	private TextView head_car_model_tv;
	
	//头车车身颜色
	@ViewInject(R.id.head_car_color)
	private RelativeLayout head_car_color;
	
	//头车车身颜色文本
	@ViewInject(R.id.head_car_color_tv)
	private TextView head_car_color_tv;
	
	//跟车品牌
	@ViewInject(R.id.follow_car_brand)
	private RelativeLayout follow_car_brand;
	
	//跟车品牌文本
	@ViewInject(R.id.follow_car_brand_tv)
	private TextView follow_car_brand_tv;
	
	//跟车车型
	@ViewInject(R.id.follow_car_model)
	private RelativeLayout follow_car_model;
	
	//跟车车型文本
	@ViewInject(R.id.follow_car_model_tv)
	private TextView follow_car_model_tv;
	
	//跟车车身颜色
	@ViewInject(R.id.follow_car_color)
	private RelativeLayout follow_car_color;
	
	//跟车车身颜色文本
	@ViewInject(R.id.follow_car_color_tv)
	private TextView follow_car_color_tv;
	
	//跟车数量
	@ViewInject(R.id.follow_car_number)
	private RelativeLayout follow_car_number;
	
	//跟车数量文本
	@ViewInject(R.id.follow_car_number_et)
	private EditText follow_car_number_et;
	
	//预约人电话
	@ViewInject(R.id.orderman_phone)
	private RelativeLayout orderman_phone;
	
	//预约人电话文本
	@ViewInject(R.id.orderman_phone_et)
	private EditText orderman_phone_et;
	
	//立即预约
	@ViewInject(R.id.oeder_commit)
	private Button oeder_commit;
	
	//返回的品牌ID
	private String Head_carbrandid;
	private String Follow_carbrandid;
	
	private Intent intent;

	//提交预约所用参数
	String memberid = GlobalParams.memberId;
	String date;
	String head_brand;
	String head_model;
	String head_color;
	String follow_brand;
	String follow_model;
	String follow_color;
	String follow_number;
	String phone;
	
	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("在线预约");
		
		Date();
		
		HeadBrand();
		HeadModel();
		HeadColor();
		
		FollowBrand();
		FollowModel();
		FollowColor();
		Commit();
	}
	
	/**
	 * 提交
	 */
	private void Commit() {
		oeder_commit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				date = usecar_data_tv.getText().toString();
				head_brand = head_car_brand_tv.getText().toString();
				head_model = head_car_model_tv.getText().toString();
				head_color = head_car_color_tv.getText().toString();
				follow_brand = follow_car_brand_tv.getText().toString();
				follow_model = follow_car_model_tv.getText().toString();
				follow_color = follow_car_color_tv.getText().toString();
				follow_number = follow_car_number_et.getText().toString();
				phone = orderman_phone_et.getText().toString();
				if(TextUtils.isEmpty(date)){
					Toast.makeText(getApplicationContext(), "请选择日期", 0).show();
				}else if(TextUtils.isEmpty(head_brand)){
					Toast.makeText(getApplicationContext(), "请选择头车品牌", 0).show();
				}else if(TextUtils.isEmpty(head_model)){
					Toast.makeText(getApplicationContext(), "请选择头车车型", 0).show();
				}else if(TextUtils.isEmpty(head_color)){
					Toast.makeText(getApplicationContext(), "请选择头车颜色", 0).show();
				}else if(TextUtils.isEmpty(follow_brand)){
					Toast.makeText(getApplicationContext(), "请选择跟车品牌", 0).show();
				}else if(TextUtils.isEmpty(follow_model)){
					Toast.makeText(getApplicationContext(), "请选择跟车车型", 0).show();
				}else if(TextUtils.isEmpty(follow_color)){
					Toast.makeText(getApplicationContext(), "请选择跟车颜色", 0).show();
				}else if(TextUtils.isEmpty(follow_number)){
					Toast.makeText(getApplicationContext(), "请填写跟车数量", 0).show();
				}else if(TextUtils.isEmpty(phone)){
					Toast.makeText(getApplicationContext(), "请填写您的联系方式", 0).show();
				}else {
					//TODO 把以上数据整合发往服务器端
					sendOrder();
				}
			}
		});
	}
	
	protected void sendOrder() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("memberid", "memberid");
		params.addBodyParameter("mcarcust_time", "usecar_data_tv");
		params.addBodyParameter("mcarcust_tbrand", "head_brand");
		params.addBodyParameter("mcarcust_tmodel", "head_model");
		params.addBodyParameter("mcarcust_tcolor", "head_color");
		params.addBodyParameter("mcarcust_gbrand", "follow_brand");
		params.addBodyParameter("mcarcust_gmodel", "follow_model");
		params.addBodyParameter("mcarcust_gcolor", "follow_color");
		params.addBodyParameter("mcarcust_phone", "phone");
		params.addBodyParameter("mcarcust_gnumber", "follow_number");
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.CUSTOM_ORDER,params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				sendOrder();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				ToastUtils.show(getApplicationContext(),"预约成功");
			}
		});
	}

	/**
	 * 选择日期
	 */
	private void Date() {
		usecar_data.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ShowAgeDialog sad = new ShowAgeDialog(OrderActivity.this);
				sad.builder().show();
				sad.setOnSaveDay(new OnsaveDayListener() {
					@Override
					public void setDay(String saveDay, int age) {
						usecar_data_tv.setText(saveDay);
					}
				});
			}
		});
	}

	/**
	 * 头车品牌界面（与快速寻车品牌界面一样）
	 */
	private void HeadBrand() {
		head_car_brand.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(getApplicationContext(), SelectBrandActivity.class);
				
				startActivityForResult(intent, HEADBRAND);
			}
		});
	}
	/**
	 * 跟车品牌界面（与快速寻车品牌界面一样）
	 */
	private void FollowBrand() {
		follow_car_brand.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(getApplicationContext(), SelectFollowBrandActivity.class);
				startActivityForResult(intent, FLOOWBRAND);
			}
		});
	}
	/**
	 * 头车车型界面（与快速寻车品牌界面一样）
	 */
	private void HeadModel() {
		head_car_model.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(getApplicationContext(), SelectCarModelActivity.class);
				intent.putExtra("Head_carbrandid", Head_carbrandid);
				startActivityForResult(intent, HEADMODEL);
			}
		});
	}
	/**
	 * 跟车车型界面（与快速寻车品牌界面一样）
	 */
	private void FollowModel() {
		follow_car_model.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(getApplicationContext(), SelectFollowCarModelActivity.class);
				intent.putExtra("Follow_carbrandid", Follow_carbrandid);
				startActivityForResult(intent, FOLLOWMODEL);
			}
		});
	}
	
	/**
	 * 头车车身颜色界面
	 */
	private void HeadColor() {
		head_car_color.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(getApplicationContext(), SelectCarColorActivity.class);
				startActivityForResult(intent, HEADCOLOR);
			}
		});
	}
	/**
	 * 跟车车身颜色界面
	 */
	private void FollowColor() {
		follow_car_color.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(getApplicationContext(), SelectCarColorActivity.class);
				startActivityForResult(intent, FOLLOWCOLOR);
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode==0&&resultCode==0){
			if(data!=null){
				head_car_brand_tv.setText(data.getStringExtra("Head_carbrand_name").toString());
				Head_carbrandid = data.getStringExtra("Head_carbrandid").toString();
				//清空头车车型
				head_car_model_tv.setText("");
			}
		}
		if(requestCode==1&&resultCode==0){
			if(data!=null){
				follow_car_brand_tv.setText(data.getStringExtra("Follow_carbrand_name").toString());
				Follow_carbrandid = data.getStringExtra("Follow_carbrandid").toString();
				//清空跟车车型
				follow_car_model_tv.setText("");
			}
		}
		if(requestCode==2&&resultCode==0){
			if(data!=null){
				head_car_model_tv.setText(data.getStringExtra("carmodel").toString());
			}
		}
		if(requestCode==3&&resultCode==0){
			if(data!=null){
				follow_car_model_tv.setText(data.getStringExtra("carmodel").toString());
			}
		}
		if(requestCode==4&&resultCode==0){
			if(data!=null){
				head_car_color_tv.setText(data.getStringExtra("carcolor").toString());
			}
		}
		if(requestCode==5&&resultCode==0){
			if(data!=null){
				follow_car_color_tv.setText(data.getStringExtra("carcolor").toString());
			}
		}
	}
	
	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.wedding_order;
	}

}
