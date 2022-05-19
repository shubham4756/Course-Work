// gcc EXEC.c -o EXEC
#include<stdio.h> 
#include<unistd.h> 
int main()  { 
    int i; 
      
    printf("I am EXEC.c called by execvp() "); 
    printf("\n"); 
      
    return 0; 
} 