#include <bits/stdc++.h>
using namespace std;
class ResourcesStatus
{
public:
    int statusRef[3][3];
    ResourcesStatus()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                statusRef[i][j] = 0;
            }
        }
    }
    ResourcesStatus(int a[3][3])
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                statusRef[i][j] = a[i][j];
            }
        }
    }
    vector<int> processStatusCount()
    {
        int free = 0, occupied = 0, inaccessible = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (statusRef[i][j] == 0)
                {
                    free++;
                }
                else if (statusRef[i][j] == 1)
                {
                    occupied++;
                }
                else if (statusRef[i][j] == 2)
                {
                    inaccessible++;
                }
            }
        }
        cout << "Total free resources are : " << free << endl;
        cout << "Total occupied resources are : " << occupied << endl;
        cout << "Total inaccessible resources are : " << inaccessible << endl;
        return {free, occupied, inaccessible};
    }
    void displayStatus()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                cout << statusRef[i][j] << " ";
            }
            cout << endl;
        }
    }
    void setStatus(int i, int j, int status)
    {
        statusRef[i][j] = status;
        vector<int> v = processStatusCount();
        if (v[1] > v[0])
        {
            string msg = "Invalid status value as occupied resources are more than free resources ";
                for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (statusRef[i][j] == 2)
                    {
                        statusRef[i][j] = 0;
                    }
                }
            }
            throw msg;
        }
    }
};
int main()
{
    int a[3][3];
    cout << "Enter the status of resources" << endl;
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            cin >> a[i][j];
        }
    }
    ResourcesStatus rs(a);
    while (1)
    {
        cout << "1. Display Status" << endl;
        cout << "2. Set Status" << endl;
        cout << "3. Exit" << endl;
        int choice;
        cin >> choice;
        switch (choice)
        {
        case 1:
            rs.displayStatus();
            break;
        case 2:
            int i, j, status;
            cout << "Enter row and column of the resource" << endl;
            cin >> i >> j;
            cout << "Enter status of the resource" << endl;
            cin >> status;
            try
            {
                rs.setStatus(i, j, status);
            }
            catch (string msg)
            {
                cout << msg << endl;
            }
            break;
        case 3:
            return 0;
        default:
            cout << "Invalid choice" << endl;
        }
    }
}