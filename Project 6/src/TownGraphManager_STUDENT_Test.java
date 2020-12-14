/**
 * This JUnit test class test the methods of TownGraphManager.java. 
 * @author Mohammad A. Kazemivarnmkhasti
 */
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {

	TownGraphManager tgm;
	String gburg, gtown, rock, pot, fred, dc;
	
	@Before
	public void setUp() throws Exception {
		tgm = new TownGraphManager();
		gburg = "Gaithersburg";
		gtown = "Germantown";
		rock = "Rockville";
		pot = "Potomac";
		fred = "Frederick";
		dc = "Washington, D.C.";
		
		tgm.addTown(gburg);	tgm.addTown(gtown);
		tgm.addTown(rock);	tgm.addTown(pot);
		tgm.addTown(fred);	tgm.addTown(dc);
		
		tgm.addRoad(gburg, gtown, 4, "Clopper Rd.");
		tgm.addRoad(gburg, rock, 6, "Frederick Ave.");
		tgm.addRoad(gtown, rock, 13, "I-270");
		tgm.addRoad(pot, rock, 2, "Wotton Pkwy.");
		tgm.addRoad(gtown, fred, 9, "I-270N");
		tgm.addRoad(pot, dc, 18, "I-270S");
		tgm.addRoad(rock, dc, 20, "I-270S");
	}

	@After
	public void tearDown() throws Exception {
		tgm = null;
	}

	@Test
	public void testAddRoad() {
		// roads added in setUp()
		assertTrue(tgm.containsRoadConnection(gburg, gtown));
		assertTrue(tgm.containsRoadConnection(rock, gburg));
		assertTrue(tgm.containsRoadConnection(rock, dc));
		assertTrue(tgm.containsRoadConnection(rock, pot));
		assertTrue(tgm.containsRoadConnection(rock, gtown));
		assertTrue(tgm.containsRoadConnection(rock, pot));
		assertTrue(tgm.containsRoadConnection(gtown, fred));
		
		assertFalse(tgm.containsRoadConnection(gtown, pot));
		assertFalse(tgm.containsRoadConnection(fred, dc));
	}
	
	@Test
	public void testAddTown() {
		// towns added in setUp()
		assertTrue(tgm.containsTown(dc));
		assertTrue(tgm.containsTown(rock));
		assertTrue(tgm.containsTown(fred));
		assertTrue(tgm.containsTown(pot));
		assertTrue(tgm.containsTown(gtown));
		assertTrue(tgm.containsTown(gburg));
		// and a fake town, just for fun :)
		assertFalse(tgm.containsTown(""));
	}
	
	@Test
	public void testAllRoads() {
		ArrayList<String> roads = tgm.allRoads();
		assertEquals("Clopper Rd.", roads.get(0));
		assertEquals("I-270", roads.get(2));
		assertEquals("I-270S", roads.get(4));
		assertEquals("Wotton Pkwy.", roads.get(6));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> towns = tgm.allTowns();
		assertEquals("Gaithersburg", towns.get(1));
		assertEquals("Potomac", towns.get(3));
		assertEquals("Washington, D.C.", towns.get(5));
	}
	
	@Test
	public void testContainsRoadConnection() {
		// this test will pass if the tests below pass,
		// since these test(s) depend on this method
		testAddRoad();
		testGetRoad();
		testDeleteRoadConnection();
	}
	
	@Test
	public void testContainsTown() {
		// this test will pass if the tests below pass,
		// since these test(s) depend on this method
		testAddTown();
		testDeleteTown();
	}
	
	@Test
	public void testDeleteRoadConnection() {
		assertTrue(tgm.containsRoadConnection(rock, pot));
		tgm.deleteRoadConnection(rock, pot, "Wotton Pkwy.");
		assertFalse(tgm.containsRoadConnection(rock, pot));
	}
	
	@Test
	public void testDeleteTown() {
		assertTrue(tgm.containsTown("Washington, D.C."));
		tgm.deleteTown(dc);
		assertFalse(tgm.containsTown("Washington, D.C."));
	}
	
	@Test
	public void testGetPath() {
		ArrayList<String> path = tgm.getPath(fred, dc);
		assertNotNull(path);
		assertTrue(path.size() > 0);
		assertEquals("Frederick via I-270N to Germantown 9 mi", path.get(0).trim());
		assertEquals("Germantown via Clopper Rd. to Gaithersburg 4 mi", path.get(1).trim());
		assertEquals("Gaithersburg via Frederick Ave. to Rockville 6 mi", path.get(2).trim());
		assertEquals("Rockville via I-270S to Washington, D.C. 20 mi", path.get(3).trim());
	}
	
	@Test
	public void testGetRoad() {
		assertEquals("Frederick Ave.", tgm.getRoad(gburg, rock));
		assertEquals("Frederick Ave.", tgm.getRoad(rock, gburg));
		assertEquals("I-270N", tgm.getRoad(fred, gtown));
		assertEquals("I-270N", tgm.getRoad(gtown, fred));
	}

}
