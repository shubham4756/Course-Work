#include <stdio.h>
void setx(int *x, int *y) /*@modifies *x@*/
{
    *y = *x;
}
void sety(int *x, int *y) /*@modifies *y@*/
{
    setx(y, x);
}
int main()
{
    int x = 5;
    int y = 3;
    printf("checking for modification\n");
    sety(&y, &x);
    return 0;
}
