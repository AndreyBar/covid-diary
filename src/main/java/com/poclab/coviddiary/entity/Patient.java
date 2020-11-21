package com.poclab.coviddiary.entity;

import lombok.Getter;
import lombok.Setter;

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

  @OneToMany
  @JoinColumn(name = "PATIENT_ID")
  private Set<Token> tokens;

}
