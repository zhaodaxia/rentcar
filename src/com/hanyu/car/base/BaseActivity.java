package com.hanyu.car.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hanyu.car.R;
import com.hanyu.car.view.ShapeLoadingDialog;
import com.lidroid.xutils.ViewUtils;

/**
 * 界面基类----所有activity需继承此类
 * 
 * @author 
 * 
 */
public abstract class BaseActivity extends FragmentActivity implements OnClickListener {
	private RelativeLayout back;
	private TextView title;
	public Intent intent;
	public ShapeLoadingDialog loadingDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 手机窗口设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置填充的layout
		setContentView(setLayout());
		
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
		
		// 注入控件
		ViewUtils.inject(this);
		init(savedInstanceState);
		setListener();
	}

	/**
	 * 设置界面加载的Layout
	 * 
	 * @return
	 */
	public abstract int setLayout();

	/**
	 * 初始化数据
	 */
	public abstract void init(Bundle savedInstanceState);

	/**
	 * 对view设置监听事件
	 */
	public abstract void setListener();

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

	/**
	 * 设置右边button的名字和监听器
	 * 
	 * @param text
	 *            名字
	 * @param listener
	 *            监听器
	 */
	public void setRightButton(String text, OnClickListener listener) {

		TextView btn = (TextView) findViewById(R.id.tv_right);
		btn.setVisibility(View.VISIBLE);
		if (!TextUtils.isEmpty(text)) {
			btn.setText(text);
		}
		if (listener != null) {
			btn.setOnClickListener(listener);
		}
	}

	/**
	 * 
	 * @param resouce
	 *            图片地址
	 * @param listener
	 */
	public void setRightIv(int resource, OnClickListener listener) {
		RelativeLayout rl_right = (RelativeLayout) findViewById(R.id.rl_right);
		ImageView iv = (ImageView) findViewById(R.id.ivRight);
		rl_right.setVisibility(View.VISIBLE);
		iv.setBackgroundResource(resource);
		if (listener != null) {
			rl_right.setOnClickListener(listener);
		}
	}

	/**
	 * 获取上下文
	 * 
	 * @return
	 */
	public Context getContext() {
		return this;
	}

}
