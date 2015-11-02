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

public class UseNameActivity extends MyBaseActivity {
	protected static final int USENAME = 0;
	@ViewInject(R.id.alter_usename_et)
	private EditText alter_usename_et;
	@ViewInject(R.id.alter_usename_save_bt)
	private Button alter_usename_save_bt;

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("修改用户名");

		alter_usename_save_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String usename = alter_usename_et.getText().toString();
				if (TextUtils.isEmpty(usename)) {
					Toast.makeText(UseNameActivity.this, "用户名不能为空", 0).show();
					return;
				} else {
					updataUsername(usename);
				}
			}
		});
	}

	protected void updataUsername(final String name) {
		loadingDialog.setLoadingText("提交中...");
		loadingDialog.show();
		
		RequestParams params = new RequestParams();
		params.addBodyParameter("memberid", GlobalParams.memberId);
		params.addBodyParameter("name", name);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.UPDATE_USERNAME, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				updataUsername(name);
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				loadingDialog.dismiss();
				try {
					JSONObject jsonObject = new JSONObject(arg0.result);
					ToastUtils.show(UseNameActivity.this, jsonObject.getString("data"));
					if (jsonObject.getString("rsp").equals("succ")) {
						SharedPreferencesUtil.saveStringData(UseNameActivity.this, "nickname", name);
						intent = new Intent();
						intent.putExtra("usename", name);
						setResult(USENAME, intent);
						UseNameActivity.this.finish();
					}
				} catch (JSONException e) {
					ToastUtils.show(UseNameActivity.this, "修改失败");
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public int setLayout() {
		return R.layout.alter_usename;
	}

}
