package com.hanyu.car.activity.center;

import org.json.JSONException;
import org.json.JSONObject;

import com.hanyu.car.GlobalParams;
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

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UsePassWordActivity extends MyBaseActivity {
	protected static final int USEPASSWORD = 0;
	// 原密码
	@ViewInject(R.id.alter_usepassword_old)
	private EditText alter_usepassword_old;

	// 新密码
	@ViewInject(R.id.alter_usepassword_new1)
	private EditText alter_usepassword_new1;
	// 确认新密码
	@ViewInject(R.id.alter_usepassword_new2)
	private EditText alter_usepassword_new2;

	@ViewInject(R.id.alter_usepassword_save_bt)
	private Button alter_usepassword_save_bt;

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("修改密码");

		alter_usepassword_save_bt.setOnClickListener(new OnClickListener() {

			// 把密码返回
			@Override
			public void onClick(View v) {
				String oldpassword = alter_usepassword_old.getText().toString();
				String usepassword1 = alter_usepassword_new1.getText().toString();
				String usepassword2 = alter_usepassword_new2.getText().toString();
				if (TextUtils.isEmpty(oldpassword)) {
					Toast.makeText(UsePassWordActivity.this, "原密码不能为空", 0).show();
					return;
				} else if (TextUtils.isEmpty(usepassword1)) {
					Toast.makeText(UsePassWordActivity.this, "新密码不能为空", 0).show();
					return;
				} else if (TextUtils.isEmpty(usepassword2)) {
					Toast.makeText(UsePassWordActivity.this, "请再次输入新密码", 0).show();
					return;
				} else if (usepassword1.equals(usepassword2)) {
					// TODO 把新密码和旧密码发往服务器验证修改
					updataPassword(oldpassword, usepassword1, usepassword2);
				} else {
					Toast.makeText(UsePassWordActivity.this, "两次输入的密码不一致", 0).show();
				}
			}
		});
	}

	protected void updataPassword(String oldpassword, String usepassword1, String usepassword2) {
		loadingDialog.setLoadingText("修改密码中..");
		loadingDialog.show();

		RequestParams params = new RequestParams();
		params.addBodyParameter("pwd", oldpassword);
		params.addBodyParameter("npwd", usepassword1);
		params.addBodyParameter("cpwd", usepassword2);
		params.addBodyParameter("memberid", GlobalParams.memberId);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.UPDATE_PWD, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				loadingDialog.dismiss();
				ToastUtils.show(UsePassWordActivity.this, "连接服务器失败");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				loadingDialog.dismiss();
				try {
					JSONObject jsonObject = new JSONObject(arg0.result);
					ToastUtils.show(UsePassWordActivity.this, jsonObject.getString("data"));
					if (jsonObject.getString("rsp").equals("succ")) {
						UsePassWordActivity.this.finish();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.alter_usepasswrod;
	}

}
