import theLinkedList.TheLinkedList;

/**
 * Main method for testing your linked list methods, NOT EXHAUSTIVE BY ANY
 * MEANS, MAKE YOUR OWN TESTS TOO!
 * 
 * @author Spencer Rudnick
 * 
 */
public class Main {

	public static void main(String[] args) {
		
		TheLinkedList<Integer> theList = new TheLinkedList<Integer>();
		TheLinkedList<String> stringList = new TheLinkedList<String>();

		// Test clearList
		theList.add(new Integer(3));
		theList.add(new Integer(2));
		theList.add(new Integer(1));

		System.out.println("Testing clearList");
		System.out.println(theList.toString());
		System.out.println(theList.toStringPrevious());
		theList.clearList();
		System.out.println(theList.toString());

		// Test reverseList();
		stringList.add("become.");
		stringList.add("should");
		stringList.add("sentence");
		stringList.add("this");
		stringList.add("Backards");

		System.out.println("\nTesting reverseList");
		System.out.println(stringList.toString());
		stringList.reverseList();
		System.out.println(stringList.toString());
		System.out.println(stringList.toStringPrevious());
		stringList.clearList();

		// Test deleteLargest
		theList.add(new Integer(2));
		theList.add(new Integer(6));
		theList.add(new Integer(1));
		theList.add(new Integer(4));
		theList.add(new Integer(2));
		theList.add(new Integer(5));
		theList.add(new Integer(6));
		theList.add(new Integer(3));
		theList.add(new Integer(2));
		theList.add(new Integer(1));

		System.out.println("\nTesting deleteLargest");
		System.out.println(theList.toString());
		Integer largest = theList.deleteLargest();
		System.out.println("Deleted " + largest);
		System.out.println(theList.toString());

		theList.clearList();

		// Test deleteMostCommon
		theList.add(new Integer(2));
		theList.add(new Integer(6));
		theList.add(new Integer(1));
		theList.add(new Integer(4));
		theList.add(new Integer(2));
		theList.add(new Integer(5));
		theList.add(new Integer(1));
		theList.add(new Integer(3));
		theList.add(new Integer(2));
		theList.add(new Integer(1));

		System.out.println("\nTesting deleteMostCommon");
		System.out.println(theList.toString());
		theList.deleteMostCommon();
		System.out.println(theList.toString());
		theList.clearList();

		// Test deleteIncreasing
		theList.add(new Integer(2));
		theList.add(new Integer(6));
		theList.add(new Integer(1));
		theList.add(new Integer(4));
		theList.add(new Integer(2));
		theList.add(new Integer(5));
		theList.add(new Integer(6));
		theList.add(new Integer(3));
		theList.add(new Integer(6));
		theList.add(new Integer(5));

		System.out.println("\nTesting deleteIncreasing");
		System.out.println(theList.toString());
		theList.deleteIncreasing();
		System.out.println(theList.toString());
		theList.clearList();

		// Test makeUnique();
		theList.add(new Integer(6));
		theList.add(new Integer(6));
		theList.add(new Integer(6));
		theList.add(new Integer(2));
		theList.add(new Integer(6));
		theList.add(new Integer(1));
		theList.add(new Integer(4));
		theList.add(new Integer(2));
		theList.add(new Integer(5));
		theList.add(new Integer(1));
		theList.add(new Integer(3));
		theList.add(new Integer(2));
		theList.add(new Integer(1));

		System.out.println("\nTesting makeUnique");
		System.out.println("Original: " + theList.toString());
		theList.makeUnique();
		System.out.println("makeUnique: " + theList.toString());
		System.out.println("makeUnique: " + theList.toStringPrevious() + "\n");
		theList.clearList();

		// Test shuffle merge
		TheLinkedList<String> stringList2 = new TheLinkedList<String>();
		
		stringList.add("become.");
		stringList.add("should");
		stringList.add("sentence");
		stringList.add("this");
		stringList.add("Backards");
		
//		stringList2.add("become.");
//		stringList2.add("should");
//		stringList2.add("sentence");
//		stringList2.add("this");
//		stringList2.add("Backwards");

//		theList.add(new Integer(6));
//		theList.add(new Integer(4));
//		theList.add(new Integer(2));
//		theList.add(new Integer(0));
//
//		theList2.add(new Integer(9));
//		theList2.add(new Integer(7));
//		theList2.add(new Integer(5));
//		theList2.add(new Integer(3));
//		theList2.add(new Integer(1));

		System.out.println("\nTesting shuffleMerge");
		System.out.println(stringList.toString());
		System.out.println(stringList2.toString());
		stringList.shuffleMerge(stringList2);
		System.out.println(stringList.toString());
		System.out.println(stringList.toStringPrevious());

		stringList.clearList();
		stringList2.clearList();

		// Test sorted merge
		TheLinkedList<Integer> theList2 = new TheLinkedList<Integer>();

		theList.add(new Integer(9));
		theList.add(new Integer(6));
		theList.add(new Integer(4));
		theList.add(new Integer(2));

		theList2.add(new Integer(8));
		theList2.add(new Integer(3));
		theList2.add(new Integer(1));

		System.out.println("\nTesting sortedMerge");
		System.out.println(theList.toString());
		System.out.println(theList2.toString());
		theList.sortedMerge(theList2);
		System.out.println(theList.toString());
		System.out.println(theList.toStringPrevious());

		theList.clearList();
		theList2.clearList();

	}
}
