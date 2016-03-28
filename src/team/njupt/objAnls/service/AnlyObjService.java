package team.njupt.objAnls.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import team.njupt.objAnls.common.AnlyObjTools;
import team.njupt.objAnls.common.CompareByX;
import team.njupt.objAnls.common.CompareByY;
import team.njupt.objAnls.common.Parameter;
import team.njupt.objAnls.exception.NullFilePathException;
import team.njupt.objAnls.exception.NullVertexListException;
import team.njupt.objAnls.exception.NullXYZException;
import team.njupt.objAnls.view.BreastView;
import team.njupt.objAnls.view.WaistView;

/**
 * OBJ文件分析类
 * @author 大闲人柴毛毛
 * @date 2016年3月27日
 */
public class AnlyObjService {
	private static List<Float[]> vertexs_all = new ArrayList<Float[]>();
	private static List<Float[]> vertexs_mid = new ArrayList<Float[]>();
	private static List<Float[]> vertexs_after = new ArrayList<Float[]>();
	
	/**
	 * 将OBJ文件的点加载进内存
	 * @param filePath 文件路径
	 * @return 返回包含顶点的List集合
	 * @throws NullFilePathException 空的文件路径字符串异常
	 * @throws IOException 文件读取过程中出现IO异常
	 */
	public static List<Float[]> loadObj(String filePath) throws NullFilePathException, IOException{
		//健壮性判断：若文件路径为空
		if(filePath==null)
			throw new NullFilePathException("文件路径为空！");
		
		//创建存放顶点的List
		List<Float[]> list = new ArrayList<Float[]>();
		
		//构建File对象
		File file = new File(filePath);
		
		//创建输入流
		BufferedReader buf_r = new BufferedReader(new FileReader(file));
		
		//读取文件内容
		for(String line=null;(line=buf_r.readLine())!=null;){
			//将一行数据按空格切分
			String[] element = line.split(" ");
			//若一行被切分成四段，且第一个元素是“v”，则继续；否则，该行直接丢弃
			if(element.length==4 && element[0].equals("v")){
				//将String类型的坐标转换为Float类型
				Float f1 = Float.parseFloat(element[1]);
				Float f2 = Float.parseFloat(element[2]);
				Float f3 = Float.parseFloat(element[3]);
				//将三个点组成一个顶点
				Float[] vertex = {f1,f2,f3};
				//将顶点放入List中
				list.add(vertex);
			}
		}
		
		//关闭输入流
		buf_r.close();
		
		//返回结果
		return list;
	}
	
	
	
	
	/**
	 * 获取模型身高
	 * @param vertexs 顶点坐标
	 * @return 模型的身高(返回－1表示异常)
	 * @throws NullVertexListException 空的顶点集合异常
	 */
	public static float getHeight(List<Float[]> vertexs) throws NullVertexListException{
		//健壮性判断：若点集合为空
		if(vertexs==null || vertexs.size()<=0)
			throw new NullVertexListException("点集为空！");
		
		//将点按照Y排序
		vertexs.sort(new CompareByY());
		
		//计算身高
		Float height = vertexs.get(vertexs.size()-1)[1] - vertexs.get(0)[1];
		
		return height;
	}
	
	
	
