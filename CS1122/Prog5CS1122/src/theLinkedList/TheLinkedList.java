package theLinkedList;

/**
 * A class that functions as a linked list.
 * 
 * @author Spencer Rudnick
 * 
 */

public class TheLinkedList<T extends Comparable<T>> {

	// properties

	// reference to first node in the list
	// (or null if the list is empty)
	private Node<T> head;
	private Node<T> tail;

	// number of Nodes in the collection
	private int count;

	/**
	 * Create a new, empty collection.
	 */
	public TheLinkedList() {

		head = null;
		tail = null;
		count = 0;
	} // end of constructor

	/**
	 * Accessor method for the head node of the linked list
	 * 
	 * @return head node of the linked list
	 */
	public Node<T> getHead() {
		return head;
	}

	/**
	 * Accessor method for the tail node of the linked list
	 * 
	 * @return tail node of the linked list
	 */
	public Node<T> getTail() {
		return tail;
	}

	/**
	 * Add an Object to the collection.
	 * 
	 * @param newValue
	 *            The object to add.
	 */
	public void add(T newValue) {
		// create a new node and put it at the beginning of the list
		head = new Node<T>(newValue, head, null);
		if (head.getNext() != null)
			head.getNext().setPrevious(head);
		else
			tail = head;
		count++;
	} // end of add method

	/**
	 * Remove an Object from the collection.
	 * 
	 * @param what
	 *            The Object to remove.
	 */
	public void remove(T what) {

		// do nothing if the list is empty
		if (head == null)
			return;

		T testHead = head.getValue();

		// if the object is in the first node in the list...
		if (testHead.compareTo(what) == 0) {
			// remove it, but have to be careful of null pointer exceptions
			// if it is the only one in the list
			if (head.getNext() == null) {
				head = null;
				tail = null;
			} else {
				head = head.getNext();
				head.setPrevious(null);
			}
			count--;
			return;
		}

		// start at the beginning of the list
		Node<T> temp = head.getNext();

		// as long as there are still nodes left
		while (temp != null) {

			// if the it node is the node to be remove removed
			T it = temp.getValue();
			if (it.equals(what)) {

				// remove it
				temp.getPrevious().setNext(temp.getNext());
				if (temp.getNext() != null)
					temp.getNext().setPrevious(temp.getPrevious());
				else
					tail = temp.getPrevious();
				count--;
				return;
			}

			// move to the next node
			temp = temp.getNext();
		}
	} // end of remove method

	/**
	 * Return the number of Nodes in the collection.
	 * 
	 * @return the length of the linked list.
	 */
	public int size() {
		return count;
	} // end of size method

	/**
	 * Return a string representation of this collection, in the form
	 * ["Obj1","Obj2",...].
	 * 
	 * @return the string representation.
	 */
	public String toString() {

		// initialize result
		String result = "[";

		// if nothing in the list, return "[]"
		if (head == null) {
			return result + "]";
		}

		// put the first string in the result
		result = result + head.getValue();

		// go down the list...
		Node<T> temp = head.getNext();
		while (temp != null) {

			// append a comma and the next string to the result
			result = result + ", " + temp.getValue();

			// advance to the next element in the list
			temp = temp.getNext();
		}

		// return the result
		return result + "]";
	} // end of toString method

	/**
	 * Return a string representation of this collection, in the form
	 * ["Obj1","Obj2",...]. Used to test that all the previous references in the
	 * linked list are connected properly.
	 * 
	 * @return the string representation.
	 */
	public String toStringPrevious() {

		// initialize result
		String result = "]";

		// if nothing in the list, return "[]"
		if (tail == null) {
			return "[" + result;
		}

		// put the first string in the result
		result = tail.getValue() + result;

		// go down the list...
		Node<T> temp = tail.getPrevious();
		while (temp != null) {

			// append a comma and the next string to the result
			result = temp.getValue() + ", " + result;

			// advance to the next element in the list
			temp = temp.getPrevious();
		}

		// return the result
		return "[" + result;
	} // end of toString method

	/**
	 * Removes all nodes from the list so that a list containing [1,2,3] would
	 * then contain [].
	 */
	public void clearList() {
		// reset all references
		head = null;
		tail = null;
		count = 0;
	}

	/**
	 * Calling this method reverses the order of all elements in the linked
	 * list.
	 * 
	 * e.g. [1,4,3,2,5] will become [5,2,3,4,1]
	 */
	public void reverseList() {
		
		// flip head and tail references
		Node<T> temp = head;
		head = tail;
		tail = temp;
		
		// pointer to traverse the list
		Node<T> p = head;
		
		// traverse the list and switch previous and next references
		while (p != null){
			temp = p.getNext();
			p.setNext(p.getPrevious());
			p.setPrevious(temp);
			p = p.getNext();
		}
	}

