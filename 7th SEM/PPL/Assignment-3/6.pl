isSquare(X, Y):-
    X =:= Y,
    write('Square').

isSquare(X, Y):-
    X \= Y,
    write('Not Square').
