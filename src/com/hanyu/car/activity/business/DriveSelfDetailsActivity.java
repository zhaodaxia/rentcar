package com.hanyu.car.activity.business;

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
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.hanyu.car.R;
import com.hanyu.car.activity.wedding.LuxuryDetailsOrderActivity;
import com.hanyu.car.base.BaseActivity;
import com.hanyu.car.bean.DriverSelfContentInfos;
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

public class DriveSelfDetailsActivity extends BaseActivity {

	@ViewInject(R.id.driveself_details_viewpager)
	private ViewPager driveself_details_viewpager;// 轮播图
	@ViewInject(R.id.driveself_details_point)
	private LinearLayout driveself_details_point;// 轮播点
	@ViewInject(R.id.driveself_details_order)
	private RelativeLayout driveself_details_order;// 立即预约

	private List<View> views = new ArrayList<View>();
	private int[] images = { R.drawable.laosilaisi, R.drawable.laosilaisi,
			R.drawable.laosilaisi, R.drawable.laosilaisi };
	private int currentItem = 0;
	private boolean flag = true;
	private LayoutParams paramsL = new LayoutParams(20, 20);

	
	//自驾租车详情信息
	private DriverSelfContentInfos contentInfos;
	private String busid;
		
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			currentItem = driveself_details_viewpager.getCurrentItem() + 1;
			driveself_details_viewpager.setCurrentItem(currentItem);
		}

	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.driveself_details_order:
			intent = new Intent(DriveSelfDetailsActivity.this,
					DriveSelfDetailsOrderActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.driveself_details;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		intent = getIntent();
		Bundle bundle = intent.getExtras();
		String title = bundle.getString("driveself_details");
		String busid = bundle.getString("busid");
		setTopTitle(title);

		driveself_details_viewpager.setAdapter(new MyPageAdapter());
		currentItem = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
				% images.length;
		driveself_details_viewpager.setCurrentItem(currentItem);

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
		
		//获取自驾车详情信息
		getDriverSelfContentInfos(busid);
	}

	private void getDriverSelfContentInfos(final String busid) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("mpackpageid",busid );
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.BUSINESS_CONTENT_INFO,params, new RequestCallBack<String>() {
		@Override
		public void onFailure(HttpException arg0, String arg1) {
			getDriverSelfContentInfos(busid);
		}

		@Override
		public void onSuccess(ResponseInfo<String> arg0) {
			String json = arg0.result;
			LogUtils.e(getClass(), json);
			contentInfos = JSON.parseObject(json, DriverSelfContentInfos.class);
			showDriverSelfContentInfos();
		}
	});
	}

	protected void showDriverSelfContentInfos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setListener() {
		driveself_details_order.setOnClickListener(this);

		driveself_details_viewpager
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

	/**
	 * 初始化点
	 */
	private void initPoint() {

		for (int i = 0; i < images.length; i++) {
			View view = new View(DriveSelfDetailsActivity.this);
			paramsL.setMargins(
					YangUtils.dip2px(DriveSelfDetailsActivity.this, 5), 0, 0, 0);
			view.setLayoutParams(paramsL);
			if (i == 0) {
				view.setBackgroundResource(R.drawable.xl_03);
			} else {
				view.setBackgroundResource(R.drawable.xl_05);
			}

			views.add(view);
			driveself_details_point.addView(view);
		}

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
			View view = View.inflate(DriveSelfDetailsActivity.this,
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
