#include <stdio.h>
#include <windows.h>
#include <iostream> #include <GL/glut.h> using namespace std;
int r, rx, ry, xc, yc;

void plotEllipse1(int x, int y) {
    glBegin(GL_POINTS);
    glVertex2i(x + xc, y + yc);
    glEnd();
    glFlush();
}

void plotEllipse2(int x, int y) {
    glBegin(GL_POINTS);
    glVertex2i(x + xc, y + yc);
    glEnd();
    glFlush();
}

void myInit(void) {
    glClearColor(0.0, 0.0, 0.0, 1.0);
    glColor3f(1.0, 0.0, 0.0);
    glPointSize(5.0);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0.0, 640.0, 0.0, 480.0);
}

void midPointEllipseAlgo() {
    float dx, dy, d1, d2, x, y;
    x = 0;
    y = ry;
    // Initial decision parameter of region 1
    d1 = (ry * ry) - (rx * rx * ry) + (0.25 * rx * rx);
    dx = 2 * ry * ry * x;
    dy = 2 * rx * rx * y; // For region 1
    while (dx < dy) {
        // Print points based on 4-way symmetry
        plotEllipse1(x, y);
        plotEllipse1(-x,
                     y);
        plotEllipse1(x, -y);
        plotEllipse1(-x, -y);
        plotEllipse2(y, x);
        plotEllipse2(-y,
                     x);
        plotEllipse2(y, -x);
        plotEllipse2(-y, -x);
        // Checking and updating value of // decision parameter based on algorithm
        if (d1 < 0) {
            x++;
            dx = dx + (2 * ry * ry);
            d1 = d1 + dx + (ry * ry);
        }
        else {
            x++;
            y--;
            dx = dx + (2 * ry * ry);
            dy = dy - (2 * rx * rx);
            d1 = d1 + dx - dy + (ry * ry);
        }
    }
    // Decision parameter of region 2
    d2 = ((ry * ry) * ((x + 0.5) * (x + 0.5))) +
         ((rx * rx) * ((y - 1) * (y - 1))) -
         (rx * rx * ry * ry); // Plotting points of region 2
    while (y >= 0) {
        // Print points based on 4-way symmetry
        plotEllipse1(x, y);
        plotEllipse1(-x,
                     y);
        plotEllipse1(x, -y);
        plotEllipse1(-x, -y);
        plotEllipse2(y, x);
        plotEllipse2(-y,
                     x);
        plotEllipse2(y, -x);
        plotEllipse2(-y, -x);
        // Checking and updating parameter // value based on algorithm
        if (d2 > 0) {
            y--;
            dy = dy - (2 * rx * rx);
            d2 = d2 + (rx * rx) - dy;
        }
        else {
            y--;
            x++;
            dx = dx + (2 * ry * ry);
            dy = dy - (2 * rx * rx);
            d2 = d2 + dx - dy + (rx * rx);
        }
    }
}
void launcher(int argc, char **argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(640, 480);
    glutInitWindowPosition(200, 200);
    glutCreateWindow("Mid-Point Drawing Algorithms");
    glutDisplayFunc(midPointEllipseAlgo);
    myInit();
    glutMainLoop();
}
int main(int argc, char **argv) {
    cout << "Enter the coordinates for the ellipse" << endl;
    cout << "Radius x : ";
    cin >> rx;
    cout << "Radius y : ";
    cin >> ry;
    cout << "x Center : ";
    cin >> xc;
    cout << "y Center : ";
    cin >> yc;
    launcher(argc, argv);
    return 0;
}
