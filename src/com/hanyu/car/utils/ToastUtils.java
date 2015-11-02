package com.hanyu.car.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	public static Toast toast;
	public static void show(Context context, String msg){
		if (toast == null) {
			toast = toast.makeText(context, msg, Toast.LENGTH_SHORT);
		}
		toast.setText(msg);
		toast.show();
		
	}
}
