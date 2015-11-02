package com.hanyu.car.utils;

import android.util.Log;

public class LogUtils {
	private static boolean isOpen = true;
	public static void i(String tag,String msg){

		if(isOpen){
			Log.i(tag, msg);
		}
	}
	
	public static void e(Class clz,String msg){
		if (isOpen) {
			Log.e(clz.getSimpleName(), msg);
		}
	}
}
