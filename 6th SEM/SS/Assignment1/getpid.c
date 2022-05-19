#include<stdio.h>
#include<unistd.h>
int main() {
    printf("Current Process id %d\n" , getpid());
    return 0;
}