#include <stdio.h>
#include <unistd.h>
int main(void) {
    for (int i = 0; i < 10; i++) {
        printf("%d\n",i);
        if (i == 5)
            pause();
    }
    return 0;
}