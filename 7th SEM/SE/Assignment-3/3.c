#include <stdio.h>
int square(/*@sef@*/ int x);
#define square(x) ((x) * (x))
int main()
{
    int i = 1;
    i = square(i++);
    i = square(i);
    return 0;
}