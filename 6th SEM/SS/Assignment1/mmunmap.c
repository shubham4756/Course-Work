//mmap() munmap()
#include <fcntl.h>
#include <stdio.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
int main() {
    int fd = open("./input.txt", O_RDONLY);
    struct stat st;
    fstat(fd, &st);
    int size = st.st_size;
    char *data = (char *)mmap(NULL, size, PROT_READ, MAP_PRIVATE, fd, 0);
    printf("%s", data);
    munmap(data, size);
    return 0;
}