package team.njupt.objAnls.common;

import java.util.Comparator;

/**
 * 按照顶点中X坐标的大小进行递增排序
 * @author 大闲人柴毛毛
 * @date 2016年3月26日
 */
public class CompareByX implements Comparator<Float[]> {

	@Override
	public int compare(Float[] f1, Float[] f2) {
		if(f1[0]<f2[0])
			return -1;
		else if(f1[0]>f2[0])
			return 1;
		else
			return 0;
	}


}
