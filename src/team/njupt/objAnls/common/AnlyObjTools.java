package team.njupt.objAnls.common;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import team.njupt.objAnls.exception.NullFilePathException;
import team.njupt.objAnls.exception.NullVertexListException;
import team.njupt.objAnls.exception.NullXYZException;
import team.njupt.objAnls.service.AnlyObjService;
import team.njupt.objAnls.view.Java2DFrame;

/**
 * 分析OBJ模型的工具包
 * @author 大闲人柴毛毛
 * @date 2016年3月27日
 */
public class AnlyObjTools {
	
	/**
	 * 将顶点按照X坐标递增排序
	 * @param list 存放顶点的List
	 * @return 排序后的顶点List
	 * @throws NullVertexListException 空的顶点集合异常
	 */
	public static List<Float[]> sortByX(List<Float[]> list) throws NullVertexListException{
		//健壮性判断：若list为空
		if(list==null || list.size()<=0)
			throw new NullVertexListException("顶点集合为空！");
		
		//将集合按照X坐标递增排序
		list.sort(new CompareByX());
		return list;
	}
	
	
	
	
	/**
	 * 将顶点按照Y坐标递增排序
	 * @param list 存放顶点的List
	 * @return 排序后的顶点List
	 * @throws NullVertexListException 空的顶点集合异常
	 */
	public static List<Float[]> sortByY(List<Float[]> list) throws NullVertexListException{
		//健壮性判断：若list为空
		if(list==null || list.size()<=0)
			throw new NullVertexListException("顶点集合为空！");
		
		//将集合按照X坐标递增排序
		list.sort(new CompareByY());
		return list;
	}
	
	
	
	
	/**
	 * 将顶点按照Z坐标递增排序
	 * @param list 存放顶点的List
	 * @return 排序后的顶点List
	 * @throws NullVertexListException 空的顶点集合异常
	 */
	public static List<Float[]> sortByZ(List<Float[]> list) throws NullVertexListException{
		//健壮性判断：若list为空
		if(list==null || list.size()<=0)
			throw new NullVertexListException("顶点集合为空！");
		
		//将集合按照X坐标递增排序
		list.sort(new CompareByZ());
		return list;
	}
	
	
	
	/**
	 * 获取距离地面startHeight－endHeight区间内的所有的点
	 * @param startHeight 起始高度
	 * @param endHeight 终止高度
	 * @param list 待筛选的点集合
	 * @return 返回该区间内所有点的集合
	 * @throws NullXYZException 非法的坐标异常
	 * @throws NullVertexListException 空的顶点集合异常
	 */
	public static List<Float[]> getAllVertexsOfSection(Float startHeight,Float endHeight,List<Float[]> list) throws NullXYZException, NullVertexListException{
		//健壮性判断：若startHeight、endHeight为空
		if(startHeight==null || endHeight==null)
			throw new NullXYZException("非法的坐标异常");
		
		//健壮性判断：若点集合为空
		if(list==null || list.size()<=0)
			throw new NullVertexListException("点集合为空！");
		
		//将点按照Y递增排序
		list.sort(new CompareByY());
		
		//剔除掉Y坐标值在startHeight－endHeight之外的点
		for(int i=list.size()-1;i>=0;i--){
			//若当前点的Y坐标在startHeight－endHeight之外，则将其删掉
			if(list.get(i)[1]<startHeight || list.get(i)[1]>endHeight)
				list.remove(i);
		}
		
		//返回结果
		return list;
	}
	
	
	
	/**
	 * 计算周长
	 * @param list 点集合
	 * @return 返回周长
	 * @throws NullVertexListException 空的顶点集合异常
	 */
	public static Float getPerimeter(List<Float[]> list) throws NullVertexListException{
		//健壮性判断：若点集合为空
		if(list==null || list.size()<=0)
			throw new NullVertexListException("点集合为空！");
		
		//将点按照X排序
		list.sort(new CompareByX());
		
		//计算中点的X坐标
		Float midX = (list.get(0)[0]+list.get(list.size()-1)[0])/2;
		
		//计算椭圆第一象限相邻点的距离之和
		Float length = (float) 0;
		
		System.out.println("腰部起点："+list.get(0)[0]+",腰部中点："+midX);
		for(int i=list.size()-1;i>0;i--){
			Float[] v1 = list.get(i);
			System.out.println("腰部起点："+list.get(0)[0]+",腰部中点："+midX+",当前点："+v1[0]);
			if(v1[0]>=list.get(0)[0] && v1[0]<=midX){
				//获取相邻的点
				Float[] v2 = list.get(i-1);
				//计算相邻点之间的距离
				length += (float) Math.sqrt(Math.pow(v1[0]-v2[0],2)+Math.pow(v1[1]-v2[1],2));
				System.out.println("length="+length);
			}
		}
		
		return length;
	}
	
	
	
