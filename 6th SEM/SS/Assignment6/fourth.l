%{
#include<string.h>
#include<math.h>
void check(char *);
%}
%%
[0-9]+ check(yytext);
%%
int main()
{
    extern FILE *yyin;
    yyin=fopen("num","r");
    yylex();
}
void check(char *a)
{
    int len=strlen(a),i,num=0;
    for(i=0;i<len;i++)
        num=num*10+(a[i]-'0');
    int x=0,temp=num;
    while(num>0)
    {
        int tt = 1;
        int dd = num%10;
        for(int i = 0; i<len;i++) {
            tt *= dd;
        }
        x=x+tt;
        num=num/10;
    }
    if(x==temp)
        printf("%d is armstrong \n",temp);
    else
        printf("%d is not armstrong \n",temp);
}

int yywrap()
{
	return 1;
}