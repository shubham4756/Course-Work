#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#define OPSIZE 16
char *op1 = "output 1";
char *op2 = "output 2";
char *op3 = "output 3";
int main() {
    char inbuf[OPSIZE];
    int p[2], i;
    if (pipe(p) < 0)
        exit(EXIT_FAILURE);
    write(p[1], op1, OPSIZE);
    write(p[1], op2, OPSIZE);
    write(p[1], op3, OPSIZE);
    for (i = 0; i < 3; i++)
    {
        read(p[0], inbuf, OPSIZE);
        printf("%s\n", inbuf);
    }
    return 0;
}