package com.poclab.coviddiary.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "QUESTIONNAIRE")
public class Questionnaire {

  @Id
  @Column(name = "ID", columnDefinition = "INT(11)")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "TOKEN_UUID", referencedColumnName = "UUID")
  private Token token;

  @Column(name = "HIGH_TEMPERATURE", columnDefinition = "TINYINT")
  private boolean highTemperature;

  @Column(name = "BREATH_SHORTNESS", columnDefinition = "TINYINT")
  private boolean breathShortness;

  @Column(name = "DRY_COUGH", columnDefinition = "TINYINT")
  private boolean dryCough;

  @Column(name = "SUBMITTED_ON")
  private Date submittedOn;

}
