package com.poclab.coviddiary.entity;

import com.poclab.coviddiary.entity.enums.TokenType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "TOKEN")
public class Token {

  @Id
  @GeneratedValue
  @Type(type = "uuid-char")
  @Column(name = "UUID", columnDefinition = "CHAR(36)")
  private UUID uuid;

  @Enumerated(EnumType.STRING)
  @Column(name = "TYPE", columnDefinition = "ENUM ('QUESTIONNAIRE')")
  private TokenType type = TokenType.QUESTIONNAIRE;

  @Column(name = "STATUS", columnDefinition = "BIT")
  private boolean status;

  @ManyToOne
  @JoinColumn(name = "PATIENT_ID", referencedColumnName = "ID", nullable = false)
  private Patient patient;

}
