package DataStructure;
public class LinkedList<T> {
	private LinkedListNode<T> head = null;
	
	public LinkedList () {
		
	}
	
	/**
	 * Get data stored in head node of list.
	 **/
	public T getFirst() {
		return head.getData();
	}
	 
	/**
	 * Get the head node of the list.
	 **/
	public LinkedListNode<T> getFirstNode() {
		return head;
	}
	 
	/**
	 * Get data stored in tail node of list.
	 **/
	public T getLast() {
		return getLastNode().getData();
	}
	 
	/**
	 * Get the tail node of the list.
	 **/
	public LinkedListNode<T> getLastNode() {
		if (!isEmpty()){
			
			LinkedListNode<T> tempt = head;
			while (tempt.getNext() != null) {
				tempt = tempt.getNext(); 	
			}
			return tempt;
		}
		return null;	
	}
	 
	/**
	 * Insert a new node with data at the head of the list.
	 **/
	public void insertFirst( T data ) {
		
		LinkedListNode<T> insertedNode = new LinkedListNode<T>();
		insertedNode.setData(data);
		insertedNode.setNext(head);
		head = insertedNode;
	}
	 
	/**
	 * Insert a new node with data after currentNode
	 **/
	public void insertAfter( LinkedListNode<T> currentNode, T data ) {
		LinkedListNode<T> newNode = new LinkedListNode<T>();
		newNode.setData(data);
		newNode.setNext(currentNode.getNext());
		currentNode.setNext(newNode);
		
	}
	 
	/**
	 * Insert a new node with data at the tail of the list.
	 **/
	public void insertLast( T data ) {
		if (isEmpty())
		{
			insertFirst(data);
		}
		else {
			LinkedListNode<T> newNode = new LinkedListNode<T>();
			newNode.setData(data);
			getLastNode().setNext(newNode);
			newNode.setNext(null);
		}
	}
	 
	/**
	 * Remove the first node
	 **/
	public void deleteFirst() {
		head = head.getNext();
		
	}
	 
	/**
	 * Remove the last node
	 **/
	public void deleteLast() {
		LinkedListNode<T> temp = head;
		if (size() ==1) {
			head = null;
		}
		
		if (!isEmpty()){
			while (temp.getNext().getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(null);
		}
	}
	 
	/**
	 * Remove node following currentNode
	 * If no node exists (i.e., currentNode is the tail), do nothing
	 **/
	public void deleteNext( LinkedListNode<T> currentNode ) {
		if (currentNode.getNext() != null)
			currentNode.setNext(currentNode.getNext().getNext());
	}
	 
	/**
	 * Return the number of nodes in this list.
	 **/
	public int size() {
		int countSize=0;
		LinkedListNode<T> temp = new LinkedListNode<T>();
		temp = head;
		while (temp.getNext() != null) {	
			temp = temp.getNext();
			countSize ++;
		}
		return countSize;	
	}
	 
	/**
	 * Check if list is empty.
	 * @return true if list contains no items.
	 **/
	public boolean isEmpty() {
		if (head==null)
			return true;
		return false;
		
	}
	 
	/**
	 * Return a String representation of the list.
	 **/
	public String toString() {
		String str ="";
		LinkedListNode<T> tempt = head;
		while (tempt.getNext() != null) {	
			
			str +=  tempt.toString() + "->";	
			tempt = tempt.getNext();
		}
		str+= getLast().toString();
		return str;
	}

}


