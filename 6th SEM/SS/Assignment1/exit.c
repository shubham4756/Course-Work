#include<stdio.h>
#include<stdlib.h>
int main() {
    for (int i = 1;i<=10;i++) {
        printf("%d\n",i);
        if(i==5) {
            exit(0);
        }
    }
    return 0;
}