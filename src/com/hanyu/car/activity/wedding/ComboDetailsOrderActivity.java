package com.hanyu.car.activity.wedding;

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
import com.hanyu.car.utils.ToastUtils;
import com.hanyu.car.utils.ShowAgeDialog.OnsaveDayListener;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class ComboDetailsOrderActivity extends BaseActivity {
	
	private RelativeLayout back;
	
	//用车日期
	@ViewInject(R.id.combo_usecar_data)
	private RelativeLayout combo_usecar_data;
	//用车日期文本
	@ViewInject(R.id.combo_usecar_data_tv)
	private TextView combo_usecar_data_tv;
	
	//用车地址文本
	@ViewInject(R.id.combo_car_addr_tv)
	private TextView combo_car_addr_tv;
	
	//联系电话
	@ViewInject(R.id.combo_orderman_phone_et)
	private EditText combo_orderman_phone_et;
	
	//预约数据参数
	String date;//用车日期
	String combo_orderman_phone;//联系电话
	String combo_carAddr;//用车地址
	String combo_mcarid;//婚车ID 
	String combo_memberid;//会员ID
	
	//立即预约
	@ViewInject(R.id.combo_atonce_order)
	private RelativeLayout combo_atonce_order;
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.combo_details_order;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("立即预约");
		
		intent = getIntent();
		Bundle bundle = intent.getExtras();
		combo_mcarid = bundle.getString("mcarid");
		
		//用车日期
		data();
		
		//提交
		ComboCommit();
	}

	private void ComboCommit() {
		// TODO Auto-generated method stub
		combo_atonce_order.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				date = combo_usecar_data_tv.getText().toString();//用车日期
				combo_orderman_phone = combo_orderman_phone_et.getText().toString();//联系电话
				combo_carAddr = combo_car_addr_tv.getText().toString();//用车地址
				
				combo_memberid = GlobalParams.memberId;
				
				if(TextUtils.isEmpty(date)){
					Toast.makeText(getApplicationContext(), "请选择日期", 0).show();
				}else if(TextUtils.isEmpty(combo_carAddr)){
					Toast.makeText(getApplicationContext(), "请选择用车地址", 0).show();
				}else if(TextUtils.isEmpty(combo_orderman_phone)){
					Toast.makeText(getApplicationContext(), "请填写您的联系方式", 0).show();
				}else {
					//TODO 把以上数据整合发往服务器端
					sendComboOrder();
				}
			}
		});
	}

	protected void sendComboOrder() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("memberid", combo_memberid);
		params.addBodyParameter("mcarid", combo_mcarid);
		params.addBodyParameter("mcarapp_time", date);
		params.addBodyParameter("mcarapp_addr", combo_carAddr);
		params.addBodyParameter("mcarapp_phone", combo_orderman_phone);
		MyApplication.httpUtils.send(HttpMethod.POST,HttpUrl.PACKAGE_ORDER, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				ToastUtils.show(ComboDetailsOrderActivity.this, "会员ID为空");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				ToastUtils.show(ComboDetailsOrderActivity.this, "预约成功");
			}
		});
	}

	private void data() {
		// TODO Auto-generated method stub
		combo_usecar_data.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ShowAgeDialog sad = new ShowAgeDialog(ComboDetailsOrderActivity.this);
				sad.builder().show();
				sad.setOnSaveDay(new OnsaveDayListener() {
					@Override
					public void setDay(String saveDay, int age) {
						combo_usecar_data_tv.setText(saveDay);
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
