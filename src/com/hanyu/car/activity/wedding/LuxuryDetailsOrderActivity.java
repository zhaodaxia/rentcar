package com.hanyu.car.activity.wedding;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hanyu.car.GlobalParams;
import com.hanyu.car.R;
import com.hanyu.car.base.BaseActivity;
import com.hanyu.car.global.MyApplication;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.utils.ShowAgeDialog;
import com.hanyu.car.utils.ShowAgeDialog.OnsaveDayListener;
import com.hanyu.car.utils.ToastUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class LuxuryDetailsOrderActivity extends BaseActivity {

	private RelativeLayout back;
	
	protected static final int LUXURY_COLOR = 0;
	//用车日期
	@ViewInject(R.id.luxury_usecar_data)
	private RelativeLayout luxury_usecar_data;
	//用车日期文本
	@ViewInject(R.id.luxury_usecar_data_tv)
	private TextView luxury_usecar_data_tv;
	
	//用车地址
	@ViewInject(R.id.luxury_car_addr)
	private RelativeLayout luxury_car_addr;
	//用车地址文本
	@ViewInject(R.id.luxury_car_addr_tv)
	private TextView luxury_car_addr_tv;
	
	//车身颜色
	@ViewInject(R.id.luxury_car_color)
	private RelativeLayout luxury_car_color;
	//车身颜色文本
	@ViewInject(R.id.luxury_car_color_tv)
	private TextView luxury_car_color_tv;
	
	//联系电话
	@ViewInject(R.id.luxury_orderman_phone_et)
	private EditText luxury_orderman_phone_et;
	
	//预约数量
	@ViewInject(R.id.luxury_car_number_et)
	private EditText luxury_car_number_et;
	
	//立即预约
	@ViewInject(R.id.luxury_atonce_order)
	private RelativeLayout luxury_atonce_order;
	
	//预约数据参数
	String date;//用车日期
	String luxury_carColor;//车身颜色
	String luxury_car_number;//用车数量
	String luxury_orderman_phone;//联系电话
	String luxury_carAddr;//用车地址
	String luxury_mcarid;//婚车ID 
	String luxury_memberid;//会员ID
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.luxury_details_order;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("立即预约");
		
		intent = getIntent();
		Bundle bundle = intent.getExtras();
		luxury_mcarid = bundle.getString("mcarid");
		
		//用车日期
		data();
		
		//车身颜色
		LuxuryColor();
		
		//提交
		LuxuryCommit();
	}
	
	private void LuxuryCommit() {
		// TODO Auto-generated method stub
		luxury_atonce_order.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				date = luxury_usecar_data_tv.getText().toString();//用车日期
				luxury_carColor = luxury_car_color_tv.getText().toString();//车身颜色
				luxury_car_number = luxury_car_number_et.getText().toString();//用车数量
				luxury_orderman_phone = luxury_orderman_phone_et.getText().toString();//联系电话
				luxury_carAddr = luxury_car_addr_tv.getText().toString();//用车地址
				
				luxury_memberid = GlobalParams.memberId;//获取会员ID
				
				if(TextUtils.isEmpty(date)){
					Toast.makeText(getApplicationContext(), "请选择日期", 0).show();
				}else if(TextUtils.isEmpty(luxury_carAddr)){
					Toast.makeText(getApplicationContext(), "请选择用车地址", 0).show();
				}else if(TextUtils.isEmpty(luxury_car_number)){
					Toast.makeText(getApplicationContext(), "请选择用车数量", 0).show();
				}else if(TextUtils.isEmpty(luxury_carColor)){
					Toast.makeText(getApplicationContext(), "请选择车身颜色", 0).show();
				}else if(TextUtils.isEmpty(luxury_orderman_phone)){
					Toast.makeText(getApplicationContext(), "请填写您的联系方式", 0).show();
				}else {
					//TODO 把以上数据整合发往服务器端
					sendLuxuryOrder();
				}
			}
		});
	}

	protected void sendLuxuryOrder() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("memberid", luxury_memberid);
		params.addBodyParameter("mcarid", luxury_mcarid);
		params.addBodyParameter("mcarapp_time", date);
		params.addBodyParameter("mcarapp_addr", luxury_carAddr);
		params.addBodyParameter("mcarapp_number", luxury_car_number);
		params.addBodyParameter("mcarapp_color", luxury_carColor);
		params.addBodyParameter("mcarapp_phone", luxury_orderman_phone);
		MyApplication.httpUtils.send(HttpMethod.POST,HttpUrl.LUXURY_ORDER, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				ToastUtils.show(LuxuryDetailsOrderActivity.this, "会员ID为空");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				ToastUtils.show(LuxuryDetailsOrderActivity.this, "预约成功");
			}
		});
	}

	private void LuxuryColor() {
		// TODO Auto-generated method stub
		luxury_car_color.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(getApplicationContext(), SelectCarColorActivity.class);
				startActivityForResult(intent, LUXURY_COLOR);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==LUXURY_COLOR && resultCode==0){
			if(data!=null){
				luxury_car_color_tv.setText(data.getStringExtra("carcolor").toString());
			}
		}
	}

	private void data() {
		// TODO Auto-generated method stub
		luxury_usecar_data.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ShowAgeDialog sad = new ShowAgeDialog(LuxuryDetailsOrderActivity.this);
				sad.builder().show();
				sad.setOnSaveDay(new OnsaveDayListener() {
					@Override
					public void setDay(String saveDay, int age) {
						luxury_usecar_data_tv.setText(saveDay);
					}
				});
			}
		});
	}
	@Override
	public void setListener() {
		// TODO Auto-generated method stub

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
