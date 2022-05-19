#include <bits/stdc++.h>
#include <graphics.h>
using namespace std;
void dda(float x0, float y0, float x1, float y1)
{
    float i;
    float x, y, dx, dy, steps;
    dx = (float)(x1 - x0);
    dy = (float)(y1 - y0);
    if (dx >= dy)
    {
        steps = dx;
    }
    else
    {
        steps = dy;
    }
    dx = dx / steps;
    dy = dy / steps;
    x = x0;
    y = y0;
    i = 1;
    while (i <= steps)
    {
        putpixel(x, y, WHITE);
        x += dx;
        y += dy;
        i = i + 1;
    }
}
void draw(float x, float y, int f, float cx, float cy, float xx, float yy)
{
    float v1x = xx - cx, v1y = yy - cy;
    float v2x = x - cx, v2y = y - cy;
    float angle = atan2(v1x * v2y - v2x * v1y, v1x * v2x + v1y * v2y);
    if (f == 0)
    { // down
        if (angle > 0)
        {
            putpixel(x, y, WHITE);
        }
    }
    else
    { // up
        if (angle < 0)
            putpixel(x, y, WHITE);
    }
}
void halfCircle(float a, float b, int f, float r, float xx, float yy)
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
        draw(x + a, y + b, f, a, b, xx, yy);
        draw(-x + a, y + b, f, a, b, xx, yy);
        draw(x + a, -y + b, f, a, b, xx, yy);
        draw(-x + a, -y + b, f, a, b, xx, yy);
        if (x != y)
        {
            draw(y + a, x + b, f, a, b, xx, yy);
            draw(-y + a, x + b, f, a, b, xx, yy);
            draw(y + a, -x + b, f, a, b, xx, yy);
            draw(-y + a, -x + b, f, a, b, xx, yy);
        }
    }
}
int main()
{
    int gdriver = DETECT, gmode;
    int midx, midy, i;
    initgraph(&gdriver, &gmode, "");
    cout << "Enter coordinates of the Point A and B\n";
    int x0, y0, x1, y1;
    cin >> x0 >> y0 >> x1 >> y1;
    dda(x0, y0, x1, y1);
    float mx = (x0 + x1) / 2, my = (y0 + y1) / 2;
    float m1x = (mx + x0) / 2, m1y = (my + y0) / 2;
    float r1 = sqrt(abs(mx - m1x) * abs(mx - m1x) + abs(my - m1y) * abs(my - m1y));
    float m2x = (mx + x1) / 2, m2y = (my + y1) / 2;
    float r2 = sqrt(abs(mx - m2x) * abs(mx - m2x) + abs(my - m2y) * abs(my - m2y));
    halfCircle(m1x, m1y, 0, r1, mx, my);
    halfCircle(m2x, m2y, 1, r2, x1, y1);
    getch();
    return 0;
}