%{
#include<stdio.h>
%}
%%
^[a-z][a-z0-9_]*(@[A-Za-z]+)(\.[a-z]+)+ {printf("valid");}
.* {printf("invalid");}
%%
int main()
{
yylex();
}

int yywrap()
{
	return 1;
}