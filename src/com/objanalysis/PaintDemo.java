package com.objanalysis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaintDemo {
	//主窗口
	private JFrame frmPaint = new JFrame("Paint Demo");
	//绘画面板
	private JPanel pnlPaint = new JPanel();
	//定义当前点坐标
	private int x, y;

	public PaintDemo (Float[] vertexs) {
		
		//创建 MouseAdapter
		MouseAdapter ma = new MouseAdapter() {
			//添加点击事件
			@Override
			public void mousePressed (MouseEvent me) {
				//鼠标按下，则把当前点坐标设定为鼠标位置
				x = me.getX();
				y = me.getY();
				//画线，从点 (x,y) 到点 (x,y)
				for(Float v : vertexs){
//					pnlPaint.getGraphics().draw(v[0],v[2],v[0],v[2]);
				}
//				pnlPaint.getGraphics().drawLine(x, y, x, y);
			}
		};

		//初始化绘画面板
		pnlPaint.setBackground(Color.white);
		pnlPaint.addMouseListener(ma);
		pnlPaint.addMouseMotionListener(ma);

		//初始化主窗口
		frmPaint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPaint.setSize(800, 600);
		frmPaint.setLocationRelativeTo(null);
		frmPaint.setContentPane(pnlPaint);
		frmPaint.setVisible(true);

		pnlPaint.getGraphics().drawLine(0, 0, 600,500);
	}
	
	public void show(){
		pnlPaint.getGraphics().drawLine(0, 0, 600,500);
		System.out.println("已执行show……");
	}

	public static void main (String args[]) {
//		new PaintDemo().show();
	}
}