package com.capgemini.wsb.persistence.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.enums.Specialization;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class DoctorEntityTests {


    @Autowired
    private DoctorDao doctorDao;


    @Test
    public void testDoctorSave() {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Daria");
        doctor.setLastName("Dariuszewska");
        doctor.setDoctorNumber("1");
        doctor.setSpecialization(Specialization.GP);
        doctor.setTelephoneNumber("123123123");

        DoctorEntity savedDoctor = doctorDao.save(doctor);
        assertNotNull(savedDoctor.getClass(), "Doctor should have an ID after being saved.");
    }
}
