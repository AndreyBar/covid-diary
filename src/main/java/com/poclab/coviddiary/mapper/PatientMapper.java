package com.poclab.coviddiary.mapper;

import com.poclab.coviddiary.dto.PatientDto;
import com.poclab.coviddiary.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.Date;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface PatientMapper {

  @Mapping(target = "createdOn", expression = "java(LocalDateTime.now())")
  Patient toEntity(PatientDto patientDto);

}
