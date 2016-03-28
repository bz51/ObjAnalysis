package com.objanalysis;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JFrame;


public class DrawLine extends JFrame {
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public DrawLine(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;

		this.setBounds(300, 100, 400, 500);

		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void init() {
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				x1 = e.getX();
				y1 = e.getY();
			}

			public void mouseReleased(MouseEvent e) {
				x2 = e.getX();
				y2 = e.getY();
			}

		});

		repaint();
	}

	public void paint(Graphics g){
		super.paint(g);
		g.drawLine(x1, y1, x2, y2);
		}

	public static void main(String[] args) {
		new DrawLine(0, 0, 0, 0).init();
	}

}