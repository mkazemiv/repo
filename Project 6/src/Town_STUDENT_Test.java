/**
 * This JUnit test class tests the methods of Town.java.
 * @author Mohammad A. Kazemivarnamkhasti
 */
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class Town_STUDENT_Test {

	Town t1, t2, t1Copy; 
	
	@Before
	public void setUp() throws Exception {
		t1 = new Town("Town 1");
		t2 = new Town("Town 2");
		// a copy of t1, created using the copy-constructor
		t1Copy = new Town(t1);
	}

	@After
	public void tearDown() throws Exception {
		t1 = t2 = t1Copy = null;
	}
	
	@Test
	public void testAddToRoads() {
		// a new Road r is introduced, which is added to t1
		// and t2's adjacent roads list
		Road r = new Road(t1, t2, 8,"Road Rd.");
		t1.addToRoads(r);
		t2.addToRoads(r);
		ArrayList<Road> t1Roads = t1.getAdjacentRoads();
		ArrayList<Road> t2Roads = t2.getAdjacentRoads();
		
		ArrayList<Road> testRoads = new ArrayList<Road>();
		testRoads.add(r);
		
		assertEquals(testRoads.get(0), t1Roads.get(0));
		assertEquals(testRoads.get(0), t2Roads.get(0));
	}
	
	@Test
	public void testAddToTowns() {
		// assume a new Road r is added, which
		// makes t1 and t2 adjacent to each other 
		t1.addToTowns(t2);
		t2.addToTowns(t1);
		
		ArrayList<Town> t1Towns = t1.getAdjacentTowns();
		ArrayList<Town> t2Towns = t2.getAdjacentTowns();
		ArrayList<Town> testTowns = new ArrayList<Town>();
		testTowns.add(t1);
		testTowns.add(t2);
		
		assertEquals(testTowns.get(1), t1Towns.get(0));
		assertEquals(testTowns.get(0), t2Towns.get(0));
	}
	
	@Test
	public void testCompreTo() {
		assertTrue(t1Copy.compareTo(t1) == 0);
		
		Town t1Fake = new Town("not Town 1");
		assertTrue(t1Fake.compareTo(t1) != 0);
	}
	
	@Test
	public void testEquals() {
		assertFalse(t1.equals(null));
		assertTrue(t1Copy.equals(t1));
	}
	
	@Test
	public void testGetAdjacentRoads() {
		// this test will pass if testAddToRoads() and testRemoveFromRoads() pass,
		// since those tests depend on getAdjacentRoads() to work
		testAddToRoads();
		testRemoveFromRoads();
	}
	
	@Test
	public void testGetAdjacentTowns() {
		// this test will pass if testAddToTowns() and testRemoveFromRoads() pass,
		// since those tests depend on getAdjacentTowns() to work
		testAddToTowns();
		testRemoveFromTowns();
	}
	
	@Test
	public void testGetDistance() {
		// initial value of distance
		int dI = Integer.MAX_VALUE;
		assertEquals(dI, t1.getDistance());
		assertEquals(dI, t2.getDistance());
		
		int a = 14, b = 7;
		t1.setDistance(a);
		t2.setDistance(b);
		assertEquals(a, t1.getDistance());
		assertEquals(b, t2.getDistance());
	}
	
	@Test
	public void testGetName() {
		assertEquals("Town 1", t1.getName());
		assertEquals("Town 2", t2.getName());
		assertEquals("Town 1", t1Copy.getName());
	}
	
	@Test
	public void testGetPredecessor() {
		assertEquals(null, t1.getPredecessor());
		t2.setPredecessor(t1);
		assertEquals(t1, t2.getPredecessor());
	}
	
	@Test
	public void testHashCode() {
		int t1Hash = ("Town 1").hashCode();
		int t2Hash = ("Town 2").hashCode();
		int t1CopyHash = t1Hash;
		
		assertEquals(t1Hash, t1.hashCode());
		assertEquals(t2Hash, t2.hashCode());
		assertEquals(t1CopyHash, t1Copy.hashCode());
	}
	
	@Test
	public void testIsVisited() {
		t1.setVisited(true);
		assertTrue(t1.isVisited());
	}
	
	@Test
	public void testRemoveFromRoads() {
		Road r = new Road(t1, t2, 8,"Road Rd.");
		// a new Road r is added to t1 and t2's lists
		t1.addToRoads(r);
		t2.addToRoads(r);
		// r is removed from the list of both Towns
		// in order to test removeFromRoads()
		t1.removeFromRoads(r);
		t2.removeFromRoads(r);
		
		ArrayList<Road> t1Roads = t1.getAdjacentRoads();
		ArrayList<Road> t2Roads = t2.getAdjacentRoads();
		// an empty ArrayList
		ArrayList<Road> noRoads = new ArrayList<Road>();
		assertEquals(noRoads, t1Roads);
		assertEquals(noRoads, t2Roads); 
	}
	
	@Test
	public void testRemoveFromTowns() {
		// assume a new Road r is added, which
		// makes t1 and t2 adjacent to each other 
		t1.addToTowns(t2);
		t2.addToTowns(t1);
		// t1 and t2 are removed from each other's lists
		// in order to test removeFromTowns()
		t1.removeFromTowns(t2);
		t2.removeFromTowns(t1);
		
		ArrayList<Town> t1Towns = t1.getAdjacentTowns();
		ArrayList<Town> t2Towns = t2.getAdjacentTowns();
		// an empty ArrayList
		ArrayList<Town> noTowns = new ArrayList<Town>();
		
		assertEquals(noTowns, t1Towns);
		assertEquals(noTowns, t2Towns);
	}
	
	@Test
	public void testSetDistance() {
		// this test will pass if testGetDistance() passes, since
		// that test depends on setDistance() to work
		testGetDistance();
	}
	
	@Test
	public void testSetPredecessor() {
		// this test will pass if testGetPredecessor() passes, since
		// that test depends on setPredecessor() to work
		testGetPredecessor();
	}
	
	@Test
	public void testSetVisited() {
		// this test will pass if testIsVisited() passes, since
		// that test depends on setVisited() to work
		testIsVisited();
	}
	
	@Test
	public void testToString() {
		assertEquals("Town 1", t1.toString());
		assertEquals("Town 2", t2.toString());
		assertEquals("Town 1", t1Copy.toString());
	}

}