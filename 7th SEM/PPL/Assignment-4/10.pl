con_cat([],L2,L2).

con_cat([H|T],L2,[H|L3]):-
    con_cat(T,L2,L3).

insertSort([H|List], Result) :-
    insertSort(List, Temp),
    printlist(Temp),
    insertItem(H, Temp, Result).

insertSort([], []).

insertItem(X, [H|List], [H|Result]) :-
    H < X, !,
    insertItem(X, List, Result).

insertItem(X, List, [X|List]).

printlist([]) :-
    nl.

printlist([X|List]) :- write(X),write(" "), printlist(List).
