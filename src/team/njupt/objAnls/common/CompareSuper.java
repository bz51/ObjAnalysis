package team.njupt.objAnls.common;

import team.njupt.objAnls.exception.VertexIllegalException;

/**
 * 本类为CompareByxxx的父类，封装了CompareByxxx的公共函数。
 * @author 大闲人柴毛毛
 * @date 2016年3月27日
 */
public class CompareSuper {
	
	/**
	 * 验证顶点是否合法
	 * @param vertex 顶点坐标
	 * @return 验证结果
	 * @throws VertexIllegalException 非法的顶点异常
	 */
	protected boolean verifyVertex(Float[] vertex) throws VertexIllegalException{
		//顶点为空
		if(vertex==null)
			throw new VertexIllegalException("顶点为空！");
		
		//顶点非三维
		else if(vertex.length<3)
			throw new VertexIllegalException("顶点的坐标个数小于3！");
			
		return true;
	}
}
