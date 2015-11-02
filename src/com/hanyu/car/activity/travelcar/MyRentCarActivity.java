package com.hanyu.car.activity.travelcar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hanyu.car.R;
import com.hanyu.car.base.MyBaseActivity;
import com.hanyu.car.fragment.business.AirPort;
import com.hanyu.car.utils.LogUtils;
import com.hanyu.car.utils.ShowAgeDialog;
import com.hanyu.car.utils.ShowAgeDialog.OnsaveDayListener;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MyRentCarActivity extends MyBaseActivity {
	protected static final int STANDARD = 0;
	protected static final int PEOPLE = 1;
	protected static final int DESTINATION = 2;
	protected static final int HOTAL = 3;
	protected static final String tag = "MyRentCarActivity";

	private String usecar_tv;
	private String people_tv;
	private String destination_tv;
	private String hotal_tv;
	private String price_tv;
	private String phone_tv;
	private String start_data_tv;
	private String end_data_tv;
	
	private ShowAgeDialog sad;

	// 租车规格
	@ViewInject(R.id.usecar_standard)
	private RelativeLayout usecar_standard;

	// 人数
	@ViewInject(R.id.people_number)
	private RelativeLayout people_number;

	// 目的地
	@ViewInject(R.id.destination)
	private RelativeLayout destination;

	// 酒店
	@ViewInject(R.id.wine_hotal)
	private RelativeLayout wine_hotal;

	// 租车规格返回文本
	@ViewInject(R.id.usecar_text)
	private TextView usecar_text;

	// 人数返回文本
	@ViewInject(R.id.people_text)
	private TextView people_text;

	// 目的地返回文本
	@ViewInject(R.id.destination_text)
	private TextView destination_text;

	// 酒店返回文本
	@ViewInject(R.id.hotal_text)
	private TextView hotal_text;

	// 起始日期
	@ViewInject(R.id.startday_text)
	private TextView startday_text;

	// 所有选项填完后显示的textview
	@ViewInject(R.id.zidingyi_text)
	private TextView zidingyi_text;

	// 结束日期
	@ViewInject(R.id.endday_text)
	private TextView endday_text;

	// 行程预算
	@ViewInject(R.id.route_price)
	private EditText route_price;

	// 电话号码
	@ViewInject(R.id.phone_number)
	private EditText phone_number;

	// 提交
	@ViewInject(R.id.road_commit)
	private Button road_commit;

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		setTopTitle("自定义路线");
		OpenActivity();
		data();
		MyTextWatcher watcher = new MyTextWatcher();
		startday_text.addTextChangedListener(watcher);
		endday_text.addTextChangedListener(watcher);
		usecar_text.addTextChangedListener(watcher);
		destination_text.addTextChangedListener(watcher);
		people_text.addTextChangedListener(watcher);
		zidingyi_text.addTextChangedListener(watcher);
		hotal_text.addTextChangedListener(watcher);
		phone_number.addTextChangedListener(watcher);
		route_price.addTextChangedListener(watcher);
	}

	/**
	 * 打开界面
	 */
	private void OpenActivity() {
		standard();
		people();
		destination();
		hotal();
		commit();
	}

	/**
	 * 文本监听器 用来使隐藏字体显示
	 */
	class MyTextWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			usecar_tv = usecar_text.getText().toString();
			people_tv = people_text.getText().toString();
			destination_tv = destination_text.getText().toString();
			hotal_tv = hotal_text.getText().toString();
			price_tv = route_price.getText().toString();
			phone_tv = phone_number.getText().toString();
			start_data_tv = startday_text.getText().toString();
			end_data_tv = endday_text.getText().toString();
			if (!TextUtils.isEmpty(destination_tv)
					&& !TextUtils.isEmpty(hotal_tv)
					&& !TextUtils.isEmpty(people_tv)
					&& !TextUtils.isEmpty(phone_tv)
					&& !TextUtils.isEmpty(usecar_tv)
					&& !TextUtils.isEmpty(price_tv)
					&& !TextUtils.isEmpty(start_data_tv)
					&& !TextUtils.isEmpty(end_data_tv)) {
				zidingyi_text.setVisibility(0);
			}
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * 目的地
	 */
	private void destination() {
		destination.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MyRentCarActivity.this,
						DestinationActivity.class);
				startActivityForResult(intent, DESTINATION);
			}
		});
	}

	/**
	 * 酒店
	 */
	private void hotal() {
		wine_hotal.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MyRentCarActivity.this,
						HotalActivity.class);
				startActivityForResult(intent, HOTAL);
			}
		});
	}

	/**
	 * 用车规格
	 */
	private void standard() {
		usecar_standard.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MyRentCarActivity.this,
						UseCarStandardActivity.class);
				startActivityForResult(intent, STANDARD);
			}
		});
	}

	/**
	 * 人数
	 */
	private void people() {
		people_number.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyRentCarActivity.this,
						PeopleNumberActivity.class);
				startActivityForResult(intent, PEOPLE);

			}
		});
	}

	/**
	 * 确认提交
	 */
	private void commit() {
		road_commit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				LogUtils.i(tag, "我已经被点击拉！~！~！~！~");
				if (TextUtils.isEmpty(start_data_tv)) {
					Toast.makeText(getApplicationContext(), "请选择起始日期", 0)
							.show();
				} else if (TextUtils.isEmpty(end_data_tv)) {
					Toast.makeText(getApplicationContext(), "请选择结束日期", 0)
							.show();
				} else if (TextUtils.isEmpty(destination_tv)) {
					Toast.makeText(getApplicationContext(), "请选择目的地", 0).show();
				} else if (TextUtils.isEmpty(hotal_tv)) {
					Toast.makeText(getApplicationContext(), "请选择酒店", 0).show();
				} else if (TextUtils.isEmpty(usecar_tv)) {
					Toast.makeText(getApplicationContext(), "请选择用车规格", 0)
							.show();
				} else if (TextUtils.isEmpty(people_tv)) {
					Toast.makeText(getApplicationContext(), "请选择人数", 0).show();
				} else if (TextUtils.isEmpty(price_tv)) {
					Toast.makeText(getApplicationContext(), "请填写行程预算", 0)
							.show();
				} else if (TextUtils.isEmpty(phone_tv)) {
					Toast.makeText(getApplicationContext(), "请填写手机号", 0).show();
				}

				// TODO 打开新界面，把所有数据传到服务器端
			}
		});

	}

	/**
	 * 选择日期
	 */

	private void data() {
		startday_text.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LogUtils.i(tag, "打开日期选择器");
				sad = new ShowAgeDialog(MyRentCarActivity.this);
				sad.builder().show();
				sad.setOnSaveDay(new OnsaveDayListener() {
					@Override
					public void setDay(String saveDay, int age) {
						startday_text.setText(saveDay);
					}
				});
			}
		});
		
		
		endday_text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sad = new ShowAgeDialog(MyRentCarActivity.this);
				sad.builder().show();
				sad.setOnSaveDay(new OnsaveDayListener() {
					@Override
					public void setDay(String saveDay, int age) {
						endday_text.setText(saveDay);
					}
				});
			}
		});
	}

	/**
	 * 返回值
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0 && resultCode == 0) {
			if (data != null) {
				String text = data.getStringExtra("standard");
				usecar_text.setText(text);
			}
		}

		if (requestCode == 1 && resultCode == 1) {
			if (data != null) {
				String text = data.getStringExtra("peoplenumber");
				people_text.setText(text);
			}
		}

		if (requestCode == 2 && resultCode == 2) {
			if (data != null) {
				String text = data.getStringExtra("destination");
				destination_text.setText(text);
			}
		}

		if (requestCode == 3 && resultCode == 3) {
			if (data != null) {
				String text = data.getStringExtra("hotal");
				hotal_text.setText(text);
			}
		}
	}

	@Override
	public int setLayout() {
		return R.layout.zidingyi_activity;
	}

}
