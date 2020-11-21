package com.poclab.coviddiary.entity;

import com.poclab.coviddiary.model.SignUpModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "DOCTOR")
public class Doctor {

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
  private String phoneNumber;

  public Doctor(SignUpModel signUpModel) {
    this.email = signUpModel.getDoctor_email();
    this.phoneNumber = signUpModel.getDoctor_phone();
    this.firstName = "Janek";
    this.lastName = "Stevenson";
  }
}
