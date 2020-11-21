package com.poclab.coviddiary.controller;

import com.poclab.coviddiary.dto.PatientDto;
import com.poclab.coviddiary.entity.Doctor;
import com.poclab.coviddiary.entity.Patient;
import com.poclab.coviddiary.model.SignUpModel;
import com.poclab.coviddiary.service.QuestionnaireService;
import com.poclab.coviddiary.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class SignUpController {

  private final SignUpService signUpService;
  private final QuestionnaireService questionnaireService;

  @GetMapping("/signup")
  public ModelAndView getSignUpTemplate(Model model) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("model", new SignUpModel());
    modelAndView.setViewName("registration");
    return modelAndView;
  }

  @PostMapping("/signup")
  public String signUpPatient(SignUpModel signUpModel) {
    Patient patient = new Patient(signUpModel);
    Doctor doctor = new Doctor(signUpModel);
    signUpService.signUpDoctor(doctor);
    signUpService.signUpPatient(patient);
    questionnaireService.sendQuestionnaireForPatient(patient.getEmail());
    return "registration_complete";
  }


}
