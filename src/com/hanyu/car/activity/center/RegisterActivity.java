package com.hanyu.car.activity.center;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

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

public class RegisterActivity extends MyBaseActivity implements OnClickListener {

	@ViewInject(R.id.register_user)
	private EditText register_user;
	@ViewInject(R.id.register_captcha)
	private EditText register_captcha;
	@ViewInject(R.id.register_pwd)
	private EditText register_pwd;
	@ViewInject(R.id.lostpwd_next)
	private Button lostpwd_next;

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("注册");
	}

	@Override
	public int setLayout() {
		return R.layout.register;
	}

	@Override
	public void initView() {

	}

	@Override
	public void initData() {

	}

	@Override
	public void initListener() {
		lostpwd_next.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lostpwd_next:
			register();
			break;

		default:
			break;
		}
	}

	private void register() {
		String username = register_user.getText().toString().trim();
		String password = register_pwd.getText().toString().trim();
		String regCode = register_captcha.getText().toString().trim();
		if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
			ToastUtils.show(this, "请检查手机号或密码是否为空!");
			return;
		}
		loadingDialog.setLoadingText("注册中...");
		loadingDialog.show();
		
		RequestParams params = new RequestParams();
		params.addBodyParameter("act", "reg");
		params.addBodyParameter("phone", username);
		params.addBodyParameter("pwd", password);
		params.addBodyParameter("cpwd", password);
		params.addBodyParameter("code", regCode);

		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.REGISTER, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				loadingDialog.dismiss();
				LogUtils.e(getClass(), arg0.result);
				if (arg0 != null && !arg0.result.isEmpty()) {
					try {
						JSONObject jsonObject = new JSONObject(arg0.result);
						String success = jsonObject.getString("rsp");
						if (!success.equals("fail")) {
							ToastUtils.show(RegisterActivity.this, jsonObject.getString("data"));
							finish();
						} else {
							ToastUtils.show(RegisterActivity.this, jsonObject.getString("data"));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				loadingDialog.dismiss();
			}
		});

	}

}
