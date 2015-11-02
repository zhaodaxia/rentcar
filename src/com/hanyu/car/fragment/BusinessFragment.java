package com.hanyu.car.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

import com.hanyu.car.R;
import com.hanyu.car.activity.business.BusinessSearch;
import com.hanyu.car.base.BaseFragment;
import com.hanyu.car.fragment.business.AirPort;
import com.hanyu.car.fragment.business.DriveOther;
import com.hanyu.car.fragment.business.DriveSelf;
import com.hanyu.car.fragment.business.TuHaoCar;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class BusinessFragment extends BaseFragment {

	@ViewInject(R.id.business_content)
	private FrameLayout business_content;

	@ViewInject(R.id.business_rg)
	// 按钮组
	private RadioGroup business_rg;

	/*@ViewInject(R.id.business_right)
	// 搜索按钮
	private RelativeLayout business_right;*/
	
	@ViewInject(R.id.business_back)
	private RelativeLayout business_back;

	private FragmentManager fm;

	private DriveSelf driveself;
	private DriveOther driveother;
	private AirPort airport;
	private TuHaoCar tuhaocar;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.business_back:
			getActivity().finish();
			break;

		default:
			break;
		}
	}

	@Override
	public View initView(LayoutInflater inflater) {
		view = View.inflate(context, R.layout.businesspager, null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		pageselect();
		

	/*	business_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, BusinessSearch.class);
				context.startActivity(intent);
			}
		});*/

	}

	/**
	 * 选择页面
	 */
	private void pageselect() {
		business_rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				fm = getFragmentManager();
				FragmentTransaction transaction = fm.beginTransaction();
				hideFragments(transaction);
				switch (checkedId) {
				case R.id.rb_driveself:
					if (driveself == null) {
						driveself = new DriveSelf();
						transaction.add(R.id.business_content, driveself);
					} else {
						transaction.show(driveself);
					}
					break;
				case R.id.rb_driveother:
					if (driveother == null) {
						driveother = new DriveOther();
						transaction.add(R.id.business_content, driveother);
					} else {
						transaction.show(driveother);
					}
					break;
				case R.id.rb_airport:
					if (airport == null) {
						airport = new AirPort();
						transaction.add(R.id.business_content, airport);
					} else {
						transaction.show(airport);
					}
					break;
				case R.id.rb_tuhaocar:
					if (tuhaocar == null) {
						tuhaocar = new TuHaoCar();
						transaction.add(R.id.business_content, tuhaocar);
					} else {
						transaction.show(tuhaocar);
					}
					break;

				default:
					break;
				}
				transaction.commit();
			}
		});
		business_rg.check(R.id.rb_driveself);
	}

	/**
	 * 隐藏所有的页面
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (driveself != null) {
			transaction.hide(driveself);
		}
		if (driveother != null) {
			transaction.hide(driveother);
		}
		if (airport != null) {
			transaction.hide(airport);
		}
		if (tuhaocar != null) {
			transaction.hide(tuhaocar);
		}

	}

	@Override
	public void setListener() {
		business_back.setOnClickListener(this);
	}

}
