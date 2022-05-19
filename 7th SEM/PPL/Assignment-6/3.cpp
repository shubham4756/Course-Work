#include <bits/stdc++.h>
using namespace std;
class student
{
public:
    int id_no;
    int grades_obtained[6];
    double SPI;
    void display()
    {
        cout << "\nID No. : " << id_no;
        cout << "\nGrades obtained : ";
        for (int i = 0; i < 6; i++)
        {
            cout << grades_obtained[i] << " ";
        }
        cout << "\nSPI : " << SPI;
    }
    void calculate_spi()
    {
        int sum = 0;
        for (int i = 0; i < 6; i++)
        {
            sum = sum + grades_obtained[i];
        }
        SPI = (double)sum / 3;
    }
};
int main()
{
    int n;
    cout << "Enter the number of students : ";
    cin >> n;
    student s[n];
    for (int i = 0; i < n; i++)
    {
        cout << "\nEnter the details of student " << i + 1 << " : ";
        cout << "\nID No. : ";
        cin >> s[i].id_no;
        cout << "Grades obtained : ";
        for (int j = 0; j < 6; j++)
        {
            cin >> s[i].grades_obtained[j];
        }
        s[i].calculate_spi();
    }
    cout << "\n\nDetails of students : ";
    for (int i = 0; i < n; i++)
    {
        s[i].display();
    }
    return 0;
}
