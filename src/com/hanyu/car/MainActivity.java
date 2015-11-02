package com.hanyu.car;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.hanyu.car.activity.home.CenterActivity;
import com.hanyu.car.activity.home.FindCarActivity;
import com.hanyu.car.activity.home.TravelActivity;
import com.hanyu.car.ui.AnimRelativeLayout;
import com.hanyu.car.ui.AnimRelativeLayout.AnimClickLisner;
import com.hanyu.car.utils.YangUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainActivity extends Activity implements AnimClickLisner {
	protected static final String tag = "HomePager";
	@ViewInject(R.id.homgpager_viewpager)
	private ViewPager homgpager_viewpager;// 轮播图
	@ViewInject(R.id.ll_point)
	private LinearLayout ll_point;// 轮播点
	@ViewInject(R.id.layout_content)
	private FrameLayout layout_content;
	@ViewInject(R.id.homepager_iv)
	private AnimRelativeLayout homepager_iv;// 快速寻车
	@ViewInject(R.id.travel_rentcar)
	private AnimRelativeLayout travel_rentcar;// 旅行租车
	@ViewInject(R.id.wedding_rentcar)
	private AnimRelativeLayout wedding_rentcar;// 婚礼租车
	@ViewInject(R.id.business_rentcar)
	private AnimRelativeLayout business_rentcar;// 商务租车
	@ViewInject(R.id.long_rentcar)
	private AnimRelativeLayout long_rentcar;// 长期租车
	@ViewInject(R.id.goto_center)
	private ImageView goto_center;// 个人中心

	private Intent intent;

	private List<View> views = new ArrayList<View>();
	private int[] images = { R.drawable.zy_02, R.drawable.zy_02,
			R.drawable.zy_02, R.drawable.zy_02 };
	private int currentItem = 0;
	private boolean flag = true;
	private LayoutParams paramsL = new LayoutParams(20, 20);

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			currentItem = homgpager_viewpager.getCurrentItem() + 1;
			homgpager_viewpager.setCurrentItem(currentItem);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepager);
		ViewUtils.inject(this);

		homgpager_viewpager.setAdapter(new MyPageAdapter());
		currentItem = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
				% images.length;
		homgpager_viewpager.setCurrentItem(currentItem);

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

		setListener();

		goto_center.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(MainActivity.this, CenterActivity.class);
				startActivity(intent);
			}
		});
		
		IntentFilter filter = new IntentFilter(CLOSE_ACTION);
		myReceiver = new MyReceiver();
		registerReceiver(myReceiver, filter);
		
		
	}
	

	/**
	 * 初始化点
	 */
	private void initPoint() {

		for (int i = 0; i < images.length; i++) {
			View view = new View(MainActivity.this);
			paramsL.setMargins(YangUtils.dip2px(MainActivity.this, 5), 0, 0, 0);
			view.setLayoutParams(paramsL);
			if (i == 0) {
				view.setBackgroundResource(R.drawable.sy_03);
			} else {
				view.setBackgroundResource(R.drawable.sy_05);
			}

			views.add(view);
			ll_point.addView(view);
		}

	}

	public void setListener() {
		homepager_iv.setAnimClickLisner(this);
		travel_rentcar.setAnimClickLisner(this);
		wedding_rentcar.setAnimClickLisner(this);
		business_rentcar.setAnimClickLisner(this);
		long_rentcar.setAnimClickLisner(this);

		homgpager_viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				if (views.size() != 0
						&& views.get(position % images.length) != null) {

					for (int i = 0; i < views.size(); i++) {
						if (i == position % images.length) {
							views.get(i)
									.setBackgroundResource(R.drawable.sy_03);
						} else {
							views.get(i)
									.setBackgroundResource(R.drawable.sy_05);
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
		if (myReceiver != null) {
			unregisterReceiver(myReceiver);
		}
		flag = false;
		super.onDestroy();
	}

	/**
	 * 页面点击事件
	 */
	public void animClick(AnimRelativeLayout animRelativeLayout) {
		switch (animRelativeLayout.getId()) {
		case R.id.homepager_iv:
			intent = new Intent(MainActivity.this, FindCarActivity.class);
			startActivity(intent);
			break;
		case R.id.travel_rentcar:
			intent = new Intent(MainActivity.this, TravelActivity.class);
			intent.putExtra("flag", 1);
			startActivity(intent);
			break;
		case R.id.wedding_rentcar:
			intent = new Intent(MainActivity.this, TravelActivity.class);
			intent.putExtra("flag", 2);
			startActivity(intent);
			break;
		case R.id.business_rentcar:
			intent = new Intent(MainActivity.this, TravelActivity.class);
			intent.putExtra("flag", 3);
			startActivity(intent);
			break;
		case R.id.long_rentcar:
			intent = new Intent(MainActivity.this, TravelActivity.class);
			intent.putExtra("flag", 4);
			startActivity(intent);
			break;

		default:
			break;
		}

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
			View view = View.inflate(MainActivity.this, R.layout.home_vp_item,
					null);

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
	
	public static final String CLOSE_ACTION = "close_this.action";
	private MyReceiver myReceiver;
	class MyReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			MainActivity.this.finish();
		}
		
	}

}
