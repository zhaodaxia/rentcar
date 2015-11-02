package com.hanyu.car.activity.wedding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.hanyu.car.R;
import com.hanyu.car.activity.findcar.SelectCarBrandActivity;
import com.hanyu.car.adapter.fastfindcar.SelectCarBrandListViewAdapter;
import com.hanyu.car.base.MyBaseActivity;
import com.hanyu.car.bean.CarBrand;
import com.hanyu.car.bean.PhoneModel;
import com.hanyu.car.global.MyApplication;
import com.hanyu.car.http.HttpUrl;
import com.hanyu.car.ui.silent.handle.CharacterParser;
import com.hanyu.car.ui.silent.handle.PinyinComparator;
import com.hanyu.car.ui.silent.handle.SideBar;
import com.hanyu.car.ui.silent.handle.SideBar.OnTouchingLetterChangedListener;
import com.hanyu.car.utils.LogUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SelectBrandActivity extends MyBaseActivity {
	/*protected static final int BRAND = 0;
	private SelectCarBrandListViewAdapter adapter;

	@ViewInject(R.id.country_lvcountry)
	private ListView sortListView;
	@ViewInject(R.id.sidrbar)
	private SideBar sideBar;
	@ViewInject(R.id.dialog)
	private TextView dialog;
	*//**
	 * 汉字转换成拼音的类
	 *//*
	private CharacterParser characterParser;
	private List<CarBrand> SourceDateList;

	*//**
	 * 根据拼音来排列ListView里面的数据类
	 *//*
	private PinyinComparator pinyinComparator;

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("品牌选择");
		setBack();
		initViews();
	}

	@Override
	public int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.select_item_index;

	}

	*//**
	 * 为ListView填充数据
	 * 
	 * @param date
	 * @return
	 *//*
	@SuppressLint("DefaultLocale")
	@SuppressWarnings("unused")
	private List<PhoneModel> filledData(String[] date, String[] imgData) {
		List<PhoneModel> mSortList = new ArrayList<PhoneModel>();

		for (int i = 0; i < date.length; i++) {
			PhoneModel sortModel = new PhoneModel();
			sortModel.setImgSrc(imgData[i]);
			sortModel.setName(date[i]);
			// 汉字转换成拼音
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}

			mSortList.add(sortModel);
		}
		return mSortList;

	}

	private void initViews() {
		// 实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar.setTextView(dialog);

//		SourceDateList = filledData(
//				getResources().getStringArray(R.array.date), getResources()
//						.getStringArray(R.array.img_src_data));

		// 根据a-z进行排序源数据
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SelectCarBrandListViewAdapter(
				SelectBrandActivity.this, SourceDateList);
		sortListView.setAdapter(adapter);

		// 设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position);
				}

			}
		});

		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// 这里要利用adapter.getItem(position)来获取当前position所对应的对象
//				Toast.makeText(SelectCarBrandActivity.this,
//						((PhoneModel) adapter.getItem(position)).getName(),
//						Toast.LENGTH_SHORT).show();
//				String brand = ((PhoneModel) adapter.getItem(position)).getName();
//				intent = new Intent();
//				intent.putExtra("brand", brand);
//				setResult(BRAND, intent);
//				finish();
			}
		});

	}*/
//**************************************************************************
	protected static final int BRAND = 0;
	private SelectCarBrandListViewAdapter adapter;
	@ViewInject(R.id.brand_tv)
	// 品牌右端文本
	private TextView brand_tv;
	@ViewInject(R.id.country_lvcountry)
	private ListView sortListView;
	@ViewInject(R.id.sidrbar)
	private SideBar sideBar;
	@ViewInject(R.id.dialog)
	private TextView dialog;
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<CarBrand> SourceDateList;

	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("品牌选择");
		setBack();
		initViews();
	}

	@Override
	public int setLayout() {
		return R.layout.select_item_index;
	}

	/**
	 * 为ListView填充数据
	 * 
	 * @param date
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	@SuppressWarnings("unused")
	private void filledData(List<CarBrand> date) {
		List<PhoneModel> mSortList = new ArrayList<PhoneModel>();

		for (int i = 0; i < date.size(); i++) {
			// 汉字转换成拼音
			String pinyin = characterParser.getSelling(date.get(i).carbrand_name);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				date.get(i).setSortLetters(sortString.toUpperCase());
			} else {
				date.get(i).setSortLetters("#");
			}
		}
	}

	private void initViews() {
		// 实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar.setTextView(dialog);

		//获取品牌数据
		getCarData();

		// 设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position);
				}

			}
		});

		sortListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String Head_carbrand_name =  adapter.getItem(position).carbrand_name;
				String Head_carbrandid =  adapter.getItem(position).carbrandid;
				intent = new Intent();
				intent.putExtra("Head_carbrand_name", Head_carbrand_name);
				intent.putExtra("Head_carbrandid", Head_carbrandid);
				setResult(BRAND, intent);
				finish();
				
				/*intent = new Intent(SelectBrandActivity.this,
						SelectCarModelActivity.class);
				intent.putExtra("brandId", adapter.getItem(position).carbrandid);
				startActivityForResult(intent, BRAND);*/
			}
		});

	}

	private void getCarData() {
		loadingDialog.show();
		
		RequestParams params = new RequestParams();
		params.addBodyParameter("pid", "0");
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.CAR_BRAND,params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				getCarData();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				loadingDialog.dismiss();
				
				LogUtils.e(getClass(), arg0.result);
				SourceDateList = JSON.parseArray(arg0.result, CarBrand.class);
				filledData(SourceDateList);
				// 根据a-z进行排序源数据
				Collections.sort(SourceDateList, pinyinComparator);
				adapter = new SelectCarBrandListViewAdapter(
						SelectBrandActivity.this, SourceDateList);
				sortListView.setAdapter(adapter);
			}
		});
	}
}
