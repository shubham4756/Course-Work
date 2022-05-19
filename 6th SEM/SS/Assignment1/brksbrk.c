// brk() sbrk()
#include <unistd.h>
int main() {
    int *p = (int *)sbrk(0);
    brk (p + 4);
    *p = 1;
    return 0;
}