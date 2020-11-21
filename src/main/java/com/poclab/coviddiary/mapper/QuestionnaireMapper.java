package com.poclab.coviddiary.mapper;

import com.poclab.coviddiary.dto.QuestionnaireDto;
import com.poclab.coviddiary.entity.Questionnaire;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Date;

@Mapper(componentModel = "spring", imports = {Date.class})
public interface QuestionnaireMapper {

  @Mapping(target = "submittedOn", expression = "java(new Date())")
  Questionnaire toEntity(QuestionnaireDto dto);

}
