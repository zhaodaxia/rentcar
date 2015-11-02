package com.hanyu.car.activity.center;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.hanyu.car.GlobalParams;
import com.hanyu.car.R;
import com.hanyu.car.base.MyBaseActivity;
import com.hanyu.car.global.MyApplication;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.utils.SharedPreferencesUtil;
import com.hanyu.car.utils.ToastUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class UsePhoneActivity extends MyBaseActivity {
	protected static final int USEPHONE = 0;
	@ViewInject(R.id.alter_phone_et)
	private EditText alter_phone_et;

	// 获取验证码
	@ViewInject(R.id.alter_phone_getcaptcha)
	private Button alter_phone_getcaptcha;
	// 验证码
	@ViewInject(R.id.alter_usephone_captcha_et)
	private EditText alter_usephone_captcha_et;
	@ViewInject(R.id.alter_usephone_save_bt)
	private Button alter_usephone_save_bt;

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("修改联系电话");

		alter_usephone_save_bt.setOnClickListener(new OnClickListener() {
			// 把电话号码返回
			@Override
			public void onClick(View v) {
				String captcha = alter_usephone_captcha_et.getText().toString();
				String usephone = alter_phone_et.getText().toString();
				if (TextUtils.isEmpty(usephone)) {
					Toast.makeText(UsePhoneActivity.this, "电话号码不能为空", 0).show();
				} else if (TextUtils.isEmpty(captcha)) {
					Toast.makeText(UsePhoneActivity.this, "验证码不能为空", 0).show();
				} else {
					// TODO 把电话号码和验证码发往服务器验证修改 如果验证码错误 需要提示
					updatePhone(usephone, captcha);
				}
			}
		});
	}

	protected void updatePhone(final String phone, String phoneCode) {
		loadingDialog.setLoadingText("修改号码中..");
		loadingDialog.show();

		RequestParams params = new RequestParams();
		params.addBodyParameter("act", "editphone");
		params.addBodyParameter("phone", phone);
		params.addBodyParameter("code", phoneCode);
		params.addBodyParameter("memberid", GlobalParams.memberId);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.UPDATE_PHONE, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				loadingDialog.dismiss();
				ToastUtils.show(UsePhoneActivity.this, "连接服务器失败");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				loadingDialog.dismiss();
				try {
					JSONObject jsonObject = new JSONObject(arg0.result);
					ToastUtils.show(UsePhoneActivity.this, jsonObject.getString("data"));
					if (jsonObject.getString("rsp").equals("succ")) {
						intent = new Intent();
						intent.putExtra("usephone", phone);
						SharedPreferencesUtil.saveStringData(UsePhoneActivity.this, "phone", phone);
						setResult(USEPHONE, intent);
						UsePhoneActivity.this.finish();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public int setLayout() {
		return R.layout.alter_usephone;
	}

}
