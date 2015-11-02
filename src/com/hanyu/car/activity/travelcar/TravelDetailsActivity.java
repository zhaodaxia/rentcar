package com.hanyu.car.activity.travelcar;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.hanyu.car.R;
import com.hanyu.car.base.BaseActivity;
import com.hanyu.car.bean.TravelDetail;
import com.hanyu.car.global.ImageLoaderCfg;
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
import com.nostra13.universalimageloader.core.ImageLoader;

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
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TravelDetailsActivity extends BaseActivity {
	@ViewInject(R.id.travel_details_viewpager)
	private ViewPager travel_details_viewpager;// 轮播图
	@ViewInject(R.id.travel_details_point)
	private LinearLayout travel_details_point;// 轮播点
	@ViewInject(R.id.travel_details_order)
	private RelativeLayout travel_details_order;// 立即预约
	@ViewInject(R.id.tv_time)
	private TextView tv_time;
	@ViewInject(R.id.tv_brand)
	private TextView tv_brand;
	@ViewInject(R.id.tv_price)
	private TextView tv_price;
	@ViewInject(R.id.tv_hotel)
	private TextView tv_hotel;
	@ViewInject(R.id.tv_price_desc)
	private TextView tv_price_desc;
	@ViewInject(R.id.tv_content)
	private TextView tv_content;
	@ViewInject(R.id.tv_title)
	private TextView tv_title;

	private List<String> headImages = new ArrayList<String>();
	private int currentItem = 0;
	private LayoutParams paramsL = new LayoutParams(20, 20);

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			currentItem = travel_details_viewpager.getCurrentItem() + 1;
			if (currentItem >= headImages.size()) {
				currentItem = 0;
			}
			travel_details_viewpager.setCurrentItem(currentItem);

			handler.sendEmptyMessageDelayed(0, 3000);
		}

	};
	private String id;

	protected void setDetailData(TravelDetail detail) {
		tv_time.setText(detail.travel_time);
		tv_brand.setText(detail.travel_brand);
		tv_price.setText(detail.travel_price);
		tv_hotel.setText(detail.travel_hotel);
		tv_price_desc.setText(detail.travel_priceexplain);
		tv_content.setText(detail.travel_content);

		String picsStr = detail.travel_pics;
		try {
			String[] images = picsStr.split("\\u002B");
			for (int i = 0; i < images.length; i++) {
				headImages.add(images[i]);
			}
		} catch (Exception e) {
			LogUtils.e(getClass(), picsStr);
			headImages.add(picsStr);
		}

		travel_details_viewpager.setAdapter(new MyPageAdapter());
		handler.sendEmptyMessageDelayed(0, 3000);
		initPoint();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.travel_details_order:
			intent = new Intent(TravelDetailsActivity.this, TravelDetailsOrderActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	public int setLayout() {
		return R.layout.travel_details;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		id = getIntent().getStringExtra("id");
		String headTitle = getIntent().getStringExtra("title");
		tv_title.setText(headTitle);

		getTravelDetail();
	}

	private void getTravelDetail() {
		RequestParams params = new RequestParams();
		params.addBodyParameter("travelid", id);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.TRAVEL_INFO, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				getTravelDetail();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String json = arg0.result;
				LogUtils.e(getClass(), "json:" + json);
				TravelDetail detail = JSON.parseObject(json, TravelDetail.class);
				setDetailData(detail);
			}
		});
	}

	@Override
	public void setListener() {
		travel_details_order.setOnClickListener(this);

		travel_details_viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				if (headImages.size() != 0 && headImages.get(position % headImages.size()) != null) {
					for (int i = 0; i < headImages.size(); i++) {
						if (i == position % headImages.size()) {
							travel_details_point.getChildAt(i).setBackgroundResource(R.drawable.xl_03);
						} else {
							travel_details_point.getChildAt(i).setBackgroundResource(R.drawable.xl_05);
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

		for (int i = 0; i < headImages.size(); i++) {
			View view = new View(TravelDetailsActivity.this);
			paramsL.setMargins(YangUtils.dip2px(TravelDetailsActivity.this, 5), 0, 0, 0);
			view.setLayoutParams(paramsL);
			if (i == 0) {
				view.setBackgroundResource(R.drawable.xl_03);
			} else {
				view.setBackgroundResource(R.drawable.xl_05);
			}

			travel_details_point.addView(view);
		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private class MyPageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return headImages.size();
		}

		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

		public Object instantiateItem(ViewGroup container, final int position) {
			View view = View.inflate(TravelDetailsActivity.this, R.layout.home_vp_item, null);

			ImageView iv_iamge = (ImageView) view.findViewById(R.id.iv_image);
			iv_iamge.setScaleType(ScaleType.FIT_XY);
			ImageLoader.getInstance().displayImage(HttpUrl.IMAGE_URL + headImages.get(position), iv_iamge,
					ImageLoaderCfg.fade_options);

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
