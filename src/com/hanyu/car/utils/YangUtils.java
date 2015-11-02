package com.hanyu.car.utils;

import java.util.Random;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;

/**
 * Android----工具类
 * 
 * @author yang
 * 
 */
public class YangUtils {

	/**
	 * 获得手机唯一IMEI
	 */
	public static String getPhoneIMEI(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String IMEI = telephonyManager.getDeviceId();
		return IMEI;
	}

	/**
	 * 获取手机屏幕宽
	 * 
	 * @param activity
	 */
	public static int getScreenWidth(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		// int width = metrics.widthPixels;// 屏幕的宽
		// int height = metrics.heightPixels;// 屏幕的高
		return metrics.widthPixels;
	}

	/**
	 * 获取手机屏幕高
	 * 
	 * @param activity
	 */
	public static int getScreenHeight(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		// int width = metrics.widthPixels;// 屏幕的宽
		// int height = metrics.heightPixels;// 屏幕的高
		return metrics.heightPixels;

	}

	/**
	 * 获取当前屏幕旋转角度
	 * 
	 * @param activity
	 * @return 0表示是竖屏; 90表示是左横屏; 180表示是反向竖屏; 270表示是右横屏
	 */
	public static int getScreenRotationOnPhone(Activity activity) {
		Display display = activity.getWindowManager().getDefaultDisplay();
		// final Display display = ((WindowManager)
		// context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

		switch (display.getRotation()) {
		case Surface.ROTATION_0:
			return 0;

		case Surface.ROTATION_90:
			return 90;

		case Surface.ROTATION_180:
			return 180;

		case Surface.ROTATION_270:
			return -90;
		}
		return 0;
	}

	/**
	 * 判断当前是否有可用的网络以及网络类型 0：无网络 1：WIFI 2：CMWAP 3：CMNET
	 * 
	 * @param context
	 * @return
	 */
	public static int isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return 0;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						NetworkInfo netWorkInfo = info[i];
						if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
							return 1;
						} else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
							String extraInfo = netWorkInfo.getExtraInfo();
							if ("cmwap".equalsIgnoreCase(extraInfo)
									|| "cmwap:gsm".equalsIgnoreCase(extraInfo)) {
								return 2;
							}
							return 3;
						}
					}
				}
			}
		}
		return 0;
	}

	/**
	 * 将指定byte数组转换成16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public static String byteToHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 * 
	 * @param context
	 *            上下文
	 * @param dpValue
	 *            dp值
	 * @return
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 * 
	 * @param context
	 *            上下文
	 * @param pxValue
	 *            像素值
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 生成0-9的随机数
	 * 
	 * @param count
	 *            生成几位数（6位）
	 * @return random 随机数
	 */
	@SuppressWarnings("unused")
	public static String getRandomNumber(int count) {
		Random rdm = new Random(System.currentTimeMillis());
		String random = "";
		for (int i = 0; i < count; i++) {
			String str = String.valueOf((int) (Math.random() * 10 - 1));
			random = random + str;
		}
		return random;
	}

	/***
	 * 获取MAC地址
	 * 
	 * @return
	 */
	public static String getMacAddress(Context context) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		if (wifiInfo.getMacAddress() != null) {
			return wifiInfo.getMacAddress();
		} else {
			return "";
		}
	}

	/**
	 * 获取运行时间
	 * 
	 * @return 运行时间(单位/s)
	 */
	public static long getRunTimes() {
		long ut = SystemClock.elapsedRealtime() / 1000;
		if (ut == 0) {
			ut = 1;
		}
		return ut;
	}

	/**
	 * 获取当前版本号
	 * 
	 * @return
	 */
	public static String getVersion(Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
			return "";
		}
	}

}
