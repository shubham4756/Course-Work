#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <sys/types.h>
int main(){
    int pid = getpid(), x = 1;
    printf("PID: %d\n", pid);
    while (x++)
    {
        printf("Running...\n");
        sleep(1);
        if (x == 5)
            kill(pid, SIGINT);
    }
    return 0;
}