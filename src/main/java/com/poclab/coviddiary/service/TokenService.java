package com.poclab.coviddiary.service;

import com.poclab.coviddiary.entity.Token;
import com.poclab.coviddiary.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final TokenRepository tokenRepository;

  public void validateToken(UUID token) {
    if (tokenRepository.findByUuidAndStatusTrue(token).isEmpty()) {
      throw new SecurityException("Token not valid");
    }
  }

  @Transactional
  public Token createTokenForPatient(Long patientId) {
    Token token = new Token();
    token.setPatientId(patientId);
    token.setStatus(true);
    tokenRepository.save(token);
    return token;
  }

}
