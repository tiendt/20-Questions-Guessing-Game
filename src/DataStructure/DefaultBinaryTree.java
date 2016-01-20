package DataStructure;
public class DefaultBinaryTree<T> implements BinaryTree<T> {

	protected BinaryTreeNode <T> root;
	//private LinkedList<T> listTest;
	
	public DefaultBinaryTree () {
		
	}
	
	/**
	 * Get the root node for this tree.
	 * 
	 * @return root or null if tree is empty.
	 */
	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	/**
	 * Set the root node for this tree.
	 */
	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}

	/**
	 * Test if the tree is empty.
	 * 
	 * @return true if tree has no data.
	 */
	public boolean isEmpty() {
			return (root == null) ;
	}

	/**
	 * Get the data of this tree using inorder traversal.
	 * 
	 * @return inorder List.
	 */
	public LinkedList<T> inorderTraversal() {

		//return helper function
		return  inorderTraversal (new LinkedList<T>() , getRoot());
			
	}
	
	public LinkedList<T> inorderTraversal(LinkedList<T> list, BinaryTreeNode <T> node) {
		//base case: node is null
		if (node == null) {
			return null;
		}
		
		//base case: node is leaf then insert data of node
		else if (node.isLeaf()) {
			list.insertLast(node.getData());
		}
		
		//otherwise
		//go to: left, node, right
		else {
			inorderTraversal (list, node.getLeftChild());
			list.insertLast(node.getData());
			inorderTraversal (list, node.getRightChild());		
		}
		
		return list;
	}
	
	/**
	 * Get the data of this tree using preorder traversal.
	 * 
	 * @return preorder List.
	 */
	
	public LinkedList<T> preorderTraversal() {

		//return helper function
		return  preorderTraversal (new LinkedList<T>() , getRoot());
			
	}
	public LinkedList<T> preorderTraversal(LinkedList<T> list, BinaryTreeNode <T> node) {
		//base case: node is null
		if (node == null) {
			return null;
		}
		//base case: node is leaf then insert data of node
		else if (node.isLeaf()) {
			list.insertLast(node.getData());
		}
		//otherwise
		//go to: node, left, right
		else {
			list.insertLast(node.getData());
			preorderTraversal (list, node.getLeftChild());
			preorderTraversal (list, node.getRightChild());	
			
		}
		
		return list;
	}

	/**
	 * Get the data of this tree using postorder traversal.
	 * 
	 * @return postorder List.
	 */
	
	public LinkedList<T> postorderTraversal() {

		//return helper function
		return  postorderTraversal (new LinkedList<T>() , getRoot());
			
	}
	public LinkedList<T> postorderTraversal(LinkedList<T> list, BinaryTreeNode <T> node) {
		//base case: node is null
		if (node == null) {
			return null;
		}
		//base case: node is leaf then insert data of node
		else if (node.isLeaf()) {
			list.insertLast(node.getData());
		}
		//otherwise
		//go to: left, right, node		
		else {
			
			postorderTraversal (list, node.getLeftChild());
			postorderTraversal (list, node.getRightChild());	
			list.insertLast(node.getData());
		}
		
		return list;
	}

	/**
	 * Print the tree using inorder traversal.
	 * 
	 * @return inorder String
	 */
	public String inorderString() {
		return inorderTraversal().toString();
	}

	/**
	 * Print the tree using preorder traversal.
	 * 
	 * @return preorder String
	 */
	public String preorderString() {
		return preorderTraversal().toString();
	}

	/**
	 * Print the tree using postorder traversal.
	 * @return postorder String
	 */
	public String postorderString(){
		return postorderTraversal().toString();
	}
	
	public static void main( String[] args ) {
		BinaryTree<String> tree = new DefaultBinaryTree<String>();
		
		BinaryTreeNode<String> happy = new DefaultBinaryTreeNode<String>();
		happy.setData("Happy");
		tree.setRoot(happy);
		BinaryTreeNode<String> doc = new DefaultBinaryTreeNode<String>();
		doc.setData("Doc");
		happy.setLeftChild (doc);
		BinaryTreeNode<String> bashful = new DefaultBinaryTreeNode<String>();
		bashful.setData("Bashful");
		doc.setLeftChild (bashful);
		BinaryTreeNode<String> grumpy = new DefaultBinaryTreeNode<String>();
		grumpy.setData("Grumpy");
		doc.setRightChild (grumpy);
		BinaryTreeNode<String> sleepy = new DefaultBinaryTreeNode<String>();
		sleepy.setData("Sleepy");
		happy.setRightChild (sleepy);
		BinaryTreeNode<String> sneezy = new DefaultBinaryTreeNode<String>();
		sneezy.setData("Sneezy");
		sleepy.setRightChild (sneezy);	
		
		System.out.println (tree.inorderString());
		System.out.println (tree.preorderString());
		
	}
}
