memberElement(X,[X|_]).

memberElement(X,[_|T]):-
    memberElement(X,T).
