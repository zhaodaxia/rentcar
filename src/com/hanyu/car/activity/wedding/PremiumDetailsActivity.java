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
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.hanyu.car.R;
import com.hanyu.car.base.BaseActivity;
import com.hanyu.car.bean.LuxuryContentInfos;
import com.hanyu.car.bean.PremiumContentInfos;
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

public class PremiumDetailsActivity extends BaseActivity {
	@ViewInject(R.id.luxury_details_viewpager)
	private ViewPager luxury_details_viewpager;// 轮播图
	@ViewInject(R.id.luxury_details_point)
	private LinearLayout luxury_details_point;// 轮播点
	@ViewInject(R.id.luxury_details_order)
	private RelativeLayout luxury_details_order;// 立即预约

	//高端轿车控件
	@ViewInject(R.id.luxury_contentInfo_mcar_pexplain)
	private TextView luxury_contentInfo_mcar_pexplain;
	@ViewInject(R.id.luxury_contentInfo_mcar_km)
	private TextView luxury_contentInfo_mcar_km;
	@ViewInject(R.id.luxury_contentInfo_mcar_time)
	private TextView luxury_contentInfo_mcar_time;
	@ViewInject(R.id.luxury_contentInfo_mcar_ctime)
	private TextView luxury_contentInfo_mcar_ctime;
	@ViewInject(R.id.luxury_contentInfo_mcar_ckm)
	private TextView luxury_contentInfo_mcar_ckm;
	@ViewInject(R.id.luxury_contentInfo_mcar_content)
	private TextView luxury_contentInfo_mcar_content;
	@ViewInject(R.id.luxury_contentInfo_mcar_linkman)
	private TextView luxury_contentInfo_mcar_linkman;
	@ViewInject(R.id.luxury_contentInfo_mcar_phone)
	private TextView luxury_contentInfo_mcar_phone;
	
	//高级轿车详情信息
	private PremiumContentInfos contentInfos;
	private String mcarid;
	
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
			currentItem = luxury_details_viewpager.getCurrentItem() + 1;
			luxury_details_viewpager.setCurrentItem(currentItem);
		}

	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.luxury_details_order:
			intent = new Intent(PremiumDetailsActivity.this,
					LuxuryDetailsOrderActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.luxury_details;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setBack();
		intent = getIntent();
		Bundle bundle = intent.getExtras();
		String title = bundle.getString("premium_details");
		mcarid = bundle.getString("mcarid");
		setTopTitle(title);

		luxury_details_viewpager.setAdapter(new MyPageAdapter());
		currentItem = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
				% images.length;
		luxury_details_viewpager.setCurrentItem(currentItem);

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
		//获取高级轿车详情信息
		PremiumContentInfos(mcarid);
	}

	private void PremiumContentInfos(final String mcarid) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("mcarid",mcarid );
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.LUXURY_CONTENT_INFO,params, new RequestCallBack<String>() {
		@Override
		public void onFailure(HttpException arg0, String arg1) {
			PremiumContentInfos(mcarid);
			//Toast.makeText(PremiumDetailsActivity.this, "豪车信息详情加载失败", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onSuccess(ResponseInfo<String> arg0) {
			String json = arg0.result;
			LogUtils.e(getClass(), json);
			contentInfos = JSON.parseObject(json, PremiumContentInfos.class);
			//高级轿车详情信息加载
			showPremiumContentInfos();
		}
	});
	}

	protected void showPremiumContentInfos() {
		// TODO Auto-generated method stub
		luxury_contentInfo_mcar_pexplain.setText(contentInfos.mcar_pexplain);
		luxury_contentInfo_mcar_km=(TextView) findViewById(R.id.luxury_contentInfo_mcar_km);
		luxury_contentInfo_mcar_km.setText(contentInfos.mcar_km);
		luxury_contentInfo_mcar_time.setText(contentInfos.mcar_time);
		luxury_contentInfo_mcar_ctime.setText(contentInfos.mcar_ctime);
		luxury_contentInfo_mcar_ckm.setText(contentInfos.mcar_ckm);
		luxury_contentInfo_mcar_content.setText(contentInfos.mcar_content);
		luxury_contentInfo_mcar_linkman.setText(contentInfos.mcar_linkman);
		luxury_contentInfo_mcar_phone.setText(contentInfos.mcar_phone);
	}

	@Override
	public void setListener() {
		luxury_details_order.setOnClickListener(this);

		luxury_details_viewpager
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
			View view = new View(PremiumDetailsActivity.this);
			paramsL.setMargins(
					YangUtils.dip2px(PremiumDetailsActivity.this, 5), 0, 0, 0);
			view.setLayoutParams(paramsL);
			if (i == 0) {
				view.setBackgroundResource(R.drawable.xl_03);
			} else {
				view.setBackgroundResource(R.drawable.xl_05);
			}

			views.add(view);
			luxury_details_point.addView(view);
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
			View view = View.inflate(PremiumDetailsActivity.this,
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
