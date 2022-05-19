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
    glBegin(GL_LINE_LOOP); // Change argument for different shapes glVertex2f(0, 0);
    glVertex2f(150, 0);
    glVertex2f(225, 105);
    glVertex2f(150, 210);
    glVertex2f(0, 210);
    glVertex2f(-75, 105);
    glEnd();
    glFlush();
}
int main(int argc, char **argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(683, 384);
    glutInitWindowPosition(0, 0);
    glutCreateWindow("Points");
    init();
    glutDisplayFunc(display);
    glutMainLoop();
    return 0;
}