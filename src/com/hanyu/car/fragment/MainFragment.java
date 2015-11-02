package com.hanyu.car.fragment;

import com.hanyu.car.R;
import com.hanyu.car.base.BaseFragment;
import com.hanyu.car.utils.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainFragment extends BaseFragment {
	public static final String tag = "MainFragment";

	private FragmentManager fm;
	@ViewInject(R.id.layout_content)
	private FrameLayout layout_content;

	private TravelFragment travelfragment;
	private WeddingFragment weddingfragment;
	private BusinessFragment businessfragment;
	private LongCarFragment longcarfragment;
	@ViewInject(R.id.main_radio)
	private RadioGroup main_radio;
	@ViewInject(R.id.rb_travel_car)
	private RadioButton rb_travel_car;

	private int flag;

	@Override
	public View initView(LayoutInflater inflater) {
		view = View.inflate(context, R.layout.frag_home, null);
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		Bundle bundle1 = getArguments();
		flag = bundle1.getInt("flag");
		LogUtils.i("flag", flag+"");
		selectpage();
	}

	/**
	 * 选择页面
	 */
	private void selectpage() {
		main_radio.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@SuppressLint("NewApi")
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				fm = getFragmentManager();
				FragmentTransaction transaction = fm.beginTransaction();
				hideFragments(transaction);
				switch (checkedId) {
				case R.id.rb_travel_car:
					if (travelfragment == null) {
						travelfragment = new TravelFragment();
						transaction.add(R.id.layout_content, travelfragment);
					} else {
						transaction.show(travelfragment);
					}
					break;
				case R.id.rb_wedding_car:
					if (weddingfragment == null) {
						weddingfragment = new WeddingFragment();
						transaction.add(R.id.layout_content, weddingfragment);
					} else {
						transaction.show(weddingfragment);
					}
					break;
				case R.id.rb_business_car:
					if (businessfragment == null) {
						businessfragment = new BusinessFragment();
						transaction.add(R.id.layout_content, businessfragment);
					} else {
						transaction.show(businessfragment);
					}
					break;
				case R.id.rb_long_car:
					if (longcarfragment == null) {
						longcarfragment = new LongCarFragment();
						transaction.add(R.id.layout_content, longcarfragment);
					} else {
						transaction.show(longcarfragment);
					}
					break;
				}
				transaction.commit();
			}
		});
		if(flag==1){
			main_radio.check(R.id.rb_travel_car);
		}else if(flag==2){
			main_radio.check(R.id.rb_wedding_car);
		}else if(flag==3){
			main_radio.check(R.id.rb_business_car);
		}else if(flag==4){
			main_radio.check(R.id.rb_long_car);
		}
	}

	/**
	 * 隐藏所有的页面
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (travelfragment != null) {
			transaction.hide(travelfragment);
		}
		if (weddingfragment != null) {
			transaction.hide(weddingfragment);
		}
		if (businessfragment != null) {
			transaction.hide(businessfragment);
		}
		if (longcarfragment != null) {
			transaction.hide(longcarfragment);
		}

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void setListener() {

	}

}