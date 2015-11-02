package com.hanyu.car.activity.center;

import com.hanyu.car.R;
import com.hanyu.car.base.MyBaseActivity;
import com.hanyu.car.utils.SharedPreferencesUtil;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PersonalInformationActivity extends MyBaseActivity {
	protected static final int USENAME = 0;
	protected static final int PHONE = 1;
	protected static final int PASSWORD = 2;
	//用户名
	@ViewInject(R.id.pi_username_rl)
	private RelativeLayout pi_username_rl;
	//用户名改变文本
	@ViewInject(R.id.pi_username_tv)
	private TextView pi_username_tv;
	//电话号码
	@ViewInject(R.id.pi_phone_rl)
	private RelativeLayout pi_phone_rl;
	//电话号码改变文本
	@ViewInject(R.id.pi_phone_tv)
	private TextView pi_phone_tv;
	//修改密码
	@ViewInject(R.id.pi_pwd_rl)
	private RelativeLayout pi_pwd_rl;
	//修改密码改变文本
	@ViewInject(R.id.pi_pwd_et)
	private EditText pi_pwd_et;

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("个人资料");
		UseName();
		UsePhone();
		UsePassWord();
		setResult(199);
		
		pi_username_tv.setText(SharedPreferencesUtil.getStringData(this, "nickname", ""));
		pi_phone_tv.setText(SharedPreferencesUtil.getStringData(this, "phone", ""));
	}
	
	/**
	 * 打开用户名修改界面并取得返回值
	 */
	private void UseName() {
		pi_username_rl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(PersonalInformationActivity.this, UseNameActivity.class);
				startActivityForResult(intent, USENAME);
			}
		});
	}
	
	/**
	 * 打开用户联系电话修改界面并取得返回值
	 */
	private void UsePhone() {
		pi_phone_rl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(PersonalInformationActivity.this, UsePhoneActivity.class);
				startActivityForResult(intent, PHONE);
			}
		});
	}
	/**
	 * 打开用户密码修改界面并取得返回值
	 */
	private void UsePassWord() {
		pi_pwd_rl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(PersonalInformationActivity.this, UsePassWordActivity.class);
				startActivityForResult(intent, PASSWORD);
			}
		});
	}
	
	/**
	 * 返回值
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==0&&resultCode==0){
			if(data!=null){
				pi_username_tv.setText(data.getStringExtra("usename"));
			}
		}
		if(requestCode==1&&resultCode==0){
			if(data!=null){
				pi_phone_tv.setText(data.getStringExtra("usephone"));
			}
		}
		if(requestCode==2&&resultCode==0){
			if(data!=null){
				pi_pwd_et.setText(data.getStringExtra("usepassword"));
			}
		}
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.personalinformation;
	}

}
