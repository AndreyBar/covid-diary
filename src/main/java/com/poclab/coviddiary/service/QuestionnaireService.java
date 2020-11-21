package com.poclab.coviddiary.service;

import com.poclab.coviddiary.dto.QuestionnaireDto;
import com.poclab.coviddiary.entity.Patient;
import com.poclab.coviddiary.entity.Questionnaire;
import com.poclab.coviddiary.entity.enums.HealthStatus;
import com.poclab.coviddiary.mapper.QuestionnaireMapper;
import com.poclab.coviddiary.repository.PatientRepository;
import com.poclab.coviddiary.repository.QuestionnaireRepository;
import com.poclab.coviddiary.repository.TokenRepository;
import com.poclab.coviddiary.util.DateUtils;
import com.poclab.coviddiary.util.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
    Comparator<Patient> dateComparator = Comparator.comparing(Patient::getCreatedOn).reversed();
    tokenService.createTokenForPatient(patient.stream().sorted(dateComparator).findFirst().get());

    // todo: gather template and send it
    emailUtils.sendEmail(email, "test", "test");
  }

  @Transactional
  public void processQuestionnaire(QuestionnaireDto questionnaireDto, UUID token) {
    Patient patient = patientRepository.findByTokensContains(tokenRepository.getOne(token));
    HealthStatus healthStatus = determineHealthStatusOfPatient(questionnaireDto, patient);
    switch (healthStatus) {
      case EMERGENCY:
        // todo: tell patient to call immediately and letter to doctor
        break;
      case MEDIUM:
        // todo: letter to doctor
        break;
      case EASY:
        // todo: tell patient to carry on
        break;
    }
    saveQuestionnaire(questionnaireDto, token);
  }

  private HealthStatus determineHealthStatusOfPatient(QuestionnaireDto questionnaireDto, Patient patient) {
    boolean dryCough = questionnaireDto.getDryCough();
    boolean fever = questionnaireDto.getHighTemperature();
    boolean breathShortness = questionnaireDto.getBreathShortness();

    if (breathShortness || (dryCough && fever)) {
      return HealthStatus.EMERGENCY;
    }

    boolean wasFeverPresentLastTime = getLastReportedFever(patient);
    if (dryCough || (wasFeverPresentLastTime && fever)) {
      return HealthStatus.MEDIUM;
    }

    if (fever && DateUtils.calculateAge(patient.getDateOfBirth()) >= 60) {
      return HealthStatus.MEDIUM;
    }

    return HealthStatus.EASY;
  }

  private boolean getLastReportedFever(Patient patient) {
    Optional<Questionnaire> lastQuestionnaire = questionnaireRepository.findLatestByPatient(patient);
    return lastQuestionnaire.map(Questionnaire::isHighTemperature).orElse(false);
  }

  @Transactional
  public void saveQuestionnaire(QuestionnaireDto questionnaireDto, UUID token) {
    Questionnaire questionnaire = questionnaireMapper.toEntity(questionnaireDto);
    questionnaire.setToken(tokenRepository.getOne(token));

    questionnaireRepository.save(questionnaire);
  }

}
