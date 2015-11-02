package com.hanyu.car.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hanyu.car.R;
import com.hanyu.car.activity.wedding.ComboActivity;
import com.hanyu.car.activity.wedding.LuxuryActivity;
import com.hanyu.car.activity.wedding.OrderActivity;
import com.hanyu.car.activity.wedding.PremiumActivity;
import com.hanyu.car.base.BaseFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class WeddingFragment extends BaseFragment {
	// 套餐详情
	@ViewInject(R.id.wedding_combo)
	private RelativeLayout wedding_combo;
	@ViewInject(R.id.ll_package)
	private LinearLayout ll_package;
	// 豪华轿车车型列表
	@ViewInject(R.id.wedding_luxury)
	private RelativeLayout wedding_luxury;
	@ViewInject(R.id.ll_Luxury)
	private LinearLayout ll_Luxury;
	
	// 高端轿车车型列表
	/*@ViewInject(R.id.wedding_premium)
	private RelativeLayout wedding_premium;
	@ViewInject(R.id.ll_premium)
	private LinearLayout ll_premium;*/
	
	// 在线预约
	@ViewInject(R.id.ll_order)
	private LinearLayout ll_order;
	@ViewInject(R.id.wedding_order)
	private RelativeLayout wedding_order;
	@ViewInject(R.id.wedding_back)
	private RelativeLayout wedding_back;

	private Intent intent;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.wedding_back:
			getActivity().finish();
			break;

		default:
			break;
		}
	}

	@Override
	public View initView(LayoutInflater inflater) {
		view = View.inflate(context, R.layout.weddingpager, null);
		ViewUtils.inject(this, view);
		Combo();
		Luxury();
		Premium();
		Order();
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setListener() {
		wedding_back.setOnClickListener(this);
		ll_package.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(context, ComboActivity.class);
				context.startActivity(intent);
			}
		});
		ll_Luxury.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(context, LuxuryActivity.class);
				context.startActivity(intent);
			}
		});
		/*ll_premium.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(context, PremiumActivity.class);
				context.startActivity(intent);
			}
		});*/
		ll_order.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(context, OrderActivity.class);
				context.startActivity(intent);
			}
		});
		
	}

	/**
	 * 打开套餐详情界面
	 */
	private void Combo() {
		wedding_combo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(context, ComboActivity.class);
				context.startActivity(intent);
			}
		});
	}

	/**
	 * 打开豪华轿车车型列表界面
	 */
	private void Luxury() {
		wedding_luxury.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(context, LuxuryActivity.class);
				context.startActivity(intent);
			}
		});
	}

	/**
	 * 打开高端轿车车型列表界面
	 */
	private void Premium() {
		/*wedding_premium.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(context, PremiumActivity.class);
				context.startActivity(intent);
			}
		});*/
	}

	/**
	 * 打开在线预约界面
	 */
	private void Order() {
		wedding_order.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(context, OrderActivity.class);
				context.startActivity(intent);
			}
		});
	}

}
