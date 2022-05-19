#include <stdio.h>
struct Point3D
{
    int x, y, z;
};
int main()
{
    struct Point3D point = {.y = 0, .z = 1};
    return 0;
}