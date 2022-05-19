con_cat([],L2,L2).

con_cat([H|T],L2,[H|L3]):-
    con_cat(T,L2,L3).
