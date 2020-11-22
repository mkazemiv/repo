/**
 * This class represents a tree of String TreeNodes which are to
 * be used to convert Morse code to English, letter by letter
 * @author Mohammad A. Kazemivarnamkhasti
 */
import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
// tree = MorseCodeTree
	
	// a TreeNode which is a reference to the root of the tree
	protected TreeNode<String> root = new TreeNode<String>("");
	
	// the ArrayList that will eventually contain the letters of the tree 
	protected ArrayList<String> letters = new ArrayList<String>();
	
	/**
	 * Default constructor, which builds the tree by calling buildTree()
	 */
	public MorseCodeTree() {
		buildTree();
	}
	
	/**
	 * Returns a reference to the root TreeNode of the tree.
	 * @return the root of the tree
	 */
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	/**
	 * Sets the root of the tree to the given TreeNode.
	 * @param newNode the TreeNode to be set as the root
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;
	}

	/**
	 * This method calls the addNode method in order to add a new TreeNode, 
	 * using code as data for the TreeNode and result for the TreeNode's
	 * location in the tree.
	 * @param code designates the location of the new TreeNode in the tree
	 * @param result the data for the new TreeNode 
	 */
	@Override
	public MorseCodeTree insert(String code, String result) {
		addNode(root, code, result);
		return this;
	}

	/**
	 * This method adds a TreeNode to the tree using the given root, code 
	 * for location, and letter for the data of the TreeNode.
	 * @param root the "root" of the tree for this operation
	 * @param code designates the location for the TreeNode being added 
	 * @param letter the data for the TreeNode being added
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		TreeNode<String> newChild = new TreeNode<String>(letter);
		
		if(code.length() == 1)
		{
			if(code.equals("."))
				root.leftChild = newChild;
			else if(code.equals("-"))
				root.rightChild = newChild;
		}
		else
		{
			if(code.charAt(0) == '.')
				root = root.leftChild;
			else if(code.charAt(0) == '-')
				root = root.rightChild;
			code = code.substring(1);
			addNode(root, code, letter);
		}
	}

	/**
	 * Returns the result of the fetchNode method, which is the letter
	 * corresponding to the given code.
	 * @param code an indicator for the location of the TreeNode being "fetched" 
	 */
	@Override
	public String fetch(String code) {
		return fetchNode(root, code);
	}

	/**
	 * Returns the English letter using the given root and corresponding to
	 * the given code.
	 * @param root the "root" of the tree for this operation
	 * @param code an indicator for the location of the TreeNode being "fetched"
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		// TODO Auto-generated method stub
		String letter = "0";
		if(code.length() == 1)
		{
			if(code.equals("."))
				letter = root.leftChild.getData();
			else if(code.equals("-"))
				letter = root.rightChild.getData();
		}
		else
		{
			if(code.charAt(0) == '.')
				root = root.leftChild;
			else if(code.charAt(0) == '-')
				root = root.rightChild;
			code = code.substring(1);
			letter = fetchNode(root, code);
		}
		return letter;
	}

	/**
	 * This method is not supported for this class.
	 */
	@Override
	public MorseCodeTree delete(String data) throws UnsupportedOperationException {
		return this;
	}

	/**
	 * This method is not supported for this class.
	 */
	@Override
	public MorseCodeTree update() throws UnsupportedOperationException {
		return this;
	}

	/**
	 * This method builds a Morse code tree by inserting the 
	 * letters of the alphabet on at a time, while ensuring that 
	 * every node inserted has a "parent" node.
	 */
	@Override
	public void buildTree() {
		// the first four lines build the left side of the tree
		insert(".","e");
		
		insert("..","i");  insert("...","s"); insert("..-","u"); insert("....","h");
		
		insert("...-","v"); insert("..-.","f"); insert(".-","a"); insert(".-.","r");
		
		insert(".--","w"); insert(".-..","l"); insert(".--.","p"); insert(".---","j");
		
		// these four lines build the right side of the tree
		insert("-","t");
		
		insert("-.","n"); insert("--","m"); insert("-..","d"); insert("-.-","k");
		
		insert("--.","g"); insert("---","o"); insert("-...","b"); insert("-..-","x");
		
		insert("-.-.","c"); insert("-.--","y"); insert("--..","z"); insert("--.-","q");
	}

	/**
	 * Sorts the tree and then returns letters, the ArrayList of letters
	 * that are in the tree. 
	 * @return an ArrayList of Strings, containing the letters of the tree
	 */
	@Override
	public ArrayList<String> toArrayList() {
		if(letters.size() <= 1)
			LNRoutputTraversal(root, letters);
		return letters;
	}
	
	/**
	 * Sorts the data of the tree indicated by the given root and then populates
	 * the list using the sorted data.
	 * @param root the root of the tree
	 * @param list an ArrayList that is to be populated using the data of the tree
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if(root !=  null) {
			LNRoutputTraversal(root.leftChild, list);
			list.add(root.data);
			LNRoutputTraversal(root.rightChild, list);
		}
	}
}