isLeap(Y) :-
    Y mod 4 =:= 0,
    Y mod 100 =:= 0,
    Y mod 400 =:= 0.

isLeap(Y) :-
    Y mod 4 =:= 0,
    Z is Y mod 100,
    Z \= 0.
