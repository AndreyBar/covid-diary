package com.poclab.coviddiary.entity;

import com.poclab.coviddiary.entity.enums.TokenType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "TOKEN")
public class Token {

  @Id
  @Column(name = "UUID", columnDefinition = "CHAR(36)")
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String uuid;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "TYPE", columnDefinition = "ENUM ('QUESTIONNAIRE')")
  private TokenType type = TokenType.QUESTIONNAIRE;

  @Column(name = "STATUS", columnDefinition = "TINYINT")
  private boolean status;

  @Column(name = "PATIENT_ID", columnDefinition = "INT(11)")
  private Long patientId;

}
