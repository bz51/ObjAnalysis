package team.njupt.objAnls.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import team.njupt.objAnls.exception.NullFilePathException;
import team.njupt.objAnls.service.AnlyObjService;
/**
 * Displays a JFrame and draws a line on it using the Java 2D Graphics API
 *
 * @author cn.outofmemory
 */
public class BreastView extends javax.swing.JFrame {
	private List<Float[]> vertexs_all;
	private List<Float[]> vertexs_mid;
	private List<Float[]> vertexs_after;
	
    /**
     * Creates a new instance of Java2DFrame
     */
    public BreastView(List<Float[]> vertexs_all,List<Float[]> vertexs_mid,List<Float[]> vertexs_after) {
    	this.vertexs_all = vertexs_all;
    	this.vertexs_mid = vertexs_mid;
    	this.vertexs_after = vertexs_after;
        initComponents();
    }

    /**
     * This is the method where the line is drawn.
     *
     * @param g The graphics object
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for(Float[] v : vertexs_all){
        	//正视图
//        	Line2D lin = new Line2D.Float(v[0]+300,300-v[1],v[0]+300,300-v[1]);
//        	g2.draw(lin);
        	Line2D lin = new Line2D.Float(v[0]*1000+300,800-v[1]*1000,v[0]*1000+300,800-v[1]*1000);
        	g2.draw(lin);
        }
        for(Float[] v : vertexs_mid){
//        	System.out.println(v[0]+","+v[1]+","+v[2]);
        	//俯视图
        	Line2D lin = new Line2D.Float(v[0]*1000+700,500-v[2]*1000,v[0]*1000+700,500-v[2]*1000);
        	//正视图
        	Line2D lin2 = new Line2D.Float(v[0]*1000+700,800-v[1]*1000,v[0]*1000+700,800-v[1]*1000);
        	g2.draw(lin);
        	g2.draw(lin2);
        }
        for(Float[] v : vertexs_after){
        	System.out.println(v[0]+","+v[1]+","+v[2]);
        	//俯视图
        	Line2D lin = new Line2D.Float(v[0]*1000+1000,500-v[2]*1000,v[0]*1000+1000,500-v[2]*1000);
        	//正视图
        	Line2D lin2 = new Line2D.Float(v[0]*1000+1000,800-v[1]*1000,v[0]*1000+1000,800-v[1]*1000);
        	g2.draw(lin);
        	g2.draw(lin2);
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" Generated Code "> 
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
        		layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        		.add(0, 1000, Short.MAX_VALUE)
        		);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 800, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold> 

    /**
     * Starts the program
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    	List<Float[]> list = new ArrayList<Float[]>();
    	Float[] v1 = {(float)-0.595267,(float)75.9778,(float)4.94104};
    	Float[] v2 = {(float)-0.235038,(float)75.9635,(float)4.93264};
    	Float[] v3 = {(float)-0.235038,(float)75.9635,(float)4.93264};
    	list.add(v3);
    	list.add(v2);
    	list.add(v1);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new BreastView(list).setVisible(true);
            }
        });
    }
}