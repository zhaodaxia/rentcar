package com.hanyu.car.activity.center;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hanyu.car.R;
import com.hanyu.car.activity.business.GearsSelectActivity;
import com.hanyu.car.activity.findcar.SelectCarModelActivity;
import com.hanyu.car.activity.wedding.SelectBrandActivity;
import com.hanyu.car.base.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

public class AddMyCarActivity extends BaseActivity {
	private static final int BRAND = 0;
	private static final int MODEL = 1;
	private static final int SEAT = 2;
	private static final int GEARS = 3;
	@ViewInject(R.id.addcar_brand_rl)
	private RelativeLayout addcar_brand_rl;// 车辆品牌
	@ViewInject(R.id.addcar_brand_tv)
	private TextView addcar_brand_tv;
	@ViewInject(R.id.addcar_model_rl)
	private RelativeLayout addcar_model_rl;// 车型
	@ViewInject(R.id.addcar_model_tv)
	private TextView addcar_model_tv;
	@ViewInject(R.id.addcar_seat_rl)
	private RelativeLayout addcar_seat_rl;// 座位数
	@ViewInject(R.id.addcar_seat_tv)
	private TextView addcar_seat_tv;
	@ViewInject(R.id.addcar_gears_rl)
	private RelativeLayout addcar_gears_rl;// 变速箱
	@ViewInject(R.id.addcar_gears_tv)
	private TextView addcar_gears_tv;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addcar_brand_rl:
			intent = new Intent(AddMyCarActivity.this,
					SelectBrandActivity.class);
			startActivityForResult(intent, BRAND);
			break;
		case R.id.addcar_model_rl:
			intent = new Intent(AddMyCarActivity.this,
					SelectCarModelActivity.class);
			startActivityForResult(intent, MODEL);
			break;
		case R.id.addcar_seat_rl:
			intent = new Intent(AddMyCarActivity.this, SelectSeatActivity.class);
			startActivityForResult(intent, SEAT);
			break;
		case R.id.addcar_gears_rl:
			intent = new Intent(AddMyCarActivity.this,
					GearsSelectActivity.class);
			startActivityForResult(intent, GEARS);
			break;

		default:
			break;
		}
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.addcar;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setBack();
		setTopTitle("添加车辆");
		setRightButton("保存", new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 将车辆信息保存并添加到车辆管理界面中
				finish();
			}
		});
	}

	@Override
	public void setListener() {
		addcar_brand_rl.setOnClickListener(this);
		addcar_model_rl.setOnClickListener(this);
		addcar_seat_rl.setOnClickListener(this);
		addcar_gears_rl.setOnClickListener(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0 && resultCode == 0) {
			if (data != null) {
				addcar_brand_tv
						.setText(data.getStringExtra("brand").toString());
			}
		}
		if (requestCode == 1 && resultCode == 0) {
			if (data != null) {
				addcar_model_tv.setText(data.getStringExtra("carmodel")
						.toString());
			}
		}
		if (requestCode == 2 && resultCode == 0) {
			if (data != null) {
				addcar_seat_tv.setText(data.getStringExtra("seatnumber")
						.toString());
			}
		}
		if (requestCode == 3 && resultCode == 0) {
			if (data != null) {
				addcar_gears_tv
						.setText(data.getStringExtra("gears").toString());
			}
		}
	}

}
