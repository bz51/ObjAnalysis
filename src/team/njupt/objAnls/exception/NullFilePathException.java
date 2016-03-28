package team.njupt.objAnls.exception;

/**
 * 空的文件路径字符串异常
 * @author 大闲人柴毛毛
 * @date 2016年3月27日
 */
public class NullFilePathException extends Exception{
	
	public NullFilePathException(){}
	
	public NullFilePathException(String reason){
		super(reason);
	}
	
}
