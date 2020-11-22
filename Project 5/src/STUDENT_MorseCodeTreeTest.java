/**
 * This student JUnit test class tests the methods of MorseCodeTree. 
 * @author Mohammad A. Kazemivarnamkhasti
 */

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class STUDENT_MorseCodeTreeTest {

	MorseCodeTree tree; 
	
	@Before
	public void setUp() throws Exception {
		tree = new MorseCodeTree();
	}

	@After
	public void tearDown() throws Exception {
		tree = null;
	}

	@Test
	public void testBuildTree() {
		/* Since buildTree() is called in the constructor, it has already been invoked in line 12.
		 * To test that it works properly, we can just make sure the tree has been "built" properly.
		 * One way to do this is by ensuring the toArrayList method returns the contents of the tree,
		 * in LNR order.
		 */
		testToArrayList();
	}
	
	@Test
	public void testGetRoot() {
		TreeNode<String> testRoot = new TreeNode<String>("");
		assertEquals(testRoot.getData(), tree.getRoot().getData());
		assertEquals(tree.root, tree.getRoot());
	}
	
	@Test
	public void testSetRoot() {
		TreeNode<String> testNode = new TreeNode<String>("wasd");
		tree.setRoot(testNode);
		assertEquals(tree.root, tree.getRoot());
		assertEquals(testNode.getData(), tree.getRoot().getData());
	}
	
	@Test
	public void testToArrayList() {
		String[] treeLettersLNR = {"h", "s", "v", "i", "f", "u", "e", "l", "r", "a", "p", "w", "j",
				"", "b", "d", "x", "n", "c", "k", "y", "t", "z", "g", "q", "m", "o"};
		ArrayList<String> builtTree = new ArrayList<String>();
		for(String text : treeLettersLNR)
			builtTree.add(text);
		assertEquals(builtTree, tree.toArrayList());
	}
	
	@Test
	public void testInsert() {
		String one = "1", two = "2";
		tree.insert(".....", one);
		tree.insert("....-", two);
		assertEquals(tree.root.leftChild.leftChild.leftChild.leftChild.leftChild.getData(), one);
		assertEquals(tree.root.leftChild.leftChild.leftChild.leftChild.rightChild.getData(), two);
	}
	
	@Test
	public void testAddNode() {
		String q = "?", ex = "!";
		tree.addNode(tree.root, "---.", q);
		tree.addNode(tree.root, "----", ex);
		assertEquals(tree.root.rightChild.rightChild.rightChild.leftChild.getData(), q);
		assertEquals(tree.root.rightChild.rightChild.rightChild.rightChild.getData(), ex);
	}
	
	@Test
	public void testFetch() {
		String a = "a", codeForA = ".-";
		String z = "z", codeForZ = "--..";
		assertEquals(a, tree.fetch(codeForA));
		assertEquals(z, tree.fetch(codeForZ));
	}
	
	@Test
	public void testFetchNode() {
		String b = "b", codeForB = "-...";
		String r = "r", codeForR = ".-.";
		assertEquals(b, tree.fetchNode(tree.root, codeForB));
		assertEquals(r, tree.fetchNode(tree.root, codeForR));
	}
	
	@Test
	public void testLNRoutputTraversal() {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> test = tree.toArrayList();
		tree.LNRoutputTraversal(tree.root, list);
		assertEquals(test, list);
	}
	
	@Test
	public void testDelete() {
		try {
			tree.delete("");
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		try {
			tree.update();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}

}