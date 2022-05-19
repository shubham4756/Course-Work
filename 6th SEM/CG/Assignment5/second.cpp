#include <graphics.h>
#include <iostream>
using namespace std;
void midPointCircleDraw(float a, float b, float r)
{
    float x = r, y = 0;
    putpixel(x + a, y + b, WHITE);
    if (r > 0)
    {
        putpixel(x + a, -y + b, WHITE);
        putpixel(y + a, x + b, WHITE);
        putpixel(-y + a, x + b, WHITE);
    }
    float P = 1 - r;
    while (x > y)
    {
        y++;
        if (P <= 0)
            P = P + 2 * y + 1;
        else
        {
            x--;
            P = P + 2 * y - 2 * x + 1;
        }
        if (x < y)
            break;
        putpixel(x + a, y + b, WHITE);
        putpixel(-x + a, y + b, WHITE);
        putpixel(x + a, -y + b, WHITE);
        putpixel(-x + a, -y + b, WHITE);
        if (x != y)
        {
            putpixel(y + a, x + b, WHITE);
            putpixel(-y + a, x + b, WHITE);
            putpixel(y + a, -x + b, WHITE);
            putpixel(-y + a, -x + b, WHITE);
        }
    }
}
void midPointEllipseDraw(int xc, int yc, int rx, int ry)
{
    float dx, dy, d1, d2, x, y;
    x = 0;
    y = ry;
    d1 = (ry * ry) - (rx * rx * ry) + (0.25 * rx * rx);
    dx = 2 * ry * ry * x;
    dy = 2 * rx * rx * y;
    while (dx < dy)
    {
        putpixel(x + xc, y + yc, WHITE);
        putpixel(-x + xc, y + yc, WHITE);
        putpixel(x + xc, -y + yc, WHITE);
        putpixel(-x + xc, -y + yc, WHITE);
        if (d1 < 0)
        {
            x++;
            dx = dx + (2 * ry * ry);
            d1 = d1 + dx + (ry * ry);
        }
        else
        {
            x++;
            y--;
            dx = dx + (2 * ry * ry);
            dy = dy - (2 * rx * rx);
            d1 = d1 + dx - dy + (ry * ry);
        }
    }
    d2 = ((ry * ry) * ((x + 0.5) * (x + 0.5))) + ((rx * rx) * ((y - 1) * (y - 1))) -
         (rx * rx * ry * ry);
    while (y >= 0)
    {
        putpixel(x + xc, y + yc, WHITE);
        putpixel(-x + xc, y + yc, WHITE);
        putpixel(x + xc, -y + yc, WHITE);
        putpixel(-x + xc, -y + yc, WHITE);
        if (d2 > 0)
        {
            y--;
            dy = dy - (2 * rx * rx);
            d2 = d2 + (rx * rx) - dy;
        }
        else
        {
            y--;
            x++;
            dx = dx + (2 * ry * ry);
            dy = dy - (2 * rx * rx);
            d2 = d2 + dx - dy + (rx * rx);
        }
    }
}
int main()
{
    int gdriver = DETECT, gmode;
    initgraph(&gdriver, &gmode, " ");
    while (true)
    {
        cout << "1 Mid Point Circle generating algorithm\n";
        cout << "2 Mid Point Ellipse generating algorithm\n";
        cout << "3 Exit\n";
        int p = -1;
        cin >> p;
        if (p == 1)
        {
            cout << "Enter center x , y and radius space separated\n";
            int x0, y0, r;
            cin >> x0 >> y0 >> r;
            midPointCircleDraw(x0, y0, r);
            getch();
        }
        else if (p == 2)
        {
            cout << "Enter center x , y , xradius and yradius space separated\n";
            int x0, y0, xr, yr;
            cin >> x0 >> y0 >> xr >> yr;
            midPointEllipseDraw(x0, y0, xr, yr);
            getch();
        }
        else
        {
            break;
        }
    }
    return 0;
}
