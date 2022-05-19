%{#include<stdio.h>
int count1 = 0, count2;
%}

%x C
%%
"/*"][.]*"*/"	{count2++;}
"/*"		{BEGIN C;}
<C>"*/"		{BEGIN 0; count2++;}
<C>\n		{;}
<C>.		{;}
\/\/.*		{count1++;}
%%

void main()
{
	char file[] = "data.c";
	yyin = fopen(file , "r");
	yylex();
	printf("\nNumber of comment in c file %s\nSingle line : %d\nMultiple line : %d\n", file, count1, count2);
}

int yywrap()
{
	return 1;
}
