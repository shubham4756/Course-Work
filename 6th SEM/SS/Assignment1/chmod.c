#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
int main() {
    mode_t mode = 0755;
    uid_t owner = 01000;
    uid_t group = 01000;
    chmod("./output.txt", mode);
    chown("./output.txt", owner, group);
    return 0;
}