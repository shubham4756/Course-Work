%{#include<stdio.h>
    /* Definition section */
%}
  
/* Rule Section */
%%
  
[+][9][1][1-9][0-9]{9} {printf("\nMobile Number Valid\n");}
  
.+ {printf("\nMobile Number Invalid\n");}
  
%%
  
// driver code 
int main() 
{
    printf("\nEnter Mobile Number : ");
    yylex();
    printf("\n");
}

int yywrap()
{
	return 1;
}