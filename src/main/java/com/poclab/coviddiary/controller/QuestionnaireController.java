package com.poclab.coviddiary.controller;

import com.poclab.coviddiary.dto.QuestionnaireDto;
import com.poclab.coviddiary.entity.Patient;
import com.poclab.coviddiary.entity.Token;
import com.poclab.coviddiary.entity.enums.HealthStatus;
import com.poclab.coviddiary.model.QuestionnaireModel;
import com.poclab.coviddiary.model.SignUpModel;
import com.poclab.coviddiary.service.QuestionnaireService;
import com.poclab.coviddiary.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class QuestionnaireController {

  private final TokenService tokenService;
  private final QuestionnaireService questionnaireService;

  @GetMapping("/questionnaire")
  public ModelAndView getQuestionnaire(@RequestParam UUID token) throws ParseException {
    Token tokenFromDb = tokenService.validateToken(token);
    Patient patient = tokenFromDb.getPatient();
    ModelAndView modelAndView = new ModelAndView();
    QuestionnaireModel questionnaireModel = new QuestionnaireModel();
    questionnaireModel.setToken(tokenFromDb.getUuid().toString());
    questionnaireModel.setPatientName(patient.getFirstName() + " " + patient.getLastName());
    questionnaireModel.setAge(getAge(patient.getDateOfBirth()));
    questionnaireModel.setHighFeverPrevious(questionnaireService.getLastReportedFever(patient));
    modelAndView.addObject("model", questionnaireModel);
    modelAndView.setViewName("questionnaire");
    return modelAndView;
  }

  @PostMapping("/questionnaire")
  public ModelAndView submitQuestionnaire(QuestionnaireModel questionnaireModel) throws MessagingException {
    Token token = tokenService.validateToken(UUID.fromString(questionnaireModel.getToken()));
    Patient patient = token.getPatient();
    QuestionnaireDto questionnaireDto = new QuestionnaireDto(questionnaireModel);
    HealthStatus healthStatus = questionnaireService.processQuestionnaire(questionnaireDto, UUID.fromString(questionnaireModel.getToken()));
    tokenService.invalidateToken(UUID.fromString(questionnaireModel.getToken()));
    ModelAndView modelAndView = new ModelAndView();
    if (healthStatus.equals(HealthStatus.EMERGENCY)) {
      questionnaireService.sendEmergencyResultForDoctor(patient, questionnaireDto ,questionnaireModel.getProperties());
      modelAndView.addObject("patientName", questionnaireModel.getPatientName());
      modelAndView.setViewName("questionnaire_emergency");
      return modelAndView;
    } else if (healthStatus.equals(HealthStatus.MEDIUM)) {
      questionnaireService.sendMediumResultForDoctor(patient, questionnaireDto, questionnaireModel.getProperties());
      modelAndView.addObject("patientName", questionnaireModel.getPatientName());
      modelAndView.setViewName("questionnaire_medium");
      return modelAndView;
    } else {
      modelAndView.addObject("patientName", questionnaireModel.getPatientName());
      modelAndView.setViewName("questionnaire_easy");
      return modelAndView;
    }
  }

  public Integer getAge(LocalDate l) throws ParseException {
    LocalDate now = LocalDate.now(); //gets localDate
    Period diff = Period.between(l, now); //difference between the dates is calculated
   return diff.getYears();
  }

}
