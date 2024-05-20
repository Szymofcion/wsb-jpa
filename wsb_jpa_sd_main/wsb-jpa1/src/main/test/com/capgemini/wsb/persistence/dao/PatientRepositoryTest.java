package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PatientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PatientRepository patientRepository;


    //Znajdowanie po nazwisku
    @Test
    void findByLastNameShouldReturnPatients() {

        PatientEntity patient = new PatientEntity();
        patient.setLastName("Nowak"); // Zmiana nazwiska na "Nowak"
        patient.setFirstName("Jan"); // Zmiana imienia na "Jan"
        patient.setTelephoneNumber("921001122");
        patient.setEmail("jan.nowak@gmail.com");
        patient.setPatientNumber("PN123");
        entityManager.persist(patient);
        entityManager.flush();

        List<PatientEntity> foundPatients = patientRepository.findByLastName("Nowak");

        assertThat(foundPatients).hasSize(1);
        assertThat(foundPatients.get(0).getLastName()).isEqualTo("Nowak");
    }

    //Znajdowanie pacjentów, którzy mieli więcej wizyt niż X

    @Test
    void findPatientsWithMoreThanVisitsShouldReturnCorrectPatients() {
        PatientEntity patient = new PatientEntity();
        patient.setLastName("Przemysławski");
        patient.setFirstName("Stefan");
        patient.setTelephoneNumber("527836498");
        patient.setEmail("stefan.przemyslawski@gmail.com");
        patient.setPatientNumber("PN124");
        entityManager.persist(patient);

        for (int i = 0; i < 5; i++) {
            VisitEntity visit = new VisitEntity();
            visit.setPatient(patient);
            visit.setTime(LocalDateTime.now());
            entityManager.persist(visit);
        }
        entityManager.flush();

        List<PatientEntity> foundPatients = patientRepository.findPatientsWithMoreThanVisits(3L);

        assertThat(foundPatients).hasSize(1);
        assertThat(foundPatients.get(0).getLastName()).isEqualTo("Przemysławski");
    }


}
