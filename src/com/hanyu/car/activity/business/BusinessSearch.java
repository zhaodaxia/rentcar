package com.hanyu.car.activity.business;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hanyu.car.R;
import com.hanyu.car.base.MyBaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

public class BusinessSearch extends MyBaseActivity {
	protected static final int BRAND = 0;
	protected static final int PRICE = 1;
	protected static final int TYPE = 2;
	protected static final int GEARS = 3;
	// 搜索按钮
	@ViewInject(R.id.search_car_model)
	private Button search_car_model;
	// 品牌
	@ViewInject(R.id.searchcar_brand)
	private RelativeLayout searchcar_brand;
	// 品牌更换文本
	@ViewInject(R.id.bs_brand_tv)
	private TextView bs_brand_tv;
	// 价格
	@ViewInject(R.id.searchcar_price)
	private RelativeLayout searchcar_price;
	// 价格更换文本
	@ViewInject(R.id.bs_price_tv)
	private TextView bs_price_tv;
	// 车型
	@ViewInject(R.id.searchcar_type)
	private RelativeLayout searchcar_type;
	// 车型更换文本
	@ViewInject(R.id.bs_type_tv)
	private TextView bs_type_tv;
	// 手动自动
	@ViewInject(R.id.searchcar_gears)
	private RelativeLayout searchcar_gears;
	// 手动自动更换文本
	@ViewInject(R.id.bs_gears_tv)
	private TextView bs_gears_tv;

	private Intent intent;

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("车型搜索");
		Brand();
		Price();
		Type();
		Gears();
		search();
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.business_search;
	}

	/**
	 * 搜索
	 */
	private void search() {
		search_car_model.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String brand = bs_brand_tv.getText().toString();
				String price = bs_price_tv.getText().toString();
				String type = bs_type_tv.getText().toString();
				String gears = bs_gears_tv.getText().toString();
				if(TextUtils.isEmpty(brand)){
					Toast.makeText(getApplicationContext(), "请选择品牌", 0).show();
				}else if(TextUtils.isEmpty(price)){
					Toast.makeText(getApplicationContext(), "请选择价格", 0).show();
				}else if(TextUtils.isEmpty(type)){
					Toast.makeText(getApplicationContext(), "请选择车型", 0).show();
				}else if(TextUtils.isEmpty(gears)){
					Toast.makeText(getApplicationContext(), "请选择档型", 0).show();
				}else {
					//TODO 把以上数据整合发往服务器端
					sendOrder();
				}
			}
		});
	}

	protected void sendOrder() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 打开品牌选择界面
	 */
	private void Brand() {
		searchcar_brand.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent(BusinessSearch.this,
						BrandSelectActivity.class);
				startActivityForResult(intent, BRAND);
			}
		});
	}

	/**
	 * 打开价格选择界面
	 */
	private void Price() {
		searchcar_price.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent(BusinessSearch.this,
						PriceSelectActivity.class);
				startActivityForResult(intent, PRICE);
			}
		});
	}

	/**
	 * 打开车型选择界面
	 */
	private void Type() {
		searchcar_type.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent(BusinessSearch.this,
						TypeSelectActivity.class);
				startActivityForResult(intent, TYPE);
			}
		});
	}

	/**
	 * 打开手动/自动选择界面
	 */
	private void Gears() {
		searchcar_gears.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent(BusinessSearch.this,
						GearsSelectActivity.class);
				startActivityForResult(intent, GEARS);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0 && resultCode == 0) {
			if (data != null) {
				bs_brand_tv.setText(data.getStringExtra("brand"));
			}
		}
		if (requestCode == 1 && resultCode == 1) {
			if (data != null) {
				bs_price_tv.setText(data.getStringExtra("price"));
			}
		}
		if (requestCode == 2 && resultCode == 2) {
			if (data != null) {
				bs_type_tv.setText(data.getStringExtra("type"));
			}
		}
		if (requestCode == 3 && resultCode == 3) {
			if (data != null) {
				bs_gears_tv.setText(data.getStringExtra("gears"));
			}
		}
	}

}
