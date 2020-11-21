package com.poclab.coviddiary.mapper;

import com.poclab.coviddiary.dto.PatientDto;
import com.poclab.coviddiary.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

  Patient toEntity(PatientDto patientDto);

}
