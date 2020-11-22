package com.poclab.coviddiary.entity;

import com.poclab.coviddiary.model.SignUpModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PATIENT")
public class Patient {

  @Id
  @Column(name = "ID", columnDefinition = "INT(11)")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "PHONE_NUMBER")
  private String phone_number;

  @Column(name = "WEIGHT")
  private double weight;

  @Column(name = "HEIGHT")
  private double height;

  @Column(name = "DATE_OF_BIRTH")
  private LocalDate dateOfBirth;

  @Column(name = "CREATED_ON")
  private LocalDateTime createdOn;

  @Column(name = "TEST_DATE")
  private LocalDateTime testDate;

  @ManyToOne
  @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "ID", nullable = false)
  private Doctor doctor;

  @OneToMany
  @JoinColumn(name = "PATIENT_ID")
  private Set<Token> tokens;

  public Patient(SignUpModel signUpModel) {
    this.firstName = signUpModel.getPatient_first_name();
    this.lastName = signUpModel.getPatient_last_name();
    this.email = signUpModel.getPatient_email();
    this.phone_number = signUpModel.getPatient_phone();
    this.testDate = signUpModel.getCovid_test_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    this.weight = signUpModel.getWeight();
    this.height = signUpModel.getHeight();
    this.dateOfBirth = signUpModel.getDate_of_birth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }

}
