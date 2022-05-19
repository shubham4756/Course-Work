minmax:-
    write('Enter first value '),
    read(X),
    write('Enter second value '),
    read(Y),
    write('Enter third value'),
    read(Z),
    min(X, Y, Mina),
    min(Mina, Z, Min),
    max(X, Y, Maxa),
    max(Maxa, Z, Max),
    write('Minimum is '), write(Min), write(' Maximum is '), write(Max).

min(X, Y, X) :- X =< Y.
min(X, Y, Y) :- Y < X.

max(X, Y, X) :- X >= Y.
max(X, Y, Y) :- Y > X.





