list_length([],0).
list_length([_|A],B) :-
    list_length(A,C),
    B is C + 1.

concate([],List,List).
concate([X|List1],List2,[X|List3]) :-
    concate(List1,List2,List3).

split(L,0,[],L).
split([X|P],N,[X|Q],R):-
    N > 0,
    N1 is N - 1,
    split(P,N1,Q,R).

rotate_right(L1,N,L2):-
    list_length(L1,P),
    N2 is N mod P,
    N1 is P - N2,
    rotate_n(L1,N1,L2).

rotate_n(L,0,L).
rotate_n(L1,N,L2):-
    list_length(L1,NL1),
    N1 is N mod NL1,
    split(L1,N1,S1,S2),
    concate(S2,S1,L2).
