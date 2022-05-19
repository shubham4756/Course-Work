#include <stdio.h>
int glob1, glob2;
int f(void) /*@globals glob1;@*/
{
    return glob2;
}
int main()
{
    int p = f();
    printf("checking for global variables\n");
    return 0;
}