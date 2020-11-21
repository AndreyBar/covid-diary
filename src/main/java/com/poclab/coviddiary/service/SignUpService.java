package com.poclab.coviddiary.service;

import com.poclab.coviddiary.dto.PatientDto;
import com.poclab.coviddiary.entity.Doctor;
import com.poclab.coviddiary.entity.Patient;
import com.poclab.coviddiary.mapper.PatientMapper;
import com.poclab.coviddiary.repository.DoctorRepository;
import com.poclab.coviddiary.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpService {

  private final PatientMapper patientMapper;
  private final PatientRepository patientRepository;
  private final DoctorRepository doctorRepository;

  @Transactional
  public void signUpPatient(PatientDto patientDto) {
    Optional<Doctor> doctor = doctorRepository.findByEmail(patientDto.getDoctorEmail());
    if (doctor.isEmpty()) {
      throw new NoSuchElementException("There is no doctor with such email");
    }

    Patient patient = patientMapper.toEntity(patientDto);
    patient.setDoctor(doctor.get());
    patientRepository.save(patient);
  }

}
