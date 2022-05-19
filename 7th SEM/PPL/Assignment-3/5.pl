evaluate_grade :-
    write("Enter marks: "),
    read(X),
    check(X,R),
    write("Your Grade is "),write(R), nl.
    
check( X , 'A') :- X =< 100, X >= 80.
check( X , 'B') :- X >= 60, X < 80.
check( X , 'C') :- X >= 35, X < 60.
check( X , 'D') :- X >= 1, X < 35.