package com.poclab.coviddiary.dto;

import com.poclab.coviddiary.model.QuestionnaireModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireDto {

  private Boolean highTemperature;
  private Boolean breathShortness;
  private Boolean dryCough;

  public QuestionnaireDto(QuestionnaireModel questionnaireModel) {
    this.highTemperature = questionnaireModel.getHighTemperature();
    this.breathShortness = questionnaireModel.getBreathShortness();
    this.dryCough = questionnaireModel.getDryCough();
  }
}
