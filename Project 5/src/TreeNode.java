/**
 * TODO fix it
 * @author Mohammad A. Kazemivarnamkhasti
 * @param <T>
 */
public class TreeNode<T> {

	T data;
	TreeNode<T> leftChild, rightChild;
	
	/**
	 * This constructor takes some generic data to initialize
	 * the TreeNode's data field.
	 * @param dataNode
	 */
	public TreeNode(T dataNode) {
		data = dataNode;
		leftChild = rightChild = null;
	}
	
	/**
	 * This constructor constructs a generic TreeNode using the given node,
	 * which is also a generic TreeNode, essentially deep-copying node.
	 * @param node
	 */
	public TreeNode(TreeNode<T> node) {
		data = node.getData();
		leftChild = node.leftChild;
		rightChild = node.rightChild;
	}
	
	/**
	 * Returns the generic data of this TreeNode object.
	 * @return the data field of this TreeNode 
	 */
	public T getData() {
		return data;
	}
	
}