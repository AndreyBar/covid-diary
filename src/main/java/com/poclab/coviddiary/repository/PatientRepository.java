package com.poclab.coviddiary.repository;

import com.poclab.coviddiary.entity.Patient;
import com.poclab.coviddiary.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

  List<Patient> findByEmail(String email);

  Patient findByTokensContains(Token token);

}
