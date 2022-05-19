create(L1):-
    read(Elem),
    create(Elem,L1).

create(-1,[]):-!.

create(Elem,[Elem|T]):-read(Next),create(Next,T).

go:- write('Creating a list'),
    nl,
    write('Enter - 1 to stop'),
    nl,
    create(L),
    write('List is:'),
    write(L).
