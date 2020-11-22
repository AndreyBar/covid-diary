package com.poclab.coviddiary.service;

import com.poclab.coviddiary.entity.Patient;
import com.poclab.coviddiary.entity.Token;
import com.poclab.coviddiary.repository.PatientRepository;
import com.poclab.coviddiary.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final TokenRepository tokenRepository;
  private final PatientRepository patientRepository;

  public Token validateToken(UUID token) {
    return tokenRepository.getOne(token);
  }

  @Transactional
  public void invalidateToken(UUID token) {
    Token tokenEntity = tokenRepository.getOne(token);
    tokenEntity.setStatus(false);
  }

  @Transactional
  public Token createTokenForPatient(Patient patient) {
    Token token = new Token();
    token.setPatient(patient);
    token.setStatus(true);
    return tokenRepository.save(token);
  }
}
