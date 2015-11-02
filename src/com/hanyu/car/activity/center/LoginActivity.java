package com.hanyu.car.activity.center;

import org.json.JSONException;
import org.json.JSONObject;

import com.hanyu.car.GlobalParams;
import com.hanyu.car.MainActivity;
import com.hanyu.car.R;
import com.hanyu.car.base.MyBaseActivity;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends MyBaseActivity {
	@ViewInject(R.id.login_back)
	private RelativeLayout login_back;
	private Intent intent;
	// 忘记密码
	@ViewInject(R.id.login_find_pwd)
	private TextView login_find_pwd;
	// 注册
	@ViewInject(R.id.login_register)
	private LinearLayout login_register;
	@ViewInject(R.id.login_bt)
	private Button login_bt;
	@ViewInject(R.id.login_use)
	private EditText login_use;

	@ViewInject(R.id.login_pwd)
	private EditText login_pwd;
	@ViewInject(R.id.login_cb)
	private CheckBox login_cb;

	@Override
	public void init(Bundle savedInstanceState) {
		Back();
		LostPWD();
		Register();
		
		if (!SharedPreferencesUtil.getStringData(this, "memberId", "-1").equals("-1")) {
			//已登陆
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
			return;
		}

		boolean isSave = SharedPreferencesUtil.getBooleanData(this, "loginSave", false);
		login_cb.setChecked(isSave);
		if (isSave) {
			login_use.setText(SharedPreferencesUtil.getStringData(this, "username", ""));
			login_pwd.setText(SharedPreferencesUtil.getStringData(this, "password", ""));
		}

		login_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String user = login_use.getText().toString();
				String pwd = login_pwd.getText().toString();
				if (TextUtils.isEmpty(user)) {
					Toast.makeText(getApplicationContext(), "请输入您的用户名", 0).show();
				} else if (TextUtils.isEmpty(pwd)) {
					Toast.makeText(getApplicationContext(), "请输入您的密码", 0).show();
				} else {
					// TODO 把用户名密码发往服务器端验证，分成功和失败两种情况
					login(user, pwd);
				}
			}
		});
	}

	protected void login(final String user, final String pwd) {
		loadingDialog.setLoadingText("登陆中...");
		loadingDialog.show();
		RequestParams params = new RequestParams();
		params.addBodyParameter("name", user);
		params.addBodyParameter("pwd", pwd);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.LOGIN, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				loadingDialog.dismiss();
				if (arg0 != null && !arg0.result.isEmpty()) {
					try {
						JSONObject jsonObject = new JSONObject(arg0.result);
						String success = jsonObject.getString("rsp");
						if (!success.equals("fail")) {
							GlobalParams.memberId = jsonObject.getJSONObject("data").getString("memberid");
							SharedPreferencesUtil.saveStringData(LoginActivity.this, "memberId", jsonObject.getJSONObject("data").getString("memberid"));
							if (login_cb.isChecked()) {
								// 保存用户名和密码
								SharedPreferencesUtil.saveStringData(LoginActivity.this, "username", user);
								SharedPreferencesUtil.saveStringData(LoginActivity.this, "password", pwd);
								SharedPreferencesUtil.saveBooleanData(LoginActivity.this, "loginSave", true);
							}else{
								SharedPreferencesUtil.saveBooleanData(LoginActivity.this, "loginSave", false);
							}
							Intent intent = new Intent(LoginActivity.this, MainActivity.class);
							LoginActivity.this.startActivity(intent);
							LoginActivity.this.finish();
						} else {
							ToastUtils.show(LoginActivity.this, jsonObject.getString("data"));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
				LogUtils.e(getClass(), arg0.result);
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				loadingDialog.dismiss();
			}
		});
	}

	@Override
	public int setLayout() {
		return R.layout.login;
	}

	private void Back() {
		login_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();

			}
		});
	}

	/**
	 * 忘记密码
	 */
	private void LostPWD() {
		login_find_pwd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(LoginActivity.this, LostPassWordActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * 注册
	 */
	private void Register() {
		login_register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
			}
		});
	}

}
