package com.poclab.coviddiary.mapper;

import com.poclab.coviddiary.dto.QuestionnaireDto;
import com.poclab.coviddiary.entity.Questionnaire;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface QuestionnaireMapper {

  @Mapping(target = "submittedOn", expression = "java(LocalDateTime.now())")
  Questionnaire toEntity(QuestionnaireDto dto);

}
