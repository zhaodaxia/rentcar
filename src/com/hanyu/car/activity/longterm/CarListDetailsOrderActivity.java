package com.hanyu.car.activity.longterm;

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
import com.hanyu.car.activity.wedding.LuxuryDetailsOrderActivity;
import com.hanyu.car.activity.wedding.SelectCarColorActivity;
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

public class CarListDetailsOrderActivity extends BaseActivity {
	
	protected static final int LONGAPP_COLOR = 0;
	//返回键
	private RelativeLayout back;
	//预约数据参数
	String longapp_starttime;//用车开始日期
	String longapp_endtime;//用车结束日期
	String longapp_color;//车身颜色
	String longapp_number;//用车数量
	String longapp_phone;//联系电话
	String longapp_addr;//用车地址
	String longid;//长期租车ID 
	String memberid;//会员ID
	
	//UI参数
	//用车日期
	@ViewInject(R.id.longapp_usecar_data)
	private RelativeLayout longapp_usecar_data;
	//用车开始日期文本
	@ViewInject(R.id.longapp_starttime)
	private TextView longapp_starttime_tv;
	//用车结束日期文本
	@ViewInject(R.id.longapp_endtime)
	private TextView longapp_endtime_tv;
		
	//用车地址
	@ViewInject(R.id.longapp_car_addr)
	private RelativeLayout longapp_car_addr;
	//用车地址文本
	@ViewInject(R.id.longapp_addr)
	private TextView longapp_addr_tv;
		
	//车身颜色
	@ViewInject(R.id.longapp_car_color)
	private RelativeLayout longapp_car_color;
	//车身颜色文本
	@ViewInject(R.id.longapp_color)
	private TextView longapp_color_tv;
		
	//联系电话
	@ViewInject(R.id.longapp_phone)
	private EditText longapp_phone_tv;
		
	//预约数量
	@ViewInject(R.id.longapp_number)
	private EditText longapp_number_et;
	
	//立即预约
	@ViewInject(R.id.carlist_atonce_order)
	private RelativeLayout carlist_atonce_order;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.carlist_details_order;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("立即预约");
		
		intent = getIntent();
		Bundle bundle = intent.getExtras();
		longid = bundle.getString("longid");
		
		//用车日期
		data();
				
		//车身颜色
		longapp_CarColor();
				
		//提交
		longapp_Commit();
	}

	private void longapp_Commit() {
		// TODO Auto-generated method stub
		carlist_atonce_order.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				longapp_starttime=longapp_starttime_tv.getText().toString();//用车开始日期
				longapp_endtime=longapp_endtime_tv.getText().toString();//用车结束日期
				longapp_color=longapp_color_tv.getText().toString();//车身颜色
				longapp_number=longapp_number_et.getText().toString();//用车数量
				longapp_phone=longapp_phone_tv.getText().toString();//联系电话
				longapp_addr=longapp_addr_tv.getText().toString();//用车地址
				memberid=GlobalParams.memberId;;//会员ID
				
				if(TextUtils.isEmpty(longapp_starttime)){
					Toast.makeText(getApplicationContext(), "请选择开始日期", 0).show();
				}else if(TextUtils.isEmpty(longapp_endtime)){
					Toast.makeText(getApplicationContext(), "请选择结束日期", 0).show();
				}else if(TextUtils.isEmpty(longapp_addr)){
					Toast.makeText(getApplicationContext(), "请选择用车地址", 0).show();
				}else if(TextUtils.isEmpty(longapp_number)){
					Toast.makeText(getApplicationContext(), "请选择用车数量", 0).show();
				}else if(TextUtils.isEmpty(longapp_color)){
					Toast.makeText(getApplicationContext(), "请选择车身颜色", 0).show();
				}else if(TextUtils.isEmpty(longapp_phone)){
					Toast.makeText(getApplicationContext(), "请填写您的联系方式", 0).show();
				}else {
					//TODO 把以上数据整合发往服务器端
					sendLongappOrder();
				}
			}
		});
	}

	protected void sendLongappOrder() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("memberid", memberid);
		params.addBodyParameter("longid", longid);
		params.addBodyParameter("longapp_starttime", longapp_starttime);
		params.addBodyParameter("longapp_endtime", longapp_endtime);
		params.addBodyParameter("longapp_addr", longapp_addr);
		params.addBodyParameter("longapp_number", longapp_number);
		params.addBodyParameter("longapp_color", longapp_color);
		params.addBodyParameter("longapp_phone", longapp_phone);
		MyApplication.httpUtils.send(HttpMethod.POST,HttpUrl.LONGTERM_ORDER, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				ToastUtils.show(CarListDetailsOrderActivity.this, "会员ID为空");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				ToastUtils.show(CarListDetailsOrderActivity.this, "预约成功");
			}
		});
	}

	private void longapp_CarColor() {
		// TODO Auto-generated method stub
		longapp_car_color.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(getApplicationContext(), SelectCarColorActivity.class);
				startActivityForResult(intent, LONGAPP_COLOR);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==LONGAPP_COLOR && resultCode==0){
			if(data!=null){
				longapp_color_tv.setText(data.getStringExtra("carcolor").toString());
			}
		}
	}

	private void data() {
		// TODO Auto-generated method stub
		//起始日期
		longapp_starttime_tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowAgeDialog sad = new ShowAgeDialog(CarListDetailsOrderActivity.this);
				sad.builder().show();
				sad.setOnSaveDay(new OnsaveDayListener() {
					@Override
					public void setDay(String saveDay, int age) {
						longapp_starttime_tv.setText(saveDay);
					}
				});
			}
		});
		
		//截止日期
		longapp_endtime_tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowAgeDialog sad = new ShowAgeDialog(CarListDetailsOrderActivity.this);
				sad.builder().show();
				sad.setOnSaveDay(new OnsaveDayListener() {
					@Override
					public void setDay(String saveDay, int age) {
						longapp_endtime_tv.setText(saveDay);
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
