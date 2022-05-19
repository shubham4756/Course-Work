#include <stdio.h>
#include <conio.h>
#include <graphics.h>
#include <stdlib.h>
int main()
{
    int gr = DETECT, gm;
    int i, x, y, j;
    initgraph(&gr, &gm, "C:\\TURBOC3\\BGI");
    // man
    for (j = 1; j <= getmaxx(); j = j + 5)
    {
        line(0, 400, 800, 400);
        setfillstyle(SOLID_FILL, YELLOW);
        circle(30 + j, 280, 20); //head
        floodfill(32 + j, 280, WHITE);
        line(30 + j, 300, 30 + j, 350);  //body
        line(30 + j, 330, 70 + j, 330);  //right hand
        line(30 + j, 330, -10 + j, 330); //left hand
        if (j % 2 == 0)
        {
            line(30 + j, 350, 35 + j, 400); //left leg
            line(30 + j, 350, 10 + j, 400); // right
        }
        else
        {
            line(30 + j, 350, 35 + j, 400); //transition
            delay(20);
        }
        delay(170);
        cleardevice();
    }
    getch();
    closegraph();
    return 0;
}
