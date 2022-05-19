list_length([],0).

list_length([_|A],B) :-
	list_length(A,C),
	B is C + 1.
