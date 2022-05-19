#include <stdio.h>
int COUNT;
int main() /*@globals undef COUNT @*/
{
    int a;
    a = COUNT;
    return 0;
}