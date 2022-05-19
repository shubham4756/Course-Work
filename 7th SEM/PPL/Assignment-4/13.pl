product([], 0).

product([H|T], P) :-
    product_1(T, H, P).

product_1([], P, P).

product_1([H|T], H0, P) :-
    product_1(T, H, P0),
    P is P0 * H0.
