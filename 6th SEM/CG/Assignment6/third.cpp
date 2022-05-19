#ifdef APPLE
#include <GLUT/glut.h>
#else
#include <windows.h>
#include <GL/glut.h>
#endif
#include <stdlib.h> 
#include <bits/stdc++.h> 
using namespace std; 
#define PI 3.14159265 
typedef vector<vector<double>> (*ScriptReflFunction)(void); 
typedef vector<vector<double>> (*ScriptShearFunction)(int); 
typedef vector<vector<double>> (*ScriptRotateFunction)(double); 
typedef vector<vector<double>> (*ScriptRotateArbitrary)(double, int, int); 
typedef vector<vector<double>> (*ScriptDoubleParamFunction)(int, int); 
typedef unordered_map<string, ScriptReflFunction> script_map_refl; 
typedef unordered_map<string, ScriptShearFunction> script_map_shear; 
typedef unordered_map<string, ScriptRotateFunction> script_map_rot; 
typedef unordered_map<string, ScriptRotateArbitrary> script_map_arbitrary; 
typedef unordered_map<string, ScriptDoubleParamFunction> script_map_double; 

script_map_refl mappingReflection; 
script_map_shear mappingShear; 
script_map_rot mappingRot; 
script_map_double mappingDouble; 
script_map_arbitrary mappingArbitrary;

