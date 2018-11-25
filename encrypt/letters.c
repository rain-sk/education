
// Programming exercise
// Leon Tabak
// CSC315 Programming Language Concepts
// Cornell College
// 28 September 2015

// Compile this program by typing:
//   gcc letters.c -o -Wall letters
//
// Create a text file named poem by typing:
//   cat > poem
//   I must go down to the sea again,
//   to the lonely sea and sky.
//   And all I need is a tall ship
//   and a star to steer her by.
//   EOF
//
// "EOF" is the end of file marker.
// On Unix systems enter EOF by pressing
// the control and d keys simultaneously.
// 
// Run this program by typing:
//   letters < poem
// This is an example of I/O redirection in
// Unix. You are telling the operating system
// and your program that data will be coming
// from a file rather than from the keyboard.

#include <stdio.h>

// Refactor this code by defining 3 functions:
// 1) a function that returns 1 if its single
//    argument is an uppercase letter and 
//    returns 0 otherwise.
// 2) a function that returns 1 if its single
//    argument is a lowercase letter and
//    returns 0 otherwise.
// 3) a function that returns 1 if its single
//    argument is a letter and returns 0 otherwise.
//    Define this third function using the first two
//    functions.

// Here is an example of a very simple function
// to get you started.
int identityFunction( char c ) {
  return c;
} // identityFunction( char )

// Next, rewrite the if statement in the main() function.
// The condition in the revised statement will be a call
// to your third new function.

int main( int argc, char** argv ) {
  // read every character in the input
  char c;
  while( EOF != scanf("%c",&c) ) {

    // print a character only if it is 
    // a lowercase letter or an uppercase letter.
    if( (('A' <= c) && (c <= 'Z')) || (('a' <= c) && (c <= 'z')) ) {
      printf("%c", c );
    } // if
  } // while

  // return 0 to indicate successful completion
  return 0;
} // main( int, char** )

// Before proceeding, look at the code that you
// already have. What looks like Java? What looks
// different?
//
// Continue this exercise by writing several more
// programs.
//
// 1) Write a program whose input is a sequence of
//    characters that you may assume are all letters.
//    Its output is the same sequence of letters except
//    that the program replaces every lowercase letter
//    with the corresponding uppercase letter.
//
// 2) Write a program whose input is a sequence of
//    characters that you may assume are all uppercase
//    letters. A statement within the program defines
//    in integer constant whose value lies between 0
//    and 25. Its output is the same sequence of letters
//    except that the program replaces each letter in the
//    input with a letter that follows a constant distance
//    later in the alphabet.
//    
//    For example, if the constant's value is 2, then
//    the program replaces every A with a C, every B
//    with a D, every Y with an A, and every Z with a
//    B.
//
// 3) Write a program whose input is a sequence of
//    characters that you may assume are all uppercase
//    letters. Its output is the same sequence of letters
//    except that the program inserts a space character
//    between every group of five letters and a newline
//    character after every eight groups (so that there
//    are 5 x 8 = 40 letters on a line).
//
// Now you have several programs that you can use together
// to encrypt a file using a Caesar cipher. Write a Bash
// script that runs each of the programs in turn, piping
// the output of the n-th program into the (n+1)-th program.
// To see how this is done, copy the following code into a
// file named countswords (leave out the // and leading spaces):
// 
//     #!/bin/bash
//
//     # This script expects one argument.
//     # That argument should be the name of
//     # a text file.
//
//     grep "s[a-z]*" -wo $1 | sort -u | wc -w
//
// Give yourself and others permission to execute
// this file:
//
//     chmod ugo+x countswords
//
// Execute the file:
//
//     countswords poem
//
// This Bash script executes 3 Unix commands
// in succession: grep, sort, and wc.
// The | is the pipe. It is used here to make
// the output of grep the input of sort and
// to make the output of sort the input of wc.
//
// Even though its name is "countswords", 
// this Bash script does not "count swords."
// What does it do?
//
// That's it! Go to work! Use the Bash scripting
// language and the C programming language to make
// a file encryption product.
//
// Want more challenge? 
// 1) If you want more to do, you might add code
//    to decrypt a file.
// 2) If you still want more to do, you might make
//    the offset (the distance between a letter in the
//    plain text and the corresponding letter in the
//    cipher text) a parameter rather than a constant.
// 3) Find out what ctype.h is. How might you use it
//    for this project?



 
