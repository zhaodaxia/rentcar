package com.hanyu.car.activity.home;

import org.json.JSONException;
import org.json.JSONObject;

import com.hanyu.car.GlobalParams;
import com.hanyu.car.MainActivity;
import com.hanyu.car.R;
import com.hanyu.car.activity.center.LoginActivity;
import com.hanyu.car.activity.center.MyOrderActivity;
import com.hanyu.car.activity.center.OwnerActivity;
import com.hanyu.car.activity.center.PersonalInformationActivity;
import com.hanyu.car.activity.center.SettingActivity;
import com.hanyu.car.base.BaseActivity;
import com.hanyu.car.global.MyApplication;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.utils.LogUtils;
import com.hanyu.car.utils.SharedPreferencesUtil;
import com.hanyu.car.utils.ToastUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CenterActivity extends BaseActivity {
	// 退出按钮
	@ViewInject(R.id.center_exit)
	private Button center_exit;
	// 个人资料
	@ViewInject(R.id.personal_information_rl)
	private RelativeLayout personal_information_rl;
	// 我的预约
	@ViewInject(R.id.my_order_rl)
	private RelativeLayout my_order_rl;
	// 我是车主
	@ViewInject(R.id.owner_rl)
	private RelativeLayout owner_rl;
	@ViewInject(R.id.center_userName)
	private TextView center_userName;

	private Intent intent;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.center_exit:
			SharedPreferencesUtil.saveStringData(CenterActivity.this, "memberId", "-1");
			intent = new Intent(CenterActivity.this, LoginActivity.class);
			startActivity(intent);
			CenterActivity.this.finish();
			sendBroadcast(new Intent(MainActivity.CLOSE_ACTION));
			break;
		case R.id.personal_information_rl:
			intent = new Intent(CenterActivity.this, PersonalInformationActivity.class);
			startActivityForResult(intent, 1);
			break;
		case R.id.my_order_rl:
			intent = new Intent(CenterActivity.this, MyOrderActivity.class);
			startActivity(intent);
			break;
		case R.id.owner_rl:
			intent = new Intent(CenterActivity.this, OwnerActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.centerpager;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("个人中心");
		setRightIv(R.drawable.zx_03, new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(CenterActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});

		getMineData();
	}

	private void getMineData() {
		String username = SharedPreferencesUtil.getStringData(this, "nickname", "");
		if (!TextUtils.isEmpty(username)) {
			center_userName.setText(username);
			return;
		}
		if (GlobalParams.memberId.equals("-1")) {
			ToastUtils.show(this, "登陆失效，请重新登陆");
			return;
		}
		RequestParams params = new RequestParams();
		params.addBodyParameter("memberid", GlobalParams.memberId);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.USER_MINE, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				getMineData();

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				LogUtils.e(getClass(), arg0.result);
				try {
					JSONObject jsonObject = new JSONObject(arg0.result);
					String mName = jsonObject.getString("me_name");
					center_userName.setText(mName);
					SharedPreferencesUtil.saveStringData(CenterActivity.this, "nickname", mName);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode,resultCode, data);
		switch (resultCode) {
		case 199:
			center_userName.setText(SharedPreferencesUtil.getStringData(this, "nickname",""));
			break;
		}
	}
	@Override
	public void setListener() {
		center_exit.setOnClickListener(this);
		personal_information_rl.setOnClickListener(this);
		my_order_rl.setOnClickListener(this);
		owner_rl.setOnClickListener(this);
	}

}
