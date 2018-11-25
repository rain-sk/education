#include <stdio.h>

int main( int argc, char** argv ) {
  int shift = 6;
  char c;
  while( EOF != scanf("%c",&c) ) {
    if (c + shift > 'Z'){
      printf( "%c", ('A' - 1) + ((c + shift) - 'Z'));
    } // if
    else{
      printf( "%c", c + shift);
    } // else
  } // while
  return 0;
} // main( int, char**)
