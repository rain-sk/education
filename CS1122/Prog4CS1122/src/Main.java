import java.util.Arrays;


public class Main {
	public static void main(String[] arg){
		
		{ // Problem 1 -- test 1:
			String input = "algorithm" ;
			try { 
				print(1, 1, input, Recursion.reverse(input), "mhtirogla");
			}
			catch(UnsupportedOperationException e) {
				print(1, 1, input, "Unimplemented!","mhtirogla" );
			}
		}
		
		{ // Problem 1 -- test 2:
			String input = "A Santa at Nasa" ;
			try { 
				print(1, 2, input, Recursion.reverse(input), "asaN ta atnaS A");
			}
			catch(UnsupportedOperationException e) {
				print(1, 2, input, "Unimplemented!","asaN ta atnaS A" );
			}
		}
		
		{ // Problem 1 -- test 3:
			String input = "1ABC?cBA1" ;
			try { 
				print(1, 3, input, Recursion.reverse(input), "1ABc?CBA1");
			}
			catch(UnsupportedOperationException e) {
				print(1, 3, input, "Unimplemented!","1ABc?CBA1" );
			}
		}
		
		{ // Problem 2 -- test 1:
			String input = "1ABC?cBA1" ;
			try { 
				print(2, 1, input, Recursion.isPalindrome(input), false);
			}
			catch(UnsupportedOperationException e) {
				print(2, 1,input, "Unimplemented!", false );
			}
		}
		
		{ // Problem 2 -- test 2:
			String input = "1AB C?C BA1" ;
			try { 
				print(2, 2, input, Recursion.isPalindrome(input), true);
			}
			catch(UnsupportedOperationException e) {
				print(2, 2, input, "Unimplemented!", true );
			}
		}
		
		{ // Problem 2 -- test 3:
			String input = "c" ;
			try { 
				print(2, 3, input, Recursion.isPalindrome(input), true);
			}
			catch(UnsupportedOperationException e) {
				print(2, 3, input, "Unimplemented!", true );
			}
		}
		
		
		
		{ // Problem 3 -- test 1:
			int input = 1234567 ;
			try { 
				print(3, 1, input, Recursion.largestDigit(input), 7);
			}
			catch(UnsupportedOperationException e) {
				print(3, 1, input, "Unimplemented!", 7 );
			}
		}
		
		{ // Problem 3 -- test 2:
			int input = -1234567 ;
			try { 
				print(3, 2, input, Recursion.largestDigit(input), 7);
			}
			catch(UnsupportedOperationException e) {
				print(3, 2, input, "Unimplemented!", 7 );
			}
		}
		
		{ // Problem 3 -- test 3:
			int input = 0 ;
			try { 
				print(3, 3, input, Recursion.largestDigit(input), 0);
			}
			catch(UnsupportedOperationException e) {
				print(3, 3, input, "Unimplemented!", 0 );
			}
		}
		
		{ // Problem 4 -- test 1:
			String input = "steven" ;
			try { 
				print(4, 1, input, 'e', 'a', Recursion.replace(input, 'e', 'a'), "stavan");
			}
			catch(UnsupportedOperationException e) {
				print(4, 1, input, 'e', 'a', "Unimplemented!", "stavan" );
			}
		}
   
		{ // Problem 4 -- test 2:
			String input = "This is an apple" ;
			try { 
				print(4, 2, input, ' ', '/', Recursion.replace(input, ' ', '/'), "This/is/an/apple");
			}
			catch(UnsupportedOperationException e) {
				print(4, 2, input, ' ', '/', "Unimplemented!", "This/is/an/apple" );
			}
		}
		
		{ // Problem 4 -- test 3:
			String input = "a" ;
			try { 
				print(4, 3, input, ' ', '/', Recursion.replace(input, ' ', '/'), "a");
			}
			catch(UnsupportedOperationException e) {
				print(4, 3, input, ' ', '/', "Unimplemented!", "a" );
			}
		}
		

		
    	
		{ // Problem 5 -- test 1:
			int []  input = {1,2,3,4,5,6,7} ;
			try { 
				print(5, 1, input, Recursion.isIncreasing(input), true);
			}
			catch(UnsupportedOperationException e) {
				print(5, 1, input, "Unimplemented!", true );
			}
		}
		
		{ // Problem 5 -- test 2:
			int []  input = {3,2,1,4,5,6,7} ;
			try { 
				print(5, 2, input, Recursion.isIncreasing(input), false);
			}
			catch(UnsupportedOperationException e) {
				print(5, 2, input, "Unimplemented!", false );
			}
		}
		
		{ // Problem 5 -- test 3:
			int []  input = {2,4,4,4,5,6,7} ;
			try { 
				print(5, 3, input, Recursion.isIncreasing(input), false);
			}
			catch(UnsupportedOperationException e) {
				print(5, 3, input, "Unimplemented!", false );
			}
		}
		
		
    	
		{ // Problem 6 -- test 1:
			int []  input = {-4, 0, 4} ;
			try { 
				print(6, 1, input, 2, 0, Recursion.subsetSum(input, 2, 0), true);
			}
			catch(UnsupportedOperationException e) {
				print(6, 1, input, 2, 0,  "Unimplemented!", true );
			}
		}
		
		{ // Problem 6 -- test 2:
			int []  input = {-4, 0, 4} ;
			try { 
				print(6, 2, input, 3, 3, Recursion.subsetSum(input, 3, 3), false);
			}
			catch(UnsupportedOperationException e) {
				print(6, 2, input, 3, 3,  "Unimplemented!", false );
			}
		}
		
		{ // Problem 6 -- test 3:
			int []  input = {-4, 0, 4} ;
			try { 
				print(6, 3, input, 3, 0, Recursion.subsetSum(input, 3, 0), true);
			}
			catch(UnsupportedOperationException e) {
				print(6, 3, input, 3, 0,  "Unimplemented!", true);
			}
		}
		
		
		
    }
	
	public static void print(int prob, int test, Object input, Object output, Object answer) {
		String format = "Problem %d - test %d:%n  Input  %s%n Output  %s%n Answer  %s%n%n";
		System.out.printf(format, prob, test, input, output, answer);
	}
	
	public static void print(int prob, int test, int[] input, Object output, Object answer) {
		print(prob, test, Arrays.toString(input), output, answer);
	}
	
	public static void print(int prob, int test,  int[] input1, Object input2, Object input3, Object output, Object answer) {
		print(prob, test, Arrays.toString(input1) + ", "+ input2 + ", " + input3, output, answer);
	}
	
	public static void print(int prob, int test,  String input1, Object input2, Object input3, Object output, Object answer) {
		print(prob, test, input1 + ", "+ input2 + ", " + input3, output, answer);
	}
}