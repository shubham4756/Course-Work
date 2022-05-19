%{
#include<stdio.h>
int lines=0, words=0,total=0;
%}
%%


 
\n { lines++; words++;}
[\t ' '] words++;
. total++;
%%





int main(void)
{
    char s[100];
    printf( "Enter a file name :");
   gets( s );

yyin= fopen(s,"r");
yylex();
printf(" This File contains ...");
printf("\n\t%d lines", lines);
printf("\n\t%d words",words);
printf("\n\t%d characters.\n",total);
}
 
int yywrap()
{
return(1);
}