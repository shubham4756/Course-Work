maximum_no([X],X).

maximum_no([H|T],Max):-
    maximum_no(T,Max),
    H < Max.

maximum_no([Max|T],Max):-
    maximum_no(T,M),
    M < Max.

minimum_no([X],X).

minimum_no([H|T],Min):-
    minimum_no(T,Min),
    H > Min.

minimum_no([Min|T],Min):-
    minimum_no(T,M),
    M > Min.
