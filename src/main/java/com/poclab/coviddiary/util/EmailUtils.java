package com.poclab.coviddiary.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;

@Component
@RequiredArgsConstructor
public class EmailUtils {

  @Value("classpath:static/assets/images/logo.png")
  File resourceFile;

  private final JavaMailSender emailSender;

  public void sendEmail(String to, String subject, String text) throws MessagingException {
    emailSender.send(new MimeMessagePreparator() {
      public void prepare(MimeMessage mimeMessage) throws MessagingException {
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setFrom("yourcoviddiary@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text, true);
        message.addInline("image", new ClassPathResource("static/assets/images/logo.png"));
      }
    });
  }

}
