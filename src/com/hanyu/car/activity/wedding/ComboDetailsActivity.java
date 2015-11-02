package com.hanyu.car.activity.wedding;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.alibaba.fastjson.JSON;
import com.hanyu.car.R;
import com.hanyu.car.base.BaseActivity;
import com.hanyu.car.bean.LuxuryContentInfos;
import com.hanyu.car.bean.PackageContentInfos;
import com.hanyu.car.global.MyApplication;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.utils.LogUtils;
import com.hanyu.car.utils.YangUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class ComboDetailsActivity extends BaseActivity {

	@ViewInject(R.id.combo_details_viewpager)
	private ViewPager combo_details_viewpager;// 轮播图
	@ViewInject(R.id.combo_details_point)
	private LinearLayout combo_details_point;// 轮播点
	@ViewInject(R.id.combo_details_order)
	private RelativeLayout combo_details_order;// 立即预约
	
	//豪车详情信息
	private PackageContentInfos contentInfos;
	private String mcarid;
	
	@ViewInject(R.id.combo_contentInfo_mpackpage_price)
	private TextView combo_contentInfo_mpackpage_price;
	@ViewInject(R.id.combo_contentInfo_mpackpage_deposit)
	private TextView combo_contentInfo_mpackpage_deposit;
	@ViewInject(R.id.combo_contentInfo_mpackpage_include)
	private TextView combo_contentInfo_mpackpage_include;
	@ViewInject(R.id.combo_contentInfo_mpackpage_km)
	private TextView combo_contentInfo_mpackpage_km;
	@ViewInject(R.id.combo_contentInfo_mpackpage_ctime)
	private TextView combo_contentInfo_mpackpage_ctime;
	@ViewInject(R.id.combo_contentInfo_mpackpage_time)
	private TextView combo_contentInfo_mpackpage_time;
	@ViewInject(R.id.combo_contentInfo_mpackpage_ckm)
	private TextView combo_contentInfo_mpackpage_ckm;
	@ViewInject(R.id.combo_contentInfo_mpackpage_addr)
	private TextView combo_contentInfo_mpackpage_addr;
	@ViewInject(R.id.combo_contentInfo_mpackpage_content)
	private TextView combo_contentInfo_mpackpage_content;
	@ViewInject(R.id.combo_contentInfo_mpackpage_linkman)
	private TextView combo_contentInfo_mpackpage_linkman;
	@ViewInject(R.id.combo_contentInfo_mpackpage_phone)
	private TextView combo_contentInfo_mpackpage_phone;
	

	private List<View> views = new ArrayList<View>();
	private int[] images = { R.drawable.laosilaisi, R.drawable.laosilaisi,
			R.drawable.laosilaisi, R.drawable.laosilaisi };
	private int currentItem = 0;
	private boolean flag = true;
	private LayoutParams paramsL = new LayoutParams(20, 20);

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			currentItem = combo_details_viewpager.getCurrentItem() + 1;
			combo_details_viewpager.setCurrentItem(currentItem);
		}

	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.combo_details_order:
			intent = new Intent(ComboDetailsActivity.this,
					ComboDetailsOrderActivity.class);
			intent.putExtra("mcarid",mcarid);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.combo_details;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		intent = getIntent();
		Bundle bundle = intent.getExtras();
		String name = bundle.getString("combo_title");
		mcarid = bundle.getString("mcarid");
		setTopTitle(name);

		combo_details_viewpager.setAdapter(new MyPageAdapter());
		currentItem = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
				% images.length;
		combo_details_viewpager.setCurrentItem(currentItem);

		initPoint();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (flag) {
					try {
						Thread.sleep(3000);
						handler.sendEmptyMessage(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}
		}).start();
		
		//获取婚车套餐详情信息
		getPackageContentInfos(mcarid);
	}

	private void getPackageContentInfos(final String mcarid) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("mpackpageid",mcarid );
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.PACKAGE_CONTENT_INFO,params, new RequestCallBack<String>() {
		@Override
		public void onFailure(HttpException arg0, String arg1) {
			getPackageContentInfos(mcarid);
		}

		@Override
		public void onSuccess(ResponseInfo<String> arg0) {
			String json = arg0.result;
			LogUtils.e(getClass(), json);
			contentInfos = JSON.parseObject(json, PackageContentInfos.class);
			showPackageContentInfos();
		}
	});
	}

	protected void showPackageContentInfos() {
		// TODO Auto-generated method stub
		combo_contentInfo_mpackpage_price.setText(contentInfos.mpackpage_price);
		combo_contentInfo_mpackpage_deposit.setText(contentInfos.mpackpage_deposit);
		combo_contentInfo_mpackpage_include.setText(contentInfos.mpackpage_include);
		combo_contentInfo_mpackpage_km.setText(contentInfos.mpackpage_km);
		combo_contentInfo_mpackpage_ctime.setText(contentInfos.mpackpage_ctime);
		combo_contentInfo_mpackpage_time.setText(contentInfos.mpackpage_time);
		combo_contentInfo_mpackpage_ckm.setText(contentInfos.mpackpage_ckm);
		combo_contentInfo_mpackpage_addr.setText(contentInfos.mpackpage_addr);
		combo_contentInfo_mpackpage_content.setText(contentInfos.mpackpage_content);
		combo_contentInfo_mpackpage_linkman.setText(contentInfos.mpackpage_linkman);
		combo_contentInfo_mpackpage_phone.setText(contentInfos.mpackpage_phone);
	}

	/**
	 * 初始化点
	 */
	private void initPoint() {

		for (int i = 0; i < images.length; i++) {
			View view = new View(ComboDetailsActivity.this);
			paramsL.setMargins(YangUtils.dip2px(ComboDetailsActivity.this, 5),
					0, 0, 0);
			view.setLayoutParams(paramsL);
			if (i == 0) {
				view.setBackgroundResource(R.drawable.xl_03);
			} else {
				view.setBackgroundResource(R.drawable.xl_05);
			}

			views.add(view);
			combo_details_point.addView(view);
		}

	}

	@Override
	public void setListener() {
		combo_details_order.setOnClickListener(this);

		// TODO Auto-generated method stub
		combo_details_viewpager
				.setOnPageChangeListener(new OnPageChangeListener() {
					public void onPageSelected(int position) {
						if (views.size() != 0
								&& views.get(position % images.length) != null) {

							for (int i = 0; i < views.size(); i++) {
								if (i == position % images.length) {
									views.get(i).setBackgroundResource(
											R.drawable.xl_03);
								} else {
									views.get(i).setBackgroundResource(
											R.drawable.xl_05);
								}
							}

						}
					}

					public void onPageScrolled(int arg0, float arg1, int arg2) {

					}

					public void onPageScrollStateChanged(int arg0) {

					}
				});
	}

	@Override
	public void onDestroy() {
		flag = false;
		super.onDestroy();
	}

	private class MyPageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

		public Object instantiateItem(ViewGroup container, final int position) {
			int index = position % images.length;
			View view = View.inflate(ComboDetailsActivity.this,
					R.layout.home_vp_item, null);

			ImageView iv_iamge = (ImageView) view.findViewById(R.id.iv_image);
			iv_iamge.setBackgroundResource(images[index]);

			((ViewPager) container).addView(view);

			iv_iamge.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

				}
			});

			return view;
		}

		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

	}

}
