package theLinkedList;

/**
 * Node class for a doubly linked list
 * 
 * @author Spencer Rudnick
 * 
 * @param <T>
 *            - Data type the node holds, must implement Comparable<T> interface
 */
public class Node<T extends Comparable<T>> {

	// properties
	private T element;
	private Node<T> next;
	private Node<T> previous;

	/**
	 * Create a new node.
	 * 
	 * @param v
	 *            The value referred to by this node.
	 * @param n
	 *            The next node in the list (or null if the end).
	 */
	public Node(T t, Node<T> n, Node<T> p) {
		element = t;
		next = n;
		previous = p;
	} // end of constructor

	/**
	 * Set the object value referred to by this node.
	 * 
	 * @param t
	 *            the value
	 */
	public void setValue(T t) {
		element = t;
	}// end of setValue method

	/**
	 * Get the object value referred to by this node.
	 * 
	 * @return the value
	 */
	public T getValue() {
		return element;
	} // end of getValue method

	/**
	 * Get the next node in the list.
	 * 
	 * @return A reference to the next node (or null if this is the last node in
	 *         the list).
	 */
	public Node<T> getNext() {
		return next;
	} // end of getNext method

	/**
	 * Get the previous node in the list.
	 * 
	 * @return A reference to the previous node (or null if this is the last
	 *         node in the list).
	 */
	public Node<T> getPrevious() {
		return previous;
	} // end of getPrevious method

	/**
	 * Make a node be the next node for this node.
	 * 
	 * @param A
	 *            reference to then new next node.
	 */
	public void setNext(Node<T> n) {
		next = n;
	} // end of setNext method

	/**
	 * Make a new node be the previous node for this node.
	 * 
	 * @param A
	 *            reference to the new previous node.
	 */
	public void setPrevious(Node<T> p) {
		previous = p;
	} // end of setPrevious method

} // end of Node class

