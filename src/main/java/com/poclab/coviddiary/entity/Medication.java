package com.poclab.coviddiary.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Setter
@Getter
@Entity
public class Medication {

  @Id
  @Column(name = "ID")
  private Long id;

  @Column(name = "NAME")
  private String name;

  @ManyToMany
  @JoinTable(
      name = "PATIENT_MEDICATION",
      joinColumns = @JoinColumn(name = "MEDICATION_ID"),
      inverseJoinColumns = @JoinColumn(name = "PATIENT_ID")
  )
  Set<Patient> patients;
}
