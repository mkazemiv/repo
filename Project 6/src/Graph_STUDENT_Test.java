/**
 * This JUnit test class tests the methods of Graph.java.
 * @author Mohammad A. Kazemivarnamkhasti
 */
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class Graph_STUDENT_Test {

	GraphInterface<Town, Road> graph;
	Town gburg, gtown, rock;
	
	@Before
	public void setUp() throws Exception {
		graph = new Graph();
		gburg = new Town("Gaithersburg");
		gtown = new Town("Germantown");
		rock = new Town("Rockville");
		
		
		graph.addVertex(gburg);
		graph.addVertex(gtown);
		graph.addVertex(rock);
		graph.addEdge(gburg, gtown, 3, "Clopper Rd.");
		graph.addEdge(gburg, rock, 5, "Frederick Ave.");
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
		gburg = gtown = rock = null;
	}

	@Test
	public void testAddEdge() {
		// edges added in setUp()
		assertTrue(graph.containsEdge(gburg, gtown));
		assertTrue(graph.containsEdge(gtown, gburg));
		
		assertTrue(graph.containsEdge(gburg, rock));
		assertTrue(graph.containsEdge(rock, gburg));
		
		assertFalse(graph.containsEdge(gtown, rock));
		assertFalse(graph.containsEdge(rock, gtown));
	}
	
	@Test
	public void testAddVertex() {
		// vertices added in setUp()
		assertTrue(graph.containsVertex(gburg));
		assertTrue(graph.containsVertex(gtown));
		assertTrue(graph.containsVertex(rock));
		assertFalse(graph.containsVertex(new Town("")));
	}
	
	@Test
	public void testContainsEdge() {
		// this test will pass if the tests below pass,
		// since these test(s) depend on this method
		testAddEdge();
		testGetEdge();
		testRemoveEdge();
	}
	
	@Test
	public void testContainsVertex() {
		// this test will pass if the tests below pass,
		// since these test(s) depend on this method
		testAddVertex();
		testRemoveVertex();
	}
	
	@Test
	public void testEdgeSet() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Clopper Rd.", roadArrayList.get(0));
		assertEquals("Frederick Ave.", roadArrayList.get(1));
	}
	
	@Test
	public void testEdgesOf() {
		Set<Road> roads = graph.edgesOf(gburg);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Clopper Rd.", roadArrayList.get(0));
		assertEquals("Frederick Ave.", roadArrayList.get(1));

		roads = graph.edgesOf(gtown);
		roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		assertEquals("Clopper Rd.", roadArrayList.get(0));
		
		roads = graph.edgesOf(rock);
		roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		assertEquals("Frederick Ave.", roadArrayList.get(0));
	}
	
	@Test
	public void testGetEdge() {
		// edges added in setUp()
		assertEquals(new Road(gburg, rock, 5, "Frederick Ave."), graph.getEdge(rock, gburg));
		assertEquals(new Road(gburg, gtown, 3, "Clopper Rd."), graph.getEdge(gtown, gburg));
	}
	
	@Test
	public void testRemoveEdge() {
		assertTrue(graph.containsEdge(gburg, gtown));
		graph.removeEdge(gburg, gtown, 3, "Clopper Rd.");
		assertFalse(graph.containsEdge(gburg, gtown));
		assertFalse(graph.containsEdge(gtown, gburg));
		
		assertTrue(graph.containsEdge(gburg, rock));
		graph.removeEdge(gburg, rock, 5, "Frederick Ave.");
		assertFalse(graph.containsEdge(gburg, rock));
		assertFalse(graph.containsEdge(rock, gburg));
		
		testRemoveVertex();
	}
	
	@Test
	public void testRemoveVertex() {
		assertTrue(graph.containsVertex(rock));
		graph.removeVertex(rock);
		assertFalse(graph.containsVertex(rock));
	}
	
	@Test
	public void testGetPath() {
		// testing path from gtown to rock (Germantown to Rockville)
		ArrayList<String> path = graph.shortestPath(gtown, rock);
		assertNotNull(path);
		assertTrue(path.size() > 0);
		assertEquals("Germantown via Clopper Rd. to Gaithersburg 3 mi", path.get(0).trim());
		assertEquals("Gaithersburg via Frederick Ave. to Rockville 5 mi", path.get(1).trim());
		
		// testing path from rock to gtown (Rockville to Germantown)
		path = graph.shortestPath(rock, gtown);
		assertNotNull(path);
		assertTrue(path.size() > 0);//Gaithersburg
		assertEquals("Rockville via Frederick Ave. to Gaithersburg 5 mi", path.get(0).trim());
		assertEquals("Gaithersburg via Clopper Rd. to Germantown 3 mi", path.get(1).trim());
	}

	@Test
	public void testVertexSet() {
		Set<Town> towns = graph.vertexSet();
		assertTrue(towns.contains(gburg));
		assertTrue(towns.contains(gtown));
		assertTrue(towns.contains(rock));
	}

}