	/**
	 * 获取模型胸围
	 * @param vertexs 顶点坐标
	 * @return 模型的胸围(返回－1表示异常)
	 * @throws NullVertexListException 空的顶点集合异常
	 * @throws NullXYZException 非法的坐标异常
	 */
	public static float getBust(List<Float[]> vertexs) throws NullXYZException, NullVertexListException, NullFilePathException, IOException{
		/////////////////
		for(Float[] v : vertexs){
			vertexs_all.add(v);
		}
		/////////////////
		
		//获取胸部范围内的所有的点
		vertexs = AnlyObjTools.getAllVertexsOfSection(Parameter.BreastHeight_start, Parameter.BreastHeight_end, vertexs);
		
/////////////////
for(Float[] v : vertexs){
	vertexs_mid.add(v);
}
/////////////////
		
		//获取胸部左侧的起点X坐标
		Float startX_breast = AnlyObjTools.getStartX_Breast(vertexs);
		
		//获取人体中心位置的X坐标
		Float midX;
		{
			//根据X将胸部范围内的所有点排序
			vertexs.sort(new CompareByX());
			//计算人体中心位置的X坐标
			midX = (vertexs.get(0)[0]+vertexs.get(vertexs.size()-1)[0])/2;
		}
		
		//计算出胸部最右侧的X坐标
		Float endX_breast = (midX-startX_breast)+midX;
		
		//根据胸部最左侧、最右侧的X坐标，过滤出胸部范围内的所有点
		for(int i=vertexs.size()-1;i>=0;i--){
			//若当前点的X坐标不在startX_breast－endX_breast范围内，则剔除掉
			if(vertexs.get(i)[0]<startX_breast || vertexs.get(i)[0]>endX_breast)
				vertexs.remove(i);
		}
/////////////////
for(Float[] v : vertexs){
	vertexs_after.add(v);
}
/////////////////
		
		final List<Float[]> list = vertexs;
		java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
          	new BreastView(vertexs_all,vertexs_mid,vertexs_after).setVisible(true);
          }
		});
		
		//计算胸部的周长
		return AnlyObjTools.getPerimeter(vertexs);
	}
	
	
	
	/**
	 * 获取模型腰围
	 * @param vertexs 顶点坐标
	 * @return 模型的腰围(返回－1表示异常)
	 * @throws NullVertexListException 空的顶点集合异常
	 * @throws NullXYZException 非法的坐标异常
	 */
	public static float getWaist(List<Float[]> vertexs) throws NullVertexListException, NullXYZException{
		/////////////////
		for(Float[] v : vertexs){
			vertexs_all.add(v);
		}
		/////////////////
		
		//健壮性判断：若点集合为空
		if(vertexs==null || vertexs.size()<=0)
			throw new NullVertexListException("点集为空！");
		
		//获取腰部范围内的所有的点
		vertexs = AnlyObjTools.getAllVertexsOfSection(Parameter.WaistHeight_start, Parameter.WaistHeight_end, vertexs);
		
		/////////////////
		for(Float[] v : vertexs){
			vertexs_mid.add(v);
		}
		/////////////////
		
		//获取腰部左侧的起点X坐标
		Float startX_waist = AnlyObjTools.getStartX_Waist(vertexs);
		
		//获取人体中心位置的X坐标
		Float midX;
		{
			//根据X将胸部范围内的所有点排序
			vertexs.sort(new CompareByX());
			//计算人体中心位置的X坐标
			midX = (vertexs.get(0)[0]+vertexs.get(vertexs.size()-1)[0])/2;
		}
		
		//计算出胸部最右侧的X坐标
		Float endX_waist = (midX-startX_waist)+midX;
		
		//根据胸部最左侧、最右侧的X坐标，过滤出胸部范围内的所有点
		for(int i=vertexs.size()-1;i>=0;i--){
			//若当前点的X坐标不在startX_breast－endX_breast范围内，则剔除掉
			if(vertexs.get(i)[0]<startX_waist || vertexs.get(i)[0]>endX_waist)
				vertexs.remove(i);
		}
		
		/////////////////
		for(Float[] v : vertexs){
			vertexs_after.add(v);
		}
		/////////////////
		
		final List<Float[]> list = vertexs;
		java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
          	new WaistView(vertexs_all,vertexs_mid,vertexs_after).setVisible(true);
          }
		});
		
		//计算胸部的周长
		return AnlyObjTools.getPerimeter(vertexs);
	}
	
	
	
	/**
	 * 获取某点在人物模型上的位置
	 * @param vertex 点坐标
	 * @return 该点在人物模型上的位置说明
	 */
	public static String getVertexLocation(Float[] vertex){
		
		return null;
	}
	
	
	
	/**
	 * 测试
	 * @throws IOException 
	 * @throws NullFilePathException 
	 * @throws NullVertexListException 
	 * @throws NullXYZException 
	 */
	public static void main(String[] args) throws NullXYZException, NullVertexListException, NullFilePathException, IOException{
		//获取胸围
		getBust(AnlyObjService.loadObj("/Users/chibozhou/Documents/code/DressCloths/obj/person.obj"));
		
		//获取身高
//		System.out.println("身高＝"+getHeight(AnlyObjService.loadObj("/Users/chibozhou/Documents/code/DressCloths/obj/person.obj")));
		
		//获取腰围
//		System.out.println("腰围长＝"+getWaist(AnlyObjService.loadObj("/Users/chibozhou/Documents/code/DressCloths/obj/person.obj")));
	}
}
