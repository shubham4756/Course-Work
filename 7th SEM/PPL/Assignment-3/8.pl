isNumber(X) :-
    X > 0,
    write('Number is Positive').

isNumber(X) :-
    X < 0,
    write('Number is Negetive').

isNumber(X) :-
    X =:= 0,
    write('Number is Zero').

