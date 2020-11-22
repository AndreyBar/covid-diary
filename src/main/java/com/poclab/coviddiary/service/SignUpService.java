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
import java.time.LocalDateTime;
import java.time.ZoneId;
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
  public void signUpDoctor(Doctor doctor) {
    doctorRepository.save(doctor);
  }

  @Transactional
  public Patient signUpPatient(Patient patient) {
    Doctor doctor = doctorRepository.findByEmail(patient.getDoctor().getEmail()).get(0);
    if (doctor == null) {
      throw new NoSuchElementException("There is no doctor with such email");
    }
    Date now = new Date();
    patient.setCreatedOn(now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    patient.setDoctor(doctor);
    return patientRepository.save(patient);
  }

}
