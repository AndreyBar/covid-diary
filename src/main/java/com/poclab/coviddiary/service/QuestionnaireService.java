package com.poclab.coviddiary.service;

import com.poclab.coviddiary.dto.QuestionnaireDto;
import com.poclab.coviddiary.entity.Patient;
import com.poclab.coviddiary.entity.Questionnaire;
import com.poclab.coviddiary.entity.Token;
import com.poclab.coviddiary.entity.enums.HealthStatus;
import com.poclab.coviddiary.mapper.QuestionnaireMapper;
import com.poclab.coviddiary.model.QuestionnaireModel;
import com.poclab.coviddiary.repository.PatientRepository;
import com.poclab.coviddiary.repository.QuestionnaireRepository;
import com.poclab.coviddiary.repository.TokenRepository;
import com.poclab.coviddiary.util.DateUtils;
import com.poclab.coviddiary.util.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import javax.mail.MessagingException;
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

  @Value("${application.url}")
  private String applicationURL;

  private String questionnaireMail = "<!DOCTYPE html>" +
          "<html>" +
          "<body>" +
          "<p>Hello, patient_name, please complete your health check form using a link below </p>" +
          "<p>_link_</p>" +
          "<p>Your doctor is doctor_name </p>" +
          "<p><img style='width:80px; height: 80px;' src='cid:image'> - helping connect patients and doctors</p>" +
          "________________________________________" +
          "<p>(Monetization example)</p>" +
          "<p>Free Pizza Delivery with promo code YOURDIARY2020</p>" +
          "<p>Online meditation. First lesson is free.</p>" +
          "</body>" +
          "</html>";

  private String doctorMediumMail = "<!DOCTYPE html>" +
          "<html>" +
          "<body>" +
          "<p style='margin-bottom: 0px;'>Patient patient_name have _symptoms_" +
          "<p style='margin-top: 0px;'>COVID positive from _date_</p>" +
          "<p></p>" +
          "<p style='margin-bottom: 0px;'>Info from patient: " +
          "<p style='margin-top: 0px;'>_properties_" +
          "<p></p>" +
          "<p style='margin-bottom: 0px;'>Patient phone number:</p>" +
          "<p style='margin-top: 0px;'>patient_number</p>" +
          "<p><img style='width:80px; height: 80px;' src='cid:image'> - helping connect patients and doctors</p>" +
          "</body>" +
          "</html>";

  private String doctorEmergencyMail = "<!DOCTYPE html>" +
          "<html>" +
          "<body>" +
          "<p style='margin-bottom: 0px;'>Patient patient_name have _symptoms_" +
          "<p style='margin-top: 0px;'>COVID positive from _date_</p>" +
          "<p></p>" +
          "<p style='margin-bottom: 0px;'>Info from patient: " +
          "<p style='margin-top: 0px;'>_properties_" +
          "<p></p>" +
          "<p style='margin-bottom: 0px;'>Patient phone number:</p>" +
          "<p style='margin-top: 0px;'>patient_number</p>" +
          "<p><img style='width:80px; height: 80px;' src='cid:image'> - helping connect patients and doctors</p>" +
          "</body>" +
          "</html>";


  public void sendQuestionnaireForPatient(String email, Patient pat) throws MessagingException {
    List<Patient> patient = patientRepository.findByEmail(email);  // Patient is not unique
    if (patient.isEmpty()) {
      throw new NoSuchElementException("Patient with such email was not found.");
    }
    Comparator<Patient> dateComparator = Comparator.comparing(Patient::getCreatedOn).reversed();
    Token token = tokenService.createTokenForPatient(patient.stream().sorted(dateComparator).findFirst().get());
    String link = applicationURL + "/questionnaire?token=" + token.getUuid().toString();
    String text = questionnaireMail;
    text = text.replaceAll("patient_name", pat.getFirstName() + " " + pat.getLastName());
    text = text.replaceAll("_link_", link);
    text = text.replaceAll("doctor_name", pat.getDoctor().getFirstName() + " " + pat.getDoctor().getLastName());
    emailUtils.sendEmail(email, "Health Check", text);
  }

  public void sendEmergencyResultForDoctor(Patient patient, QuestionnaireDto questionnaire, String properties) throws MessagingException {
    String text = doctorEmergencyMail;
    List<String> stringList = new ArrayList<>();
    if (questionnaire.getBreathShortness()) {
      stringList.add("shortness of breath");
    }
    if (questionnaire.getHighTemperature()) {
      stringList.add("fever more than 38°C");
    }
    if (questionnaire.getDryCough()) {
      stringList.add("dry cough");
    }
    String symptoms = String.join(", ", stringList);
    text = text.replaceAll("patient_name", patient.getFirstName() + " " + patient.getLastName());
    text = text.replaceAll("_symptoms_", symptoms);
    text = text.replaceAll("_properties_", properties);
    text = text.replaceAll("_date_", patient.getTestDate().toString().substring(0, 10));
    text = text.replaceAll("patient_number", patient.getPhone_number());
    emailUtils.sendEmail(patient.getDoctor().getEmail(), "Emergency case. Patient " + patient.getFirstName() + " "
            + patient.getLastName() + " have critical symptoms", text);
  }

  public void sendMediumResultForDoctor(Patient patient, QuestionnaireDto questionnaire, String properties) throws MessagingException {
    String text = doctorMediumMail;
    List<String> stringList = new ArrayList<>();
    if (questionnaire.getBreathShortness()) {
      stringList.add("shortness of breath");
    }
    if (questionnaire.getHighTemperature()) {
      stringList.add("fever more than 38°C");
    }
    if (questionnaire.getDryCough()) {
      stringList.add("dry cough");
    }
    String symptoms = String.join(", ", stringList);
    text = text.replaceAll("patient_name", patient.getFirstName() + " " + patient.getLastName());
    text = text.replaceAll("_symptoms_", symptoms);
    text = text.replaceAll("_properties_", properties);
    text = text.replaceAll("_date_", patient.getTestDate().toString().substring(0, 10));
    text = text.replaceAll("patient_number", patient.getPhone_number());
    emailUtils.sendEmail(patient.getDoctor().getEmail(), "Important. Patient " + patient.getFirstName() + " "
            + patient.getLastName() + " have new symptom", text);
  }

  @Transactional
  public HealthStatus processQuestionnaire(QuestionnaireDto questionnaireDto, UUID token) {
    Patient patient = patientRepository.findByTokensContains(tokenRepository.getOne(token));
    saveQuestionnaire(questionnaireDto, token);
    return determineHealthStatusOfPatient(questionnaireDto, patient);
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

  public boolean getLastReportedFever(Patient patient) {
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
