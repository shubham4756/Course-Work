find_roots(A,B,C):-
    D = B*B - 4*A*C,
    type_of_root(A,B,D).

type_of_root(A,B,D):-
    D < 0,
    write("The roots of the equation are imaginary."), nl,
    T is -D,
    Z is sqrt(T),
    X is -B/(2*A),
    Y is Z/(2*A),
    write("The roots of the equation are "),write(X),write("+i"),write(Y),nl,
    write("and "), write(X), write("-i"), write(Y),nl.

type_of_root(A,B,D):-
    D =:= 0,
    X is -B/(2*A),
    write("The roots are real and equal, they are X = "), write(X), nl.

type_of_root(A,B,D):-
    D > 0,
    Z is sqrt(D),
    X1 is (-B + Z)/(2*A),
    X2 is (-B - Z)/(2*A),
    write("The roots are real and unequal"),nl,
    write("The first root is "), write(X1),nl,
    write("The second root is "), write(X2),nl.
