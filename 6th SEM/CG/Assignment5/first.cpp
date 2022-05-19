#include <windows.h>
#include <stdio.h>
#include <GL/glut.h>
#include <math.h>
void init()
{
    glClearColor(1.0, 1.0, 1.0, 1.0);
    glColor3f(0.0, 0.0, 1.0);
    glPointSize(7.0);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-780, 780, -420, 420);
}
void display()
{
    glClear(GL_COLOR_BUFFER_BIT);
    glEnable(GL_POINT_SMOOTH);
    // Triangles
    glBegin(GL_TRIANGLES);
    glVertex2i(-600, 0 + 200);
    glVertex2i(-600, 50 + 200);
    glVertex2i(-600 + 100, 0 + 200);
    glVertex2i(-600 + 120, 10 + 200);
    glVertex2i(-600 + 15, 70 + 200);
    glVertex2i(-600 + 225, 70 + 200);
    glEnd();
    // Triangle Strip
    glBegin(GL_TRIANGLE_STRIP);
    glVertex2i(0 - 100, 100 + 180);
    glVertex2i(0 - 100, 0 + 180);
    glVertex2i(100 - 100, 100 + 180);
    glVertex2i(150 - 100, 0 + 180);
    glVertex2i(180 - 100, 100 + 180);
    glVertex2i(200 - 100, -20 + 180);
    glEnd();
    // Triangle FAN
    glBegin(GL_TRIANGLE_FAN);
    glVertex2i(0 + 450, 0 + 180);
    glVertex2i(0 + 450, 100 + 180);
    glVertex2i(70 + 450, 85 + 180);
    glVertex2i(100 + 450, 35 + 180);
    glVertex2i(100 + 450, -10 + 180);
    glEnd();
    glBegin(GL_QUADS);
    glVertex2i(0 - 600, 0 - 180);
    glVertex2i(20 - 600, 40 - 180);
    glVertex2i(100 - 600, 50 - 180);
    glVertex2i(110 - 600, -10 - 180);
    glVertex2i(130 - 600, -20 - 180);
    glVertex2i(140 - 600, 35 - 180);
    glVertex2i(210 - 600, 55 - 180);
    glVertex2i(170 - 600, -20 - 180);
    glEnd();
    glBegin(GL_QUAD_STRIP);
    glVertex2i(-10 - 100, 50 - 180);
    glVertex2i(0 - 100, 0 - 180);
    glVertex2i(60 - 100, 55 - 180);
    glVertex2i(60 - 100, 0 - 180);
    glVertex2i(90 - 100, 60 - 180);
    glVertex2i(110 - 100, 15 - 180);
    glVertex2i(150 - 100, 65 - 180);
    glVertex2i(155 - 100, 20 - 180);
    glEnd();
    glBegin(GL_POLYGON);
    glVertex2i(0 + 450, 0 - 150);
    glVertex2i(150 + 450, 20 - 150);
    glVertex2i(120 + 450, -25 - 150);
    glVertex2i(-10 + 450, -60 - 150);
    glVertex2i(-15 + 450, -30 - 150);
    glEnd();
    glFlush();
}
int main(int argc, char **argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(1200, 750);
    glutInitWindowPosition(100, 0);
    glutCreateWindow("Points");
    init();
    glutDisplayFunc(display);
    glutMainLoop();
    return 0;
}
