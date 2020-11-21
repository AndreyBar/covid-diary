package com.poclab.coviddiary.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PatientDto {

  private String firstName;
  private String lastName;
  private String email;
  private Double weight;
  private Double height;
  private Date dateOfBirth;
  private Date testDate;
  private String doctorEmail;

}
