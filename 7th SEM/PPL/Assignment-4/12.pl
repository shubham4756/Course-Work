find(1,[X|L],X).

find(N,[Y|L],X):-
    N1 is N-1,
    find(N1,L,X).
