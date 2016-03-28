package team.njupt.objAnls.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
/**
 * Displays a JFrame and draws a line on it using the Java 2D Graphics API
 *
 * @author cn.outofmemory
 */
public class Java2DFrame extends javax.swing.JFrame {
	private List<Float[]> vertexs;
	
    /**
     * Creates a new instance of Java2DFrame
     */
    public Java2DFrame(List<Float[]> vertexs) {
    	this.vertexs = vertexs;
        initComponents();
    }

    /**
     * This is the method where the line is drawn.
     *
     * @param g The graphics object
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for(Float[] v : vertexs){
//        	System.out.println(v[0]+","+v[1]+","+v[2]);
        	//俯视图
        	Line2D lin = new Line2D.Float(v[0]*1000+300,500-v[2]*1000,v[0]*1000+300,500-v[2]*1000);
        	//正视图
        	Line2D lin2 = new Line2D.Float(v[0]*1000+300,800-v[1]*1000,v[0]*1000+300,800-v[1]*1000);
        	g2.draw(lin);
        	g2.draw(lin2);
        }
//        Line2D lin = new Line2D.Float((float) 100.55, (float) 100.55, (float) 200.55, (float) 200.55);
//        Line2D lin2 = new Line2D.Float((float) 200.55, (float) 100.55, (float) 200.55, (float) 200.55);
//        g2.draw(lin);
//        g2.draw(lin2);
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
                new Java2DFrame(list).setVisible(true);
            }
        });
    }
}