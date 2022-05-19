patient(p21, shubham, address(building1, delhi, 395001), [treatment(d1, flu), treatment(d2,commoncold)] ). 
patient(p22, sagar, address(building2, delhi, 395004), [treatment(d2, commoncold), treatment(d3,chickenpox)] ). 
patient(p23, bhavik, address(building3, surat, 395007), [treatment(d1, chickenpox), treatment(d4,measles)] ). 

% flattens list of lists into lists
flatten([],[]).
flatten([H|T],W):-flatten(T,W1), append(W1,H,W). 

% remove duplicates from List1 and form List2
remove_duplicates([],[]).
remove_duplicates([H|T], [H|T2]):- not(member(H,T)), remove_duplicates(T,T2).
remove_duplicates([H|T], L2):- member(H,T), remove_duplicates(T,L2).


%q1
get_all_diseases(Diseases):- findall(X,patient(_,_,_,X),L),flatten(L,S),remove_duplicates(S,Diseases).
number_of_diseases:-patient(_,X,_,Y), length(Y,L), write(X), write(": "), write(L).

%q2
city_zip:- patient(_,Name,address(_,_,Zip),_), write("("), write(Name), write(", "), write(Zip), write(")"), nl.

%q3
delhipatients:- patient(PID,Name,address(_,delhi, _),_), write("("), write(PID), write(", "), write(Name), write(")"), nl.

%q4
contains_doc1([]):-fail.
contains_doc1([treatment(d1,_)|_]):-!.
contains_doc1([_|T]):-contains_doc1(T).
patients_doc1:-
    patient(_,Name,_,X), 
    contains_doc1(X), write(Name), nl.


%q5
contains_cold([]):-fail.
contains_cold([treatment(_,commoncold)|_]):-!.
contains_cold([_|T]):-
    contains_cold(T).
common_cold_patient:- 
    patient(Id,_,_,X), 
    contains_cold(X), write(Id), nl.

%q6
building_code:- patient(_,_,address(Building, _, Code),_), write("("), write(Building), write(", "), write(Code), write(")"), nl.

%q7
extract_doctor([],[]).
extract_doctor([treatment(T,_)|Rest], [T|Tail]):-extract_doctor(Rest,Tail).
doctor_for_patients:- patient(_,Name,_,Y),extract_doctor(Y,Z), write(Name), write(": "), write(Z), nl.
