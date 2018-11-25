#include <stdio.h>

int main( int argc, char** argv) {

  char c;
  int letters = 0;
  while ( EOF != scanf("%c",&c) ) {
    if ((letters > 0) && (letters%5 == 0)){
      if (letters == 40){
	printf( "\n" );
	letters = 0;
      } // if
      else {
	printf( " " );
      }
    } // if
    printf("%c",c);
    letters += 1;
  } // while
  return 0;
} // main( int, char** )
