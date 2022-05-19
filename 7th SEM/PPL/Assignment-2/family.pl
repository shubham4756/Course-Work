female(mary).
female(sandra).
female(juliet).
female(lisa).
female(lily).

male(peter).
male(paul).
male(dick).
male(bob).
male(harry).

parent(peter, dick).
parent(dick, lily).
parent(paul, peter).
parent(paul, harry).
parent(peter, juliet).
parent(mary, dick).
parent(juliet, lily).
parent(sandra, peter).
parent(sandra, harry).
parent(mary, juliet).

husband(peter, mary).
husband(paul, sandra).
husband(dick, juliet).
husband(bob, lisa).


child(X, Y) :-
    parent(Y, X).

mother(X, Y) :-
    female(X),
    parent(X, Y).

father(X, Y) :-
    male(X),
    parent(X, Y).

wife(X, Y) :-
    husband(Y, X).

son(X, Y) :-
    male(X),
    parent(Y, X).

daughter(X, Y) :-
    female(X),
    parent(Y, X).

brother(X, Y) :-
    male(X),
    parent(Z, X),
    parent(Z, Y),
    X \= Y.

sister(X, Y) :-
    female(X),
    parent(Z, X),
    parent(Z, Y),
    X \= Y.

uncle(X, Y) :-
    male(X),
    parent(Y, Z),
    brother(Z, X).

uncle(X, Y) :-
    male(X),
    parent(Y, Z),
    sister(Z, X).

aunt(X, Y) :-
    husband(Z, X),
    uncle(Z, Y).

nephew(X, Y) :-
    male(X),
    uncle(Y, X).

nephew(X, Y) :-
    male(X),
    aunt(Y, X).

niece(X, Y) :-
    female(X),
    uncle(Y, X).

niece(X, Y) :-
    female(X),
    aunt(Y, X).


sisterInLow(X,Y) :-
    husband(Y,Z),
    sister(X,Z).