	/**
	 * 获取胸部最左边的X的坐标
	 * @param vertexs 胸部所有的点
	 * @return 返回胸部最左侧的点的X坐标
	 * @throws NullVertexListException 空的顶点集合异常
	 */
	public static Float getStartX_Breast(List<Float[]> vertexs) throws NullVertexListException{
		//健壮性判断：若顶点集合为空
		if(vertexs==null || vertexs.size()<=0)
			throw new NullVertexListException("顶点集合为空！");
		
		//将胸部所有的点集按照X坐标递增顺序排序
		vertexs.sort(new CompareByX());
		
		//计算人体中点位置
		Float midx = (vertexs.get(0)[0]+vertexs.get(vertexs.size()-1)[0])/2;
		System.out.println("胸部起点位置："+vertexs.get(0)[0]);
		System.out.println("胸部终点位置："+vertexs.get(vertexs.size()-1)[0]);
		System.out.println("胸部中点位置："+midx);
		
		//将人体胸部位置坐标在X坐标上分成100份，计算每部分的长度
		Float inc = (midx-vertexs.get(0)[0])/10;
		System.out.println("inc="+inc);
		
		//从人体中心点开始，向左计算每一部分的点的个数
		int[] density = new int[10];//数组density记录100个区间内每个区间中的点的个数
		for(int i=0;i<10;i++){
			//当前区间的起始X坐标和结束X坐标
			Float startX = midx-(i+1)*inc;
			Float endX = midx-i*inc;
			//计算当前区间内点的个数
			Iterator<Float[]> it = vertexs.iterator();
			while(it.hasNext()){
				Float[] v = it.next();
				//若当前的点在当前区间内
				if(v[0]>=startX && v[0]<=endX){
					//当前区间的点的个数＋1
					density[i]++;
				}
			}
		}

		//左胸的边界
		Float startX_Breast = (float) 0.0;
		//扫描密度数组，计算密度突然变小的位置
		for(int i=0;i<9;i++){
			System.out.println("第"+(i+1)+"个区间，点的数量为："+density[i]+",["+(midx-i*inc)+","+(midx-(i+1)*inc)+"]");
			//若后一个－前一个<0，且小于50%，则认为已到左胸的边界
			if(density[i+1]-density[i]<0 && density[i+1]*2<density[i]){
				startX_Breast = midx-(i+1)*inc;
				break;
			}
		}
		System.out.println("左胸的边界为:"+startX_Breast);
		return startX_Breast;
	}
	
	
	
	/**
	 * 获取腰部最左边的X的坐标
	 * @param vertexs 腰部所有的点
	 * @return 返回腰部最左侧的点的X坐标
	 * @throws NullVertexListException 空的顶点集合异常
	 */
	public static Float getStartX_Waist(List<Float[]> vertexs) throws NullVertexListException{
		//健壮性判断：若顶点集合为空
		if(vertexs==null || vertexs.size()<=0)
			throw new NullVertexListException("顶点集合为空！");
		
		//将腰部所有的点集按照X坐标递增顺序排序
		vertexs.sort(new CompareByX());
		
		//计算人体中点位置
		Float midx = (vertexs.get(0)[0]+vertexs.get(vertexs.size()-1)[0])/2;
		System.out.println("腰部起点位置："+vertexs.get(0)[0]);
		System.out.println("腰部终点位置："+vertexs.get(vertexs.size()-1)[0]);
		System.out.println("腰部中点位置："+midx);
		
		//将人体腰部位置坐标在X坐标上分成100份，计算每部分的长度
		Float inc = (midx-vertexs.get(0)[0])/10;
		System.out.println("inc="+inc);
		
		//从人体中心点开始，向左计算每一部分的点的个数
		int[] density = new int[10];//数组density记录100个区间内每个区间中的点的个数
		for(int i=0;i<10;i++){
			//当前区间的起始X坐标和结束X坐标
			Float startX = midx-(i+1)*inc;
			Float endX = midx-i*inc;
			//计算当前区间内点的个数
			Iterator<Float[]> it = vertexs.iterator();
			while(it.hasNext()){
				Float[] v = it.next();
				//若当前的点在当前区间内
				if(v[0]>=startX && v[0]<=endX){
					//当前区间的点的个数＋1
					density[i]++;
				}
			}
		}
		
		//左腰的边界
		Float startX_Waist = (float) 0.0;
		//扫描密度数组，计算密度突然变小的位置
		for(int i=0;i<9;i++){
			System.out.println("第"+(i+1)+"个区间，点的数量为："+density[i]+",["+(midx-i*inc)+","+(midx-(i+1)*inc)+"]");
			//若后一个－前一个<0，且小于50%，则认为已到左胸的边界
			if(density[i+1]-density[i]<0 && density[i+1]*2<density[i]){
				startX_Waist = midx-(i+1)*inc;
				break;
			}
		}
		System.out.println("左腰的边界为:"+startX_Waist);
		
		final List<Float[]> list = vertexs;
		java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
          	new Java2DFrame(list).setVisible(true);
          }
		});
		return startX_Waist;
	}
	
	
	
	/**
	 * 测试
	 * @throws IOException 
	 * @throws NullFilePathException 
	 * @throws NullVertexListException 
	 * @throws NullXYZException 
	 */
	public static void main(String[] args) throws NullFilePathException, IOException, NullXYZException, NullVertexListException{
		//获取胸部的所有点
//		final List<Float[]> vertexs = AnlyObjTools.getAllVertexsOfSection(Parameter.BreastHeight_start, Parameter.BreastHeight_end, AnlyObjService.loadObj("/Users/chibozhou/Documents/code/DressCloths/obj/person.obj"));
//		java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//            	new Java2DFrame(vertexs).setVisible(true);
//            }
//        });
		
		//计算胸的起点
//		final List<Float[]> vertexs = AnlyObjTools.getAllVertexsOfSection(Parameter.BreastHeight_start, Parameter.BreastHeight_end, AnlyObjService.loadObj("/Users/chibozhou/Documents/code/DressCloths/obj/person.obj"));
//		getStartX_Breast(vertexs);
		
		//计算腰的起点
		final List<Float[]> vertexs = AnlyObjTools.getAllVertexsOfSection(Parameter.WaistHeight_start, Parameter.WaistHeight_end, AnlyObjService.loadObj("/Users/chibozhou/Documents/code/DressCloths/obj/person.obj"));
		getStartX_Waist(vertexs);
	}
}
