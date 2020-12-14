/**
 * This JUnit test class tests the methods of Road.java.
 * @author Mohammad A. Kazemivarnamkhasti
 */
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {
	
	Road r1, r2;
	
	@Before
	public void setUp() throws Exception {
		r1 = new Road(new Town("Gaithersburg"), new Town("Rockville"), 5, "I-270");
		r2 = new Road(new Town("Gaithersburg"), new Town("Germantown"), "Game Preserve Rd.");
	}

	@After
	public void tearDown() throws Exception {
		r1 = r2 = null;
	}

	@Test
	public void testCompareTo() {
		// r1 and r2 have different names, since they are
		// different Roads, so 1 is returned
		assertEquals(1, r1.compareTo(r2));
		
		Road road = new Road(new Town(""), new Town(""), "Game Preserve Rd.");
		// 0 is returned since the name of each Road is the same
		assertEquals(0, r2.compareTo(road));
		assertEquals(0, road.compareTo(r2));
	}
	
	@Test
	public void testContains() {
		// checking for the Towns r1 and r2 have
		assertTrue(r1.contains(new Town("Gaithersburg")));
		assertTrue(r1.contains(new Town("Rockville")));
		assertTrue(r2.contains(new Town("Gaithersburg")));
		assertTrue(r2.contains(new Town("Germantown")));
		
		// checking for a Town r1 and r2 don't have
		assertFalse(r1.contains(new Town("Germantown")));
		assertFalse(r2.contains(new Town("Rockville")));
	}
	
	@Test
	public void testEquals() {
		// r1 and r2 aren't equal, so the method should return false
		assertFalse(r1.equals(r2));
		
		// a new Road is created, with the same source & destination as r1
		Road road = new Road(new Town("Gaithersburg"), new Town("Rockville"), "");
		assertTrue(r1.equals(road));
		
		// the source & destination of road are switched
		road = new Road(new Town("Rockville"), new Town("Gaithersburg"), "");
		assertTrue(r1.equals(road));
	}
	
	@Test
	public void testGetDestination() {
		assertEquals("Rockville", r1.getDestination());
		assertEquals("Germantown", r2.getDestination());
	}
	
	@Test
	public void testGetName() {
		assertEquals("I-270", r1.getName());
		assertEquals("Game Preserve Rd.", r2.getName());
	}
	
	@Test
	public void testGetSource() {
		assertEquals("Gaithersburg", r1.getSource());
		assertEquals("Gaithersburg", r2.getSource());
		// since r1 and r2 have the same source, this test should also pass
		assertEquals(r1.getSource(), r2.getSource());
	}

	@Test
	public void testGetVertexA() {
		assertEquals(new Town("Gaithersburg"), r1.getVertexA());
		assertEquals(new Town("Gaithersburg"), r2.getVertexA());
		// since r1 and r2 have the same vertexA, this test should also pass
		assertEquals(r1.getVertexA(), r2.getVertexA());
	}
	
	@Test
	public void testGetVertexB() {
		assertEquals(new Town("Rockville"), r1.getVertexB());
		assertEquals(new Town("Germantown"), r2.getVertexB());
	}
	
	@Test
	public void testGetWeight() {
		assertEquals(5, r1.getWeight());
		assertEquals(1, r2.getWeight());
	}
	
	@Test
	public void testToString() {
		assertEquals("I-270 gets you from Gaithersburg to Rockville and is 5 mi long.", r1.toString());
		assertEquals("Game Preserve Rd. gets you from Gaithersburg to Germantown and is 1 mi long.", r2.toString());
	}

}
