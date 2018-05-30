
run_candidate_elim(Str) :- candidate_elim([[_,_,_,_,_]], [], 
    [[small, medium, large], [light, medium, heavy], [red, blue, white], [paper, wood, metal], [triangle, rectangle, circle]], Str).

candidate_elim([],[],_,_) :-
    write("There is no concept matching the examples"), nl.
    
candidate_elim([G],[S],_,_) :-
    covers(G,S),covers(S,G),
    write("target concept is "), write(G),nl.

candidate_elim(G, S, Types,Str) :-
    read(Str, Instance), Instance \= end_of_file,
    write("G= "), write(G), nl,
    write("S= "), write(S), nl,
    write("Reading instance..."), nl,
    write(Instance), nl,
    process(Instance, G, S, Updated_G, Updated_S, Types),
    candidate_elim(Updated_G, Updated_S, Types, Str).
    
candidate_elim(_,_,_,_) :-
    write("Nup"), nl.
    
main :-
    open('file.txt', read, Str),
    run_candidate_elim(Str),
    close(Str),
    write(X), nl,
    write(Y), nl.

read_file(Stream,[]) :-
    at_end_of_stream(Stream).

read_file(Stream,[X|L]) :-
    \+ at_end_of_stream(Stream),
    read(Stream,X),
    read_file(Stream,L).

process(negative(Instance), G, S, Updated_G, Updated_S, Types) :- 
    delete(X, S, covers(X, Instance), Updated_S),
    specialize_set(G,Spec_G, Instance, Types),
    delete(X, Spec_G, (member(Y, Spec_G), more_general(Y, X)), Pruned_G),
    delete(X, Pruned_G, (member(Y, Updated_S), not(covers(X, Y))), Updated_G).

process(positive(Instance), G, [], Updated_G, [Instance],_):- 
    delete(X, G, not(covers(X, Instance)), Updated_G).

process(positive(Instance), G, S, Updated_G, Updated_S,_) :- 
    delete(X, G, not(covers(X, Instance)), Updated_G),
    generalize_set(S,Gen_S, Instance),
    delete(X, Gen_S, (member(Y, Gen_S), more_general(X, Y)), Pruned_S),
    delete(X, Pruned_S, not((member(Y, Updated_G), covers(Y, X))), Updated_S).

process(Input, G, P, G, P,_):- 
    Input \= positive(_),
    Input \= negative(_),
    write("Enter either positive(Instance) or negative(Instance) "), nl.
    
specialize_set([], [], _, _).

specialize_set([Hypothesis|Rest],Updated_H,Instance, Types):-	
    covers(Hypothesis, Instance),			
    (bagof(Hypothesis, specialize(Hypothesis, Instance, Types), Updated_head); Updated_head = []),
    specialize_set(Rest, Updated_rest, Instance, Types),
    append(Updated_head, Updated_rest, Updated_H).

specialize_set([Hypothesis|Rest],[Hypothesis|Updated_rest],Instance, Types) :-
    not(covers(Hypothesis, Instance)),			
    specialize_set(Rest,Updated_rest, Instance, Types). 
    
specialize([Prop|_], [Inst_prop|_], [Instance_values|_]):-
    var(Prop),
    member(Prop, Instance_values),
    Prop \= Inst_prop.

specialize([_|Tail], [_|Inst_tail], [_|Types]):-
    specialize(Tail, Inst_tail, Types).

generalize_set([],[],_).

generalize_set([Hypothesis|Rest],Updated_H,Instance):-			
    not(covers(Hypothesis, Instance)),
    (bagof(X, generalize(Hypothesis, Instance, X), Updated_H); Updated_head = []),
    generalize_set(Rest,Updated_rest, Instance),
    append(Updated_head, Updated_rest, Updated_H).

generalize_set([Hypothesis|Rest],[Hypothesis|Updated_rest],Instance):-
    covers(Hypothesis, Instance),
    generalize_set(Rest,Updated_rest, Instance).

generalize([],[],[]).
generalize([Feature|Rest], [Inst_prop|Rest_inst], [Feature|Rest_gen]) :-
    not(Feature \= Inst_prop),
    generalize(Rest, Rest_inst, Rest_gen).

generalize([Feature|Rest], [Inst_prop|Rest_inst], [_|Rest_gen]) :-
    Feature \= Inst_prop,
    generalize(Rest, Rest_inst, Rest_gen).

more_general(X, Y) :- not(covers(Y, X)), covers(X, Y).

covers([],[]).
covers([H1|T1], [H2|T2]) :-
    var(H1), var(H2), 
    covers(T1, T2).
covers([H1|T1], [H2|T2]) :-
    var(H1), atom(H2), 
    covers(T1, T2).	
covers([H1|T1], [H2|T2]) :-
    atom(H1), atom(H2), H1 = H2,
covers(T1, T2).

delete(X, L, Goal, New_L) :-
    (bagof(X, (member(X, L), not(Goal)), New_L);New_L = []). 
