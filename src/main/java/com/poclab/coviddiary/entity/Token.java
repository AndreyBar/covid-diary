package com.poclab.coviddiary.entity;

import com.poclab.coviddiary.entity.enums.TokenType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Token {

  @Id
  @Column(name = "UUID")
  private String uuid;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "TYPE")
  private TokenType type = TokenType.QUESTIONNAIRE;

  @Column(name = "STATUS")
  private boolean status;

  @Column(name = "PATIENT_ID")
  private Long patientId;

}
