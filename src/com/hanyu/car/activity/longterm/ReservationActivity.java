package com.hanyu.car.activity.longterm;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hanyu.car.R;
import com.hanyu.car.base.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;


public class ReservationActivity extends BaseActivity {
	@ViewInject(R.id.title_et)
	private EditText title_et;//标题
	@ViewInject(R.id.content_et)
	private EditText content_et;//内容
	@ViewInject(R.id.tel_et)
	private EditText tel_et;//电话号
	@ViewInject(R.id.reservation_commit)
	private Button reservation_commit;//提交按钮

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setBack();
		setTopTitle("咨询");
		
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.reservation_activity;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.reservation_commit:
			String title = title_et.getText().toString().trim();
			String content = content_et.getText().toString().trim();
			String phone = tel_et.getText().toString().trim();
			if (TextUtils.isEmpty(title)) {
				Toast.makeText(ReservationActivity.this, "请填写标题", 0).show();
			}else if (TextUtils.isEmpty(content)) {
				Toast.makeText(ReservationActivity.this, "请填写反馈内容", 0).show();
			}else if (TextUtils.isEmpty(phone)) {
				Toast.makeText(ReservationActivity.this, "请填写您的联系电话", 0).show();
			}else{
				//TODO 把以上反馈内容发往服务器端
			}
			break;

		default:
			break;
		}
		
	}

	@Override
	public void setListener() {
		reservation_commit.setOnClickListener(this);
		
	}

}
