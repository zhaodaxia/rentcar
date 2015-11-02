package com.hanyu.car.activity.home;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.hanyu.car.R;
import com.hanyu.car.fragment.MainFragment;

public class TravelActivity extends FragmentActivity {
	private MainFragment travelFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		Intent intent = getIntent();
		int flag = intent.getExtras().getInt("flag");
		travelFragment = new MainFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, travelFragment, "TRAVEL")
				.commit();
		Bundle bundle1 = new Bundle();
		bundle1.putInt("flag", flag);
		travelFragment.setArguments(bundle1);
	}

}
