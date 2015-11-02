package com.hanyu.car.ui.silent.handle;

import java.util.Comparator;

import com.hanyu.car.bean.CarBrand;
import com.hanyu.car.bean.PhoneModel;


/**
 * 
 * @author Mr.Z
 */
public class PinyinComparator implements Comparator<CarBrand> {

	public int compare(CarBrand o1, CarBrand o2) {
		if (o1.getSortLetters().equals("@") || o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
