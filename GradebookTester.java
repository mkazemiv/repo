import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * This is the JUnit test class for GradeBook
 * @author Mohammad A. Kazemivarnamkhasti
 */
public class GradebookTester {

	GradeBook gb1, gb2;
	
	@Before
	public void setUp() throws Exception {
		gb1 = new GradeBook(5);
		gb1.addScore(68.7);
		gb1.addScore(82.3);
		
		gb2 = new GradeBook(5);
		gb2.addScore(96.1);
		gb2.addScore(100);
		gb2.addScore(88.4);
	}

	@After
	public void tearDown() throws Exception {
		gb1 = gb2 = null;
	}

	@Test
	public void testAddScore() {
		String str = gb1.toString();
		assertTrue(str.equals("68.7 82.3 "));
		assertEquals(gb1.getScoreSize(), 2);
		
		str = gb2.toString();
		assertTrue(str.equals("96.1 100.0 88.4 "));
		assertEquals(gb2.getScoreSize(), 3);
	}
	
	@Test
	public void testSum() {
		assertEquals(gb1.sum(), 151.0, 0.001);
		assertEquals(gb2.sum(), 284.5, 0.001);
	}
	
	@Test
	public void testMinimum() {
		assertEquals(gb1.minimum(), 68.7, 0.001);
		assertEquals(gb2.minimum(), 88.4, 0.001);
	}
	
	@Test
	public void testFinalScore() {
		assertEquals(gb1.finalScore(), 82.3, 0.001);
		assertEquals(gb2.finalScore(), 196.1, 0.001);
	}

}