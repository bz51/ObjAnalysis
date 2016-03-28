package team.njupt.objAnls.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import team.njupt.objAnls.exception.NullFilePathException;
import team.njupt.objAnls.service.AnlyObjService;

/**
 * 程序的开始页面
 * @author 大闲人柴毛毛
 * @date 2016年3月28日
 */
public class MainWindow extends JFrame implements ActionListener{
	//点集合
	private List<Float[]> vertexs;
	//Panel
	private JPanel pane1 = new JPanel();
	private JPanel pane2 = new JPanel();
	private JPanel pane3 = new JPanel();
	private JPanel pane4 = new JPanel();
	private JPanel pane5 = new JPanel();
	private JPanel pane6 = new JPanel();
    //按钮
	private JButton open=null;
	private JButton b1 = new JButton("计算胸围");
	private JButton b2 = new JButton("计算腰围");
	private JButton b3 = new JButton("计算身高");
	
    public static void main(String[] args) throws NullFilePathException, IOException {  
        new MainWindow();  
    }  
    
    public MainWindow() throws NullFilePathException, IOException{  
    	//初始化窗口
    	initFrame();
    	
    	//初始化
        
    }  
    
    /**
     * 初始化窗口
     * @throws IOException 
     * @throws NullFilePathException 
     */
    private void initFrame() throws NullFilePathException, IOException {
    	//初始化窗口
        this.setBounds(0, 0, 1200, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLayout(new BoxLayout());
        
        pane1 = new JPanel();
        pane2 = new JPanel();
        pane3 = new JPanel();
        pane4 = new JPanel();
        pane5 = new JPanel();
        pane6 = new JPanel();
        pane1.setLayout(new FlowLayout());
        pane2.setLayout(new FlowLayout());
        pane3.setLayout(new FlowLayout());
        pane4.setLayout(new FlowLayout());
        pane5.setLayout(new FlowLayout());
        pane6.setLayout(new FlowLayout());
        pane1.setSize(300,400);
        pane2.setSize(300,400);
        pane3.setSize(300,400);
        pane4.setSize(300,400);
        pane5.setSize(300,400);
        pane6.setSize(300,400);
        pane1.setBackground(Color.WHITE);
        pane2.setBackground(Color.WHITE);
        pane3.setBackground(Color.WHITE);
        pane4.setBackground(Color.WHITE);
        pane5.setBackground(Color.WHITE);
        pane6.setBackground(Color.WHITE);
        pane1.setVisible(true);
        pane2.setVisible(true);
        pane3.setVisible(true);
        pane4.setVisible(true);
        pane5.setVisible(true);
        pane6.setVisible(true);
        this.add(pane1);
        this.add(pane2);
        this.add(pane3);
        this.add(pane4);
        this.add(pane5);
        this.add(pane6);
        open=new JButton("选择Obj文件");
        pane1.add(open);
        open.addActionListener(this);
        
        Panel1 p = new Panel1(AnlyObjService.loadObj("/Users/chibozhou/Documents/code/DressCloths/obj/person.obj"));
        pane2.add(p);
        p.setVisible(true);
    }

	@Override
    public void actionPerformed(ActionEvent e) {  
        JFileChooser jfc=new JFileChooser();  
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
        jfc.showDialog(new JLabel(), "选择");  
        File file=jfc.getSelectedFile();  
        if(file.isDirectory()){  
            System.out.println("文件夹:"+file.getAbsolutePath());  
        }else if(file.isFile()){  
            System.out.println("文件:"+file.getAbsolutePath());  
            
            //加载OBJ文件
            loadObj(file.getAbsolutePath());
            
            //隐藏选择文件按钮
            open.setVisible(false);
            //显示其余四个按钮
            b1 = new JButton("计算胸围");
        	b2 = new JButton("计算腰围");
        	b3 = new JButton("计算身高");
        	//按钮添加进panel中
        	pane1.add(b1);
        	pane1.add(b2);
        	pane1.add(b3);
        }  
        System.out.println(jfc.getSelectedFile().getName());  
          
    }  
    
    
    
    /**
     * 加载OBJ文件
     */
    private void loadObj(String filePath) {
    	try {
			this.vertexs = AnlyObjService.loadObj(filePath);
		} catch (NullFilePathException | IOException e) {
			JOptionPane.showMessageDialog(this, "OBJ加载失败！", "",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
    }




	class Panel1 extends JPanel{
    	private List<Float[]> vertexs;
    	
        public Panel1(List<Float[]> vertexs) {
        	this.vertexs = vertexs;
        }

        public void paint(Graphics g) {
        	System.out.println("paint……");
            Graphics2D g2 = (Graphics2D) g;
            for(Float[] v : vertexs){
//            	Line2D lin = new Line2D.Float(0,0,10,400);
            	//俯视图
            	Line2D lin = new Line2D.Float(v[0]*500+150,100-v[2]*500,v[0]*500+150,100-v[2]*500);
            	g2.draw(lin);
            }
        }
    }
    
}
