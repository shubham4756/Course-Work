len([], Ans):-
    Ans is 0.

len([_|Y], Ans):-
    len(Y, L),
    Ans is L + 1.