	/**
	 * Deletes first occurrence of the largest element in the linked list.
	 * 
	 * e.g.[1,2,6,1,6,2,4,1,6,2] will become [1,2,1,6,2,4,1,6,2]
	 * 
	 * @return The value that was deleted, null otherwise.
	 */
	public T deleteLargest() {
		
		// place to store largest T in list
		T largest = head.getValue();
		
		// pointer to traverse list
		Node<T> p = getHead();
		
		// traverse the list and record the largest T
		while (p != null){
			if (p.getValue().compareTo(largest) > 0){
				largest = p.getValue();
			}
			p = p.getNext();
		}
		
		// remove largest from the list and then return it
		remove(largest);
		return largest;
	}

	/**
	 * Deletes all occurrences of the element that occurs most commonly in the
	 * linked list. In the case of a tie in frequency, delete the element which
	 * appears first in the linked list.
	 * 
	 * e.g.[7,2,3,7,5,2,4,7,8,2] will become [2,3,5,2,4,8,2]
	 */
	public void deleteMostCommon() {
		// cursor to traverse list
		Node<T> p1 = getHead();

		// variables to track most common T
		T mostCommon = null;
		int commonCount = 0;

		// traverse the list with p1
		while (p1 != null){

			// only iterate through the list if mostCommon is null
			// or if p1's value is different than the mostCommon value
			if (mostCommon == null || p1.getValue().compareTo(mostCommon) != 0){
				
				// variables to track the commonality of p1's value
				T tempCommon = p1.getValue();
				int tempCount = 1;
				// cursor to traverse everything following p1
				Node<T> p2 = p1.getNext();

				// traverse the list with p2 and count occurrences of p1's value
				while (p2 != null){
					if (p1.getValue().compareTo(p2.getValue()) == 0){
						tempCount += 1;
					}
					p2 = p2.getNext();
				}
				// in case no value has been recorded yet
				if (mostCommon == null){
					mostCommon = tempCommon;
					commonCount = tempCount;
				}
				// compare tempCount and commonCount to see which T is more common
				else if (tempCount > commonCount){
					mostCommon = tempCommon;
					commonCount = tempCount;
				}
			}
			// iterate p1
			p1 = p1.getNext();
		}

		// remove all occurrences of the most common T
		while (commonCount > 0){
			remove(mostCommon);
			commonCount -= 1;
		}

	}

	/**
	 * Delete any element after the first element, that is larger than the its
	 * previous element. When an element is deleted, the element that takes its
	 * position in the list must also be checked to make sure it is not larger
	 * than its previous element.
	 * 
	 * e.g.[4,2,3,1,5,2,4,1,6,2] will become [4,2,1,1]
	 */
	public void deleteIncreasing() {
		if (head == null) return;
		// traverse the list
		Node<T> p = head.getNext();
		while (p != null){
			// compare the previous node's value to the current node's value
			if (p.getPrevious().getValue().compareTo(p.getValue()) < 0){
				
				// delete the current node by stitching the previous and next references
				p.getPrevious().setNext(p.getNext());
				if (p.getNext() != null)
					p.getNext().setPrevious(p.getPrevious());
				// update tail reference in case p == tail
				else
					tail = p.getPrevious();
				count -= 1;
			}
			p = p.getNext();
		}
	}

	/**
	 * This method will remove duplicate entries in the list, leaving exactly
	 * one copy of each value that existed in the list before it was called.
	 * 
	 * e.g. if the current list contains [5, 2, 3, 2, 3, 1] before this method
	 * is called, it should contain [5, 2, 3, 1] when this method finishes.
	 */
	public void makeUnique() {
		
		// traverse the list
		Node<T> p1 = head;
		while (p1 != null){
			
			// traverse the list
			Node<T> p2 = p1.getNext();
			while (p2 != null){
				
				// remove nodes that have a value equal to p1's value
				if (p1.getValue().compareTo(p2.getValue()) == 0){
					p2.getPrevious().setNext(p2.getNext());
					if (p2.getNext() != null)
						p2.getNext().setPrevious(p2.getPrevious());
					else
						tail = p2.getPrevious();
					p2 = p2.getPrevious();
					count -= 1;
				}
				// iterate p2
				p2 = p2.getNext();
			}
			// iterate p1
			p1 = p1.getNext();
		}
	}

