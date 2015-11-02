package com.hanyu.car.activity.center;

import android.content.Intent;
import android.nfc.cardemulation.OffHostApduService;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.hanyu.car.R;
import com.hanyu.car.base.MyBaseActivity;
import com.hanyu.car.utils.ToastUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;

public class LostPassWordActivity extends MyBaseActivity implements OnClickListener{
	
	@ViewInject(R.id.lostpwd_next)
	private Button lostpwd_next;
	@ViewInject(R.id.lostpwd_user)
	private EditText lostpwd_user;
	@ViewInject(R.id.lostpwd_captcha)
	private EditText lostpwd_captcha;
	
	

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("忘记密码");
		Next();
	}
	
	/**
	 * 下一步
	 */
	private void Next() {
		lostpwd_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String username = lostpwd_user.getText().toString().trim();
				String phoneCode = lostpwd_captcha.getText().toString().trim();
				if (TextUtils.isEmpty(username)) {
					ToastUtils.show(LostPassWordActivity.this, "请检查手机号或验证码是否填写");
					return;
				}
				intent = new Intent(LostPassWordActivity.this, LostPassWordSecondActivity.class);
				intent.putExtra("username", username);
				intent.putExtra("phoneCode", phoneCode);
				startActivity(intent);
			}
		});
	}

	@Override
	public int setLayout() {
		return R.layout.lostpwd;
	}

	@Override
	public void initListener() {
		super.initListener();
	}
	@Override
	public void onClick(View v) {
		
	}

}
