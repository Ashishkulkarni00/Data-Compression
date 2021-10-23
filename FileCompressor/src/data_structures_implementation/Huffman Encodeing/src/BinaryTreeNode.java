import java.io.Serializable;

public class BinaryTreeNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String data;
	int frequency;
	BinaryTreeNode left;
	BinaryTreeNode right;
	
	//Constructor...
	public BinaryTreeNode(String data,int frequency) {
		this.data = data;
		this.frequency = frequency;
		this.left = null;
		this.right = null;
	
	}
	
}
