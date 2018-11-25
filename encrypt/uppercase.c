#include <stdio.h>

int main( int argc, char** argv ) {
  // read every character in the input
  char c;
  while( EOF != scanf("%c",&c) ) {
    
    // convert lowercase to uppercase
    if( (('a' <= c) && (c <= 'z')) ){
	printf("%c", c - 32);
    } // if
    else {
	printf("%c", c);
    } // else
    
  } // while
  
  return 0;
} // main
