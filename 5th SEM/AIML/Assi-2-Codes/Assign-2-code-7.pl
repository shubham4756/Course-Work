drop(L1,N,L2) :- drop(L1,N,L2,N).

drop([],_,[],_).
drop([_|P],N,Q,1):- drop(P,N,Q,N).
drop([X|P],N,[X|Q],K):-
    K >1,
    K1 is K - 1,
    drop(P,N,Q,K1).

