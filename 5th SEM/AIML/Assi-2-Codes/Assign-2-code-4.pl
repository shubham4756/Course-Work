concate([],List,List).

concate([X|List1],List2,[X|List3]) :-
    concate(List1,List2,List3).

rev([],[]).
rev([X|Tail],List) :-
    rev(Tail,Tail1),
    concate(Tail1,[X],List).

palindrome(List) :-
        reverse(List,List).









