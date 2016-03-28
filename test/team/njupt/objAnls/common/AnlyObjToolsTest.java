package team.njupt.objAnls.common;


import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import team.njupt.objAnls.exception.NullFilePathException;
import team.njupt.objAnls.exception.NullVertexListException;
import team.njupt.objAnls.exception.NullXYZException;
import team.njupt.objAnls.service.AnlyObjService;
import team.njupt.objAnls.view.Java2DFrame;

public class AnlyObjToolsTest {
	private static List<Float[]> vertexs;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//加载OBJ
		try {
			vertexs = AnlyObjService.loadObj("/Users/chibozhou/Documents/code/DressCloths/obj/person.obj");
		} catch (NullFilePathException | IOException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testSortByX() {
	}

	@Test
	public void testSortByY() {
	}

	@Test
	public void testSortByZ() {
	}

	@Test
	public void testGetAllVertexsOfSection() throws NullXYZException, NullVertexListException {
		//筛选出胸部的点
				vertexs = AnlyObjTools.getAllVertexsOfSection(Parameter.BreastHeight_start, Parameter.BreastHeight_end, vertexs);
				java.awt.EventQueue.invokeLater(new Runnable() {
		            public void run() {
		            	new Java2DFrame(vertexs).setVisible(true);
		            }
		        });
	}

	@Test
	public void testGetPerimeter() {
	}

	@Test
	public void testGetStartX_Breast() throws NullVertexListException {
//		Float startX_Breast = AnlyObjTools.getStartX_Breast(vertexs);
//		System.out.println("人的");
	}

}
