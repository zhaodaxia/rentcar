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
import com.hanyu.car.utils.ToastUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class LostPassWordSecondActivity extends MyBaseActivity implements OnClickListener {

	@ViewInject(R.id.newpwd_et1)
	private EditText newpwd_et1;
	@ViewInject(R.id.newpwd_et2)
	private EditText newpwd_et2;
	@ViewInject(R.id.login_bt)
	private Button login_bt;

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("设置新密码");
	}

	@Override
	public int setLayout() {
		return R.layout.lostpwd_second;
	}

	@Override
	public void initData() {
		super.initData();
	}

	@Override
	public void initListener() {
		super.initListener();
		login_bt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_bt:
			findPassword();
			break;

		default:
			break;
		}
	}

	private void findPassword() {
		String phone = getIntent().getStringExtra("username");
		String phoneCode = getIntent().getStringExtra("phoneCode");
		String newPwd = newpwd_et1.getText().toString().trim();
		String newPwd2 = newpwd_et2.getText().toString().trim();

		if (TextUtils.isEmpty(newPwd) || TextUtils.isEmpty(newPwd2)) {
			ToastUtils.show(this, "请检查密码和确认密码是否填写");
			return;
		}

		if (!newPwd.equals(newPwd2)) {
			ToastUtils.show(this, "请检查密码和确认密码是否一致");
			return;
		}
		
		loadingDialog.setLoadingText("找回密码中...");
		loadingDialog.show();

		RequestParams params = new RequestParams();
		params.addBodyParameter("act", "setpwd");
		params.addBodyParameter("phone", phone);
		params.addBodyParameter("pwd", newPwd);
		params.addBodyParameter("cpwd", newPwd2);
		params.addBodyParameter("code", phoneCode);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.LOST_PASSWORD, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				loadingDialog.dismiss();
				String json = arg0.result;
				try {
					JSONObject jsonObject = new JSONObject(json);
					if (jsonObject.getString("rsp").equals("succ")) {
						ToastUtils.show(LostPassWordSecondActivity.this, "修改成功");
						finish();
						return;
					} else {
						ToastUtils.show(LostPassWordSecondActivity.this, jsonObject.getString("data"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				loadingDialog.dismiss();
				ToastUtils.show(LostPassWordSecondActivity.this, "连接服务器失败");
			}

		});

	}

}
