package com.hanyu.car.activity.center;

import javax.net.ssl.ManagerFactoryParameters;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hanyu.car.R;
import com.hanyu.car.base.MyBaseActivity;
import com.hanyu.car.utils.DataCleanManager;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SettingActivity extends MyBaseActivity{
	
	//意见反馈
	@ViewInject(R.id.rl_feedback)
	private RelativeLayout rl_feedback;
	//清除缓存
	@ViewInject(R.id.rl_cleancache)
	private RelativeLayout rl_cleancache;
	//关于我们
	@ViewInject(R.id.rl_aboutus)
	private RelativeLayout rl_aboutus;
	@ViewInject(R.id.setting_tv)
	private TextView setting_tv;

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("设置");
		
		FeedBack();
		CleanCache();
		AboutUS();
		
		try {
			String cachesize = DataCleanManager.getTotalCacheSize(SettingActivity.this);
			setting_tv.setText(cachesize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.setting;
	}

	/**
	 * 意见反馈
	 */
	private void FeedBack() {
		rl_feedback.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(SettingActivity.this,FeedBackActivity.class);
				startActivity(intent);
			}
		});
	}
	/**
	 * 清除缓存
	 */
	private void CleanCache() {
		rl_cleancache.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DataCleanManager.cleanInternalCache(SettingActivity.this);
				setting_tv.setText("");
			}
		});
	}
	/**
	 * 关于我们
	 */
	private void AboutUS() {
		rl_aboutus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(SettingActivity.this,AboutUSActivity.class);
				startActivity(intent);
			}
		});
	}

}