	/**
	 * Merge the current linked list with the argument linked list such that the
	 * nodes alternate between the two lists, starting with the current list. If
	 * either list runs out, all the remaining nodes should be taken from the
	 * other list. The first node of the new list should be taken from the
	 * current list, assuming the list is not empty. The contents of the
	 * argument list is not checked after this method is called, so it does not
	 * matter what happens to that linked list.
	 * 
	 * e.g. if the current list contains [5, 2, 3] and the argument two contains
	 * [7, 13, 1, 8] shuffleMerge should cause the current list to contain [5,
	 * 7, 2, 13, 3, 1, 8].
	 * 
	 * @param two
	 *            another linked list
	 */
	public void shuffleMerge(TheLinkedList<T> two) {
		
		// if this list is empty, point all references to two's references
		if (count == 0){
			head = two.getHead();
			tail = two.getTail();
			count = two.size();
			two.clearList();
			return;
		}
		
		// pointers to traverse list
		Node<T> p1 = head;
		Node<T> p2 = head.getNext();
		
		// traverse the list
		while (p1 != null && two.size() > 0){
			// create new node and set it between p1 and p2
			Node<T> inBetween = new Node<T>(two.getHead().getValue(), p2, p1);
			
			// update p1 and p2's references
			p1.setNext(inBetween);
			if (p2 != null)
				p2.setPrevious(inBetween);
			// update tail reference if p2 is null
			else
				tail = inBetween;
			
			// iterate p1 and p2
			p1 = p2;
			if (p2 != null)
				p2 = p2.getNext();
			
			// update two and increment counter
			two.remove(two.getHead().getValue());
			count += 1;
		}
		
		// if there are any nodes left in two, append them to the end of this list
		if (two.size() > 0){
			tail.setNext(two.getHead());
			two.getHead().setPrevious(tail);
			tail = two.getTail();
			count += two.size();
			two.clearList();
		}
		
	}

	/**
	 * Merge the current linked list with the argument linked list, both of
	 * which will already be sorted in increasing order. If the lists are not
	 * sorted, you are not responsible for this method working. Merges the two
	 * lists into one list which is still in increasing order. The list this
	 * method is called on should contain all the elements from it and list two.
	 * The contents of list two after this call are not checked.
	 * 
	 * You are NOT allowed to simply append one list to the other then sort the
	 * list into ascending order! You must merge the lists without any
	 * reordering of nodes.
	 * 
	 * e.g. if the current list is [2, 3, 5, 8] and the argument two is a list
	 * containing [1, 4, 6, 7] then after the sorted merge, the current list
	 * will contain [1, 2, 3, 4, 5, 6, 7, 8]
	 * 
	 * @param two
	 *            another sorted linked list
	 */
	public void sortedMerge(TheLinkedList<T> two) {
		
		// if this list is empty, point all references to two's references
		if (count == 0){
			head = two.getHead();
			tail = two.getTail();
			count = two.size();
			two.clearList();
			return;
		}
		
		// pointers to traverse list
		Node<T> p1 = head;
		Node<T> p2 = p1.getNext();
		
		// traverse the list
		while (p1 != null && two.count > 0){
			
			// temporary value to compare
			T temp = two.getHead().getValue();
			
			// if temp should be the new head
			if (p1.getValue().compareTo(temp) > 0){
				
				// do the stuff
				two.remove(temp);
				Node<T> insert = new Node<T>(temp, p1, p1.getPrevious());
				p1.setPrevious(insert);
				
				// point head to insert
				head = insert;
				
				// iterate counter
				count += 1;
				
				// iterate p1 and p2
				p1 = insert;
				p2 = insert.getNext();
			}
			// if temp fits between p1 and p2
			else if (p1.getValue().compareTo(temp) <= 0 && (p2 == null || p2.getValue().compareTo(temp) >= 0)){
				
				// insert it in the list between p1 and p2
				two.remove(temp);
				Node<T> insert = new Node<T>(temp, p2, p1);
				p1.setNext(insert);
				
				// logic to make sure tail pointer doesn't get messed up
				if (p2 != null)
					p2.setPrevious(insert);
				else
					tail = insert;
				
				// iterate counter
				count += 1;
				
				// iterate p1 and p2
				p1 = insert;
				p2 = insert.getNext();
			}
			// else iterate the pointer nodes
			else{
				p1 = p2;
				p2 = p2.getNext();
			}
		}
		
		// if there are any nodes left in two, append them to the end of this list
		if (two.size() > 0){
			tail.setNext(two.getHead());
			two.getHead().setPrevious(tail);
			tail = two.getTail();
			count += two.size();
			two.clearList();
		}
		
	}

} // end of TheLinkedList