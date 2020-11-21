package com.poclab.coviddiary.controller;

import com.poclab.coviddiary.dto.PatientDto;
import com.poclab.coviddiary.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class SignUpController {

  private final SignUpService signUpService;

  @GetMapping("/signup")
  public String getSignUpTemplate(Model model) {
    model.addAttribute("patient", new PatientDto());
    return "registration";
  }

  @PostMapping("/signup")
  public @ResponseBody
  void signUpPatient(PatientDto patientDto) {
    signUpService.signUpPatient(patientDto);
  }

}
