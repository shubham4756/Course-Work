#ifdef APPLE
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif
#include <stdlib.h> 
#include <bits/stdc++.h> 
using namespace std; 
#define PI 3.14159265 
void init(void) {
    glClearColor(0, 0, 0, 0);
}
void put_pixel(float r, float g, float b, double x, double y) {
    glColor3f(r, g, b);
    glVertex2d(x,y);
}
void translate_points(int dx, int dy) {
    double points[3][2] = {{20, 30}, {50, 30}, {40, 60}};
    for (int i = 0; i < 3; ++i) {
        put_pixel(1, 1, 1, points[i][0], points[i][1]);
    }
    double tMatrix[3][3] = {
        {1, 0, 0},
        {0, 1, 0},
        {dx, dy, 1}};
    double pointMatrix[3][3] = {{20, 30, 1}, {50, 30, 1}, {40, 60, 1}};
    double resMatrix[3][3] = {0};
    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 3; ++k) {
                resMatrix[i][j] += pointMatrix[i][k] * tMatrix[k][j];
            }
        }
    }
    for (int i = 0; i < 3; ++i) {
        put_pixel(1, 0, 1, resMatrix[i][0], resMatrix[i][1]);
    }
}
void scale_points(int sx, int sy) {
    double points[3][2] = {{-40, 10}, {-20, 10}, {-30, 20}};
    for (int i = 0; i < 3; ++i) {
        put_pixel(1, 1, 1, points[i][0], points[i][1]);
    }
    double sMatrix[3][3] = {
        {sx, 0, 0},
        {0, sy, 0},
        {0, 0, 1}};
    double pointMatrix[3][3] = {
        {-40, 10, 1},
        {-20, 10, 1},
        {-30, 20, 1}};
    double resMatrix[3][3] = {0};
    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 3; ++k) {
                resMatrix[i][j] += pointMatrix[i][k] * sMatrix[k][j];
            }
        }
    }
    // // // glColor3i(1, 0, 0); for
    (int i = 0; i < 3; ++i) {
        put_pixel(1, 1, 0, resMatrix[i][0], resMatrix[i][1]);
    }
}
void rotate_points(double degree) {
    double points[3][2] = {{-20, -30}, {0, -30}, {-10, -10}};
    for (int i = 0; i < 3; ++i)
    {
        put_pixel(1, 1, 1, points[i][0], points[i][1]);
    }
    double rMatrix[3][3] = {
        {cos(degree * PI / 180), sin(degree * PI / 180), 0}, {sin(-1 * degree * PI / 180), cos(degree * PI / 180), 0}, {0, 0, 1}};
    double pointMatrix[3][3] = {
        {-20, -30, 1},
        {0, -30, 1},
        {-10, -10, 1}};
    double resMatrix[3][3] = {0};
    for (int i = 0; i < 3; ++i)
    {
        for (int j = 0; j < 3; ++j)
        {
            for (int k = 0; k < 3; ++k)
            {
                resMatrix[i][j] += pointMatrix[i][k] * rMatrix[k][j];
            }
        }
    }
    // // glColor3i(0, 0, 1); for
    (int i = 0; i < 3; ++i)
    {
        put_pixel(0, 1, 1, resMatrix[i][0], resMatrix[i][1]);
    }
}
void display()
{
    glClear(GL_COLOR_BUFFER_BIT);
    glLoadIdentity();
    glPointSize(3.0);
    glBegin(GL_TRIANGLES);
    translate_points(-20, -35);
    scale_points(2, 2);
    rotate_points(45.0);
    glEnd();
    glFlush();
}
void reshape(int w, int h)
{
    glViewport(0, 0, w, h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-100,100,-100, 100);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
}
int main(int argc, char **argv)
{
    glutInit(&argc, argv);
    glutInitWindowPosition(200, 100);
    glutInitWindowSize(500, 500);
    glutInitDisplayMode(GLUT_RGB);
    glutCreateWindow("Transformations");
    init();
    glutDisplayFunc(display);
    glutReshapeFunc(reshape);
    glutMainLoop();
}
