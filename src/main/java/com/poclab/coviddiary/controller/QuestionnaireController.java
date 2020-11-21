package com.poclab.coviddiary.controller;

import com.poclab.coviddiary.dto.QuestionnaireDto;
import com.poclab.coviddiary.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class QuestionnaireController {

  private final TokenService tokenService;

  @GetMapping("/questionnaire")
  public String getQuestionnaire(@RequestParam UUID token) {
    tokenService.validateToken(token);
    return "questionnaire";
  }

  @PostMapping("/questionnaire")
  public @ResponseBody
  void submitQuestionnaire(@RequestParam UUID token,
      QuestionnaireDto questionnaireDto) {
    tokenService.validateToken(token);
  }

}
