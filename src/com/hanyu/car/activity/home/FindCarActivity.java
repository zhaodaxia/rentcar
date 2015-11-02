package com.hanyu.car.activity.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.hanyu.car.R;
import com.hanyu.car.activity.findcar.SelectCarBrandActivity;
import com.hanyu.car.activity.findcar.UseActivity;
import com.hanyu.car.activity.findcar.VoiceSearchCarActivity;
import com.hanyu.car.base.MyBaseActivity;
import com.hanyu.car.global.MyApplication;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.utils.LogUtils;
import com.hanyu.car.utils.ToastUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 快速寻车界面
 * 
 * @author yang
 * 
 */
public class FindCarActivity extends MyBaseActivity {
	private static final String tag = "FindCarActivity";

	@ViewInject(R.id.use_tv)
	// 用途右端文本
	private TextView use_tv;
	@ViewInject(R.id.brand_tv)
	// 品牌右端文本
	private TextView brand_tv;
	@ViewInject(R.id.findcar_et)
	// 联系人
	private EditText findcar_et;
	@ViewInject(R.id.findcar_phone_et)
	// 手机号
	private TextView findcar_phone_et;

	private String use;
	private String brand;
	private String user;
	private String phone;

	private SharedPreferences sp;

	protected static final int USECODE = 0;

	protected static final int BRAND = 1;

	@ViewInject(R.id.findcar_use)
	// 用途
	private RelativeLayout findcar_use;

	@ViewInject(R.id.findcar_model)
	// 车型
	private RelativeLayout findcar_model;

	@ViewInject(R.id.search_fast_findcar)
	// 搜索
	private Button search_fast_findcar;

	@ViewInject(R.id.voice_fast_findcar)
	// 语音寻车
	private RelativeLayout voice_fast_findcar;

	@Override
	public int setLayout() {
		return R.layout.fast_findcar;
	}

	public void init(Bundle savedInstanceState) {
		setTopTitle("快速寻车");
		setBack();

		Use();
		Brand();
		Commit();
		Voice();
	}

	private void Commit() {
		search_fast_findcar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				use = use_tv.getText().toString();
				brand = brand_tv.getText().toString();
				user = findcar_et.getText().toString();
				phone = findcar_phone_et.getText().toString();
				LogUtils.i(tag, "提交");
				if (TextUtils.isEmpty(use)) {
					Toast.makeText(FindCarActivity.this, "请选择用途", 0).show();
					return;
				} else if (TextUtils.isEmpty(brand)) {
					Toast.makeText(FindCarActivity.this, "请选择品牌", 0).show();
					return;
				} else if (TextUtils.isEmpty(user)) {
					Toast.makeText(FindCarActivity.this, "请填写您的姓名", 0).show();
					return;
				} else if (TextUtils.isEmpty(phone)) {
					Toast.makeText(FindCarActivity.this, "请填写您的联系方式", 0).show();
					return;
				}
				// TODO 把以上信息发往服务器端
				commitFindCar(use, brand, user, phone);
			}
		});
	}

	/**
	 * 提交寻车
	 */
	protected void commitFindCar(String use, String brand, String user, String phone) {
		loadingDialog.setLoadingText("提交寻车中...");
		loadingDialog.show();

		RequestParams params = new RequestParams();
		params.addBodyParameter("fast_use", use);
		params.addBodyParameter("fast_brand", brand);
		params.addBodyParameter("fast_linkman", user);
		params.addBodyParameter("fast_phone", phone);

		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.FAST_FIND, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				loadingDialog.dismiss();
				ToastUtils.show(FindCarActivity.this, "提交失败，请检查网络是否正常");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				loadingDialog.dismiss();
				try {
					JSONObject jsonObject = new JSONObject(arg0.result);
					ToastUtils.show(FindCarActivity.this, jsonObject.getString("data"));
					if (jsonObject.getString("rsp").equals("succ")) {
						FindCarActivity.this.finish();
					}
				} catch (JSONException e) {
					ToastUtils.show(FindCarActivity.this, "提交寻车发生错误");
					e.printStackTrace();
				}
			}
		});
	}

	private void Voice() {
		voice_fast_findcar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LogUtils.i(tag, "进入到语音寻车界面");
				intent = new Intent(FindCarActivity.this, VoiceSearchCarActivity.class);
				startActivity(intent);
			}
		});
	}

	private void Brand() {
		findcar_model.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LogUtils.i(tag, "进入到品牌选择界面");
				intent = new Intent(FindCarActivity.this, SelectCarBrandActivity.class);
				startActivityForResult(intent, BRAND);
			}
		});
	}

	private void Use() {
		findcar_use.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				LogUtils.i(tag, "进入到选择用途界面");
				intent = new Intent(FindCarActivity.this, UseActivity.class);
				startActivityForResult(intent, USECODE);

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0 && resultCode == 0) {
			if (data != null) {
				use_tv.setText(data.getStringExtra("use").toString());
			}
		}
		if (requestCode == 1 && resultCode == 0) {
			if (data != null) {
				String brand = data.getStringExtra("brand").toString();
				sp = getSharedPreferences("carmodel", 0);
				String model = sp.getString("model", "");
				brand_tv.setText(brand + model);
			}
		}
	}

}
