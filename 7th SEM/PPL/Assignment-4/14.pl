numbers(L1,L2,L3):-
    findall(X,(member(X,L1), X mod 2=:=0),L2),
    findall(X,(member(X,L1), X mod 2 =\= 0),L3).
