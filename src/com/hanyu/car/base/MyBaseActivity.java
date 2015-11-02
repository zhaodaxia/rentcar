package com.hanyu.car.base;

import com.hanyu.car.R;
import com.hanyu.car.view.ShapeLoadingDialog;
import com.lidroid.xutils.ViewUtils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class MyBaseActivity extends Activity {

	private RelativeLayout back;
	private TextView title;

	public Intent intent;
	public ShapeLoadingDialog loadingDialog;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 手机窗口设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置填充的layout
		setContentView(setLayout());

		// 注入控件
		ViewUtils.inject(this);

		loadingDialog = new ShapeLoadingDialog(this);
		loadingDialog.setLoadingText("加载中...");
		loadingDialog.setCanceledOnTouchOutside(false);
		loadingDialog.setOnkeyDownListener(new OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					loadingDialog.dismiss();
					finish();
				}
				return false;
			}
		});

		init(savedInstanceState);
		initView();
		initData();
		initListener();
	}

	public void initView() {

	}

	public void initData() {

	}

	public void initListener() {

	}

	public abstract void init(Bundle savedInstanceState);

	public abstract int setLayout();

	/**
	 * 设置头部标题
	 * 
	 * @param str
	 */
	public void setTopTitle(String str) {
		title = (TextView) findViewById(R.id.tv_title);
		if (str != null) {
			title.setText(str);
		}

	}

	/**
	 * 返回键
	 */
	public void setBack() {
		back = (RelativeLayout) findViewById(R.id.rl_back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}
}
