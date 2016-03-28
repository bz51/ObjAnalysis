package team.njupt.objAnls.common;

import java.util.Comparator;

import team.njupt.objAnls.exception.VertexIllegalException;

/**
 * 按照顶点中Z坐标的大小进行递增排序
 * @author 大闲人柴毛毛
 * @date 2016年3月26日
 */
public class CompareByZ extends CompareSuper implements Comparator<Float[]> {

	@Override
	public int compare(Float[] f1,Float[] f2) {
		//顶点合法性判断(暂时不用)
//		try {
//			super.verifyVertex(f1);
//			super.verifyVertex(f2);
//		} catch (VertexIllegalException e) {
//			e.printStackTrace();
//		}
		
		if(f1[2]<f2[2])
			return -1;
		else if(f1[2]>f2[2])
			return 1;
		else
			return 0;
	}


}
