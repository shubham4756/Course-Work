patient(p1,darshil, address(swami, surat, 395007), [treatment(d1, flu), treatment(d2,commoncold), treatment(d4, measles)]).
patient(p2,smit, address(gajjar, delhi, 375012), [treatment(d2, chickenpox), treatment(d3,commoncold), treatment(d4, flu)]).
patient(p3,shikhar, address(bhabha, surat, 395091), [treatment(d3, measles), treatment(d1, flu), treatment(d4, chickenpox)]).

symptom(flu, [fever, headache, body_ache, conjunctivitis, chills, sore_throat, runny_nose, cough]).
symptom(commoncold, [headache, sneezing, sore_throat, runny_nose, chills]).
symptom(chickenpox, [fever, chills, body_ache, rash]).
symptom(measles, [cough, sneezing, runny_nose]).

/* find total number of diseases for each patient */
total_diseases(PatientId,TotalDiseases) :-
    patient(PatientId,_,_,Treatments),
    length(Treatments,TotalDiseases).

/* Find the name and zip code of each patient. */
name_and_zip(PatientId,Name,Zip) :-
    patient(PatientId,Name,address(_,_,Zip),_).

/* Write P_Id and name of all patients staying in Delhi. */
surat_patients(P_Id,Name) :-
    patient(P_Id,Name,address(_,surat,_),_).

/* List name of all patients treated by doctor D1. */
doctor_patients(Name) :-
    patient(_,Name,_,Treatments),
    member(treatment(d1,_),Treatments).

/* List roll no. of all patients suffering from Common cold */
common_cold_patients(P_Id) :-
    patient(P_Id,_,_,Treatments),
    member(treatment(_,commoncold),Treatments).

/* List building_name and city_code for all patients in the given format (format: [(building_name, citycode)]). */
patient_address(X):-
    findall(
        (Building_name,City_code),
        (
            patient(_,_,address(Building_name,City_code,_),_)
        ),
        X
    ).

/* List all doctors for each given patient. */
patient_doctors(P_Id,Doctors) :-
    patient(P_Id,_,_,Treatments),
    findall(D,member(treatment(D,_),Treatments),Doctors).















