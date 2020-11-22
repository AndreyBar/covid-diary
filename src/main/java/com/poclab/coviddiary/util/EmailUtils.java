package com.poclab.coviddiary.util;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailUtils {

  private final JavaMailSender emailSender;

  public void sendEmail(String to, String subject, String text) {
    emailSender.send(mimeMessage -> {
      MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
      message.setFrom("yourcoviddiary@gmail.com");
      message.setTo(to);
      message.setSubject(subject);
      message.setText(text, true);
      message.addInline("image", new ClassPathResource("static/assets/images/logo.png"));
    });
  }

}
