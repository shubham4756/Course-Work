#include <stdio.h>
#include <graphics.h>
#include <conio.h>
#include <dos.h>
int main()
{
    int gd = DETECT, gm, i;
    initgraph(&gd, &gm, "C:\\TurboC3\\BGI");
    while (true)
    {
        //for moving circle from left to right,the following loop works
        for (i = 50; i <= getmaxx(); i++)
        {
            setfillstyle(HATCH_FILL, RED);
            circle(50 + i, 50, 50);
            floodfill(52 + i, 52, WHITE);
            delay(25);
            cleardevice();
        }
        //for moving circle from right to left, the following loop works
        for (i = getmaxx(); i >= 0; i--)
        {
            setfillstyle(HATCH_FILL, RED);
            circle(i, 50, 50);
            floodfill(i + 2, 52, WHITE);
            delay(25);
            cleardevice();
        }
    }
    getch();
    return 0;
}
