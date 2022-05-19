count :-
   write('Write a number: '),
   read(Number),
   process(Number).
process(time) :- statistics, nl, count.
process(exit) :- halt.
process(stop) :- !.
process(Number) :-
   C is Number * Number,
   write('Square of '),write(Number),write(': '),write(C),nl, count.