void put_pixel(float r, float g, float b, double x, double y)
{
    glColor3f(r, g, b);
    glVertex2d(x,y);
}
vector<vector<double>> multiplyMatrix(vector<vector<double>> a, vector<vector<double>> b)
{
    vector<vector<double>> resMatrix(3, vector<double>(3, 0));
    for (int i = 0; i < 3; ++i)
    {
        for (int j = 0; j < 3; ++j)
        {
            for (int k = 0; k < 3; ++k)
            {
                resMatrix[i][j] += a[i][k] * b[k][j];
            }
        }
    }
    return resMatrix;
}
vector<vector<double>> set_translation_matrix(int dx, int dy)
{
    vector<vector<double>> tMatrix{
        {1, 0, 0},
        {0, 1, 0},
        {(double)dx, (double)dy, 1}};
    return tMatrix;
}
vector<vector<double>> set_scaling_matrix(int sx, int sy)
{
    vector<vector<double>> sMatrix{
        {(double)sx, 0, 0},
        {0, (double)sy, 0},
        {0, 0, 1}};
    return sMatrix;
}
vector<vector<double>> set_rotate_matrix(double degree)
{
    vector<vector<double>> rMatrix{
        {cos(degree * PI / 180), sin(degree * PI / 180), 0},
        {sin(-1 * degree * PI / 180), cos(degree * PI / 180), 0},
        {0, 0, 1}};
    return rMatrix;
}
vector<vector<double>> set_rotate_point_matrix(double degree, int px, int py) {
    // T(-px, -py).R(degree).T(px, py) 
    vector<vector<double>> rPointMatrix(3, vector<double>(3, 0)); rPointMatrix = multiplyMatrix(set_translation_matrix(-1 * px, -1 * py), set_rotate_matrix(degree)); rPointMatrix = multiplyMatrix(rPointMatrix, set_translation_matrix(px, py));
    return rPointMatrix;
}
vector<vector<double>> set_shearX_matrix(int shx)
{
    vector<vector<double>> shearXMatrix{
        {1, 0, 0},
        {(double)shx, 1, 0},
        {0, 0, 1}};
    return shearXMatrix;
}
vector<vector<double>> set_shearY_matrix(int shy)
{
    vector<vector<double>> shearYMatrix{
        {1, (double)shy, 0},
        {0, 1, 0},
        {0,0, 1}};
    return shearYMatrix;
}
vector<vector<double>> set_reflection_xaxis()
{
    // y = 0
    vector<vector<double>> reflXMatrix{
        {1, 0, 0},
        {0, -1, 0},
        {0,0, 1}};
    return reflXMatrix;
}
vector<vector<double>> set_reflection_yaxis()
{
    vector<vector<double>> reflYMatrix{
        {-1, 0, 0},
        {0, 1, 0},
        {0, 0, 1}};
    return reflYMatrix;
}
vector<vector<double>> set_reflection_origin()
{
    vector<vector<double>> reflOriginMatrix{
        {-1, 0, 0},
        {0, -1, 0},
        {0,0, 1}};
    return reflOriginMatrix;
}
vector<vector<double>> set_reflection_line()
{
    // line => y = x
    vector<vector<double>> reflLineMatrix{
        {0, 1, 0},
        {1, 0, 0},
        {0,0, 1}};
    return reflLineMatrix;
}
vector<vector<double>> calc_composite_transformation(vector<string> sequence)
{
    vector<vector<double>> resMatrix(3, vector<double>(3, 0));
    for (auto seq : sequence)
    {
        auto itr1 = mappingRot.find(seq);
        auto itr2 = mappingDouble.find(seq);
        auto itr3 = mappingReflection.find(seq);
        auto itr4 = mappingArbitrary.find(seq);
        auto itr5 = mappingShear.find(seq);
        vector<vector<double>> tempMatrix(3,
                                          vector<double>(3, 0));
        if (itr1 !=
            mappingRot.end())
        {
            tempMatrix = (*itr1->second)(45);
        }
        if (itr2 != mappingDouble.end())
        {
            if (seq == "T")
                tempMatrix = (*itr2->second)(35, 30);
            if (seq == "S")
                tempMatrix = (*itr2 -
                              > second)(2, 2);
        }
        if (itr3 != mappingReflection.end())
        {
            tempMatrix = (*itr3->second)();
        }
        if (itr4 != mappingArbitrary.end())
        {
            tempMatrix = (*itr4->second)(45, 35, 30);
        }
        if (itr5 != mappingShear.end())
        {
            tempMatrix = (*itr5->second)(3);
        }
        if (seq == sequence.front())
            resMatrix = tempMatrix;
        if (seq != sequence.front())
            resMatrix = multiplyMatrix(resMatrix, tempMatrix);
    }
    return resMatrix;
}
void placePoints()
{
    vector<string> sequence = {"RAr", "S", "ReflX"};
    vector<vector<double>> res = calc_composite_transformation(sequence);
    vector<vector<double>>
        initialPoints{
            {{-20, 30}, {0, 30}, {-10, 40}}};
    vector<vector<double>> points{
        {{-20, 30, 1}, {0, 30, 1}, {-10, 40, 1}}};
    vector<vector<double>> finalPoints = multiplyMatrix(points, res);
    for (int i = 0; i < 3; ++i)
    {
        put_pixel(1, 1, 1, initialPoints[i][0], initialPoints[i][1]);
    }
    for (int i = 0; i < 3; ++i)
    {
        put_pixel(1, 0.4, 1, finalPoints[i][0], finalPoints[i][1]);
    }
}
void display()
{
    glClear(GL_COLOR_BUFFER_BIT);
    glLoadIdentity();
    glPointSize(3.0);
    glBegin(GL_TRIANGLES);
    placePoints();
    glEnd();
    glFlush();
}
void reshape(int w, int h)
{
    glViewport(0, 0, w, h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-100,100, -100, 100);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
}
void init(void)
{
    glClearColor(0, 0, 0, 0);
}
int main(int argc, char **argv)
{
    mappingReflection.emplace("ReflX", &set_reflection_xaxis);
    mappingReflection.emplace("ReflY", &set_reflection_yaxis);
    mappingReflection.emplace("ReflOrigin", &set_reflection_origin);
    mappingReflection.emplace("ReflLine", &set_reflection_line);
    mappingShear.emplace("ShX", &set_shearX_matrix);
    mappingShear.emplace("ShY", &set_shearX_matrix);
    mappingRot.emplace("R", &set_rotate_matrix);
    mappingDouble.emplace("T",&set_translation_matrix);
    mappingDouble.emplace("S",&set_scaling_matrix);
    mappingArbitrary.emplace("RAr",&set_rotate_point_matrix);
    glutInit(&argc, argv);
    glutInitWindowPosition(200,100);
    glutInitWindowSize(500, 500);
    glutInitDisplayMode(GLUT_RGB);
    glutCreateWindow("Composite Transformations");
    init();
    glutDisplayFunc(display);
    glutReshapeFunc(reshape);
    glutMainLoop();
}