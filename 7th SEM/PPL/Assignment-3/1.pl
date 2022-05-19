start:-
    write("Enter op1 "),
    read(X),
    write("Enter op2 "),
    read(Y),
    write("Enter operator"),nl,
    write("1 for Addition"),nl,
    write("2 for Substration"),nl,
    write("3 for Multiplication"),nl,
    write("4 for Division"),nl,
	write("5 for Integer division"),nl,
	write("6 for Power"),nl,
	write("7 for Modulus "),
    read(O),
    process(X,Y,O),
    start.

process(X,Y,O) :-
    O =:= 1,
    Z is X + Y,
    write('Addition of '), write(X), write(" and "), write(Y), write(" is "), write(Z), nl.

process(X,Y,O) :-
    O =:= 2,
    Z is X - Y,
    write('Substraction of '), write(X), write(" and "), write(Y), write(" is "), write(Z), nl.

process(X,Y,O) :-
    O =:= 3,
    Z is X * Y,
    write('Multiplication of '), write(X), write(" and "), write(Y), write(" is "), write(Z), nl.

process(X,Y,O) :-
    O =:= 4,
    Z is X / Y,
    write('Division of '), write(X), write(" and "), write(Y), write(" is "), write(Z), nl.

process(X,Y,O) :-
    O =:= 5,
    Z is X // Y,
    write('Integer Division of '), write(X), write(" and "), write(Y), write(" is "), write(Z), nl.

process(X,Y,O) :-
    O =:= 6,
    Z is X ** Y,
    write('Power of '), write(X), write(" and "), write(Y), write(" is "), write(Z), nl.

process(X,Y,O) :-
    O =:= 7,
    Z is X mod Y,
    write('Modulus of '), write(X), write(" and "), write(Y), write(" is "), write(Z), nl.










