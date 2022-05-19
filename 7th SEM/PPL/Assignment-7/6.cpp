#include <bits/stdc++.h>
using namespace std;
template <class T>
class Stack
{
private:
    int top;
    int capacity;
    T *array;

public:
    Stack(int capacity)
    {
        this->capacity = capacity;
        top = -1;
        array = new T[capacity];
    }
    bool isFull()
    {
        return top == capacity - 1;
    }
    bool isEmpty()
    {
        return top == -1;
    }
    void push(T data)
    {
        if (isFull())
        {
            cout << "Stack is full" << endl;
            return;
        }
        array[++top] = data;
    }
    T pop()
    {
        if (isEmpty())
        {
            cout << "Stack is empty" << endl;
            return -1;
        }
        return array[top--];
    }
    T peek()
    {
        if (isEmpty())
        {
            cout << "Stack is empty" << endl;
            return -1;
        }
        return array[top];
    }
};
int main()
{
    int n;
    cout << "Enter the number of elements in the stack: ";
    cin >> n;
    Stack<int> s(n);
    while (1)
    {
        cout << "1. PUSH" << endl;
        cout << "2. POP" << endl;
        cout << "3. PEEK" << endl;
        cout << "4. EXIT" << endl;
        int choice;
        cin >> choice;
        switch (choice)
        {
        case 1:
        {
            int data;
            cout << "Enter the data to be pushed: ";
            cin >> data;
            s.push(data);
            break;
        }
        case 2:
        {
            cout << "Popped element: " << s.pop() << endl;
            break;
        }
        case 3:
        {
            cout << "Peeked element: " << s.peek() << endl;
            break;
        }
        case 4:
        {
            return 0;
        }
        default:
        {
            cout << "Invalid choice" << endl;
            break;
        }
        }
    }
    return 0;
}