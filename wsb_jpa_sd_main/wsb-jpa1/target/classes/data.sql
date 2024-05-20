INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization)
VALUES (1, 'Jan', 'Kowalski', '777777777', 'janK@gmail.com', 'doc1', 'C'),
       (2, 'Piotr', 'Nowicki', '123123123', 'piotr.nowicki@gmail.com', 'doc2', 'CARDIOLOGY');

INSERT INTO patient (first_name, last_name, date_of_birth, telephone_number, email, patient_number, doctor_id)
VALUES ('Maria', 'Kowalska', '2021-03-01', '123123123', 'mkowalska@gmail.com', 'PAT1', 1),
       ('Jacek', 'Andrzejewski', '2020-04-25', '123123123', 'jacek.andrzejewski@gmail.com', 'PAT2', 1),
       ('Piotr', 'Nowicki', '1899-08-30', '142141243', 'piotr.nowicki@gmail.com', 'PAT3', 2);

INSERT INTO medical_treatment (treatment_description, type)
VALUES ('MRI Scan', 'DIAGNOSIS');

INSERT INTO visit (description, time, doctor_id, patient_id, medical_treatment_id)
VALUES ('Routine Checkup', '2024-07-01 09:00:00', 1, 1, 1),
       ('Routine Checkup', '2024-08-01 13:00:00', 1, 2, 1),
       ('Consultation', '2024-08-15 15:00:00', 2, 3, 1),
       ('MRI Scan', '2024-09-01 08:00:00', 2, 3, 1);

INSERT INTO address (address_line1, address_line2, city, postal_code, doctor_id)
VALUES ('Street 456', 'Apartment 1', 'Wroclaw', '00-002', 1);
