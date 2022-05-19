#include <bits/stdc++.h>
using namespace std;
class Rectangle
{
private:
    double width;
    double height;

public:
    Rectangle()
    {
        width = 1;
        height = 1;
    }
    Rectangle(double w, double h)
    {
        width = w;
        height = h;
    }
    double getArea()
    {
        return width * height;
    }
    double getPerimeter()
    {
        return 2 * (width + height);
    }
};
int main()
{
    Rectangle r1;
    Rectangle r2(2.2, 3.3);
    cout << "Area of r1: " << r1.getArea() << endl;
    cout << "Perimeter of r1: " << r1.getPerimeter() << endl;
    cout << "Area of r2: " << r2.getArea() << endl;
    cout << "Perimeter of r2: " << r2.getPerimeter() << endl;
    return 0;
}