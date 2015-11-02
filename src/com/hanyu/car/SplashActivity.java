package com.hanyu.car;

import com.hanyu.car.activity.center.LoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);
		new Thread() {
			public void run() {
				try {
					sleep(3000);
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							startActivity(new Intent(SplashActivity.this, LoginActivity.class));
							SplashActivity.this.finish();
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();

	}
}
