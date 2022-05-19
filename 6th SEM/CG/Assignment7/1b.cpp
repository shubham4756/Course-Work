#include <stdio.h>
#include <graphics.h>
#include <math.h>
#include <conio.h>
#include <dos.h>
#define M_PI 3.14
int xc = 150, yc = 200, r = 60;
int x[15], y[15];
int main() {
    double angle = 0, theta;
    int i, a;
    int gd = DETECT, gm;
    initgraph(&gd, &gm, " ");
    while (!kbhit())
    {
        theta = M_PI * angle / 180;
        cleardevice();
        setfillstyle(SOLID_FILL, BROWN);
        line(150, 200, 130, 400);
        line(150, 200, 170, 400);
        line(130, 400, 170, 400);
        floodfill(152, 398, WHITE);
        for (i = 0; i < 4; i++) {
            theta = M_PI * angle / 180;
            x[i] = xc + r * cos(theta);
            y[i] = yc + r * sin(theta);
            angle += 90;
            line(xc, yc, x[i], y[i]);
        }
        angle += 15;
        for (i = 0; i < 4; i++) {
            theta = M_PI * angle / 180;
            x[i] = xc + r * cos(theta);
            y[i] = yc + r * sin(theta);
            angle += 90;
            line(xc, yc, x[i], y[i]);
        }
        angle += 15;
        delay(100);
    }
    getch();
    closegraph();
    return 0;
}
