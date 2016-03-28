package team.njupt.objAnls.exception;

/**
 * 顶点坐标异常
 * @author 大闲人柴毛毛
 * @date 2016年3月27日
 */
public class VertexIllegalException extends Exception{
	
	public VertexIllegalException(){}
	
	public VertexIllegalException(String reason){
		super(reason);
	}
	
}
