package com.capgemini.wsb.persistence.entity;

import com.capgemini.wsb.persistence.enums.Specialization;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DOCTOR")
public class DoctorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String telephoneNumber;

	private String email;

	@Column(nullable = false, unique = true)
	private String doctorNumber;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Specialization specialization;

	// Dwustronna: Doctor jest właścicielem relacji z Address (od strony rodzica)

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AddressEntity> addressEntities = new ArrayList<>();

	// Dwustronna: Doctor jest właścicielem relacji z Patient (od strony rodzica)

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PatientEntity> patients = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDoctorNumber() {
		return doctorNumber;
	}

	public void setDoctorNumber(String doctorNumber) {
		this.doctorNumber = doctorNumber;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public List<AddressEntity> getAddressEntities() {
		return addressEntities;
	}

	public void setAddressEntities(List<AddressEntity> addressEntities) {
		this.addressEntities = addressEntities;
	}

	public List<PatientEntity> getPatients() {
		return patients;
	}

	public void setPatients(List<PatientEntity> patients) {
		this.patients = patients;
	}

	public void addPatient(PatientEntity patient) {
		patients.add(patient);
		patient.setDoctor(this);
	}

	public void removePatient(PatientEntity patient) {
		patients.remove(patient);
		patient.setDoctor(null);
	}
}

