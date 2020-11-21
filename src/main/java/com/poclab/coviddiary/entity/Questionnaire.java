package com.poclab.coviddiary.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Questionnaire {

  @Id
  @Column(name = "ID")
  private Long id;

  @Column(name = "HIGH_TEMPERATURE")
  private boolean highTemperature;

  @Column(name = "BREATH_SHORTNESS")
  private boolean breathShortness;

  @Column(name = "DRY_COUGH")
  private boolean dryCough;

  @Column(name = "SUBMITTED_ON")
  private Date submittedOn;

}
