package team.njupt.objAnls.exception;

/**
 * 空的顶点集合异常
 * @author 大闲人柴毛毛
 * @date 2016年3月27日
 */
public class NullVertexListException extends Exception{
	
	public NullVertexListException(){}
	
	public NullVertexListException(String reason){
		super(reason);
	}
	
}
