compress([],[]).
compress([X],[X]).
compress([X,X|Y],Z) :- compress([X|Y],Z).
compress([X,Y|Z],[X|P]) :-
    X\=Y,
    compress([Y|Z],P).




