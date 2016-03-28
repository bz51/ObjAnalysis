package com.objanalysis;

import java.util.Comparator;

/**
 * 本类按照顶点中Y坐标的大小进行排序
 * @author 大闲人柴毛毛
 * @date 2016年3月26日
 */
public class CompareByY implements Comparator<Float[]> {

	@Override
	public int compare(Float[] f1, Float[] f2) {
		if(f1[1]<f2[1])
			return -1;
		else if(f1[1]>f2[1])
			return 1;
		else
			return 0;
	}


}
