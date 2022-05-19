duplicate([H], 1, [H]).

duplicate([H], N, [H|X]) :-
        M is N - 1,
        M > 0,
        duplicate([H], M, X).

duplicate([H|T], N, X) :-
      duplicate([H], N, Y),
      duplicate(T, N, Z),
      concate(Y, Z, X).





