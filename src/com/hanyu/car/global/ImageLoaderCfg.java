package com.hanyu.car.global;

import com.hanyu.car.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class ImageLoaderCfg {
	public static DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.ic_launcher)
			.showImageForEmptyUri(R.drawable.center_01)
			.showImageOnFail(R.drawable.center_01).cacheInMemory(true)
			.cacheOnDisk(true).considerExifParams(true)
			.displayer(new SimpleBitmapDisplayer()).build();
	public static DisplayImageOptions fade_options = new DisplayImageOptions.Builder()
	.showImageOnLoading(R.drawable.laosilaisi)
	.showImageForEmptyUri(R.drawable.laosilaisi)
	.showImageOnFail(R.drawable.laosilaisi).cacheInMemory(true)
	.cacheOnDisk(true).considerExifParams(true)
	.displayer(new FadeInBitmapDisplayer(300)).build();
}
