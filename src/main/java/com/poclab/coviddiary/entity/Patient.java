package com.poclab.coviddiary.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Setter
@Getter
@Entity
public class Patient {

  @Id
  @Column(name = "ID")
  private Long id;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "STATUS")
  private boolean status;

  @Column(name = "WEIGHT")
  private double weight;

  @Column(name = "HEIGHT")
  private double height;

  @Column(name = "DATE_OF_BIRTH")
  private Date dateOfBirth;

  @Column(name = "CREATED_ON")
  private Date createdOn;

  @Column(name = "TEST_DATE")
  private Date testDate;

  @ManyToOne
  @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "ID", nullable = false)
  private Doctor doctor;

  @ManyToMany
  @JoinTable(
      name = "PATIENT_MEDICATION",
      joinColumns = @JoinColumn(name = "PATIENT_ID"),
      inverseJoinColumns = @JoinColumn(name = "MEDICATION_ID")
  )
  Set<Medication> medications;

}
