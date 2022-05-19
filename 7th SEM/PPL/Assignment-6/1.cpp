#include <bits/stdc++.h>
using namespace std;
class author
{
private:
    string author_name;

public:
    void get_author_name()
    {
        cout << "Enter the author name: ";
        cin >> author_name;
    }
    void display_author_name()
    {
        cout << "Author name: " << author_name << endl;
    }
};
class book_publication : public author
{
private:
    string title;

public:
    void get_title()
    {
        cout << "Enter the title of the book: ";
        cin >> title;
    }
    void display_title()
    {
        cout << "Title of the book: " << title << endl;
    }
};
class paper_publication : public author
{
private:
    string title;

public:
    void get_title()
    {
        cout << "Enter the title of the paper: ";
        cin >> title;
    }
    void display_title()
    {
        cout << "Title of the paper: " << title << endl;
    }
};
int main()
{
    book_publication b;
    paper_publication p;
    cout << "Book Publication" << endl;
    b.get_title();
    b.get_author_name();
    cout << "Paper Publication" << endl;
    p.get_title();
    p.get_author_name();
    author *a;
    cout << "\nAuthor of the book: " << endl;
    a = &b;
    a->display_author_name();
    cout << "\nAuthor of the paper: " << endl;
    a = &p;
    a->display_author_name();
}