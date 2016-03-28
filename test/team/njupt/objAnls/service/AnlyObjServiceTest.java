package team.njupt.objAnls.service;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import team.njupt.objAnls.exception.NullFilePathException;

public class AnlyObjServiceTest {
	private List<Float[]> vertexs;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testLoadObj() {
		try {
			vertexs = AnlyObjService.loadObj("/Users/chibozhou/Documents/code/DressCloths/obj/person.obj");
		} catch (NullFilePathException | IOException e) {
			e.printStackTrace();
		}
		
		Iterator<Float[]> it = vertexs.iterator();
		int count = 0;
		while(it.hasNext()){
			Float[] v = it.next();
			System.out.println(v[0]+","+v[1]+","+v[2]);
			count++;
		}
		System.out.println("总记录数："+count);
	}

	@Test
	public void testGetHeight() {
	}

	@Test
	public void testGetBust() {
	}

	@Test
	public void testGetWaist() {
	}

	@Test
	public void testGetVertexLocation() {
	}

}
