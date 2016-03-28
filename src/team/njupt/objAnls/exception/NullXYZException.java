package team.njupt.objAnls.exception;

/**
 * 非法的坐标异常
 * @author 大闲人柴毛毛
 * @date 2016年3月27日
 */
public class NullXYZException extends Exception{
	
	public NullXYZException(){}
	
	public NullXYZException(String reason){
		super(reason);
	}
	
}
