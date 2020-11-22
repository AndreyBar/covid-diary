package com.poclab.coviddiary.controller;

import com.poclab.coviddiary.dto.PatientDto;
import com.poclab.coviddiary.entity.Doctor;
import com.poclab.coviddiary.entity.Patient;
import com.poclab.coviddiary.model.SignUpModel;
import com.poclab.coviddiary.service.QuestionnaireService;
import com.poclab.coviddiary.service.SignUpService;
import com.poclab.coviddiary.util.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;

@Controller
@RequiredArgsConstructor
public class SignUpController {
  private final EmailUtils emailUtils;

  private final SignUpService signUpService;
  private final QuestionnaireService questionnaireService;

  @GetMapping("/signup")
  public ModelAndView getSignUpTemplate(Model model) throws MessagingException {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("model", new SignUpModel());
    modelAndView.setViewName("registration");
    return modelAndView;
  }

  @PostMapping("/signup")
  public ModelAndView signUpPatient(SignUpModel signUpModel) throws MessagingException {
    Patient patient = new Patient(signUpModel);
    Doctor doctor = new Doctor(signUpModel);
    signUpService.signUpDoctor(doctor);
    patient.setDoctor(doctor);
    Patient newPatient = signUpService.signUpPatient(patient);
    questionnaireService.sendQuestionnaireForPatient(patient.getEmail(), newPatient);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("patient_name", patient.getFirstName() + " " + patient.getLastName());
    modelAndView.setViewName("registration_complete");
    return modelAndView;
  }


}
