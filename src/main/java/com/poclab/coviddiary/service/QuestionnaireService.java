package com.poclab.coviddiary.service;

import com.poclab.coviddiary.dto.QuestionnaireDto;
import com.poclab.coviddiary.entity.Patient;
import com.poclab.coviddiary.entity.Questionnaire;
import com.poclab.coviddiary.entity.Token;
import com.poclab.coviddiary.mapper.QuestionnaireMapper;
import com.poclab.coviddiary.repository.PatientRepository;
import com.poclab.coviddiary.repository.QuestionnaireRepository;
import com.poclab.coviddiary.repository.TokenRepository;
import com.poclab.coviddiary.util.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionnaireService {

  private final EmailUtils emailUtils;
  private final TokenService tokenService;
  private final PatientRepository patientRepository;
  private final TokenRepository tokenRepository;
  private final QuestionnaireRepository questionnaireRepository;
  private final QuestionnaireMapper questionnaireMapper;


  public void sendQuestionnaireForPatient(String email) {
    List<Patient> patient = patientRepository.findByEmail(email);  // Patient is not unique
    if (patient.isEmpty()) {
      throw new NoSuchElementException("Patient with such email was not found.");
    }
    tokenService.createTokenForPatient(patient.get(0).getId());
    QuestionnaireDto questionnaire = new QuestionnaireDto();
    // todo: gather template and send it
    emailUtils.sendEmail(email, "test", "test");
  }

  @Transactional
  public Questionnaire saveQuestionnaire(QuestionnaireDto questionnaireDto, UUID token) {
    Questionnaire questionnaire = questionnaireMapper.toEntity(questionnaireDto);
    questionnaire.setToken(tokenRepository.getOne(token));

    questionnaireRepository.save(questionnaire);
    return questionnaire;
  }

}
