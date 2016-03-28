package com.objanalysis;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 解析人物模型
 * @author 大闲人柴毛毛
 * @date 2016年3月26日
 */
public class HumanAnalysis {
	
	/**
	 * 解析人物的OBJ模型
	 * @param objPath obj路径
	 * @throws IOException 
	 */
	public static void analysisHuman(String objPath) throws IOException{
		//健壮性判断
		if(objPath==null){
			System.out.println("文件路径为空！");
			return;
		}
		
		// 读取OBJ
		BufferedReader buf_r = new BufferedReader(new FileReader("/Users/chibozhou/Documents/code/DressCloths/obj/person.obj"));

		//创建容器，用于存放String[]类型的顶点
		List<String[]> vertex_string = new ArrayList<String[]>();
		//创建容器，用于存放float[]类型的顶点
		List<Float[]> vertex_float = new ArrayList<Float[]>();
		
		// count用于统计顶点个数
		int count = 0;
		for (String line = null;(line = buf_r.readLine()) != null;) {
			// 将一行按照空格拆分
			String[] strs = line.split(" ");

			// 若当前行代表顶点
			if (strs[0].equals("v")) {
				// 顶点个数+1
				count++;
				
				//将String类型的x、y、z转化为float类型，并存入容器vertex_float中
				Float[] v = {Float.parseFloat(strs[1]),Float.parseFloat(strs[2]),Float.parseFloat(strs[3])};
				vertex_float.add(v);
				System.out.println(strs[0]);
			}
		}
		System.out.println("count=" + count);
		
		//关闭输入流
		buf_r.close();
		
		
		//将点按照y坐标排序
//		vertex_float.sort(new CompareByY());
		
		//输出排序结果
		Iterator<Float[]> it = vertex_float.iterator();
//		while(it.hasNext()){
//			System.out.println(it.next()[1]);
//		}
		
		//统计模型身高
		float height = vertex_float.get(vertex_float.size()-1)[1] - vertex_float.get(0)[1];
		System.out.println("模型身高为："+height);
		
		//统计肩宽
		
		//获取y=0.75xxx的所有点
//		it = vertex_float.iterator();
//		while(it.hasNext()){
//			Float[] v = it.next();
//			if(v[1]<0.75 || v[1]>=0.76){
//				it.remove();
////				System.out.println(v[1]);
//			}
//		}
//
//		
		for(Float[] v : vertex_float){
			v[0] *= 1000;
			v[1] *= 1000;
			v[2] *= 1000;
		}
		 java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	new Java2DFrame(vertex_float).setVisible(true);
	            }
	        });
	}
	
	
	
	
	/**
	 * 测试
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		analysisHuman("");
	}
}
